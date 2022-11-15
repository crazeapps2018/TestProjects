package com.it.testprojects.di

import android.util.Log
import javax.inject.Inject

const val TAG  = "HiltDemo"



interface UserRepository{
    fun saveUser(email: String,password: String)
}

class SQLRepository @Inject constructor() : UserRepository {
    override fun saveUser(email: String, password: String) {
        Log.d(TAG,"User Saved In DB")
    }
}

class FirebaseRepository : UserRepository{
    override fun saveUser(email: String, password: String) {
        Log.d(TAG,"User saved in Firebase")
    }
}