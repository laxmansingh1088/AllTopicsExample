package com.example.test.alltopicsexample.dagger.modules;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.test.alltopicsexample.dagger.SharedManagerScope;
import com.example.test.alltopicsexample.dagger.managers.SharedPreferenceManager;

import dagger.Module;
import dagger.Provides;

@Module(includes = {SharedPrefModule.class})
public class SharedPrefManagerModule {

    @SharedManagerScope
    @Provides
    SharedPreferenceManager getSharedPreferenceManager(SharedPreferences sharedPreferences) {
        return new SharedPreferenceManager(sharedPreferences);
    }

}
