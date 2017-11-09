package com.example.tijoj.newsapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{

    public String URL="http://content.guardianapis.com/us-news?show-references=author&show-fields=all&api-key=test";

    public ListView newsLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsLV = findViewById(R.id.activity_main_list);

        getLoaderManager().initLoader(1, null, this);
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        updateUI(news);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

    }

    public void updateUI(List<News> myNews){
        myCustomNewsAdapter adapter = new myCustomNewsAdapter(this, myNews);
        newsLV.setAdapter(adapter);
    }

}
