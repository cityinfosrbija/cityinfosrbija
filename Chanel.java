package com.mzbcacak.kele.cityinfosrbija;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kele on 6/18/2017.
 */

public class Chanel implements JSONp {
    private Units units;
    private Items items;

    @Override
    public void populate(JSONObject data) {
        units = new Units();
        items = new Items();
        try {
            units.populate(data.optJSONObject("units"));
            items.populate(data.optJSONObject("item"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Units getUnits() {
        return units;
    }

    public Items getItems() {
        return items;
    }
}
