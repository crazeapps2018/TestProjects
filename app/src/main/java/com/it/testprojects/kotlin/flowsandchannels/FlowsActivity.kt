package com.it.testprojects.kotlin.flowsandchannels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.it.testprojects.R
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@DelicateCoroutinesApi
class FlowsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flows)

        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            delay(6000)

            Log.d("MeFlow", result.value.toString())
        }
    }

    private fun producer(): StateFlow<Int> {
        val mutableStateFlow = MutableStateFlow(10)
        GlobalScope.launch {
          delay(2000)
            mutableStateFlow.emit(20)
            delay(2000)
            mutableStateFlow.emit(30)
        }
        return mutableStateFlow
    }


}