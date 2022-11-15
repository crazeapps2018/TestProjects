package com.it.testprojects.service;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.it.testprojects.R;

import java.util.concurrent.TimeUnit;

public class ServiceActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called");
        setContentView(R.layout.activity_service);
        startServiceViaWorker();
    }

    public void onStartServiceClick(View v){
        startService();
    }

    public void onStopServiceClick(View v){
        stopService();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: called");
        stopService();
        super.onDestroy();
    }

    public void startService(){
        Log.d(TAG, "startService: called");
        if (!MyService.isServiceRunning){
            Intent service = new Intent(this,MyService.class);
            ContextCompat.startForegroundService(this,service);
        }
    }


    public void stopService(){
        Log.d(TAG, "stopService: called");
        if (MyService.isServiceRunning){
            Intent serviceIntent = new Intent(this,MyService.class);
            stopService(serviceIntent);
        }
    }

    public void startServiceViaWorker(){
        Log.d(TAG, "startServiceViaWorker: called");
        String UNIQUE_WORK_HOME = "StarMyServiceViaWorker";
        WorkManager workManager = WorkManager.getInstance(this);

        //As per documentation minimum repeat interval that can be defined is 15 minutes.
        PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(
                MyWorker.class,16,
                TimeUnit.MINUTES).build();

        workManager.enqueueUniquePeriodicWork(UNIQUE_WORK_HOME, ExistingPeriodicWorkPolicy.KEEP,request);

    }

}
