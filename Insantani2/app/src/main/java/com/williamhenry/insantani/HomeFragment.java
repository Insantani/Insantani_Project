package com.williamhenry.insantani;

<<<<<<< HEAD
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
=======
>>>>>>> a80a3f010b43fd581a43c7902a4989abfb6ee117
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
<<<<<<< HEAD
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
=======
import android.support.v4.view.ViewPager;
>>>>>>> a80a3f010b43fd581a43c7902a4989abfb6ee117
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.TabHost;

import java.util.ArrayList;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class HomeFragment extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView mRecyclerView1;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.LayoutManager mLayoutManager1;
    RecyclerView.Adapter mAdapter;
    private String url;
    private RequestQueue mQueue;
    private ArrayList<Article> articles;
    private ArrayList<Product> mItems;
    public static final String REQUEST_TAG = "HomeFragment";
    private SharedPreferences pref;
    private Editor editor;
=======


public class HomeFragment extends Fragment {

    private FeedTabFragment feedTabFragment;
    private ShopTabFragment shopTabFragment;

    private TabLayout tabLayout;
>>>>>>> a80a3f010b43fd581a43c7902a4989abfb6ee117

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getActionBar().show();
<<<<<<< HEAD

=======
        getActivity().getActionBar().setElevation(0);
>>>>>>> a80a3f010b43fd581a43c7902a4989abfb6ee117
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
<<<<<<< HEAD
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        final TabHost tabHost = (TabHost) rootView.findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("feed");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Feed", null);
        tabHost.addTab(spec1);

        TabHost.TabSpec spec2 = tabHost.newTabSpec("shop");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Shop", null);
        tabHost.addTab(spec2);
        mRecyclerView1 = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        mRecyclerView1.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager1 = new GridLayoutManager(getActivity(), 2);
        mRecyclerView1.setLayoutManager(mLayoutManager1);

        mItems = new ArrayList<Product>();
        mQueue= CustomVolleyRequestQueue.getInstance(getContext()).getRequestQueue();
        url="http://104.155.213.80/insantani/public/api/products?page=0&limit=3";
        final CustomJSONObjectRequest jsonRequestProduct= new CustomJSONObjectRequest(Request.Method.GET,
                url,new JSONObject(),
                new Response.Listener<JSONObject>(){
                    //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            JSONObject resultProduct=response.getJSONObject("result");
//                        if(result.getString("next_page_url"))

                            JSONArray dataProduct=resultProduct.getJSONArray("data");
                            Log.d("response_products", dataProduct.get(0).toString());
                            for (int i=0;i<dataProduct.length();i++){
                                final JSONObject dataDetailProduct=(JSONObject)dataProduct.get(i);
                                url="http://104.155.213.80/insantani/public/"+dataDetailProduct.getString("product_picture_url");
//                                Log.d("url_product",url);
                                final ImageRequest imageRequest= new ImageRequest(url,
                                        new Response.Listener<Bitmap>() {
                                            @Override
                                            public void onResponse(Bitmap bitmap) {
                                                try{
                                                    Log.d("bitmap",bitmap.toString());
                                                    Product product = new Product();
                                                    product.setFarmerName(dataDetailProduct.getString("farmer_username"));
                                                    product.setDescription(dataDetailProduct.getString("prod_desc"));
                                                    product.setName(dataDetailProduct.getString("product_name"));
                                                    product.setPrice(dataDetailProduct.getInt("prod_price"));
                                                    product.setStock(dataDetailProduct.getInt("stock_num"));
                                                    product.setId(dataDetailProduct.getInt("id"));
                                                    product.setUrl(dataDetailProduct.getString("product_picture_url"));
                                                    product.setThumbnail(bitmap);
//
                                                    mItems.add(product);

                                                    mAdapter = new GridAdapter(mItems,getContext());
                                                    mRecyclerView1.setAdapter(mAdapter);




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

                        } catch(Exception e){
                            Log.d("JSON_err",e.toString());
                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("error_response",error.toString());
            }
        });
        jsonRequestProduct.setTag(REQUEST_TAG);
        mQueue.add(jsonRequestProduct);







        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        articles = new ArrayList<Article>();
//        mQueue= CustomVolleyRequestQueue.getInstance(getContext()).getRequestQueue();
        url="http://104.155.213.80/insantani/public/api/feed?page=1&limit=3";
        final CustomJSONObjectRequest jsonRequest= new CustomJSONObjectRequest(Request.Method.GET,
                url,new JSONObject(),
                new Response.Listener<JSONObject>(){
                    //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            JSONObject result=response.getJSONObject("result");
//                        if(result.getString("next_page_url"))

                            JSONArray data=result.getJSONArray("data");
                            Log.d("response1", data.get(0).toString());
                            for (int i=0;i<data.length();i++){
                                final JSONObject dataDetail=(JSONObject)data.get(i);
                                url="http://104.155.213.80/insantani/public/"+dataDetail.getString("article_picture_url");
                                Log.d("url",url);
                                final ImageRequest imageRequest= new ImageRequest(url,
                                        new Response.Listener<Bitmap>() {
                                            @Override
                                            public void onResponse(Bitmap bitmap) {
                                                try{
                                                    Log.d("bitmap",bitmap.toString());
                                                    Article article = new Article(dataDetail.getString("author"),
                                                            dataDetail.getString("title"), dataDetail.getString("content"), bitmap,
                                                            dataDetail.getString("article_picture_url"));
//
                                                    articles.add(article);
                                                    mAdapter = new MyAdapter(articles, getContext());
                                                    mRecyclerView.setAdapter(mAdapter);
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
//                                @Override
//                                public void onResponse(Bitmap bitmap) {
//                                    mImageView.setImageBitmap(bitmap);
//                                }
//                            }, 0, 0, null,
//                                    new Response.ErrorListener() {
//                                        public void onErrorResponse(VolleyError error) {
//                                            mImageView.setImageResource(R.drawable.image_load_error);
//                                        }
//                                    });

//                            Log.d("articles",articles.toString());
                        } catch(Exception e){
                            Log.d("JSON_err",e.toString());
                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("error_response",error.toString());
            }
        });
        jsonRequest.setTag(REQUEST_TAG);
        mQueue.add(jsonRequest);


=======
        View view = inflater.inflate(R.layout.fragment_home, container, false);
>>>>>>> a80a3f010b43fd581a43c7902a4989abfb6ee117
        // Inflate the layout for this fragment

        feedTabFragment = new FeedTabFragment();
        shopTabFragment = new ShopTabFragment();

        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getChildFragmentManager());
        adapter.addFragment(feedTabFragment, "Feed");
        adapter.addFragment(shopTabFragment, "Shop");

<<<<<<< HEAD
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//
//            return true
//                    ;
//        }
=======
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
>>>>>>> a80a3f010b43fd581a43c7902a4989abfb6ee117

        return view;
    }



}
