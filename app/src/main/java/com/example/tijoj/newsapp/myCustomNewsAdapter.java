package com.example.tijoj.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by tijoj on 11/8/2017.
 */

public class myCustomNewsAdapter extends ArrayAdapter<News>{

    public myCustomNewsAdapter(@NonNull Context context, @NonNull ArrayList<News> mynews) {
        super(context, 0, mynews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View rootView = convertView;


        return rootView;
    }
}
