package com.mzbcacak.kele.cityinfosrbija;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Items implements JSONp {
    private Condition condition;
    private Forecast forecast;
    private ArrayList<Forecast> dani = new ArrayList<Forecast>();

    public Condition getCondition() {
        return condition;
    }

    public ArrayList<Forecast> getForecast(){
        return dani;
    }

    public Forecast getFore(){
        return forecast;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
        try {
            JSONArray objekti = data.getJSONArray("forecast");
            int len = objekti.length();
            for(int k=0;k<len;k++) {
                dani.add(new Forecast(objekti.getJSONObject(k)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
