package com.williamhenry.insantani;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

/**
 * Created by agungwy on 24-Nov-15.
 */
public class FarmerProfileActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private String url;
    public static final String REQUEST_TAG = "HomeFragment";
    private RequestQueue mQueue;
    private ArrayList<Product> mItems;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_profile);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        Bundle extras = getIntent().getExtras();
        final Bundle item = (Bundle) extras.get("farmer");

        final Farmer farmer = new Farmer();
        farmer.setName(item.getString("name"));
        farmer.setAddress("Depok");
        farmer.setEmail("Kodok@gmail.com");
        farmer.setPhoneNumber("081316477140");
        ArrayList<Integer> photos = new ArrayList<Integer>();
        photos.add(R.mipmap.carrot);
        photos.add(R.mipmap.cabe);
        photos.add(R.mipmap.col);
        photos.add(R.mipmap.ic_email);
        farmer.setPhoto(photos);
        farmer.setPhotoProfile(R.drawable.sample_0);
        farmer.setPhotoBackground(R.color.insantaniGreen);
        farmer.setProducts(null);

        TextView name = (TextView) findViewById(R.id.farmerProfileName);
        name.setText(farmer.getName());

        TextView address = (TextView) findViewById(R.id.farmerAddress);
        address.setText(farmer.getAddress());

        TextView email = (TextView) findViewById(R.id.farmerEmail);
        email.setText(farmer.getEmail());

        TextView phone = (TextView) findViewById(R.id.farmerPhoneNumber);
        phone.setText(farmer.getPhoneNumber());

        final ImageView profileP = (ImageView) findViewById(R.id.farmerProfilePicture);
        final int photo = farmer.getPhotoProfile();
        profileP.setImageResource(photo);

        profileP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PhotoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("image", photo);

                intent.putExtra("photo", bundle);
                context.startActivity(intent);

            }
        });

        LinearLayout pictureB = (LinearLayout) findViewById(R.id.farmerPictureBackground);
        pictureB.setBackgroundResource(R.mipmap.kentang);


        mRecyclerView = (RecyclerView)findViewById(R.id.farmerProfilePhotos);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new FarmerPhotosAdapter(photos,this);
        mRecyclerView.setAdapter(mAdapter);
        
        

        mRecyclerView = (RecyclerView) findViewById(R.id.farmerProducts);
        mRecyclerView.setHasFixedSize(true);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

       mItems = new ArrayList<Product>();
        mQueue= CustomVolleyRequestQueue.getInstance(this).getRequestQueue();
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_farmer_profile, menu);
        setTitle("");

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


        return super.onOptionsItemSelected(item);
    }

}

