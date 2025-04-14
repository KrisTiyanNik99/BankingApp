package main.services.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.HttpURLConnection;

/*
* APIService defines the contract for interacting with external APIs.
* It includes methods for fetching, handling, and processing API responses.
*/
public interface APIService {
    HttpURLConnection fetchApiResponse(String url);
    JSONObject handleResponse(HttpURLConnection connection);
    JSONArray getArray(JSONObject jsonObject, String keyword);
    String getSourceData();
}
