package com.williamhenry.insantani;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductActivity extends ActionBarActivity {
    private Context context;
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

//        Button add_to_shopping_cart = (Button) findViewById(R.id.shoppingcart_button);
//        add_to_shopping_cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),)
//            }
//        };


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
