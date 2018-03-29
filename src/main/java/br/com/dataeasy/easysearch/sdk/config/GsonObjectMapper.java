package br.com.dataeasy.easysearch.sdk.config;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.mashape.unirest.http.ObjectMapper;

public class GsonObjectMapper implements ObjectMapper {

    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    public <T> T readValue(String value, Class<T> valueType) {
        return gson.fromJson(value, valueType);
    }

    public String writeValue(Object value) {
        return gson.toJson(value);
    }
}
