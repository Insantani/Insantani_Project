package com.williamhenry.insantani;

/**
 * Created by william on 10/17/2015.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

//import android.app.Activity;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<Product> mItems;
    private Context context;
    public ProductAdapter(ArrayList<Product>mItems,Context context) {
        super();
        this.context=context;
        this.mItems=mItems;



    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_item_page, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final Product nature = mItems.get(i);
        Log.d("mitems",nature.toString());
        Log.d("i", Integer.toString(i));
        viewHolder.tvspecies.setText(nature.getName());
        viewHolder.grid_price.setText("Rp "+nature.getPrice() + " / "+ nature.getUom());
        viewHolder.imgThumbnail.setImageBitmap(nature.getThumbnail());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
//                Intent intent= new Intent(getAc,ProductActivity.class);
//                startActivtiy(intent);
                Log.d("i", Integer.toString(i));
                Intent intent= new Intent(context,ProductActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("title", nature.getName());
//                bundle.putInt("thumbnail",nature.getThumbnail());
//                ByteArrayOutputStream baos=new  ByteArrayOutputStream();
//                nature.getThumbnail().compress(Bitmap.CompressFormat.PNG, 100, baos);

//                byte [] b=baos.toByteArray();
//                String image= Base64.encodeToString(b, Base64.DEFAULT);
//                bundle.putString("thumbnail",image);

                bundle.putString("url",nature.getUrl());
                bundle.putString("description",nature.getDescription());
                bundle.putString("fname",nature.getFarmerName());
                bundle.putInt("price", nature.getPrice());
                bundle.putInt("stock", nature.getStock());
                bundle.putInt("id",nature.getId());
                bundle.putString("uom",nature.getUom());
                intent.putExtra("nature",bundle);
//                intent.putExtra("nature_thumbnail",nature.getThumbnail());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvspecies;
        public TextView grid_price;
//        public final Context context;

        public ViewHolder(View itemView) {
            super(itemView);
//            itemView.setOnClickListener(this);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvspecies = (TextView)itemView.findViewById(R.id.tv_species);
            grid_price= (TextView) itemView.findViewById(R.id.grid_price);

        }
//        @Override
//        public void onClick(View v) {
//
//            Intent intent=new Intent(getActivity(),ProductActivity.class);
//            startActivity(intent);
//
////            context.startActivity(new Intent(context,ProductActivity.class));
//        }
    }
}