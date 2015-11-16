package com.williamhenry.insantani;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductActivity extends ActionBarActivity {
    private Context context;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        context = this;
        mRecyclerView = (RecyclerView)findViewById(R.id.recycle_view_relative_item);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RelativeItemAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        Bundle extras= getIntent().getExtras();
        final Bundle item=(Bundle)extras.get("nature");
        Log.d("item_name",item.getString("title"));
        Log.d("thumbnail",Integer.toString(item.getInt("thumbnail")));

        ImageView image= (ImageView)findViewById(R.id.img_thumbnail1);
        int x=item.getInt("thumbnail");
        Drawable draw=getResources().getDrawable(x);
        image.setImageDrawable(draw);

        final TextView text1 = (TextView)findViewById(R.id.productname);
        text1.setText(item.getString("title"));

        TextView text2 = (TextView)findViewById(R.id.description);
        text2.setText(item.getString("description"));

        TextView text3 = (TextView)findViewById(R.id.farmername);
        text3.setText("By: " + item.getString("fname"));

        TextView text4 = (TextView)findViewById(R.id.price);
        text4.setText("$" + Integer.toString(item.getInt("price")));

        TextView text5 = (TextView)findViewById(R.id.stock);
        text5.setText("Stock: " + Integer.toString(item.getInt("stock")));

        Button shopping = (Button) findViewById(R.id.shoppingcart_button);
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();

                AlertDialog alertDialog = new AlertDialog.Builder(ProductActivity.this).create();
                alertDialog.setView(inflater.inflate(R.layout.confirmation_add_to_shopping_cart_dialog, null));
                alertDialog.setTitle("Quantity");

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Confirm",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context, CartFragment.class);
                                Bundle bundle = new Bundle();
                                EditText text = (EditText)((Dialog) dialog).findViewById(R.id.confirmation_quantity);
                                int quantity = Integer.parseInt(text.getText().toString());
                                bundle.putString("ProductName", item.getString("title"));
                                bundle.putString("FarmerName", item.getString("fname"));
                                bundle.putFloat("price", item.getInt("price"));
                                bundle.putInt("image", item.getInt("thumbnail"));
                                bundle.putInt("qty", quantity);
                                intent.putExtra("Cart", bundle);

                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                Button plus = (Button) alertDialog.findViewById(R.id.increse_button);
                Button min = (Button) alertDialog.findViewById(R.id.decrese_button);
                EditText qty = (EditText) alertDialog.findViewById(R.id.confirmation_quantity);
                final TextView text = (TextView) alertDialog.findViewById(R.id.confirmation_quantity);
                counter = Integer.parseInt(text.getText().toString());

                qty.addTextChangedListener(new TextWatcher() {

                   public void afterTextChanged(Editable s) {

                       // you can call or do what you want with your EditText here
                       if (!text.getText().toString().equals("")){

                           counter = Integer.parseInt(text.getText().toString());
                       }



                   }
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }
                });
                plus.setOnClickListener(new View.OnClickListener()
                                        {
                    public void onClick(View view){
                        counter += 1;
                        text.setText("" + counter);

                    }
                    }

                    );
                    min.setOnClickListener(new View.OnClickListener()

                       {

                           public void onClick(View view) {
                               if (counter <= 0) {
                                   text.setText("" + counter);
                               }
                               if (counter > 0) {
                                   counter -= 1;
                                   text.setText("" + counter);
                               }
                           }
                       }

                    );

                }
            });

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
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
