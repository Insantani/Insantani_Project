package com.williamhenry.insantani;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;


/**
 * Created by agungwy on 27-Nov-15.
 */
public class PhotoActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_activity);

        Bundle extras = getIntent().getExtras();
        final Bundle item = (Bundle) extras.get("photo");


        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setImageResource(item.getInt("image"));

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        getActionBar().hide();

    }


}
