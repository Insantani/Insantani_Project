package com.williamhenry.insantani;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class SearchLocation extends AppCompatActivity {
    private Location loc;
    private RequestQueue mQueue;

    private AutoCompleteTextView searchAutocompleteText;
    private PlaceAutocompleteAdapter mAdapter;
    private HashMap<String, Places> mPlaces = new HashMap<>();
    private ListView listView;
    private EditText enterLocation;
    private TextView textLocation;
    private String url;
    private String REQUEST_TAG="SearchLocation";
    private String totalPrice;
    private String totalItem;
    private ArrayList<ProductFarmer>dataFarmer;
    private String receiverInfo;
    private String zipcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);
        enterLocation=(EditText)findViewById(R.id.edEnterLocation);
        mAdapter = new  PlaceAutocompleteAdapter (getApplicationContext(), R.layout.adapter_google_places_autocomplete);
        listView=(ListView) findViewById(R.id.listViewLocations);
        listView.setAdapter(mAdapter);
        listView.setTextFilterEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras= getIntent().getExtras();
        Bundle item=(Bundle) extras.get("information");
        totalItem= item.getString("totalItem");
        totalPrice=item.getString("totalPrice");
        zipcode=item.getString("zipcode");
        receiverInfo=item.getString("receiverInfo");

        dataFarmer=(ArrayList<ProductFarmer>)item.getSerializable("data");


        enterLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.getFilter().filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
            {
                Log.d("testing_location_adapter",mAdapter.getResultList(position));

                String value = (String) adapter.getItemAtPosition(position);
                Log.d("testing_location", value);
                String placeId="";
                try {
                    JSONObject place = new JSONObject(mAdapter.getResultList(position));
                    placeId = place.getString("place_id");
                }catch(Exception e){
                    Log.d("error_json_place_id",e.toString());
                }
                mQueue= CustomVolleyRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
                url="https://maps.googleapis.com/maps/api/place/details/json?placeid="+placeId+"&key=AIzaSyAOdIwlRwYwFKWueHlpgXSjy66id--rd8o";
                final CustomJSONObjectRequest jsonRequestPlace= new CustomJSONObjectRequest(Request.Method.GET,
                        url,new JSONObject(),
                        new Response.Listener<JSONObject>(){
                            //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                            @Override
                            public void onResponse(JSONObject response){
                                try {
                                    Log.d("test_response",response.toString());
                                    JSONObject result=response.getJSONObject("result");
                                    JSONObject resultGeometry=result.getJSONObject("geometry");
                                    JSONObject resultLocation=resultGeometry.getJSONObject("location");
                                    Double latitude= resultLocation.getDouble("lat");
                                    Double longitude= resultLocation.getDouble("lng");
                                    Log.d("lat",latitude.toString());
                                    Log.d("lng", longitude.toString());

                                    Intent intent= new Intent(getApplicationContext(),MapsActivity.class);
                                    MapsActivity.ma.finish();
                                    Bundle bundle= new Bundle();
                                    bundle.putDouble("lat",latitude);
                                    bundle.putDouble("lng", longitude);
                                    intent.putExtra("locationSearch", bundle);
                                    Bundle bundle2= new Bundle();
                                    bundle2.putString("totalPrice", totalPrice);
                                    bundle2.putString("totalItem", totalItem);
                                    bundle2.putSerializable("data",dataFarmer);
                                    bundle2.putString("zipcode",zipcode);
                                    bundle2.putString("receiverInfo",receiverInfo);
                                    intent.putExtra("information",bundle2);
                                    finish();
                                    startActivity(intent);




                                } catch(Exception e){
                                    Log.d("JSON_err_place",e.toString());
                                }
                            }
                        },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("error_response_place",error.toString());
                    }
                });
                jsonRequestPlace.setTag(REQUEST_TAG);
                mQueue.add(jsonRequestPlace);

            }

        });




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
