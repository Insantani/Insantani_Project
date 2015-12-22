package com.williamhenry.insantani;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgotPasswordActivity extends AppCompatActivity {
    private String url;
    private RequestQueue mQueue;
    private Context context;
    private LinearLayout linearLayout;
    private TextView emailView;
    private Button send;
    private ProgressDialog ringProgressDialog;

    public static final String REQUEST_TAG = "ForgotPasswordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        linearLayout= (LinearLayout) findViewById(R.id.linearLayoutForgotPassword);
        emailView=(TextView) findViewById(R.id.enteremail);
        send=(Button) findViewById(R.id.sendButtonForgot);
        context=this;
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringProgressDialog=ProgressDialog.show(context, "Please Wait ...", "Loading", true);
                executeReset(emailView.getText().toString());
            }
        });

    }


    public void executeReset(final String email){
        mQueue= CustomVolleyRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
        url="http://104.155.213.80/insantani/public/api/password/recover";
        final StringRequest stringRequestRecover= new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>(){
            //                    private ArrayList<Article> articles1=new ArrayList<Article>();
            @Override
            public void onResponse(String response){
                Log.d("recover", response.toString());
                try {
                    Log.d("response_recover", response.toString());
                    JSONObject jsonObject= new JSONObject(response.toString());
                    ringProgressDialog.dismiss();
                    if(jsonObject.has("message")) {
                        String message = jsonObject.getString("message");
                        Log.d("JSON_response_recover", jsonObject.getString("message"));
//                                      String message=response.getString("message");
//
                        if(message.equals("Success!")) {
                            Snackbar snackbar = Snackbar.make(linearLayout, "The link has been sent to your email", Snackbar.LENGTH_LONG);
                            snackbar.setAction("RESEND", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    executeReset(email);
                                }

                            });
                            snackbar.setActionTextColor(Color.YELLOW);

                            View snackbarView = snackbar.getView();
                            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            textView.setTextColor(Color.WHITE);

                            snackbar.show();
                        }else{
                            Snackbar snackbar = Snackbar.make(linearLayout, message, Snackbar.LENGTH_LONG);
                            snackbar.setActionTextColor(Color.WHITE);

                            View snackbarView = snackbar.getView();
                            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            textView.setTextColor(Color.WHITE);

                            snackbar.show();
                        }


                    }
                    if(jsonObject.has("email")) {
                        JSONArray errorArray= jsonObject.getJSONArray("email");
                        for (int j=0; j<errorArray.length();j++){
                            String error= (String) errorArray.get(j);
                            emailView.setError(error);
                        }



                    }


                } catch(Exception e){
                    Log.d("JSON_error_recover",e.toString());
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

                Log.d("error_response_recover",error.toString());
                Snackbar snackbar = Snackbar.make(linearLayout, error.toString(), Snackbar.LENGTH_LONG);
                snackbar.setActionTextColor(Color.WHITE);

                View snackbarView= snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);

                snackbar.show();
            }
        }){


            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String,String>();
                // the POST parameters:

                params.put("email", email);


                return params;
            };


        };


        stringRequestRecover.setTag(REQUEST_TAG);
        mQueue.add(stringRequestRecover);




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
