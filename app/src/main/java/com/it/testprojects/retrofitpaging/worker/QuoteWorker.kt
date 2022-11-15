package com.it.testprojects.retrofitpaging.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class QuoteWorker(private val context: Context, params: WorkerParameters):Worker(context,params) {
    override fun doWork(): Result {
        Log.d("MeProg","Worker Called")
      //  val repository = (context as ApplicationClass).quoteRepository
//        CoroutineScope(Dispatchers.IO).launch {
//            repository.getQuoteBackground()
//        }
        return Result.success()
    }

}