package com.williamhenry.insantani;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends AppCompatActivity implements
        OnMapReadyCallback{

    private GoogleMap mMap;
    private GPSTracker gps;
    private LatLng MyPos;
    private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(10000)         // 10 seconds
            .setFastestInterval(5000)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    public static String ShopLat;
    public static String ShopPlaceId;
    public static String ShopLong;

//    private LocationClie mLocationClient;
    boolean mUpdatesRequested = false;
    private TextView markerText;
    private LatLng center;
    private LinearLayout markerLayout;
    private Geocoder geocoder;
    private List<Address> addresses;
    private TextView Address;
    private ImageView imageView;
    private GoogleApiClient mGoogleApiClient;
    private LinearLayout linearLayout;
    private LatLng Pos;
    private String totalPrice;
    private String totalItem;
    public static MapsActivity ma;
    private ArrayList<ProductFarmer> dataFarmer;
    private String addressShip;
    private LatLng coordinatesShip;
    private String receiverInfo;
    private String zipcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        markerText = (TextView) findViewById(R.id.locationMarkertext);
        Address = (TextView) findViewById(R.id.addressText);
        markerLayout = (LinearLayout) findViewById(R.id.locationMarker);
        imageView = (ImageView) findViewById(R.id.imageViewMarker);
        imageView.setImageResource(R.mipmap.add_marker);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutSearchLocation);
        Bundle extras = getIntent().getExtras();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle item1= (Bundle) extras.get("information");
        totalItem= item1.getString("totalItem");
        totalPrice=item1.getString("totalPrice");
        dataFarmer= (ArrayList<ProductFarmer>)item1.getSerializable("data");
        zipcode=item1.getString("zipcode");
        receiverInfo=item1.getString("receiverInfo");
        ma=this;


        if ((Bundle) extras.get("locationSearch") != null) {
            Bundle item = (Bundle) extras.get("locationSearch");
            Double lat = item.getDouble("lat");
            Double lng = item.getDouble("lng");
            Pos = new LatLng(lat, lng);
        } else {
            Pos = null;

        }



        gps= new GPSTracker(this);
        if(!gps.canGetLocation()){
            gps.showSettingsAlert();
            MyPos=new LatLng(-6.2087634,106.84559899999999);
        }else {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            MyPos=new LatLng(latitude,longitude);
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mapFragment.getMap().setPadding(0,200,0,0);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLong;
        mMap = googleMap;
        googleMap.setMyLocationEnabled(true);
        CameraPosition camPosition=null;
        for (int i=0;i<dataFarmer.size();i++){
            Log.d("markers",dataFarmer.get(i).getFarmerUsername());
            LatLng farmer = new LatLng(dataFarmer.get(i).getLat(), dataFarmer.get(i).getLng());
            mMap.addMarker(new MarkerOptions().position(farmer).title(dataFarmer.get(i).getFarmerUsername()));
        }
        if(Pos!=null) {

            camPosition = new CameraPosition.Builder()
                    .target(Pos).zoom(19f).build();
        }else{
            camPosition = new CameraPosition.Builder()
                    .target(MyPos).zoom(19f).build();
        }
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camPosition));
//        mMap.clear();
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                center = mMap.getCameraPosition().target;
                markerText.setText("Set your Position");
//                mMap.clear();
                markerLayout.setVisibility(View.VISIBLE);
                try {
                    new GetLocationAsync(center.latitude, center.longitude).execute();

                } catch (Exception e) {

                }
            }
        });


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchLocation.class);
                Bundle bundle = new Bundle();
                bundle.putString("totalPrice", totalPrice);
                bundle.putString("totalItem", totalItem);
                bundle.putSerializable("data", dataFarmer);
                bundle.putString("zipcode", zipcode);
                bundle.putString("receiverInfo",receiverInfo);
                intent.putExtra("information", bundle);
//                finish();
                startActivity(intent);
            }
        });



        markerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    LatLng latLng1= new LatLng(center.latitude,center.longitude);
                    Marker m= mMap.addMarker(new MarkerOptions().position(latLng1)
                                                                .snippet("")
                                                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.add_marker)));
                    m.setDraggable(true);
//                    m.setInfoWindowAnchor();
                    Intent intent=new Intent(getApplicationContext(), CheckoutActivity.class);
                    CheckoutActivity.ca.finish();
                    Bundle bundle=new Bundle();
                    bundle.putString("totalItem", totalItem);
                    Log.d("totalItem", totalItem);
                    bundle.putString("totalPrice", totalPrice);
                    bundle.putSerializable("data", dataFarmer);

                    bundle.putDouble("lat", coordinatesShip.latitude);
                    bundle.putDouble("lng", coordinatesShip.longitude);
                    bundle.putString("address", addressShip);
                    bundle.putString("receiverInfo", receiverInfo);
                    bundle.putString("zipcode",zipcode);
                    intent.putExtra("information", bundle);
                    startActivity(intent);
                    markerLayout.setVisibility(View.GONE);
//                    markerLayout.setVisibility(View.VISIBLE);
//                    markerText.setVisibility(View.VISIBLE);



                }catch(Exception e){

                }
            }


        });


        // Add a marker in Sydney and move the camera
//        MarkerOptions myPos=new MarkerOptions().position(MyPos).draggable(true);
//        MyPos=myPos.getPosition();
//        Log.d("MyPos",MyPos.toString());
//        Geocoder geocoder = new Geocoder(getApplicationContext());
//        List<Address> addresses;
//        try {
//            addresses = geocoder.getFromLocation(MyPos.latitude, MyPos.longitude, 1);
//            if (addresses.size() > 0) {
//
//                myPos.title(addresses.get(0).getAddressLine(0));
//            }
//            mMap.addMarker(myPos);


//
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(MyPos));
//        }catch(Exception e){
//            Log.d("error_maps",e.toString());
//        }

    }

//    protected synchronized void buildGoogleApiClient() {
//        mGoogleApiClient = new GoogleApiClient().Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .addApi(Places.PLACE_DETECTION_API)
//                .addApi(Places.GEO_DATA_API)
//                .enableAutoManage(this,0,this)
//                .build();
//    }
//    private AdapterView.OnItemClickListener mSearchAutocompleteClickListener = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            /*
//             Retrieve the place ID of the selected item from the Adapter.
//             The adapter stores each Place suggestion in a PlaceAutocomplete object from which we
//             read the place ID.
//              */
//            final PlaceAutocompleteAdapter.PlaceAutocomplete item = mAdapter.getItem(position);
//            final String placeId = String.valueOf(item.placeId);
//
//             /*
//             Issue a request to the Places Geo Data API to retrieve a Place object with additional
//              details about the place.
//              */
//            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
//                    .getPlaceById(mGoogleApiClient, placeId);
//            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
//
//
////            Toast.makeText(getApplicationContext(), "Clicked: " + item.description,
////                    Toast.LENGTH_SHORT).show();
//        }
//    };



    private class GetLocationAsync extends AsyncTask<String, Void, String> {

        // boolean duplicateResponse;
        double x, y;
        StringBuilder str;

        public GetLocationAsync(double latitude, double longitude) {
            // TODO Auto-generated constructor stub

            x = latitude;
            y = longitude;
        }

        @Override
        protected void onPreExecute() {
            Address.setText(" Getting location ");
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                geocoder = new Geocoder(getApplicationContext());
                addresses = geocoder.getFromLocation(x, y, 1);
                coordinatesShip= new LatLng(x,y);

                str = new StringBuilder();
                if (geocoder.isPresent()) {
                    Address returnAddress = addresses.get(0);

                    String localityString = returnAddress.getLocality();
                    String city = returnAddress.getCountryName();
                    String region_code = returnAddress.getCountryCode();
                    String zipcode = returnAddress.getPostalCode();

                    str.append(localityString + " ");
                    str.append(city + " " + region_code + " ");
                    str.append(zipcode + " ");

                } else {
                }
            } catch (IOException e) {
                Log.e("tag", e.getMessage());
            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            try {
                Address.setText(addresses.get(0).getAddressLine(0)
                        + addresses.get(0).getAddressLine(1) + " ");

                addressShip=addresses.get(0).getAddressLine(0)
                        + addresses.get(0).getAddressLine(1) + " ";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_search) {
//            return true;
//        }
//        //noinspection SimplifiableIfStatement
        onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}
