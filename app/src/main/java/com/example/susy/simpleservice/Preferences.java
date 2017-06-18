package com.example.susy.simpleservice;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

/**
 * Created by susy on 18/06/17.
 */

public class Preferences {

    private static final String PREFERENCES = "PREFERENCES_SERVICE";

    public static void setServiceEnable(Context context, boolean enable){
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("service_is_enable",enable);
        editor.commit();
    }

    public static boolean isServiceEnable(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES,context.MODE_PRIVATE);
        return  preferences.getBoolean("service_is_enable",false);
    }
}
