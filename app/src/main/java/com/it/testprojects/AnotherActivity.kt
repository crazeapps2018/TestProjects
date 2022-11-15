package com.it.testprojects

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.it.testprojects.R
import kotlinx.android.synthetic.main.item_view.*

class AnotherActivity : AppCompatActivity() {

    private val anotherLabel: TextView
        get() = findViewById(R.id.anotherLabel)

    private val anotherActivityLabel: TextView
        get() = findViewById(R.id.anotherActivityLabel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)

        anotherLabel.text = intent.getStringExtra("REQUEST_KEY")


    }
}