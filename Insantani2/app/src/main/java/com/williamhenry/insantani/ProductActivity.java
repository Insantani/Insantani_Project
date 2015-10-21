package com.williamhenry.insantani;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.app.Activity;
import android.util.Log;
import android.support.v7.app.ActionBarActivity;

public class ProductActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Bundle extras= getIntent().getExtras();
        Bundle item=(Bundle)extras.get("nature");
        Log.d("item_name",item.getString("title"));
        Log.d("thumbnail",Integer.toString(item.getInt("thumbnail")));

        ImageView image= (ImageView)findViewById(R.id.img_thumbnail1);
        int x=item.getInt("thumbnail");
        Drawable draw=getResources().getDrawable(x);
        image.setImageDrawable(draw);


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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
