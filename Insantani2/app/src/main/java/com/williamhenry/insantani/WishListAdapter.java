package com.williamhenry.insantani;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {
    private ArrayList<Wish> wishes;


    // Provide a suitable constructor (depends on the kind of dataset)
    public WishListAdapter(ArrayList<Wish> wish) {
        this.wishes = wish;
        Log.d("asda", String.valueOf(wish.get(0).getProductName()));

    }

    // Create new views (invoked by the layout manager)
    @Override
    public WishListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wishlist_listview_layout, parent, false);
        // set the view's size, margins, paddings and layout parameter

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView productName = (TextView) holder.view.findViewById(R.id.product_name_text_view);
        TextView farmer = (TextView) holder.view.findViewById(R.id.farmername_text_view);
        TextView price = (TextView) holder.view.findViewById(R.id.price_text_view);
        ImageView imageView = (ImageView) holder.view.findViewById(R.id.image_wish);

        TextView deleteIcon = (TextView) holder.view.findViewById(R.id.delete_icon);

        productName.setText(wishes.get(position).getProductName());
        farmer.setText(wishes.get(position).getFarmer());
        price.setText(String.valueOf(wishes.get(position).getPrice()));

        imageView.setImageResource(wishes.get(position).getImage());

        deleteIcon.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Log.d("DELETE", "DELETE THIS ONE");
            }
        });



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return wishes.size();
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

