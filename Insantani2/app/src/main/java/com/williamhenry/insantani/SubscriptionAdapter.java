package com.williamhenry.insantani;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.ViewHolder> {
    private ArrayList<Subscription> subscriptions;
    private Context context;
    private boolean checkToken;
    private boolean checkRefreshToken;
    private boolean user_id;
    private boolean tokenType;
    private String url;
    private RequestQueue mQueue;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public static final String REQUEST_TAG = "SubscriptionAdapter";
    private RelativeLayout relativeLayout;


    // Provide a suitable constructor (depends on the kind of dataset)
    public SubscriptionAdapter(ArrayList<Subscription> subscriptions, Context context) {
        this.subscriptions = subscriptions;
        this.context=context;

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
        LinearLayout linearLayout= (LinearLayout)holder.view.findViewById(R.id.linearLayoutSubscription);
        final ImageView following = (ImageView) holder.view.findViewById(R.id.following);
        ImageView imageView = (ImageView) holder.view.findViewById(R.id.image_farmer);
        pref=context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        editor=pref.edit();

        checkToken=pref.contains("access_token");
        checkRefreshToken=pref.contains("refresh_token");
        tokenType=pref.contains("token_type");
        user_id=pref.contains("user_id");
        relativeLayout= (RelativeLayout) holder.view.findViewById(R.id.relativeLayoutSubscriptionListView);




        farmername.setText(subscriptions.get(position).getFarmerName());

        int temp;
        if (subscriptions.get(position).getFollowing()){
            temp = R.mipmap.following;
        }
        else {
            temp = R.mipmap.follow;
        }

        following.setImageResource(temp);

        imageView.setImageBitmap(subscriptions.get(position).getImage());

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = subscriptions.get(position).getFarmerName();
                boolean status=subscriptions.get(position).getFollowing();
                Intent intent = new Intent(context, FarmerProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", username);
                intent.putExtra("farmer", bundle);
                context.startActivity(intent);

            }

        });

        following.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("FOLLOW", "FOLLOW THIS ONE");
                if(checkToken && checkRefreshToken && tokenType && user_id){

                    mQueue= CustomVolleyRequestQueue.getInstance(context).getRequestQueue();
                    url="http://104.155.213.80/insantani/public/api/follow?user_id="+pref.getString("user_id",null);
                    final StringRequest stringRequestFollow= new StringRequest(Request.Method.POST,
                            url, new Response.Listener<String>(){
                        //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                        @Override
                        public void onResponse(String response){
                            Log.d("follow", response.toString());
                            try {
                                Log.d("response_follow", response.toString());
                                JSONObject jsonObject= new JSONObject(response.toString());
                                String message=jsonObject.getString("message");
                                if (message.equals("Follow")){
                                    following.setImageResource(R.mipmap.following);
                                    subscriptions.get(position).setFollowing(true);
//                                    wishes.remove(position);
//                                    notifyItemRemoved(position);
//                                    notifyItemRangeChanged(position, wishes.size());
//                                        wishListFragment.refresh();
                                }else if(message.equals("Unfollow")){
                                    following.setImageResource(R.mipmap.follow);
                                    subscriptions.get(position).setFollowing(true);


                                }


                            } catch(Exception e){
                                Log.d("JSON_error_follow",e.toString());
                            }
                        }
                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){

                            Log.d("error_response_follow",error.toString());
                            if(error.toString().equals("com.android.volley.AuthFailureError")) {
                                RefreshTokenManager refreshToken = new RefreshTokenManager(context);
                                refreshToken.login();
                            }else {

                                Snackbar snackbar = Snackbar.make(relativeLayout, error.toString(), Snackbar.LENGTH_LONG);
                                snackbar.setActionTextColor(Color.WHITE);

                                View snackbarView = snackbar.getView();
                                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                textView.setTextColor(Color.WHITE);

                                snackbar.show();
                            }
                        }
                    }){
                        @Override
                        public Map<String, String> getHeaders() {
                            Map<String, String> headers = new HashMap<String, String>();
                            String auth = "Bearer " + pref.getString("access_token", null);
                            Log.d("auth_delete_follow", auth);
                            headers.put("Authorization", auth);
                            return headers;
                        };

                        protected Map getParams() {
                            Map params = new HashMap();
                            // the POST parameters:

                            params.put("farmer_username", subscriptions.get(position).getFarmerName());
                            return params;
                        };



                    };


                    stringRequestFollow.setTag(REQUEST_TAG);
                    mQueue.add(stringRequestFollow);



                }


            }

        });




//        });




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