package com.williamhenry.insantani;

<<<<<<< HEAD
=======
/**
 * Created by william on 11/11/2015.
 */
>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

<<<<<<< HEAD
/**
 * Created by CantyaPS on 12-Nov-15.
 */
=======
>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef
public class CustomJSONObjectRequest extends JsonObjectRequest {

    public CustomJSONObjectRequest(int method, String url, JSONObject jsonRequest,
                                   Response.Listener<JSONObject> listener,
                                   Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return headers;
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        // here you can write a custom retry policy
        return super.getRetryPolicy();
    }
<<<<<<< HEAD
=======

>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef
}
