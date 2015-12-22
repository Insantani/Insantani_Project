package com.williamhenry.insantani;

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

/**
 * Created by agungwy on 10/20/2015.
 */
public class FarmerActivityAdapter extends RecyclerView.Adapter<FarmerActivityAdapter.ViewHolder> {

    private Context context;
    private  ArrayList<FarmerActivity> feeds;


    // Provide a suitable constructor (depends on the kind of dataset)
    public FarmerActivityAdapter(ArrayList<FarmerActivity> feeds, Context context) {

        this.context = context;
        this.feeds = feeds;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public FarmerActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.farmer_activity, parent, false);
        // set the view's size, margins, paddings and layout parameter



        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView name = (TextView) holder.view.findViewById(R.id.farmerActivityName);
        TextView detail = (TextView) holder.view.findViewById(R.id.farmerActivityDetail);
        ImageView imageView = (ImageView) holder.view.findViewById(R.id.farmerActivityPicture);



        name.setText(feeds.get(position).getName());
        detail.setText(feeds.get(position).getDetail());


        imageView.setImageBitmap(feeds.get(position).getPhoto());
//        Log.d("farmer_activity_trace",feeds.get(position).getPhoto().toString());





    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return feeds.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
        }
    }
}
