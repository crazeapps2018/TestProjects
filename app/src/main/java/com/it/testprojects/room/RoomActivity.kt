package com.it.testprojects.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.room.RoomDatabase
import com.it.testprojects.R
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomActivity : AppCompatActivity() {

    private lateinit var database: ContactDatabase

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        database = ContactDatabase.getDatabase(this)

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0,"John","69589",1))
        }


    }

    fun getData(view: View) {
        database.contactDao().getContact().observe(this) {
            Log.d("Room", "getData: $it")
            tvShow.text = it.toString()
        }
    }
}