package com.williamhenry.insantani;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

/**
 * Created by agungwy on 24-Nov-15.
 */
public class FarmerProfileActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView1;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManager1;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mAdapter1;
    private String url;
    public static final String REQUEST_TAG = "FarmerProfileAcitivty";
    private RequestQueue mQueue;
    private ArrayList<Product> mItems;
    Context context;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private boolean checkToken;
    private boolean checkRefreshToken;
    private boolean tokenType;
    private boolean user_id;
    private CoordinatorLayout relativeLayout;
    private Farmer farmer;
    private ArrayList<Photo> photos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_profile);

        pref=getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        editor=pref.edit();
        checkToken= pref.contains("access_token");
        checkRefreshToken= pref.contains("refresh_token");
        tokenType= pref.contains("token_type");
        user_id=pref.contains("user_id");
        relativeLayout=(CoordinatorLayout) findViewById(R.id.relativeLayoutFarmerProfile);


//

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        Bundle extras = getIntent().getExtras();
        final Bundle item = (Bundle) extras.get("farmer");

        //collapsing bar
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), "EXTRA_IMAGE");
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(item.getString("name"));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //rating
        float rating = (float) 3.5;
        final RatingBar mRatingBar = (RatingBar) findViewById(R.id.farmerRating);


        final TextView numberRating = (TextView) findViewById(R.id.farmerNumbeRating);
//        numberRating.setText(String.valueOf(rating));

//        final Farmer farmer = new Farmer();
//        farmer.setName(item.getString("name"));
//        farmer.setAddress("Depok");
//        farmer.setEmail("Kodok@gmail.com");
//        farmer.setPhoneNumber("081316477140");
//        ArrayList<Integer> photos = new ArrayList<Integer>();
//        photos.add(R.mipmap.carrot);
//        photos.add(R.mipmap.cabe);
//        photos.add(R.mipmap.col);
//        photos.add(R.mipmap.ic_email);
//        farmer.setPhoto(photos);
        final RoundedImageView roundedImageView = new RoundedImageView(this);
        final ImageView profileP = (ImageView) findViewById(R.id.farmerProfilePicture);

        final TextView name = (TextView) findViewById(R.id.farmerProfileName);
        name.setText(item.getString("name"));

        final TextView address = (TextView) findViewById(R.id.farmerAddress);
//        address.setText(farmer.getAddress());

        final TextView email = (TextView) findViewById(R.id.farmerEmail);
//        email.setText(farmer.getEmail());

        final TextView phone = (TextView) findViewById(R.id.farmerPhoneNumber);
//        phone.setText(farmer.getPhoneNumber());
        //background pricture
        final LinearLayout pictureB = (LinearLayout) findViewById(R.id.farmerPictureBackground);



        mQueue= CustomVolleyRequestQueue.getInstance(getApplicationContext()).getRequestQueue();



        mRecyclerView1 = (RecyclerView)findViewById(R.id.farmerProfilePhotos);
        mRecyclerView1.setHasFixedSize(true);
        // The number of Columns
        mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView1.setLayoutManager(mLayoutManager1);

        photos=new ArrayList();
        url="http://104.155.213.80/insantani/public/api/pictures/farmer/"+item.getString("name");
        final CustomJSONObjectRequest jsonRequestImages= new CustomJSONObjectRequest(Request.Method.GET,
                url,new JSONObject(),
                new Response.Listener<JSONObject>(){
                    //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                    @Override
                    public void onResponse(JSONObject response){
                        try {
//                            JSONObject resultProduct=response.getJSONObject("result");
//                        if(result.getString("next_page_url"))

                            JSONArray dataImages=response.getJSONArray("data");
                            Log.d("response_images", dataImages.get(0).toString());
                            for (int i=0;i<dataImages.length();i++){
                                final JSONObject dataDetailImages=(JSONObject)dataImages.get(i);
                                url="http://104.155.213.80/insantani/public/"+dataDetailImages.getString("farmer_images_url");
//                                Log.d("url_product",url);
                                final ImageRequest imageRequest= new ImageRequest(url,
                                        new Response.Listener<Bitmap>() {
                                            @Override
                                            public void onResponse(Bitmap bitmap) {
                                                try{
                                                    Log.d("bitmap", bitmap.toString());
                                                    Photo photo=new Photo();
                                                    photo.setImage(bitmap);
                                                    photo.setUrl(dataDetailImages.getString("farmer_images_url"));
                                                    photos.add(photo);

                                                    mAdapter1 = new FarmerPhotosAdapter(photos,context);
                                                    mRecyclerView1.setAdapter(mAdapter1);

                                                }catch(Exception e){
                                                    Log.d("error_picture",e.toString());

                                                }

                                            }
                                        },0,0,null,new Response.ErrorListener() {
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d("error_response_image", error.toString());
                                    }
                                });
                                imageRequest.setTag(REQUEST_TAG);
                                mQueue.add(imageRequest);

                            }

                        } catch(Exception e){
                            Log.d("JSON_error_images",e.toString());
                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("error_response_images",error.toString());
            }
        });
        jsonRequestImages.setTag(REQUEST_TAG);
        mQueue.add(jsonRequestImages);

        url="http://104.155.213.80/insantani/public/api/farmer/"+item.getString("name");
        final CustomJSONObjectRequest jsonRequestFarmer= new CustomJSONObjectRequest(Request.Method.GET,
                url,new JSONObject(),
                new Response.Listener<JSONObject>(){
                    //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            JSONArray resultFarmers=response.getJSONArray("result");
//                            Log.d()
                            final JSONObject resultFarmer=(JSONObject)resultFarmers.get(0);

                            Log.d("response_farmer", resultFarmer.toString());

                            url="http://104.155.213.80/insantani/public/"+resultFarmer.getString("background_picture_url");
//                                Log.d("url_product",url);
                            final ImageRequest imageRequest= new ImageRequest(url,
                                        new Response.Listener<Bitmap>() {
                                            @Override
                                            public void onResponse(final Bitmap bitmap) {
                                                try{
                                                    Log.d("bitmap", bitmap.toString());

                                                    url="http://104.155.213.80/insantani/public/"+resultFarmer.getString("profile_picture_url");
                                                    final ImageRequest imageRequestProfile= new ImageRequest(url,
                                                            new Response.Listener<Bitmap>() {
                                                                @Override
                                                                public void onResponse(Bitmap bitmapProfile) {
                                                                    try {

                                                                        Log.d("farmer", "lalal");
                                                                        farmer = new Farmer();
                                                                        farmer.setName(resultFarmer.getString("farmer_username"));
                                                                        farmer.setAddress(resultFarmer.getString("address"));
                                                                        farmer.setEmail(resultFarmer.getString("email"));
                                                                        farmer.setRating((float) resultFarmer.getDouble("rating"));
                                                                        farmer.setPhoneNumber(resultFarmer.getString("phone_number"));
                                                                        farmer.setProfilePictureUrl(resultFarmer.getString("profile_picture_url"));
//                                                                        ArrayList<Integer> photos = new ArrayList<Integer>();
//                                                                        photos.add(R.mipmap.carrot);
//                                                                        photos.add(R.mipmap.cabe);
//                                                                        photos.add(R.mipmap.col);
//                                                                        photos.add(R.mipmap.ic_email);
//                                                                        farmer.setPhoto(photos);
                                                                        farmer.setPhotoBackground(bitmap);
                                                                        farmer.setProducts(null);

                                                                        //rating bar
                                                                        mRatingBar.setRating(farmer.getRating());
                                                                        LayerDrawable stars = (LayerDrawable) mRatingBar.getProgressDrawable();
                                                                        stars.getDrawable(2).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                                                                        //rating
                                                                        numberRating.setText(String.valueOf(farmer.getRating()));

                                                                        address.setText(farmer.getAddress());
                                                                        email.setText(farmer.getEmail());
                                                                        phone.setText(farmer.getPhoneNumber());

                                                                        BitmapDrawable ob = new BitmapDrawable(getResources(), farmer.getPhotoBackground());
                                                                        pictureB.setBackground(ob);




                                                                        Bitmap photo = roundedImageView.getCroppedBitmap(bitmapProfile, 150);
                                                                        profileP.setImageBitmap(photo);

                                                                        profileP.setOnClickListener(new View.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(View v) {
                                                                                Intent intent = new Intent(context, PhotoActivity.class);
                                                                                Bundle bundle = new Bundle();
                                                                                bundle.putString("profile_picture_url", farmer.getProfilePictureUrl());
                                                                                intent.putExtra("photo", bundle);

                                                                                context.startActivity(intent);

                                                                            }
                                                                        });





                                                                        //

                                                                    } catch (Exception e) {
                                                                        Log.d("error_picture_profile", e.toString());
                                                                        }
                                                                    }
                                                                },0,0,null,new Response.ErrorListener(){
//                                                                        Log.d("error_response_picture_profile",e.toString());
                                                                    public void onErrorResponse(VolleyError error) {
                                                                        Log.d("error_response_picture_profile", error.toString());
                                                                    }
                                                                });

                                                                imageRequestProfile.setTag(REQUEST_TAG);
                                                                mQueue.add(imageRequestProfile);





                                                }catch(Exception e){
                                                    Log.d("error_picture_background",e.toString());

                                                }

                                            }
                                        },0,0,null,new Response.ErrorListener() {
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d("error_response", error.toString());
                                    }
                                });
                                imageRequest.setTag(REQUEST_TAG);
                                mQueue.add(imageRequest);

//                            }

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
        jsonRequestFarmer.setTag(REQUEST_TAG);
        mQueue.add(jsonRequestFarmer);


//
//        url="http://104.155.213.80/insantani/public/api/farmer/"+item.get("name");
//        Log.d("url", url);
//        final ImageRequest imageRequest= new ImageRequest(url,
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap bitmap) {
//                        try{
//                            Log.d("bitmap", bitmap.toString());
//
//                            Bitmap photo = roundedImageView.getCroppedBitmap(bitmap, 150);
//
//                            profileP.setImageBitmap(photo);
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


        LinearLayout linearLayoutPhone= (LinearLayout)findViewById(R.id.linearLayoutPhoneNumber);
        LinearLayout linearLayoutAddress= (LinearLayout)findViewById(R.id.linearLayoutAddress);
//        final Intent in = new Intent(Intent.ACTION_CALL,Uri.parse(phone.getText().toString()));
//        linearLayoutPhone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("phone",phone.getText().toString());
//                try {
//
//
//
//                    startActivity(in);
//                }catch(Exception e){
//                    Snackbar snackbar = Snackbar.make(relativeLayout, e.toString(), Snackbar.LENGTH_LONG);
//                    snackbar.setActionTextColor(Color.WHITE);
//
//                    View snackbarView = snackbar.getView();
//                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    textView.setTextColor(Color.WHITE);
//
//                    snackbar.show();
//                }
//
//            }
//        });






        mRecyclerView = (RecyclerView) findViewById(R.id.farmerProducts);
        mRecyclerView.setHasFixedSize(true);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        mItems = new ArrayList<Product>();
//        mQueue= CustomVolleyRequestQueue.getInstance(this).getRequestQueue();
        url="http://104.155.213.80/insantani/public/api/products/farmer/"+item.getString("name");
        final CustomJSONObjectRequest jsonRequestProduct= new CustomJSONObjectRequest(Request.Method.GET,
                url,new JSONObject(),
                new Response.Listener<JSONObject>(){
                    //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                    @Override
                    public void onResponse(JSONObject response){
                        try {
//                            JSONObject resultProduct=response.getJSONObject("result");
//                        if(result.getString("next_page_url"))

                            JSONArray dataProduct=response.getJSONArray("result");
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
                                                    Log.d("bitmap", bitmap.toString());
                                                    Product product = new Product();
                                                    product.setFarmerName(dataDetailProduct.getString("farmer_username"));
                                                    product.setDescription(dataDetailProduct.getString("prod_desc"));
                                                    product.setName(dataDetailProduct.getString("product_name"));
                                                    product.setPrice(dataDetailProduct.getInt("prod_price"));
                                                    product.setStock(dataDetailProduct.getInt("stock_num"));
                                                    product.setId(dataDetailProduct.getInt("id"));
                                                    product.setUom(dataDetailProduct.getString("uom"));
                                                    product.setUrl(dataDetailProduct.getString("product_picture_url"));
                                                    product.setThumbnail(bitmap);
//
                                                    mItems.add(product);

                                                    mAdapter = new ProductAdapter(mItems,context);
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

        // button for subscription
        final Button subscribe = (Button) findViewById(R.id.farmerFollowButton);

        if(checkToken && checkRefreshToken && user_id && tokenType){
            url = "http://104.155.213.80/insantani/public/api/follow/following?user_id="+pref.getString("user_id",null);
            final StringRequest stringRequestFollowing = new StringRequest(Request.Method.GET,
                    url, new Response.Listener<String>() {
                //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                @Override
                public void onResponse(String response) {
                    Log.d("response_following", response.toString());
                    try {
                        Log.d("response_following", response.toString());
                        JSONObject jsonObject = new JSONObject(response.toString());
                        JSONArray jsonArray=jsonObject.getJSONArray("data");
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject followingDetail= (JSONObject) jsonArray.get(i);
                            if(followingDetail.getString("farmer_username").equals(item.getString("name"))){
                                Log.d("name", item.getString("name"));
//                                Log.d("farmer_username",)
                                subscribe.setText("FOLLOWING");
                                subscribe.setBackgroundColor(getResources().getColor(R.color.insantaniButtonSilver));
                            }

//                            else {
//                                subscribe.setText("FOLLOW");
//                            }
                        }





                    } catch (Exception e) {
                        Log.d("JSON_error_following", e.toString());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.d("error_response_following", error.toString());
//                    if(error.toString().equals("com.android.volley.AuthFailureError")) {
//                        RefreshTokenManager refreshToken = new RefreshTokenManager(context);
//                        refreshToken.login();
//                    }else {


                    Snackbar snackbar = Snackbar.make(relativeLayout, error.toString(), Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.WHITE);

                    View snackbarView = snackbar.getView();
                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);

                    snackbar.show();
//                        Toast toast = Toast.makeText(context, error.toString(), Snackbar.LENGTH_LONG);
//                        toast.show();
//                    }
                }
            }) {

                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<String, String>();
                    // the POST parameters:
                    String auth = "Bearer " + pref.getString("access_token", null);
                    Log.d("Auth_follow", auth);
                    headers.put("Authorization", auth);
                    return headers;
                };


            };


            stringRequestFollowing.setTag(REQUEST_TAG);
            mQueue.add(stringRequestFollowing);



        }else{
            subscribe.setText("Follow");
            subscribe.setBackgroundColor(R.color.isantaniGreen);
            Log.d("status", "not login");
//            Toast toast = Toast.makeText(context, "Login First", Snackbar.LENGTH_LONG);
//            toast.show();
//            Snackbar snackbar = Snackbar.make(relativeLayout, "Login First", Snackbar.LENGTH_LONG);
//            snackbar.setActionTextColor(Color.WHITE);
//
//            View snackbarView = snackbar.getView();
//            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//            textView.setTextColor(Color.WHITE);
//
//            snackbar.show();

        }


        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkToken && checkRefreshToken && user_id && tokenType){
                    url = "http://104.155.213.80/insantani/public/api/follow";
                    final StringRequest stringRequestFollow = new StringRequest(Request.Method.POST,
                            url, new Response.Listener<String>() {
                        //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                        @Override
                        public void onResponse(String response) {
                            Log.d("response_follow", response.toString());
                            try {
                                Log.d("response_follow", response.toString());
                                JSONObject jsonObject = new JSONObject(response.toString());

                                Log.d("message_follow", jsonObject.getString("message"));
                                String message=jsonObject.getString("message");
                                if(message.equals("Follow")) {
                                    subscribe.setText("Following");
                                    subscribe.setBackgroundColor(getResources().getColor(R.color.insantaniButtonSilver));
                                } else if(message.equals("Unfollow")) {
                                    subscribe.setText("Follow");
                                    subscribe.setBackgroundColor(getResources().getColor(R.color.isantaniGreen));

                                }



                            } catch (Exception e) {
                                Log.d("JSON_error_follow", e.toString());
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.d("error_response_follow", error.toString());
                            if(error.toString().equals("com.android.volley.AuthFailureError")) {
//                                RefreshTokenManager refreshToken = new RefreshTokenManager(context);
//                                refreshToken.login();
                            }else {

                                subscribe.setText("Follow");
                                subscribe.setBackgroundColor(getResources().getColor(R.color.isantaniGreen));
                                Snackbar snackbar = Snackbar.make(relativeLayout, error.toString(), Snackbar.LENGTH_LONG);
                                snackbar.setActionTextColor(Color.WHITE);

                                View snackbarView = snackbar.getView();
                                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                textView.setTextColor(Color.WHITE);

                                snackbar.show();
//                                Toast toast = Toast.makeText(context, error.toString(), Snackbar.LENGTH_LONG);
//                                toast.show();
                            }
                        }
                    }) {

                        @Override
                        public Map<String, String> getHeaders() {
                            Map<String, String> headers = new HashMap<String, String>();
                            // the POST parameters:
                            String auth = "Bearer " + pref.getString("access_token", null);
                            Log.d("Auth_follow", auth);
                            headers.put("Authorization", auth);
                            return headers;
                        };

                        protected Map getParams() {
                            Map params = new HashMap();
                            // the POST parameters:

                            String user_id = pref.getString("user_id", null);
                            params.put("user_id", user_id);
                            params.put("farmer_username",item.getString("name"));

                            return params;
                        };


                    };


                    stringRequestFollow.setTag(REQUEST_TAG);
                    mQueue.add(stringRequestFollow);



                }else{

                    subscribe.setText("Follow");
                    subscribe.setBackgroundColor(getResources().getColor(R.color.isantaniGreen));
                    Snackbar snackbar = Snackbar.make(relativeLayout, "Login First", Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.WHITE);

                    View snackbarView = snackbar.getView();
                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);

                    snackbar.show();

//                    Toast toast = Toast.makeText(context, "Login First", Snackbar.LENGTH_LONG);
//                    toast.show();

                }

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_product, menu);
        TextView name = (TextView) findViewById(R.id.farmerProfileName);
        setTitle(name.getText());

        return true;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.mainActivity.onResume();
    }

}

