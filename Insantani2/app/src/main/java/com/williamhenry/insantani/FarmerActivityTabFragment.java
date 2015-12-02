package com.williamhenry.insantani;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FarmerActivityTabFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private String url;
    public static final String REQUEST_TAG = "HomeFragment";
    private RequestQueue mQueue;

    private ArrayList<FarmerActivity> feeds;

    public FarmerActivityTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_farmer_activity_tab, container, false);


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.farmerActivityRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // farmer feed
        feeds = new ArrayList<FarmerActivity>();


        final RoundedImageView roundedImageView = new RoundedImageView(getContext());
        mQueue= CustomVolleyRequestQueue.getInstance(getContext()).getRequestQueue();
        url="http://104.155.213.80/insantani/public/api/feed/article/1/picture";

        final ImageRequest imageRequest= new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        try{
                            Log.d("bitmap", bitmap.toString());
                            FarmerActivity feed = new FarmerActivity();
                            feed.setName("William Henry");
                            feed.setDetail("I want to plant carrot at 5 December");


                            FarmerActivity feed2 = new FarmerActivity();
                            feed2.setName("Agung Wirayogi");
                            feed2.setDetail("Cucumber will be harvested at 3 December");


                            FarmerActivity feed3 = new FarmerActivity();
                            feed3.setName("William henry");
                            feed3.setDetail("I have put fertilezer good quality");

                            Bitmap photo = roundedImageView.getCroppedBitmap(bitmap, 75);

                            feed.setPhoto(photo);
                            feed2.setPhoto(photo);
                            feed3.setPhoto(photo);

                            feeds.add(feed);
                            feeds.add(feed2);
                            feeds.add(feed3);


                            mAdapter = new FarmerActivityAdapter(feeds, getContext());
                            mRecyclerView.setAdapter(mAdapter);



                        }catch(Exception e){
                            Log.d("error_picture_article",e.toString());

                        }

                    }
                },0,0,null,new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.d("error_response_image_article", error.toString());
            }
        });
        imageRequest.setTag(REQUEST_TAG);
        mQueue.add(imageRequest);










        // Inflate the layout for this fragment
        return rootView;
    }


}
