package com.example.test.alltopicsexample.utils;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    protected abstract void onFindView();

    protected abstract void onInView();

    protected abstract void onBindView();


    @Override
    public void onClick(View v) {
    }
}
