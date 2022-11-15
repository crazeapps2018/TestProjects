package com.it.testprojects.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.it.testprojects.R
import kotlinx.android.synthetic.main.activity_kotlin.*

class LocationActivity : AppCompatActivity() {

    private lateinit var locationViewModel: LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        locationViewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)

        startLocationUpdate()
    }

    private fun startLocationUpdate(){
        locationViewModel.getLocationData().observe(this) {
            latLong.text = "Latitude ${it.latitude}\nLongitude ${it.longitude}"
        }
    }


    

}