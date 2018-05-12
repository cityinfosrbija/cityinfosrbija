package com.example.kele.test_1;

import org.json.JSONObject;

/**
 * Created by Kele on 7/14/2017.
 */

public class Forecast implements JSONp {
    private int code;
    private int high;
    private int low;
    private String description;
    private String day;

    public Forecast() {
    }

    public Forecast(JSONObject data) {
        code = data.optInt("code");
        high = data.optInt("high");
        low = data.optInt("low");
        day = data.optString("day");
        description = data.optString("text");
    }

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        high = data.optInt("high");
        low = data.optInt("low");
        day = data.optString("day");
        description = data.optString("text");
    }

    public int getCode() {
        return code;
    }

    public int getHigh() {
        return high;
    }

    public int getLow() {
        return low;
    }

    public String getDescription() {
        return description;
    }

    public String getDay() {
        return day;
    }
}
