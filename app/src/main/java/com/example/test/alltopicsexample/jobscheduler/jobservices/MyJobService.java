package com.example.test.alltopicsexample.jobscheduler.jobservices;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class MyJobService extends JobService {

    public static final String TAG = "JobService";
    private boolean jobCancelled = false;


    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "Job Started");
        doSomeStuff(params);
        // return true if some other work is done in another thread and that thread will complete the job.
        // return false if no other thread is started and this method executes every thing.
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "Job Stopped");
        jobCancelled = true;
        return true;
    }


    private void doSomeStuff(final JobParameters parameters) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if (jobCancelled)
                        return;
                    Log.i(TAG, "Job Running " + i);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.i(TAG, "Job Finished");
                jobFinished(parameters, false);
            }
        }).start();

    }


}
