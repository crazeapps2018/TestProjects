package com.it.testprojects.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.it.testprojects.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)



    }
}