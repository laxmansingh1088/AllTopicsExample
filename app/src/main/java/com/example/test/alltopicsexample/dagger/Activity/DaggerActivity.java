package com.example.test.alltopicsexample.dagger.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.alltopicsexample.R;
import com.example.test.alltopicsexample.dagger.components.DaggerSharedPrefComponent;
import com.example.test.alltopicsexample.dagger.components.SharedPrefComponent;
import com.example.test.alltopicsexample.dagger.managers.SharedPreferenceManager;
import com.example.test.alltopicsexample.dagger.modules.ActivityContextModule;
import com.example.test.alltopicsexample.utils.BaseActivity;

public class DaggerActivity extends BaseActivity {


    SharedPreferenceManager sharedPreferenceManager;
    SharedPrefComponent sharedPrefComponent;

    TextView tv_value;
    EditText et_val;
    Button btn_save;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dagger_activity);

        sharedPrefComponent = DaggerSharedPrefComponent
                .builder()
                .activityContextModule(new ActivityContextModule(this))
                .build();
        sharedPreferenceManager = sharedPrefComponent.getSharedPreferenceManager();

        onFindView();
        onInView();
        onBindView();

    }

    @Override
    protected void onFindView() {
        tv_value = findViewById(R.id.tv_value);
        et_val = findViewById(R.id.et_val);
        btn_save = findViewById(R.id.btn_save);
    }

    @Override
    protected void onInView() {
        if (sharedPreferenceManager != null) {
            tv_value.setText(sharedPreferenceManager.getString("name"));
        }

    }

    @Override
    protected void onBindView() {
        btn_save.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                if (et_val.getText().length() == 0) {
                    Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show();
                } else {
                    savesharedPrefname(et_val.getText().toString());
                }

                break;
        }
    }


    public void savesharedPrefname(String name) {
        if (sharedPreferenceManager != null) {
            sharedPreferenceManager.put("name", name);
            et_val.setText("");
            tv_value.setText(sharedPreferenceManager.getString("name"));
        }
    }
}
