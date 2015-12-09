package com.williamhenry.insantani;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.ViewHolder> {
    private ArrayList<Subscription> subscriptions;


    // Provide a suitable constructor (depends on the kind of dataset)
    public SubscriptionAdapter(ArrayList<Subscription> subscriptions) {
        this.subscriptions = subscriptions;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public SubscriptionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subscription_listview_layout, parent, false);
        // set the view's size, margins, paddings and layout parameter

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView farmername = (TextView) holder.view.findViewById(R.id.farmername);

        ImageView following = (ImageView) holder.view.findViewById(R.id.following);
        ImageView imageView = (ImageView) holder.view.findViewById(R.id.image_farmer);

        farmername.setText(subscriptions.get(position).getFarmerName());

        int temp;
        if (subscriptions.get(position).getFollowing()){
            temp = R.drawable.following;
        }
        else {
            temp = R.drawable.follow;
        }

        following.setImageResource(temp);

        imageView.setImageResource(subscriptions.get(position).getImage());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return subscriptions.size();
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

