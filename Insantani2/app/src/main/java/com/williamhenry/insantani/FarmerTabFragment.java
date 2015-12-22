package com.williamhenry.insantani;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FarmerTabFragment extends android.support.v4.app.Fragment {

    private ListView resultListView;
    private SearchListViewFarmerAdapter searchListViewFarmerAdapter;
    private String url;
    private String query="null";
    private RequestQueue mQueue;
    public static final String REQUEST_TAG = "Search";

    private CustomJSONObjectRequest jsonRequestAll;

    GPSTracker gps;

    public FarmerTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_farmer_tab, container, false);

        final List<Farmer> newsList = new ArrayList<>();
        final RoundedImageView roundedImageView = new RoundedImageView(getContext());
        searchListViewFarmerAdapter = new SearchListViewFarmerAdapter(getActivity(), newsList);
        resultListView = (ListView) view.findViewById(R.id.itemList1);
        resultListView.setAdapter(searchListViewFarmerAdapter);

        mQueue= CustomVolleyRequestQueue.getInstance(getContext()).getRequestQueue();
        gps= new GPSTracker(getContext());
        if(!gps.canGetLocation()){
            gps.showSettingsAlert();
        }else{
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            Log.d("query", query);

            searchListViewFarmerAdapter = new SearchListViewFarmerAdapter(getActivity(), newsList);
//            System.out.println(searchListViewFarmerAdapter.toString());
//            System.out.println(resultListView.toString());
            resultListView = (ListView) view.findViewById(R.id.itemList1);
            resultListView.setAdapter(searchListViewFarmerAdapter);


            url = "http://104.155.213.80/insantani/public/api/search/farmer/" + query+"/"+latitude+"/"+longitude;

            final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method.GET,
                    url, new JSONObject(),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray result = response.getJSONArray("result");
                                Log.d("result_seach", result.toString());
//
//
//                            JSONArray data=result.getJSONArray("data");
//                            Log.d("response1", data.get(0).toString());
                                for (int i=0;i<result.length();i++){
                                    final JSONObject dataDetail=(JSONObject)result.get(i);
                                    url="http://104.155.213.80/insantani/public/"+dataDetail.getString("profile_picture_url");
                                    Log.d("url",url);
                                    final ImageRequest imageRequest= new ImageRequest(url,
                                            new Response.Listener<Bitmap>() {
                                                @Override
                                                public void onResponse(Bitmap bitmap) {
                                                    try{
                                                        Log.d("bitmap", bitmap.toString());
                                                        Farmer searchItem = new Farmer();
                                                        searchItem.setName(dataDetail.getString("farmer_username"));
                                                        searchItem.setPhoneNumber(dataDetail.getString("phone_number"));
                                                        searchItem.setRating((float) dataDetail.getDouble("rating"));
                                                        searchItem.setAddress(dataDetail.getString("address"));
                                                        searchItem.setProfilePictureUrl(dataDetail.getString("profile_picture_url"));
                                                        Bitmap photo= roundedImageView.getCroppedBitmap(bitmap, 75);
                                                        searchItem.setPhotoProfile(photo);
                                                        searchItem.setPhoto(null);
                                                        searchItem.setFullName(dataDetail.getString("farmer_name"));
                                                        searchItem.setEmail(dataDetail.getString("email"));
                                                        searchItem.setProducts(null);
                                                        searchItem.setPhotoBackground(null);
//                                                        searchItem.setUom(dataDetail.getString("uom"));
//                                                        searchItem.setThumbnail(bitmap);
                                                        searchItem.setDistance(dataDetail.getDouble("distance"));
//                                                        Log.d("distance",dataDetail.get);
                                                        newsList.add(searchItem);



                                                        searchListViewFarmerAdapter = new SearchListViewFarmerAdapter(getActivity(), newsList);
                                                        System.out.println(searchListViewFarmerAdapter.toString());
//                                                        System.out.println(resultListView.toString());
                                                        resultListView = (ListView) view.findViewById(R.id.itemList1);
                                                        resultListView.setAdapter(searchListViewFarmerAdapter);

                                                        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                            @Override
                                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                Bundle bundle= new Bundle();
                                                                Intent intent= new Intent(getContext(),FarmerProfileActivity.class);
                                                                bundle.putString("name",searchListViewFarmerAdapter.getItem(position).getName());
//                                                                bundle.putString("description",searchListViewAdapter.getItem(position).getDescription());
//                                                                bundle.putString("fname",searchListViewAdapter.getItem(position).getFarmerName());
//                                                                bundle.putString("url",searchListViewAdapter.getItem(position).getUrl());
//                                                                bundle.putInt("id", searchListViewAdapter.getItem(position).getId());
//                                                                bundle.putInt("price", searchListViewAdapter.getItem(position).getPrice());
//                                                                bundle.putInt("stock", searchListViewAdapter.getItem(position).getStock());
//                                                                bundle.putString("uom",searchListViewAdapter.getItem(position).getUom());
                                                                intent.putExtra("farmer", bundle);
                                                                getContext().startActivity(intent);

//                                        Toast.makeText(getActivity(),
//                                                getString(R.string.clicked) + " " + searchListViewAdapter.getItem(position).getTitle().toLowerCase(),
//                                                Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
//
                                                    }catch(Exception e){
                                                        Log.d("error_picture",e.toString());

                                                    }

                                                }
                                            },0,0,null,new Response.ErrorListener() {
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("error_response", error.toString());
                                        }
                                    });
                                    imageRequest.setTag(REQUEST_TAG);
                                    mQueue.add(imageRequest);

                                }
                            } catch (Exception e) {
                                Log.d("JSON_err", e.toString());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error_response", error.toString());
                }
            });
            jsonRequest.setTag(REQUEST_TAG);
            jsonRequestAll=jsonRequest;
            mQueue.add(jsonRequest);

        }







        // add items to the list
//        searchListViewAdapter.add(new SearchItem("News 1", "", 1));
//        searchListViewAdapter.add(new SearchItem("News 2", "", 2));
//        searchListViewAdapter.add(new SearchItem("News 3", "", 3));

        // show toast on item click
//        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(),
//                        getString(R.string.clicked) + " " + searchListViewAdapter.getItem(position).getName().toLowerCase(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }

    public void search(String param) {
        this.query=param;
        Log.d("param", param);


        searchListViewFarmerAdapter.getFilter().filter(param);
        if(searchListViewFarmerAdapter==null) {
            TextView search = (TextView) getView().findViewById(R.id.farmer_search_result);
            search.setText("Item not found");
        }


//        searchListViewFarmerAdapter.getFilter().filter(param);
    }

}
