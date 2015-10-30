package com.williamhenry.insantani;

/**
 * Created by william on 9/10/2015.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class ServerRequest {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
    static HttpEntity httpEntity=null;


    public ServerRequest() {

    }

    public JSONObject getJSONFromUrl(String url, List<NameValuePair> params, String method) {
        String json=null;


        try {

                DefaultHttpClient httpClient = new DefaultHttpClient();

                if(method.equals("get")){
                    HttpGet httpGet=null;
                    for(int i=0; i<params.size();i++){
                        NameValuePair x= params.get(i);

                        if(x.getName()=="null") {
                            if(x.getValue()=="false") {
                                params.remove(i);
                                httpGet = new HttpGet(url+"?"+ URLEncodedUtils.format(params,"utf-8"));
                            }else{
                                httpGet = new HttpGet(url);
                            }
                        }
                    }
                    Log.d("params",params.toString());
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();
                }else{
                    HttpPost httpPost = new HttpPost(url);

                    for(int i=0; i<params.size();i++){
                        NameValuePair x= params.get(i);

                        if(x.getName()=="null") {
                            if(x.getValue()=="false") {
                                params.remove(i);
                                httpPost.setEntity(new UrlEncodedFormEntity(params));
                            }
                        }
                    }




                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();
                }










        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                Log.e("line",line);
                sb.append(line);
            }
            is.close();

            json = sb.toString();
            Log.e("JSON", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }


        try {
            Log.e("json input",json);
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }


        return jObj;

    }
    JSONObject jobj;
    public JSONObject getJSON(String url, List<NameValuePair> params,String method) {
        Log.e("list",params.toString());
        Params param = new Params(url,params,method);
        Request myTask = new Request();
        try{
            jobj= myTask.execute(param).get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return jobj;
    }


    private static class Params {
        String url;
        List<NameValuePair> params;
        String method;


        Params(String url, List<NameValuePair> params,String method) {
            this.url = url;
            this.params = params;
            this.method=method;

        }
    }

    private class Request extends AsyncTask<Params, String, JSONObject> {

        @Override
        protected JSONObject doInBackground(Params... args) {
//            Log.e("args",args.toString());
            ServerRequest request = new ServerRequest();
            JSONObject json = request.getJSONFromUrl(args[0].url,args[0].params,args[0].method);

            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {

            super.onPostExecute(json);

        }

    }
}
