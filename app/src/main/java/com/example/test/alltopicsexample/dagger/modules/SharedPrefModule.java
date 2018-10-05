package com.example.test.alltopicsexample.dagger.modules;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.test.alltopicsexample.dagger.SharedManagerScope;

import dagger.Module;
import dagger.Provides;


@Module(includes = ActivityContextModule.class)
public class SharedPrefModule {

    private final String MY_PREFERENCES = "my_prefrence";

    @SharedManagerScope
    @Provides
    SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
    }


}
