package com.williamhenry.insantani;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {
    private ArrayList<Wish> wishes;
    private String url;
    private RequestQueue mQueue;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public static final String REQUEST_TAG = "WishListFragment";
    private RelativeLayout relativeLayout;
    private Context context;
    private boolean checkToken;
    private boolean checkRefreshToken;
    private boolean user_id;
    private boolean tokenType;
//    private WishListFragment wishListFragment;


    // Provide a suitable constructor (depends on the kind of dataset)
    public WishListAdapter(ArrayList<Wish> wish, Context context) {
        this.wishes = wish;
        Log.d("asda", String.valueOf(wish.get(0).getProductName()));
        this.context=context;
//        this.wishListFragment= wishListFragment;

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
        TextView uom = (TextView)holder.view.findViewById(R.id.UnitOfMeasurement);
        ImageView imageView = (ImageView) holder.view.findViewById(R.id.image_wish);

        TextView deleteIcon = (TextView) holder.view.findViewById(R.id.delete_icon);

        productName.setText(wishes.get(position).getProductName());
        farmer.setText(wishes.get(position).getFarmer());
        price.setText(String.valueOf(wishes.get(position).getPrice()));
        uom.setText(wishes.get(position).getUom());

        imageView.setImageBitmap(wishes.get(position).getImage());
        pref=context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        editor=pref.edit();

        checkToken=pref.contains("access_token");
        checkRefreshToken=pref.contains("refresh_token");
        tokenType=pref.contains("token_type");
        user_id=pref.contains("user_id");
        relativeLayout= (RelativeLayout) holder.view.findViewById(R.id.relativeLayoutWish);

        deleteIcon.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Log.d("DELETE", "DELETE THIS ONE");
                if(checkToken && checkRefreshToken && tokenType && user_id){

                    mQueue= CustomVolleyRequestQueue.getInstance(context).getRequestQueue();
                    url="http://104.155.213.80/insantani/public/api/wish/delete?user_id="+pref.getString("user_id",null)
                            +"&product_id="+wishes.get(position).getId();
                    final StringRequest stringRequestDeleteWish= new StringRequest(Request.Method.DELETE,
                            url, new Response.Listener<String>(){
                        //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                        @Override
                        public void onResponse(String response){
                            Log.d("delete_wish", response.toString());
                            try {
                                Log.d("response_delete_wish", response.toString());
                                JSONObject jsonObject= new JSONObject(response.toString());
                                String message=jsonObject.getString("message");
                                if (message.equals("Success")){
                                        wishes.remove(position);
                                        notifyItemRemoved(position);
                                        notifyItemRangeChanged(position, wishes.size());
//                                        wishListFragment.refresh();



                                }


                            } catch(Exception e){
                                Log.d("JSON_error_delete_wish",e.toString());
                            }
                        }
                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){

                            Log.d("error_response_delete_wish",error.toString());
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
                            Log.d("auth_delete_wish", auth);
                            headers.put("Authorization", auth);
                            return headers;
                        };


                    };


                    stringRequestDeleteWish.setTag(REQUEST_TAG);
                    mQueue.add(stringRequestDeleteWish);



                }
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
