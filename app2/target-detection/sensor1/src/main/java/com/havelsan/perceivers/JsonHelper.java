package com.havelsan.perceivers;

import com.google.gson.Gson;

public class JsonHelper {
    private Gson gson = new Gson();
    public <T> T getObjectFromJson(String json, Class<T> type) {

        return gson.fromJson(json, type);
    }

    public String getJsonFromObject(Object model) {
        return gson.toJson(model);
    }

}
