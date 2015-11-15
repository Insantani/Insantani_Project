package com.williamhenry.insantani;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductActivity extends ActionBarActivity {
    private Context context;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    private String url;
    private RequestQueue mQueue;
//    private ArrayList<Article> articles;
//    private ArrayList<Product> mItems;
    public static final String REQUEST_TAG = "RelatedItems";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycle_view_relative_item);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);


        Bundle extras= getIntent().getExtras();
        Bundle item=(Bundle)extras.get("nature");
//        final Context context=getApplicationContext();
        final ArrayList<Product> relatedItems = new ArrayList<Product>();
        mQueue= CustomVolleyRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
        url="http://104.155.213.80/insantani/public/api/farmer/"+item.getString("fname")+"/products";
        Log.d("url_farmer",url);
        final CustomJSONObjectRequest jsonRequest= new CustomJSONObjectRequest(Request.Method.GET,
                url,new JSONObject(),
                new Response.Listener<JSONObject>(){
                    //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            JSONArray result=response.getJSONArray("result");
//                        if(result.getString("next_page_url"))

//                            JSONArray data=result.getJSONArray("data");
//                            Log.d("response1", data.get(0).toString());
                            for (int i=0;i<result.length();i++){
                                final JSONObject dataDetail=(JSONObject)result.get(i);
                                url="http://104.155.213.80/insantani/public/"+dataDetail.getString("product_picture_url");
                                Log.d("url",url);
                                final ImageRequest imageRequest= new ImageRequest(url,
                                        new Response.Listener<Bitmap>() {
                                            @Override
                                            public void onResponse(Bitmap bitmap) {
                                                try{
                                                    Log.d("bitmap",bitmap.toString());
//                                                    Article article = new Article(dataDetail.getString("author"),
//                                                            dataDetail.getString("title"), dataDetail.getString("content"), bitmap);
////
//                                                    articles.add(article);
//                                                    mAdapter = new MyAdapter(articles, getContext());
//                                                    mRecyclerView.setAdapter(mAdapter);
                                                    Product product = new Product();
                                                    product.setFarmerName(dataDetail.getString("farmer_username"));
                                                    product.setDescription(dataDetail.getString("prod_desc"));
                                                    product.setName(dataDetail.getString("product_name"));
                                                    product.setPrice(dataDetail.getInt("prod_price"));
                                                    product.setStock(dataDetail.getInt("stock_num"));
                                                    product.setThumbnail(bitmap);
                                                    relatedItems.add(product);

                                                    mAdapter = new RelativeItemAdapter(relatedItems,getApplicationContext());
                                                    mRecyclerView.setAdapter(mAdapter);


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

        Log.d("item_name",item.getString("title"));
        Log.d("thumbnail",item.getString("thumbnail"));

        ImageView image= (ImageView)findViewById(R.id.img_thumbnail1);
//        int x=item.getInt("thumbnail");
//        Drawable draw=getResources().getDrawable(x);
        byte [] encodeByte= Base64.decode(item.getString("thumbnail"), Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);


        image.setImageBitmap(bitmap);

        TextView text1 = (TextView)findViewById(R.id.productname);
        text1.setText(item.getString("title"));

        TextView text2 = (TextView)findViewById(R.id.description);
        text2.setText(item.getString("description"));

        TextView text3 = (TextView)findViewById(R.id.farmername);
        text3.setText("By: " + item.getString("fname"));

        TextView text4 = (TextView)findViewById(R.id.price);
        text4.setText("$" + Integer.toString(item.getInt("price")));

        TextView text5 = (TextView)findViewById(R.id.stock);
        text5.setText("Stock: " + Integer.toString(item.getInt("stock")));




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
