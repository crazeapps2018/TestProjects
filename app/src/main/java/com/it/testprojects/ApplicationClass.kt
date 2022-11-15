package com.it.testprojects

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.it.testprojects.retrofitpaging.api.QuoteAPI
import com.it.testprojects.retrofitpaging.db.QuoteDatabase
import com.it.testprojects.retrofitpaging.worker.QuoteWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class ApplicationClass: Application() {


    override fun onCreate() {
        super.onCreate()

    }

    private fun setupWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java,15,TimeUnit.MINUTES)
            .setConstraints(constraint).build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initialize() {
     //   val quoteAPI = RetrofitHelper.getInstance().create(QuoteAPI::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
       // quoteRepository = QuoteRepository(quoteService,database,applicationContext)
    }


}