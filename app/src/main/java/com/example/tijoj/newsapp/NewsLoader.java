package com.example.tijoj.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by tijojose27 on 11/9/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    public String URL;

    public NewsLoader(Context context, String url) {
        super(context);
        this.URL = url;
    }

    @Override
    public List<News> loadInBackground() {
        networkUtils myutils = new networkUtils(URL);
        List<News> currNews = myutils.getMyNews();
        return currNews;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

}
