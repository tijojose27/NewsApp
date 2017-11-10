package com.example.tijoj.newsapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tijojose27 on 11/10/2017.
 */

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        //GETTING BACK BUTTON ON THE ACTIONBAR
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static class MyPreferenceFragment extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener{

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.query_main);
            //GETTING VALUE FROM THE INPUT
            Preference query = findPreference(getString(R.string.query_select_key));
            bindPreferenceSummaryToValue(query);
        }

        private void bindPreferenceSummaryToValue(Preference query) {
            query.setOnPreferenceChangeListener(this);
            SharedPreferences queries = PreferenceManager.getDefaultSharedPreferences(query.getContext());
            String queryString = queries.getString(query.getKey(), "");
            onPreferenceChange(query, queryString);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            String stringValue = o.toString();
            preference.setSummary(stringValue);
            return true;
        }
    }
}
