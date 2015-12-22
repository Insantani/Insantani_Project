package com.williamhenry.insantani;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SubscriptionsFragment extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    private String url;
    private RequestQueue mQueue;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public static final String REQUEST_TAG = "SubscriptionFragment";
    private RelativeLayout relativeLayout;
    private ArrayList<Subscription> subscriptions;

    public SubscriptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getActionBar().setElevation(11);
        pref=getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        editor=pref.edit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_subscriptions, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        relativeLayout= (RelativeLayout) rootView.findViewById(R.id.relativeLayoutSubscription);
        final RoundedImageView roundedImageView = new RoundedImageView(getContext());
        subscriptions = new ArrayList<Subscription>();
//        subscriptions.add(new Subscription("Izhar Almizan", R.mipmap.ic_name, false));
//        subscriptions.add(new Subscription("Izhar Almizan", R.mipmap.ic_name, true));
//        subscriptions.add(new Subscription("Izhar Almizan", R.mipmap.ic_name, true));
//        subscriptions.add(new Subscription("Izhar Almizan", R.mipmap.ic_name, false));
//        subscriptions.add(new Subscription("Izhar Almizan", R.mipmap.ic_name, true));
        mQueue= CustomVolleyRequestQueue.getInstance(getContext()).getRequestQueue();
        url="http://104.155.213.80/insantani/public/api/follow/following?user_id="+pref.getString("user_id",null);
        final StringRequest stringRequestFollowing= new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>(){
            //                    private ArrayList<Article> articles1=new ArrayList<Article>();
            @Override
            public void onResponse(String response){
                Log.d("following", response.toString());
                try {
                    Log.d("response_following", response.toString());
                    JSONObject jsonObject1= new JSONObject(response.toString());
                    JSONArray jsonArray= jsonObject1.getJSONArray("data");

                    for (int i=0;i<jsonArray.length();i++){
                        final JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                        Log.d("farmer", jsonObject.getString("farmer_username"));
//                        Log.d("user_id", jsonObject.getString("user_id"));
//                        Log.d("product_quantity", jsonObject.getString("product_quantity"));


                        url="http://104.155.213.80/insantani/public/api/farmer/"+jsonObject.getString("farmer_username");
                        final CustomJSONObjectRequest jsonRequestFarmerDetail= new CustomJSONObjectRequest(Request.Method.GET,
                                url,new JSONObject(),
                                new Response.Listener<JSONObject>(){
                                    //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                                    @Override
                                    public void onResponse(JSONObject response){
                                        try {
                                            JSONArray resultFarmerDetail=response.getJSONArray("result");
//                        if(result.getString("next_page_url"))

//                                            JSONArray dataProduct=resultProduct.getJSONArray("data");
                                            Log.d("response_farmer_detail", resultFarmerDetail.get(0).toString());

                                                final JSONObject dataDetailFarmer=(JSONObject)resultFarmerDetail.get(0);
                                                url="http://104.155.213.80/insantani/public/"+dataDetailFarmer.getString("profile_picture_url");
//                                Log.d("url_product",url);
                                                final ImageRequest imageRequest= new ImageRequest(url,
                                                        new Response.Listener<Bitmap>() {
                                                            @Override
                                                            public void onResponse(Bitmap bitmap) {
                                                                try{
                                                                    Log.d("bitmap", bitmap.toString());
                                                                    Bitmap photo= roundedImageView.getCroppedBitmap(bitmap,75);
                                                                    Subscription sub = new Subscription(dataDetailFarmer.getString("farmer_username"),photo,true);

                                                                    subscriptions.add(sub);
                                                                    mAdapter = new SubscriptionAdapter(subscriptions,getContext());

                                                                    mRecyclerView.setAdapter(mAdapter);

                                                                }catch(Exception e){
                                                                    Log.d("error_picture_farmer",e.toString());

                                                                }

                                                            }
                                                        },0,0,null,new Response.ErrorListener() {
                                                    public void onErrorResponse(VolleyError error) {
                                                        Log.d("error_response_farmer_image", error.toString());
                                                    }
                                                });
                                                imageRequest.setTag(REQUEST_TAG);
                                                mQueue.add(imageRequest);


                                        } catch(Exception e){
                                            Log.d("JSON_err",e.toString());
                                        }
                                    }
                                },new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                Log.d("error_response_farmer",error.toString());
                            }
                        });
                        jsonRequestFarmerDetail.setTag(REQUEST_TAG);
                        mQueue.add(jsonRequestFarmerDetail);

                    }

                } catch(Exception e){
                    Log.d("JSON_error_follow",e.toString());
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

                Log.d("error_response_follow",error.toString());
                if(error.toString().equals("com.android.volley.AuthFailureError")) {
                    RefreshTokenManager refreshToken = new RefreshTokenManager(getContext());
                    refreshToken.login();
                }else {

                    Snackbar snackbar = Snackbar.make(relativeLayout, "Something wrong", Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.WHITE);

                    View snackbarView = snackbar.getView();
                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);

                    snackbar.show();
                }
            }
        }){

            @Override
            public Map<String, String> getHeaders(){
                Map<String, String> headers = new HashMap<String,String>();
                // the POST parameters:
                String auth="Bearer "+pref.getString("access_token",null);
                Log.d("Auth_follow",auth);
                headers.put("Authorization",auth);
                return headers;
            };



        };


        stringRequestFollowing.setTag(REQUEST_TAG);
        mQueue.add(stringRequestFollowing);






        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

//    public void onResume() {
//        super.onResume();
//
//        mQueue= CustomVolleyRequestQueue.getInstance(getContext()).getRequestQueue();
//        url="http://104.155.213.80/insantani/public/api/follow/following?user_id="+pref.getString("user_id",null);
//        final StringRequest stringRequestFollowing= new StringRequest(Request.Method.GET,
//                url, new Response.Listener<String>(){
//            //                    private ArrayList<Article> articles1=new ArrayList<Article>();
//            @Override
//            public void onResponse(String response){
//                Log.d("following", response.toString());
//                try {
//                    Log.d("response_following", response.toString());
//                    JSONObject jsonObject1= new JSONObject(response.toString());
//                    JSONArray jsonArray= jsonObject1.getJSONArray("data");
//                    for (int i=0;i<subscriptions.size();i++){
//                        for(int j=0;j<jsonArray.length();j++){
//                            if(((JSONObject)jsonArray.get(j)).getString("farmer_username").equals(subscriptions.get(i).getFarmerName()))
//
//
//                        }
//
//
//                    }
//
//
//                } catch(Exception e){
//                    Log.d("JSON_error_follow",e.toString());
//                }
//            }
//        },new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError error){
//
//                Log.d("error_response_follow",error.toString());
//                if(error.toString().equals("com.android.volley.AuthFailureError")) {
//                    RefreshTokenManager refreshToken = new RefreshTokenManager(getContext());
//                    refreshToken.login();
//                }else {
//
//                    Snackbar snackbar = Snackbar.make(relativeLayout, "Something wrong", Snackbar.LENGTH_LONG);
//                    snackbar.setActionTextColor(Color.WHITE);
//
//                    View snackbarView = snackbar.getView();
//                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    textView.setTextColor(Color.WHITE);
//
//                    snackbar.show();
//                }
//            }
//        }){
//
//            @Override
//            public Map<String, String> getHeaders(){
//                Map<String, String> headers = new HashMap<String,String>();
//                // the POST parameters:
//                String auth="Bearer "+pref.getString("access_token",null);
//                Log.d("Auth_follow",auth);
//                headers.put("Authorization",auth);
//                return headers;
//            };
//
//
//
//        };
//
//
//        stringRequestFollowing.setTag(REQUEST_TAG);
//        mQueue.add(stringRequestFollowing);
//
//
//    }

}