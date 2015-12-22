package com.williamhenry.insantani;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.model.LatLng;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CheckoutActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private boolean user_id;
    private boolean checkToken;
    private boolean checkRefreshToken;
    private boolean tokenType;
    private TextView address;
    private TextView zipcode;
    private TextView receiverInfo;
    private Button buy;
    private TextView totalPrice;
    private TextView total;
    private String url;
    private RequestQueue mQueue;
    public static final String REQUEST_TAG = "CheckoutActivity";
    ArrayList<JSONObject> carts;
    private StringEntity stringEntity;
    private LinearLayout linearLayout;
    private CartFragment cartFragment;
    private TextView totalPriceInput;
    private TextView totalInput;
    private double lat;
    private double lng;
    private String addressShip;
    private double latitude;
    private double longitude;
    private ProgressDialog ringProgressDialog;
//    private HashMap map;
    private ArrayList<ProductFarmer> allInfo;
    GPSTracker gps;
    public static  CheckoutActivity ca;
//    private ProgressDialog ringProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pref=getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        user_id=pref.contains("user_id");
        checkToken=pref.contains("access_token");
        checkRefreshToken=pref.contains("refresh_token");
        tokenType=pref.contains("token_type");
        linearLayout=(LinearLayout) findViewById(R.id.linearLayoutCheckout);
        cartFragment= new CartFragment();

        receiverInfo= (TextView) findViewById(R.id.receiverInfo);
        address= (TextView) findViewById(R.id.location);
        zipcode= (TextView) findViewById(R.id.postcode);
        buy=(Button) findViewById(R.id.buyButton);
        total=(TextView) findViewById(R.id.total);
        totalPrice= (TextView) findViewById(R.id.totalprice);
        totalPriceInput=(TextView)findViewById(R.id.totalPriceInput);
        totalInput= (TextView) findViewById(R.id.totalInput);
        ca=this;

//        map=new HashMap();




//        Bundle extras= getIntent().getExtras();
//        final Bundle item=(Bundle)extras.get("nature");
        Bundle extras=getIntent().getExtras();
        final Bundle item=(Bundle)extras.get("information");

        totalPriceInput.setText("Rp " + item.getString("totalPrice"));
        totalInput.setText(item.getString("totalItem"));

//        Log.d("lat",String.valueOf(item.getDouble("lat")));
//        Log.d("addressShip",item.getString("address"));
        allInfo= new ArrayList<ProductFarmer>();
        if(item.getDouble("lat")!=0 && item.getDouble("lng")!=0 && item.getString("address")!=null
                && item.getSerializable("data")!=null && item.getString("receiverInfo")!=null
                && item.getString("zipcode")!=null){
            Log.d("isian","kok ga bisa");
            lat=item.getDouble("lat");
            lng=item.getDouble("lng");
            address.setText(item.getString("address"));

            addressShip=item.getString("address");

            receiverInfo.setText(item.getString("receiverInfo"));
            zipcode.setText(item.getString("zipcode"));
            MapsActivity.ma.finish();
        }
//        Log.d("cart",item.getStringArray("cart").toString());


        carts = new ArrayList<JSONObject>();

        gps= new GPSTracker(this);
        if(!gps.canGetLocation()){
            gps.showSettingsAlert();
        }else {


            if (item.getDouble("lat") != 0 && item.getDouble("lng") != 0) {
                latitude = lat;
                longitude = lng;
            } else {
                latitude = gps.getLatitude();
                longitude = gps.getLongitude();
            }
        }
            Log.d("latitude",String.valueOf(latitude));
            Log.d("longitude",String.valueOf(longitude));

            mQueue = CustomVolleyRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
            url = "http://104.155.213.80/insantani/public/api/cart?user_id=" + pref.getString("user_id", null);
            final StringRequest stringRequestCart = new StringRequest(Request.Method.GET,
                    url, new Response.Listener<String>() {
                //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                @Override
                public void onResponse(String response) {
                    Log.d("checkout_item", response.toString());
                    try {
                        JSONObject jsonObjectData = new JSONObject(response.toString());
                        JSONArray jsonArrayData = jsonObjectData.getJSONArray("data");
                        for (int i = 0; i < jsonArrayData.length(); i++) {
                            carts.add((JSONObject) jsonArrayData.get(i));
                            JSONObject cart = (JSONObject) jsonArrayData.get(i);
                            url = "http://104.155.213.80/insantani/public/api/products/" + cart.getInt("product_id")
                                    + "/" + latitude + "/" + longitude;
//                        set.add(((JSONObject)jsonArrayData.get(i)).getString(""));
                            final JsonObjectRequest jsonRequestProduct = new JsonObjectRequest(Request.Method.GET,
                                    url,new JSONObject(), new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        HashMap map = new HashMap();
                                        JSONArray jsonArray = response.getJSONArray("result");
                                        JSONObject dataDetail = (JSONObject) jsonArray.get(0);
                                        JSONObject farmerDetail = (JSONObject) dataDetail.getJSONObject("farmer");
                                        ProductFarmer productFarmer = new ProductFarmer(dataDetail.getString("farmer_username"),
                                                dataDetail.getDouble("distance"), farmerDetail.getDouble("latitude"),
                                                farmerDetail.getDouble("longitude"));

                                                allInfo.add(productFarmer);



                                    } catch (Exception e) {
                                        Log.d("JSON_product_farmer_err", e.toString());
                                    }

                                }

                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d("response_product_farmer_err", error.toString());
                                }
                            });
                            jsonRequestProduct.setTag(REQUEST_TAG);
                            mQueue.add(jsonRequestProduct);


                        }
                    } catch (Exception e) {
                        Log.d("checkout_item_error", e.toString());
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.d("error_response_checkout_item", error.toString());
//                    if (error.toString().equals("com.android.volley.AuthFailureError")) {
//                        RefreshTokenManager refreshToken = new RefreshTokenManager(getApplicationContext());
//                        refreshToken.login();
//                    } else {

                        Snackbar snackbar = Snackbar.make(linearLayout, "Something wrong", Snackbar.LENGTH_LONG);
                        snackbar.setActionTextColor(Color.WHITE);

                        View snackbarView = snackbar.getView();
                        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(Color.WHITE);

                        snackbar.show();
//                    }
                }
            }) {

                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<String, String>();
                    // the POST parameters:
                    String auth = "Bearer " + pref.getString("access_token", null);
                    Log.d("Auth_cart", auth);
                    headers.put("Authorization", auth);

                    return headers;
                }

                ;


            };


            stringRequestCart.setTag(REQUEST_TAG);
            mQueue.add(stringRequestCart);

//                    totalInput.setText(Integer.toString(carts.size()));


//                    if (!item.getString("totalItem").equals("0") && !address.getText().toString().equals("") && !zipcode.getText().toString().equals("")){


            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!item.getString("totalItem").equals("0")  && !zipcode.getText().toString().equals("") &&
                            addressShip!=null && lat!=0 && lng!=0) {
                        ringProgressDialog=ProgressDialog.show(ca, "Please Wait ...", "Loading", true);


                        ArrayList<JSONObject> array = new ArrayList<JSONObject>();


//                                    try {
//                                        Geocoder geocoder = new Geocoder(getApplicationContext());
//                                        List<Address> addresses;
//                                        addresses = geocoder.getFromLocationName(address.getText().toString() + " ," + zipcode.getText().toString(), 1);
//                                        if (addresses.size() > 0) {
//                                            double latitude = addresses.get(0).getLatitude();
//                                            double longitude = addresses.get(0).getLongitude();
//                                            Log.d("latitude", Double.toString(latitude));
//                                            Log.d("longitude", Double.toString(longitude));

                        ArrayList legal = new ArrayList();
                        for (int p = 0; p < allInfo.size(); p++) {
                            if (allInfo.get(p).getDistance() < 25) {
                                legal.add(true);
                                Log.d("info_p",String.valueOf(allInfo.get(p).getDistance()));
                            } else {
                                legal.add(false);
                            }
                        }

                        Log.d("legal",legal.toString());
                        Log.d("allInfo",allInfo.toString());

                        if (!legal.contains(false)){
                            for (int i = 0; i < carts.size(); i++) {
                                try {
                                    JSONObject sendObject = new JSONObject();
                                    sendObject.put("product_id", carts.get(i).getString("product_id"));
                                    sendObject.put("productQty", carts.get(i).getInt("product_quantity"));
                                    sendObject.put("user_id", pref.getString("user_id", null));
                                    sendObject.put("address", address.getText().toString());
                                    sendObject.put("location_detail",address.getText().toString());
                                    sendObject.put("latitude", lat);
                                    sendObject.put("longitude", lng);
                                    sendObject.put("receiver_name", receiverInfo.getText().toString());

                                    array.add(sendObject);
                                } catch (Exception e) {
                                    Log.d("translation_error", e.toString());
                                }
                                //            temp.add(sendObject);

                            }

                        JSONArray jsonArrayItems = new JSONArray(array);
                        JSONObject params = new JSONObject();
                        // the POST parameters:
                        Log.d("params", jsonArrayItems.toString());
//                Log.d("data", jsonArrayItems.toString());
                        try {
                            params.put("data", jsonArrayItems);
                        } catch (Exception e) {
                            Log.d("error_json_request", e.toString());
                        }


//    mQueue= CustomVolleyRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
                        url = "http://104.155.213.80/insantani/public/api/checkout";
                        final JsonObjectRequest jsonRequestCheckout = new JsonObjectRequest(Request.Method.POST,
                                url, params, new Response.Listener<JSONObject>() {
                            //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("checkout", response.toString());
                                try {
                                    Log.d("response_checkout", response.toString());
//                JSONObject jsonObjectMessage= new JSONObject(response.toString());
                                    String message = response.getString("message");
//                                Log.d("JSON_response_checkout", jsonObject.getString("message"));
//                                      String message=response.getString("message");
//
                                    if (message.equals("success")) {
//                                            Snackbar snackbar = Snackbar.make(linearLayout, message, Snackbar.LENGTH_LONG);
//                                            snackbar.setActionTextColor(Color.WHITE);
//
//                                            View snackbarView = snackbar.getView();
//                                            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                                            textView.setTextColor(Color.WHITE);
//
//                                            snackbar.show();
                                        ringProgressDialog.dismiss();
                                        LayoutInflater inflater = getLayoutInflater();

                                        AlertDialog alertDialog = new AlertDialog.Builder(CheckoutActivity.this).create();
                                        alertDialog.setView(inflater.inflate(R.layout.cart_warn, null));
                                        alertDialog.setTitle("Warning");
                                        alertDialog.setMessage("You have successfully purchase items");

                                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {

                                                        dialog.dismiss();
                                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                });
                                        alertDialog.show();
//                                            getSupportFragmentManager().beginTransaction().detach(cartFragment).attach(cartFragment).commit();
                                    } else if (message.equals("out of stocks")) {
                                        JSONArray itemOut = response.getJSONArray("data");
                                        int sizeItemOut = itemOut.length();
                                        int leftOver = Integer.valueOf(item.getString("totalItem")) - sizeItemOut;
                                        ringProgressDialog.dismiss();
                                        LayoutInflater inflater = getLayoutInflater();

                                        AlertDialog alertDialog = new AlertDialog.Builder(CheckoutActivity.this).create();
                                        alertDialog.setView(inflater.inflate(R.layout.cart_warn_limited, null));
                                        alertDialog.setTitle("Warning");

//                                                TextView warn= (TextView) alertDialog.findViewById(R.id.warn);
//                                                warn.setText("Can only process "+Integer.toString(leftOver)+" items due to limited stock");
                                        alertDialog.setMessage("Can only process " + Integer.toString(leftOver) + " items due to limited stock");

                                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {

                                                        dialog.dismiss();
                                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                });
                                        alertDialog.show();

                                    } else if (message.equals("too far from the farmer")) {
                                        JSONArray itemOut = response.getJSONArray("data");
                                        int sizeItemOut = itemOut.length();
                                        int leftOver = Integer.valueOf(item.getString("totalItem")) - sizeItemOut;
                                        ringProgressDialog.dismiss();

                                        LayoutInflater inflater = getLayoutInflater();

                                        AlertDialog alertDialog = new AlertDialog.Builder(CheckoutActivity.this).create();
                                        alertDialog.setView(inflater.inflate(R.layout.cart_warn_limited, null));
                                        alertDialog.setTitle("Warning");

//                                                TextView warn= (TextView) alertDialog.findViewById(R.id.warn);
//                                                warn.setText("Can only process "+Integer.toString(leftOver)+" items due to limited stock");
                                        alertDialog.setMessage("Can only process " + Integer.toString(leftOver) + " items due to too far from farmer");

                                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {

                                                        dialog.dismiss();
                                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                });
                                        alertDialog.show();
                                    } else {
                                        Snackbar snackbar = Snackbar.make(linearLayout, message, Snackbar.LENGTH_LONG);
                                        snackbar.setActionTextColor(Color.WHITE);

                                        View snackbarView = snackbar.getView();
                                        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                        textView.setTextColor(Color.WHITE);
                                        ringProgressDialog.dismiss();

                                        snackbar.show();

                                    }


                                } catch (Exception e) {
                                    Log.d("JSON_error_checkout", e.toString());
                                    ringProgressDialog.dismiss();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.d("error_response_checkout", error.toString());
//                                if (error.toString().equals("com.android.volley.AuthFailureError")) {
//                                    RefreshTokenManager refreshToken = new RefreshTokenManager(getApplicationContext());
//                                    refreshToken.login();
//                                } else {
                                    Snackbar snackbar = Snackbar.make(linearLayout, "Something wrong", Snackbar.LENGTH_LONG);
                                    snackbar.setActionTextColor(Color.WHITE);

                                    View snackbarView = snackbar.getView();
                                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                    textView.setTextColor(Color.WHITE);
                                    ringProgressDialog.dismiss();

                                    snackbar.show();
//                                }
                            }
                        }) {

                            @Override
                            public Map<String, String> getHeaders() {
                                Map<String, String> headers = new HashMap<String, String>();
                                // the POST parameters:
                                String auth = "Bearer " + pref.getString("access_token", null);
                                Log.d("Auth_cart", auth);
                                headers.put("Authorization", auth);

                                return headers;
                            };

                        };


                        jsonRequestCheckout.setTag(REQUEST_TAG);
                        mQueue.add(jsonRequestCheckout);
                    }else{
                            Snackbar snackbar = Snackbar.make(linearLayout, "Location too far from one of the farmer", Snackbar.LENGTH_LONG);
                            snackbar.setActionTextColor(Color.WHITE);

                            View snackbarView = snackbar.getView();
                            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            textView.setTextColor(Color.WHITE);
                            ringProgressDialog.dismiss();

                            snackbar.show();

                        }

//                                        } else {
//
//                                        }
//                                    } catch (Exception e) {
//                                        Log.d("error_geocoder", e.toString());
//                                    }


                    } else {
                        Snackbar snackbar = Snackbar.make(linearLayout, "Please input the missing fields", Snackbar.LENGTH_LONG);
                        snackbar.setActionTextColor(Color.WHITE);

                        View snackbarView = snackbar.getView();
                        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(Color.WHITE);

                        snackbar.show();
                    }
                }


            });


            LinearLayout map = (LinearLayout) findViewById(R.id.viewMaps);

            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("totalPrice", item.getString("totalPrice"));
                    bundle.putString("totalItem", item.getString("totalItem"));
                    bundle.putSerializable("data", allInfo);
                    bundle.putString("zipcode", zipcode.getText().toString());
                    bundle.putString("receiverInfo", receiverInfo.getText().toString());
                    intent.putExtra("information", bundle);
                    startActivity(intent);

                }
            });



    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.) {
//            return true;
//        }

        switch (id) {
            case android.R.id.home:
                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
                // if this doesn't work as desired, another possibility is to call `finish()` here.
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

//        return super.onOptionsItemSelected(item);
    }
}
