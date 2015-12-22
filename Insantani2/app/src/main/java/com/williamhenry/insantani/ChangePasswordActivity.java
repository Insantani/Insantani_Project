package com.williamhenry.insantani;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.Snackbar;
import android.support.design.widget.CoordinatorLayout;

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
import android.widget.Button;

/**
 * Created by agungwy on 10/29/2015.
 */
public class ChangePasswordActivity extends Activity {
    private String url;
    private RequestQueue mQueue;
    private Context context;
    private LinearLayout linearLayout;
    //    private ArrayList<Article> articles;
//    private ArrayList<Product> mItems;
    public static final String REQUEST_TAG = "ChangePasswordActivity";
    private TextView password,confirm;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private boolean checkToken;
    private boolean checkRefreshToken;
    private boolean tokenType;
    private boolean user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        linearLayout= (LinearLayout) findViewById(R.id.linearLayoutChangePassword);
        password = (TextView)findViewById(R.id.enterpasswordchange);
        confirm= (TextView) findViewById(R.id.enterconfirmpasswordchange);
        pref= getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        editor=pref.edit();
        checkToken=pref.contains("access_token");
        tokenType=pref.contains("token_type");
        user_id=pref.contains("user_id");
        checkRefreshToken=pref.contains("refresh_token");



        context=this;
        Button save = (Button) findViewById(R.id.sendButtonChange);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("password", password.getText().toString());
                Log.d("confirm_password", confirm.getText().toString());
//                Log.d("name", name.getText().toString());
                if (checkToken && checkRefreshToken && tokenType && user_id && !password.getText().toString().equals("")&&
                        !confirm.getText().toString().equals("")) {


                    mQueue = CustomVolleyRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
                    url = "http://104.155.213.80/insantani/public/api/change/password";
                    final StringRequest stringRequestChangePassword = new StringRequest(Request.Method.PUT,
                            url, new Response.Listener<String>() {
                        //                    private ArrayList<Article> articles1=new ArrayList<Article>();
                        @Override
                        public void onResponse(String response) {
                            Log.d("edit_password", response.toString());
                            try {
                                Log.d("response_edit_password", response.toString());
                                JSONObject jsonObject = new JSONObject(response.toString());
                                if (jsonObject.has("message")) {
                                    String message = jsonObject.getString("message");
                                    Log.d("JSON_response_register", jsonObject.getString("message"));
                                    //                                      String message=response.getString("message");
                                    //
                                    if (message.equals("Success!")) {
                                        Snackbar snackbar = Snackbar.make(linearLayout, message, Snackbar.LENGTH_LONG);
                                        snackbar.setActionTextColor(Color.WHITE);

                                        View snackbarView = snackbar.getView();
                                        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                        textView.setTextColor(Color.WHITE);

                                        snackbar.show();


                                    } else {
                                        Snackbar snackbar = Snackbar.make(linearLayout, message, Snackbar.LENGTH_LONG);
                                        snackbar.setActionTextColor(Color.WHITE);

                                        View snackbarView = snackbar.getView();
                                        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                        textView.setTextColor(Color.WHITE);

                                        snackbar.show();
                                    }
                                }else if(jsonObject.has("password")){
                                    JSONArray passwordMessage = jsonObject.getJSONArray("password");
                                    Snackbar snackbar = Snackbar.make(linearLayout, passwordMessage.get(0).toString(), Snackbar.LENGTH_LONG);
                                    snackbar.setActionTextColor(Color.WHITE);

                                    View snackbarView = snackbar.getView();
                                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                    textView.setTextColor(Color.WHITE);

                                    snackbar.show();
                                }

                            } catch (Exception e) {
                                Log.d("JSON_error_password", e.toString());
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.d("error_response_password", error.toString());
                            if(error.toString().equals("com.android.volley.AuthFailureError")) {
                                RefreshTokenManager refreshToken = new RefreshTokenManager(getApplicationContext());
                                refreshToken.login();
                            }else {
                                Snackbar snackbar = Snackbar.make(linearLayout, error.toString(), Snackbar.LENGTH_LONG);
                                snackbar.setActionTextColor(Color.WHITE);

                                View snackbarView = snackbar.getView();
                                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                textView.setTextColor(Color.WHITE);

                                snackbar.show();
                            }
                        }
                    }) {

                        @Override
                        public Map<String, String> getHeaders() {
                            Map<String, String> headers = new HashMap<String, String>();
                            // the POST parameters:
                            String auth = "Bearer " + pref.getString("access_token", null);
                            Log.d("Auth_password", auth);
                            headers.put("Authorization", auth);

                            return headers;
                        };


                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            // the POST parameters:
                            params.put("user_id", pref.getString("user_id", null));
                            params.put("password",password.getText().toString());
                            params.put("password_confirmation",confirm.getText().toString());
                            return params;
                        };


                    };
                    stringRequestChangePassword.setTag(REQUEST_TAG);
                    mQueue.add(stringRequestChangePassword);
                }
            }

        });






//                Intent intent= new Intent(context,MainActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("Email", email.getText().toString());
//                bundle.putString("Password", password.getText().toString());
//                intent.putExtra("User", bundle);
//                context.startActivity(intent);





    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.) {
//            return true;
//        }

        switch (id) {
            case android.R.id.home:
                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
                // if this doesn't work as desired, another possibility is to call `finish()` here.
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

//        return super.onOptionsItemSelected(item);
    }
}
