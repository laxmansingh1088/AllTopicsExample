package com.example.test.alltopicsexample.utils;

import android.app.AppComponentFactory;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog progressDialog;

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


    protected void showProgressDailog(Context context,String message){
        if(progressDialog==null) {
            progressDialog=new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }


    protected void dissmissProgressDialog(){
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.cancel();
        }
    }
}
