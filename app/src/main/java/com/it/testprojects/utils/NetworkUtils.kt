package com.it.testprojects.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Debug
import java.io.IOException


class NetworkUtils {

    companion object{

        fun isInternetAvailable(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo ?: return false
            when (activeNetwork.type) {
                ConnectivityManager.TYPE_WIFI -> if ((activeNetwork.state == NetworkInfo.State.CONNECTED ||
                            activeNetwork.state == NetworkInfo.State.CONNECTING) &&
                    isInternet()
                ) return true
                ConnectivityManager.TYPE_MOBILE -> if ((activeNetwork.state == NetworkInfo.State.CONNECTED ||
                            activeNetwork.state == NetworkInfo.State.CONNECTING) &&
                    isInternet()
                ) return true
                else -> return false
            }
            return false
        }

        private fun isInternet(): Boolean {
            val runtime = Runtime.getRuntime()
            try {
                val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
                val exitValue = ipProcess.waitFor()
               // Debug.i(exitValue.toString() + "")
                return exitValue == 0
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return false
        }
    }
}