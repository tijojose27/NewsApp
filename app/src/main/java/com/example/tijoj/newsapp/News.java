package com.example.tijoj.newsapp;

/**
 * Created by tijoj on 11/8/2017.
 */

public class News {


    public String sectionName;
    public String heading;
    public String article;
    public String URL;
    public String authorName;
    public String publishDate;

    public News(String sectionName, String heading, String article, String URL, String authorName, String publishDate) {
        this.sectionName = sectionName;
        this.heading = heading;
        this.article = article;
        this.URL = URL;
        this.authorName = authorName;
        this.publishDate = publishDate;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getHeading() {
        return heading;
    }

    public String getArticle() {
        return article;
    }

    public String getURL() {
        return URL;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getPublishDate() {
        String date = publishDate.substring(0,10);
        return date;
    }
}
