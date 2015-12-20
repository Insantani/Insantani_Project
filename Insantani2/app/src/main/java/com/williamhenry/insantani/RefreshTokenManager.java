package com.williamhenry.insantani;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
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
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by william on 12/7/2015.
 */
public class RefreshTokenManager extends Service {

    private Context context;
    public static final String REQUEST_TAG = "RefreshTokenManager";
    private RequestQueue mQueue;
    private String url;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private boolean checkToken;
    private boolean tokenType;
    private boolean checkRefreshToken;
    private boolean user_id;

    private Timer timer= new Timer();

    public RefreshTokenManager(){
//        this.context=context;
//        this.pref= this.context.getSharedPreferences("MyPref",Context.MODE_PRIVATE);
//        this.editor= this.pref.edit();
//        this.checkToken= this.pref.contains("access_token");
//        this.checkRefreshToken= this.pref.contains("refresh_token");
//        this.tokenType= this.pref.contains("token_type");
//        this.user_id=this.pref.contains("user_id");
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        pref=getSharedPreferences("MyPref",Context.MODE_PRIVATE);
        editor=pref.edit();
        checkToken=pref.contains("access_token");
        checkRefreshToken=pref.contains("refresh_token");
        tokenType=pref.contains("token_type");
        user_id=pref.contains("user_id");
        context=this;
        login();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                login();   //Your code here
            }
        }, 0, 40 * 60 * 1000);//5 Minutes


    }

    @Override
    public void onStart(Intent intent, int startId) {
//        handleCommand(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        handleCommand(intent);
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_REDELIVER_INTENT;
    }

//    @Override
//    public void onDestroy()
//    {
//        super.onDestroy();
//    }





    public void login(){

        if(checkToken && checkRefreshToken && tokenType && user_id){
                mQueue= CustomVolleyRequestQueue.getInstance(context).getRequestQueue();
                url="http://104.155.213.80/insantani/public/oauth/token";
                final StringRequest stringRequestRefresh= new StringRequest(Request.Method.POST,
                        url, new Response.Listener<String>(){
                    //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                    @Override
                    public void onResponse(String response){
                        Log.d("login", response.toString());
                        try {
                            Log.d("response_login_refresh", response.toString());
                            JSONObject jsonObject= new JSONObject(response.toString());
                            editor.putString("access_token",jsonObject.getString("access_token"));
                            editor.putString("token_type",jsonObject.getString("token_type"));
                            editor.putString("refresh_token", jsonObject.getString("refresh_token"));
//                            accessToken=jsonObject.getString("access_token");
                            editor.commit();
                            Log.d("access_token", jsonObject.getString("access_token"));
                            Log.d("token_type", jsonObject.getString("token_type"));
                            Log.d("refresh_token",jsonObject.getString("refresh_token"));

                        } catch(Exception e){
                            Log.d("JSON_error_token_refresh",e.toString());
                        }
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){

                        Log.d("error_response_token_refresh",error.toString());
//                        Snackbar snackbar = Snackbar.make(relativeLayout, "Login Failed", Snackbar.LENGTH_LONG);
//                        snackbar.setActionTextColor(Color.WHITE);
//
//                        View snackbarView= snackbar.getView();
//                        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                        textView.setTextColor(Color.WHITE);
//
//                        snackbar.show();
                        Toast toast = Toast.makeText(context, error.toString(), Snackbar.LENGTH_LONG);
                        toast.show();
                    }
                }){


                    protected Map<String, String> getParams(){
                        Map<String, String> params = new HashMap<String,String>();
                        // the POST parameters:
                        params.put("grant_type", "refresh_token");
//                        params.put("email", email.getText().toString());
                        Log.d("refresh_token_input", pref.getString("refresh_token",null));
                        params.put("refresh_token", pref.getString("refresh_token",null));
                        params.put("scope","read");
                        params.put("client_id", "testclient");
                        params.put("client_secret","testpass");
                        return params;
                    };


                };


                stringRequestRefresh.setTag(REQUEST_TAG);
                mQueue.add(stringRequestRefresh);
        }
    }
}
