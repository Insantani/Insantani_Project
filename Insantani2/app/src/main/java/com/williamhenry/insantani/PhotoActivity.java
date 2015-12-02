package com.williamhenry.insantani;

import android.app.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.ArrayList;


/**
 * Created by agungwy on 27-Nov-15.
 */
public class PhotoActivity extends Activity {

    private String url;
    public static final String REQUEST_TAG = "FarmerPhotoProfile";
    private RequestQueue mQueue;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_activity);
        final ImageView photo = (ImageView) findViewById(R.id.photo);
        Bundle extras = getIntent().getExtras();

        if (extras != null){
            final Bundle item = (Bundle) extras.get("photo");
            photo.setImageResource(item.getInt("image"));
        }
        else{
            mQueue= CustomVolleyRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
            url="http://104.155.213.80/insantani/public/api/feed/article/1/picture";
            Log.d("url", url);
            final ImageRequest imageRequest= new ImageRequest(url,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            try{
                                Log.d("bitmap",bitmap.toString());
                                photo.setImageBitmap(bitmap);

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
        }






        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });



    }


}
