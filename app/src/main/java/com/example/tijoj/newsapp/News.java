package com.example.tijoj.newsapp;

/**
 * Created by tijoj on 11/8/2017.
 */

public class News {

    public String heading;
    public String article;

    public News(String heading, String article) {
        this.heading = heading;
        this.article = article;
    }

    public String getHeading() {
        return heading;
    }

    public String getArticle() {
        return article;
    }
}
