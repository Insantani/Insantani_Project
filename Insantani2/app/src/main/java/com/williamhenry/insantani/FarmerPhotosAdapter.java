package com.williamhenry.insantani;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by agungwy on 26-Nov-15.
 */
public class FarmerPhotosAdapter extends RecyclerView.Adapter<FarmerPhotosAdapter.ViewHolder> {

    ArrayList<Photo> mItems;
    private Context context;
    //    private int id;
    public FarmerPhotosAdapter(ArrayList<Photo>mItems,Context context) {
        super();
        this.context=context;
        this.mItems=mItems;



    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.farmer_photo_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        final Photo nature = mItems.get(i);
//        if(id!=mItems.get(i).getId()) {
        viewHolder.imgThumbnail.setImageBitmap(nature.getImage());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PhotoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("image", nature.getUrl());

                intent.putExtra("photo1", bundle);
                context.startActivity(intent);

            }
        });

//        }


    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);

            imgThumbnail = (ImageView)itemView.findViewById(R.id.farmer_photo);


        }

    }
}
