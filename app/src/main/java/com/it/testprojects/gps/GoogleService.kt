package com.it.testprojects.gps

import android.Manifest
import android.location.LocationManager
import android.content.Intent
import com.it.testprojects.gps.GoogleService.TimerTaskToGetLocation
import com.it.testprojects.gps.GoogleService
import android.annotation.SuppressLint
import android.app.Service
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.*
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.it.testprojects.gps.LocationConverter.latitudeAsDMS
import com.it.testprojects.gps.LocationConverter.longitudeAsDMS
import java.util.*

class GoogleService : Service(), LocationListener {
    var isGPSEnable = false
    var isNetworkEnable = false
    var latitude = 0.0
    var longitude = 0.0
    var locationManager: LocationManager? = null
    var location: Location? = null
    private val mHandler = Handler(Looper.myLooper()!!)
    private var mTimer: Timer? = null
    var notify_interval: Long = 1000
    var intent: Intent? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mTimer = Timer()
        mTimer!!.schedule(TimerTaskToGetLocation(), 5, notify_interval)
        intent = Intent(str_receiver)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)

    }

    override fun onDestroy() {
        super.onDestroy()

    }

    @SuppressLint("MissingPermission")
    private fun fn_getLocation() {
        locationManager = applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager
        isGPSEnable = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        isNetworkEnable = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (!isGPSEnable && !isNetworkEnable) {
        } else {
            if (isNetworkEnable) {
                location = null
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                locationManager!!.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    1000,
                    0f,
                    this
                )
                if (locationManager != null) {
                    location =
                        locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    if (location != null) {
                        Log.e("Latitude", location!!.latitude.toString() + "")
                        Log.e("Longitude", location!!.longitude.toString() + "")
                        latitude = latitudeAsDMS(location!!.latitude,10).toDouble()
                        longitude = longitudeAsDMS(location!!.longitude,10).toDouble()
                        fn_update(location!!)
                    }
                }
            }
            if (isGPSEnable) {
                location = null
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                locationManager!!.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    1000,
                    0f,
                    this
                )
                if (locationManager != null) {
                    location =
                        locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    if (location != null) {
                        Log.e("Latitude", location!!.latitude.toString() + "")
                        Log.e("Longitude", location!!.longitude.toString() + "")
                        latitude = location!!.latitude
                        longitude = location!!.longitude
                        fn_update(location!!)
                    }
                }
            }
        }
    }

    private inner class TimerTaskToGetLocation : TimerTask() {
        override fun run() {
            mHandler.post { fn_getLocation() }
        }
    }

    fun fn_update(location: Location) {

        intent!!.putExtra("latitude", location.latitude.toString() + "")
        intent!!.putExtra("longitude", location.longitude.toString() + "")
        sendBroadcast(intent)
    }

    override fun onLocationChanged(location: Location) {}
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onLocationChanged(locations: List<Location>) {
        super.onLocationChanged(locations)
    }

    override fun onFlushComplete(requestCode: Int) {
        super.onFlushComplete(requestCode)
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        super.onStatusChanged(provider, status, extras)
    }

    override fun onProviderEnabled(provider: String) {
        super.onProviderEnabled(provider)
    }

    override fun onProviderDisabled(provider: String) {
        super.onProviderDisabled(provider)
    }

    companion object {
        var str_receiver = "servicetutorial.service.receiver"
    }
}