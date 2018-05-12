package com.example.kele.test_1;

import android.net.Uri;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Kele on 6/18/2017.
 */

public class YahooW {
    private Wcallback wcallback;
    private String location;
    private Exception exception;

    public YahooW(Wcallback wcallback) {
        this.wcallback = wcallback;
    }

    public String getLocation() {
        return location;
    }

    public void refreshL(String l){
        this.location = l;
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                String SQY = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='c'",params[0]);
                String Endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(SQY));
                try {
                    URL url = new URL(Endpoint);

                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder resoults = new StringBuilder();
                    String line;
                    while ((line = reader.readLine())!= null){
                        resoults.append(line);
                    }
                    return resoults.toString();
                } catch (Exception e) {
                    exception = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if(s==null && exception!=null){
                    wcallback.serviceE(exception);
                    return;
                }
                try {
                    JSONObject object = new JSONObject(s);
                    JSONObject queryJ = object.optJSONObject("query");
                    int count = queryJ.optInt("count");
                    if (count==0){
                        wcallback.serviceE(exception);
                        return;
                    }

                    Chanel chanel = new Chanel();
                    chanel.populate(queryJ.optJSONObject("results").optJSONObject("channel"));
                    wcallback.serviceSucces(chanel);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(location);
    }
}
