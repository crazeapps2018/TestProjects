package com.it.testprojects.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

    private final Context context;
    private String TAG = "MyWorker";

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters parameters){
        super(context,parameters);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork(){
        Log.d(TAG, "doWork: called for: " + this.getId());
        Log.d(TAG, "Service Running: " + MyService.isServiceRunning);
        if (!MyService.isServiceRunning){
            Log.d(TAG, "started service from do work");
            Intent intent = new Intent(this.context,MyService.class);
            ContextCompat.startForegroundService(context,intent);
        }
        return Result.success();
    }

    @Override
    public void onStopped() {
        Log.d(TAG, "onStopped: called for : "+this.getId());
        super.onStopped();
    }
}
