package com.it.testprojects.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.it.testprojects.AnotherActivity
import com.it.testprojects.R
import kotlinx.coroutines.*

class CoroutineActivity : AppCompatActivity() {

    private val TAG = "Mep"
    lateinit var viewModel:MainViewModel
    private val counterTextView: TextView
        get() = findViewById(R.id.tvCounter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        lifecycleScope.launch {
            delay(2000)
            startActivity(Intent(this@CoroutineActivity, AnotherActivity::class.java))
            finish()
        }

    }



}