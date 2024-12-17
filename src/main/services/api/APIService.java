package main.services.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.HttpURLConnection;

public interface APIService {
    HttpURLConnection fetchApiResponse(String url);
    JSONObject handleResponse(HttpURLConnection connection);
    JSONArray getArray(JSONObject jsonObject, String keyword);
    String getData();
}