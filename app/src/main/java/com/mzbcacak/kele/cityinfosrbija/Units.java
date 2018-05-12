package com.mzbcacak.kele.cityinfosrbija;

import org.json.JSONException;
import org.json.JSONObject;

public class Units implements JSONp {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) throws JSONException {
        temperature = data.getString("temperature");
    }
}
