package com.example.tijoj.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<News> myNews = new ArrayList<>();
        myNews.add(new News("Cat caught dog","this cat had caught a dog and had been" +
                "trying for so long that it could make it"));

        myNews.add(new News("Boy meets world","This is the first time that a biy meets wold" +
                "and is catching on"));

    }


}
