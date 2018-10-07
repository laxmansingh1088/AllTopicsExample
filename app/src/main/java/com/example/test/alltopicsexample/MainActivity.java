package com.example.test.alltopicsexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.test.alltopicsexample.adapters.AndroidTopicsAdapter;
import com.example.test.alltopicsexample.utils.BaseActivity;
import com.example.test.alltopicsexample.utils.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {

    private Context mContext;
    private RecyclerView mRvTopics;
    private AndroidTopicsAdapter mAndroidTopicsAdapter;
    private List<String> listTopics = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        listTopics.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.topics_list)));

        onFindView();
        onInView();
        onBindView();
    }

    @Override
    protected void onFindView() {
        mRvTopics = (RecyclerView) findViewById(R.id.rv_topics);
    }

    @Override
    protected void onInView() {

    }

    @Override
    protected void onBindView() {
        int spanCount = 2; // 3 columns
        int spacing = 16; // 50px
        boolean includeEdge = true;
        mRvTopics.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        mRvTopics.setLayoutManager(new GridLayoutManager(mContext, spanCount));
        mAndroidTopicsAdapter = new AndroidTopicsAdapter(mContext, listTopics);
        mRvTopics.setAdapter(mAndroidTopicsAdapter);
    }


}
