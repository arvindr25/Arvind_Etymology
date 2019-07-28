package com.example.arv_etym;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
public class MyDictionaryRequest extends AsyncTask<String, Integer, String >
{
    Context context;
    TextView t1;
    MyDictionaryRequest(Context context, TextView tV)
    {
        this.context = context;
        t1 = tV;
            }
    @Override
    protected String doInBackground(String... params) {
        final String app_id = "9ca7badf";
        final String app_key = "1a69cd1393c5723f1e41b1617f216dd5";
        try {
            URL url = new URL(params[0]);
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
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
String def;

        try {
            JSONObject js = new JSONObject(result);
            JSONArray results = js.getJSONArray("results");

            JSONObject lEntries = results.getJSONObject(0);
            JSONArray la = lEntries.getJSONArray("lexicalEntries");

            JSONObject entries = la.getJSONObject(0);
            JSONArray e = entries.getJSONArray("entries");

            JSONObject jsonObject = e.getJSONObject(0);
            JSONArray sensesArray = jsonObject.getJSONArray("senses");


            JSONObject de = sensesArray.getJSONObject(0) ;
            JSONArray d = de.getJSONArray("definitions");


            def = d.getString(0);
            t1.setText(def);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.v("Result of Etymology","onPostExecute"+result);
    }
}
