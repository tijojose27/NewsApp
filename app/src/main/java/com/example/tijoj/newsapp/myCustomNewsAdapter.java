package com.example.tijoj.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.zip.Inflater;

/**
 * Created by tijoj on 11/8/2017.
 */

public class myCustomNewsAdapter extends ArrayAdapter<News>{

    public myCustomNewsAdapter(@NonNull Context context, @NonNull List<News> mynews) {
        super(context, 0, mynews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View rootView = convertView;

        if(rootView==null){
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }

        TextView headingTV = rootView.findViewById(R.id.news_item_heading);
        TextView articleTV = rootView.findViewById(R.id.news_item_article);
        TextView dateTV = rootView.findViewById(R.id.news_item_date);
        TextView authorTV = rootView.findViewById(R.id.news_item_author);
        TextView sectionTV = rootView.findViewById(R.id.news_item_section);

        final News currNews = getItem(position);

        headingTV.setText(currNews.getHeading());
        articleTV.setText(currNews.getArticle());
        dateTV.setText(currNews.getPublishDate());
        authorTV.setText(currNews.getAuthorName());
        sectionTV.setText(currNews.getSectionName());

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openNews = new Intent(Intent.ACTION_VIEW, Uri.parse(currNews.getURL()));
                getContext().startActivity(openNews);
            }
        });

        return rootView;
    }
}
