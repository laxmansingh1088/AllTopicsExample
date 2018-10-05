package com.example.test.alltopicsexample.dagger.modules;

import android.content.Context;

import com.example.test.alltopicsexample.dagger.SharedManagerScope;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityContextModule {

    private Context context;


    public ActivityContextModule(Context context) {
        this.context = context;
    }

    @SharedManagerScope
    @Provides
    Context getActivityContext() {
        return context;
    }
}
