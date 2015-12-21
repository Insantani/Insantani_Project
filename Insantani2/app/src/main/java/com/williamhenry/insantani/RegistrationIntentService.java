package com.williamhenry.insantani;

/**
 * Created by william on 11/26/2015.
 */
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};
    private SharedPreferences pref;
    private Editor editor;
    private boolean checkToken;
    private boolean checkRefreshToken;
    private boolean tokenType;
    private boolean user_id;
    private String url;
    private RequestQueue mQueue;
    public static final String REQUEST_TAG = "RegistrationIntentService";

    public RegistrationIntentService() {

        super(TAG);
//        this.pref= PreferenceManager.getDefaultSharedPreferences("MyPref",Context.MODE_PRIVATE);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        pref=getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        editor=pref.edit();
        checkToken= pref.contains("access_token");
        checkRefreshToken= pref.contains("refresh_token");
        tokenType= pref.contains("token_type");
        user_id=pref.contains("user_id");

        try {
            // [START register_for_gcm]
            // Initially this call goes out to the network to retrieve the token, subsequent calls
            // are local.
            // R.string.gcm_defaultSenderId (the Sender ID) is typically derived from google-services.json.
            // See https://developers.google.com/cloud-messaging/android/start for details on this file.
            // [START get_token]
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            // [END get_token]
            Log.i(TAG, "GCM Registration Token: " + token);

            // TODO: Implement this method to send any registration to your app's servers.
            sendRegistrationToServer(token);

            // Subscribe to topic channels
            subscribeTopics(token);

            // You should store a boolean that indicates whether the generated token has been
            // sent to your server. If the boolean is false, send the token to your server,
            // otherwise your server should have already received the token.
            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply();
            // [END register_for_gcm]
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.
            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false).apply();
        }
        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(QuickstartPreferences.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    /**
     * Persist registration to third-party servers.
     *
     * Modify this method to associate the user's GCM registration token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(final String token) {

        // Add custom implementation, as needed.
        if(checkToken && checkRefreshToken && tokenType && user_id){

            mQueue= CustomVolleyRequestQueue.getInstance(this).getRequestQueue();
            url="http://104.155.213.80/insantani/public/api/notify/register";
            final StringRequest stringRequestNotification= new StringRequest(Request.Method.POST,
                    url, new Response.Listener<String>(){
                //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                @Override
                public void onResponse(String response){
                    Log.d("notification_registration", response.toString());
                    try {
                        Log.d("response_notification_registration", response.toString());
                        JSONObject jsonObject= new JSONObject(response.toString());
                        String message= jsonObject.getString("message");
                        if(message.equals("OK")) {
                            editor.putString("notification_token", token);
                            editor.commit();
                            Log.d("notification_token", token);
                            Log.d("notification_register_message",message);
                        }else{
                            editor.putString("notification_token", token);
                            editor.commit();
                            Log.d("notification_token", token);
                            Log.d("notification_register_message", message);
                        }

                    } catch(Exception e){
                        Log.d("JSON_error_notification_register",e.toString());
                    }
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){

                    Log.d("error_response_notification_register",error.toString());

                }
            }){
                @Override
                public Map<String,String> getHeaders(){
                    Map<String,String> headers= new HashMap<String, String>();
                    String auth="Bearer "+pref.getString("access_token",null);
                    Log.d("auth_notification_register",auth);
                    headers.put("Authorization",auth);
                    return headers;
                };


                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<String,String>();
                    // the POST parameters:
                    params.put("user_id",pref.getString("user_id",null));
                    params.put("device_token",token);
                    return params;
                };


            };


            stringRequestNotification.setTag(REQUEST_TAG);
            mQueue.add(stringRequestNotification);











        }
    }

    /**
     * Subscribe to any GCM topics of interest, as defined by the TOPICS constant.
     *
     * @param token GCM token
     * @throws IOException if unable to reach the GCM PubSub service
     */
    // [START subscribe_topics]
    private void subscribeTopics(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS) {
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
    // [END subscribe_topics]

}
