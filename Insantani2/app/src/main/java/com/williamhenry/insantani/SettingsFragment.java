package com.williamhenry.insantani;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Button;
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
import java.util.Map;


public class SettingsFragment extends Fragment {
    private SharedPreferences pref;
    private Editor editor;
    private RelativeLayout relativeLayout;
    private String url;
    private RequestQueue mQueue;
    public static final String REQUEST_TAG = "SettingsFragement";


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getActionBar().show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        pref= this.getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final boolean checkToken= pref.contains("access_token");
        final boolean checkRefreshToken= pref.contains("refresh_token");
        final boolean tokenType= pref.contains("token_type");
        final boolean user_id=pref.contains("user_id");
        final boolean checkRegistrationToken=pref.contains("notification_token");
        Button logout= (Button) rootView.findViewById(R.id.logoutButton);
        Button editProfile=(Button) rootView.findViewById(R.id.editProfile);
        Button changePassword=(Button) rootView.findViewById(R.id.changePassword);
        relativeLayout=(RelativeLayout) rootView.findViewById(R.id.LogoutLayout);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("token", Boolean.toString(checkToken));
                if(!checkToken && !tokenType && !checkRefreshToken && !user_id && !checkRegistrationToken){
//                    logout.setEnabled(false);

                    Snackbar snackbar = Snackbar.make(relativeLayout, "Login First", Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.WHITE);

                    View snackbarView= snackbar.getView();
                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);

                    snackbar.show();
                }else{
                    editor=pref.edit();

                    mQueue= CustomVolleyRequestQueue.getInstance(getContext()).getRequestQueue();
//                    Log.d("notification_registration",pref.getString("notification_token",null));
                    url="http://104.155.213.80/insantani/public/api/notify/register?user_id="+pref.getString("user_id",null)
                            +"&device_token="+pref.getString("notification_token",null);
                    final StringRequest stringRequestDeleteNotification= new StringRequest(Request.Method.DELETE,
                            url, new Response.Listener<String>(){
                        //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                        @Override
                        public void onResponse(String response){
                            Log.d("delete_notification_registration", response.toString());
                            try {
                                Log.d("response_delete_notification_registration", response.toString());
                                JSONObject jsonObject= new JSONObject(response.toString());
                                String message= jsonObject.getString("message");
                                if(message.equals("Delete Success")) {
                                    editor.clear();
                                    editor.commit();
                                    Intent intent= new Intent (getActivity(),MainActivity.class);
                                    startActivity(intent);

                                    Log.d("delete_notification_register_message",message);
                                }

                            } catch(Exception e){
                                Log.d("JSON_error_delete_notification_register",e.toString());
                            }
                        }
                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){

                            Log.d("error_response_delete_notification_register",error.toString());
                            if(error.toString().equals("com.android.volley.AuthFailureError")) {
                                RefreshTokenManager refreshToken = new RefreshTokenManager(getContext());
                                refreshToken.login();
                            }

                        }
                    }){
                        @Override
                        public Map<String,String> getHeaders(){
                            Map<String,String> headers= new HashMap<String, String>();
                            String auth="Bearer "+pref.getString("access_token",null);
                            Log.d("auth_notificaation_register", auth);
                            headers.put("Authorization",auth);
                            return headers;
                        };
                    };


                    stringRequestDeleteNotification.setTag(REQUEST_TAG);
                    mQueue.add(stringRequestDeleteNotification);


                    //real logout




//                    Snackbar snackbar = Snackbar.make(relativeLayout, "Logout", Snackbar.LENGTH_LONG);
//                    snackbar.setActionTextColor(Color.WHITE);
//
//                    View snackbarView= snackbar.getView();
//                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    textView.setTextColor(Color.WHITE);
//                    snackbar.show();

//                    Intent intent= new Intent ()
                }








            }


        });



        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent (getActivity(),ManageAccountActivity.class);
                startActivity(intent);

            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);

            }
        });







        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
