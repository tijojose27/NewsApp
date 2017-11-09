package com.example.tijoj.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijojose27 on 11/9/2017.
 */

public class networkUtils {

    private String LOG_TAG=getClass().getName();
    private String URL;

    public networkUtils(String URL) {
        this.URL = URL;
    }

    public List<News> getMyNews(){
        String jsonResp = "";
        try{
            jsonResp = makeHttpRequest(createUrl(URL));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<News> myNews = extractNewsFromJSON(jsonResp);
        return myNews;
    }

    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        if (url == null) {
            return jsonResponse;
        }
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else{
                Log.e(LOG_TAG,"Error response code : "+urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            Log.e("IOExcepteion",e.toString());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public ArrayList<News> extractNewsFromJSON(String JsonNews){
        ArrayList<News> news = new ArrayList<>();
        try{
            JSONObject root = new JSONObject(JsonNews);
            JSONObject resp = root.getJSONObject("response");
            JSONArray results = resp.getJSONArray("results");
            for (int i =0; i<results.length();i++){
                JSONObject currObj = results.getJSONObject(i);
                String heading = currObj.getString("webTitle");
                JSONObject currfield = currObj.getJSONObject("fields");
                String article = currfield.getString("trailText");
                news.add(new News(heading, article));
            }
        }catch (JSONException e){
            Log.e(LOG_TAG,"Parsing JSON fault");
        }
        return news;
    }

}
