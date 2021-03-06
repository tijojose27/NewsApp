package com.example.tijoj.newsapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    public String URL = "http://content.guardianapis.com/us-news?show-references=author&show-fields=all&api-key=test";

    public ListView newsLV;
    public TextView nodataTV;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GETTING VIEWS FROM ID
        newsLV = findViewById(R.id.activity_main_list);
        nodataTV = findViewById(R.id.actvity_main_no_data);
        progressBar = findViewById(R.id.news_item_progress);

        //CHECKING APP IS CONNECTED TO THE INTERNET
        if (isNetworkAvailable()) {
            //LOADING THE NEWSLOADER TO FETCH DATA FROM API
            getLoaderManager().initLoader(1, null, this);
        } else {
            progressBar.setVisibility(View.GONE);
            nodataTV.setText("Please check if your internet is turned on");
            newsLV.setEmptyView(nodataTV);
        }

    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        progressBar.setVisibility(View.GONE);
        updateUI(news);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
    }

    //HELPER METHOD TO UPDATE UI
    public void updateUI(List<News> myNews) {
        if (myNews.size() != 0) {
            myCustomNewsAdapter adapter = new myCustomNewsAdapter(this, myNews);
            newsLV.setAdapter(adapter);
        } else {
            //MAKING SURE THAT THE API CAN BE CONNECTED TO OR IF THE URL OR SERVER IS DOWN
            nodataTV.setText("Check if api is getting data from server");
            newsLV.setEmptyView(nodataTV);
        }

    }

    //HELPER METHOD TO CHECK IF APP IS ABLE TO CONNECT TO THE INTERNET
    private boolean isNetworkAvailable() {
        ConnectivityManager mCM = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = mCM.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
