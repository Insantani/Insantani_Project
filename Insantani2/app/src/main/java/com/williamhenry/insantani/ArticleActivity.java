package com.williamhenry.insantani;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        Bundle item = (Bundle) extras.get("article");
        Log.d("article", item.getString("title"));
        Log.d("author", item.getString("author"));
        Log.d("desc", item.getString("desc"));
        Log.d("image", item.getString("image"));

        ImageView image = (ImageView) findViewById(R.id.imageViewArticle);
        TextView title = (TextView) findViewById(R.id.titleArticle);
        TextView author = (TextView) findViewById(R.id.authorArticle);
        TextView desc = (TextView) findViewById(R.id.descriptionArticle);

        byte [] encodeByte= Base64.decode(item.getString("image"), Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

//        Bitmap x = (Bitmap)item.get("image");
//        Drawable draw = getResources().getDrawable(x);
        image.setImageBitmap(bitmap);
        title.setText(item.getString("title"));
        author.setText(item.getString("author"));
        desc.setText(item.getString("desc"));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_article, menu);
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
