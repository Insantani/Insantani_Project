package com.williamhenry.insantani;

import android.content.Context;
import android.content.Intent;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.List;
import java.util.Map;


public class FarmerActivityTabFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private String url;
    public static final String REQUEST_TAG = "Updates";
    private RequestQueue mQueue;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private boolean checkToken;
    private boolean checkRefreshToken;
    private boolean tokenType;
    private boolean user_id;
    private RelativeLayout relativeLayout;

    private ArrayList<FarmerActivity> feeds;

    public FarmerActivityTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_farmer_activity_tab, container, false);
        pref= getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        editor=pref.edit();
        checkToken= pref.contains("access_token");
        checkRefreshToken= pref.contains("refresh_token");
        tokenType= pref.contains("token_type");
        user_id=pref.contains("user_id");


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.farmerActivityRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        relativeLayout=(RelativeLayout) rootView.findViewById(R.id.relativeLayoutUpdates);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // farmer feed
        feeds = new ArrayList<FarmerActivity>();

        final RoundedImageView roundedImageView = new RoundedImageView(getContext());

        mQueue= CustomVolleyRequestQueue.getInstance(getContext()).getRequestQueue();

        url="http://104.155.213.80/insantani/public/api/updates?user_id="+pref.getString("user_id",null);
        final StringRequest jsonRequestUpdates= new StringRequest(Request.Method.GET,
                url,new Response.Listener<String>() {

            @Override
            public void onResponse(String response1){

                try{
//                                                                            Log.d("bitmap", bitmap.toString());
                    JSONObject response= new JSONObject(response1.toString());
                    JSONArray dataUpdates= response.getJSONArray("data");
                    for(int i=0;i<dataUpdates.length();i++){
                        final JSONObject update= (JSONObject)dataUpdates.get(i);
                        url="http://104.155.213.80/insantani/public/api/farmer/"+
                                ((JSONObject)dataUpdates.get(i)).getString("farmer_username");
                        final CustomJSONObjectRequest jsonRequestFarmerDetail= new CustomJSONObjectRequest(Request.Method.GET,
                                url,new JSONObject(),
                                new Response.Listener<JSONObject>(){
                                    @Override
                                    public void onResponse(JSONObject response){
                                        try {
                                            JSONArray resultFarmerDetail=response.getJSONArray("result");

                                            Log.d("response_farmer_detail", resultFarmerDetail.get(0).toString());
                                            for (int x=0;x<resultFarmerDetail.length();x++){
                                                final JSONObject dataFarmerDetail=(JSONObject)resultFarmerDetail.get(x);
                                                url="http://104.155.213.80/insantani/public/"+dataFarmerDetail.getString("profile_picture_url");
//                                Log.d("url_product",url);
                                                final ImageRequest imageRequest= new ImageRequest(url,
                                                        new Response.Listener<Bitmap>() {
                                                            @Override
                                                            public void onResponse(final Bitmap bitmap) {
                                                                try{
                                                                    Log.d("bitmap",bitmap.toString());

//                                                                                                                                JSONObject update=(JSONObject) dataUpdates.get(i);

                                                                    FarmerActivity feed = new FarmerActivity();
                                                                    feed.setName(dataFarmerDetail.getString("farmer_username"));
                                                                    feed.setDetail(update.getString("updates"));
                                                                    Bitmap photo= roundedImageView.getCroppedBitmap(bitmap,75);

                                                                    feed.setPhoto(photo);
                                                                    feeds.add(feed);
                                                                    mAdapter = new FarmerActivityAdapter(feeds, getContext());
                                                                    mRecyclerView.setAdapter(mAdapter);

                                                                }catch(Exception e){
                                                                        Log.d("error_farmer_profile_picture",e.toString());
    //
                                                                    }

                                                                }
                                                            },0,0,null,new Response.ErrorListener() {
                                                        public void onErrorResponse(VolleyError error) {
                                                            Log.d("error_response_farmer_profile_picture", error.toString());
                                                        }
                                                    });
                                                    imageRequest.setTag(REQUEST_TAG);
                                                    mQueue.add(imageRequest);

                                                }

                                            } catch(Exception e){
                                                Log.d("JSON_err_farmer",e.toString());
                                            }
                                        }
                                    },new Response.ErrorListener(){
                                @Override
                                public void onErrorResponse(VolleyError error){
                                    Log.d("error_response_farmer_detail",error.toString());
                                }
                            });
                            jsonRequestFarmerDetail.setTag(REQUEST_TAG);
                            mQueue.add(jsonRequestFarmerDetail);
                    }


                }catch(Exception e){

                    Log.d("json_error_updates",e.toString());


                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response_error_updates",error.toString());
                if(error.toString().equals("com.android.volley.AuthFailureError")) {
                    RefreshTokenManager refreshToken = new RefreshTokenManager(getContext());
                    refreshToken.login();
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<String, String>();
                // the POST parameters:
                String auth = "Bearer " + pref.getString("access_token", null);
                Log.d("Auth", auth);
                headers.put("Authorization", auth);
                return headers;
            }

        };

        jsonRequestUpdates.setTag(REQUEST_TAG);
        mQueue.add(jsonRequestUpdates);







//                        Cart cartData=new Cart (jsonObject.getString(""),jsonObject.get);




//        mQueue= CustomVolleyRequestQueue.getInstance(getContext()).getRequestQueue();
//        url="http://104.155.213.80/insantani/public/api/feed/article/1/picture";
//
//        final ImageRequest imageRequest= new ImageRequest(url,
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap bitmap) {
//                        try{
//                            Log.d("bitmap", bitmap.toString());
//                            FarmerActivity feed = new FarmerActivity();
//                            feed.setName("William Henry");
//                            feed.setDetail("I want to plant carrot at 5 December");
//
//
//                            FarmerActivity feed2 = new FarmerActivity();
//                            feed2.setName("Agung Wirayogi");
//                            feed2.setDetail("Cucumber will be harvested at 3 December");
//
//
//                            FarmerActivity feed3 = new FarmerActivity();
//                            feed3.setName("William henry");
//                            feed3.setDetail("I have put fertilezer good quality");
//
//                            Bitmap photo = roundedImageView.getCroppedBitmap(bitmap, 75);
//
//                            feed.setPhoto(photo);
//                            feed2.setPhoto(photo);
//                            feed3.setPhoto(photo);
//
//                            feeds.add(feed);
//                            feeds.add(feed2);
//                            feeds.add(feed3);
//
//
//                            mAdapter = new FarmerActivityAdapter(feeds, getContext());
//                            mRecyclerView.setAdapter(mAdapter);
//
//
//
//                        }catch(Exception e){
//                            Log.d("error_picture_article",e.toString());
//
//                        }
//
//                    }
//                },0,0,null,new Response.ErrorListener() {
//            public void onErrorResponse(VolleyError error) {
//                Log.d("error_response_image_article", error.toString());
//            }
//        });
//        imageRequest.setTag(REQUEST_TAG);
//        mQueue.add(imageRequest);










        // Inflate the layout for this fragment
        return rootView;
    }


}
