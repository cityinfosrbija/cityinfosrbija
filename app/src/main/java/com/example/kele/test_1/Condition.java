package com.example.kele.test_1;

import org.json.JSONObject;

/**
 * Created by Kele on 6/18/2017.
 */

public class Condition implements JSONp {
    private int code;
    private int temperature;
    private String description;

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        temperature = data.optInt("temp");
        description = data.optString("text");
    }

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }
}
