package com.rz.dictionaryapp.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.rz.dictionaryapp.R;

public class MySharedPreferences {

    private SharedPreferences prefs;
    private Context context;

    public MySharedPreferences(Context context) {
        this.context = context;
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setFirstRun(Boolean input) {
        SharedPreferences.Editor editor = prefs.edit();
        String key = context.getResources().getString(R.string.first_run);
        editor.putBoolean(key, input);
        editor.apply();
    }

    public Boolean getFirstRun() {
        String key = context.getResources().getString(R.string.first_run);
        return prefs.getBoolean(key, true);
    }
}

