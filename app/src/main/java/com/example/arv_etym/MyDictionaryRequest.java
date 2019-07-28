package com.example.arv_etym;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


public class MyDictionaryRequest extends AsyncTask<String, Integer, String >
{
    @Override
    protected String doInBackground(String... strings) {
        String myurl;
        String[] params = new String[0];
        myurl = params[0];

        final String app_id = "9ca7badf";
        final String app_key = "1a69cd1393c5723f1e41b1617f216dd5";

        try {
            URL url = new URL(myurl);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject js = new JSONObject(s);
            JSONArray results = js.getJSONArray("results");
            String def = null;
            for(int i = 0; i<results.length(); i++){
                JSONObject lentries = results.getJSONObject(i);
                JSONArray la = lentries.getJSONArray("lexicalEntries");
                for(int j=0;j<la.length();j++){
                    JSONObject entries = la.getJSONObject(j);
                    JSONArray e = entries.getJSONArray("entries");
                    for(int i1=0;i1<e.length();i1++){
                        JSONObject senses = la.getJSONObject(i1);
                        JSONArray senses1 = entries.getJSONArray("senses");
                        JSONObject d = senses1.getJSONObject(0);
                        JSONArray de = d.getJSONArray("definitions");
                        def = de.getString(0);
                    }
                }
            }

            Log.e("def",def);

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


}
