package com.example.test.alltopicsexample.dagger.managers;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferenceManager {

    SharedPreferences sharedPreferences;

    public SharedPreferenceManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }


    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }


    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }


    public Float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0);
    }

    public Long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }


    public void put(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void put(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void put(String key, Float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public void put(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void put(String key, Long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }
}
