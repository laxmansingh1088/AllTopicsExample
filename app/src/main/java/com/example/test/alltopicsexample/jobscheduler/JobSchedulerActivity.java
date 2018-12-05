package com.example.test.alltopicsexample.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.test.alltopicsexample.R;
import com.example.test.alltopicsexample.jobscheduler.jobservices.MyJobService;
import com.example.test.alltopicsexample.utils.BaseActivity;

public class JobSchedulerActivity extends BaseActivity {
    public static final String TAG = "Job Scheduler Example";

    private Button start_job, stop_job;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobschedule);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        onFindView();
        onInView();
        onBindView();
    }

    @Override
    protected void onFindView() {
        start_job = findViewById(R.id.start_job);
        stop_job = findViewById(R.id.stop_job);
    }

    @Override
    protected void onInView() {

    }

    @Override
    protected void onBindView() {
        start_job.setOnClickListener(this);
        stop_job.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {

            case R.id.start_job:
                scheduleJob();
                break;


            case R.id.stop_job:
                cancelJob();
                break;


            default:
                break;
        }
    }


    private void scheduleJob() {
        ComponentName componentName = new ComponentName(this, MyJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(122, componentName)
                //  .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(jobInfo);

        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.i(TAG, "Job Scheduled");
        } else if (resultCode == JobScheduler.RESULT_FAILURE) {
            Log.i(TAG, "Job Failed");
        }
    }

    private void cancelJob() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(122);
    }
}
