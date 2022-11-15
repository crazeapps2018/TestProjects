package com.it.testprojects.notesapp.repository

import android.util.Log
import com.it.testprojects.notesapp.api.UserAPI
import com.it.testprojects.notesapp.models.UserRequest
import com.it.testprojects.utils.Constants.TAG
import javax.inject.Inject


class UserRepository @Inject constructor(private val userAPI: UserAPI){

    suspend fun registerUser(userRequest: UserRequest){
        val response = userAPI.signup(userRequest)
        Log.d(TAG,response.body().toString())

    }

    suspend fun loginUser(userRequest: UserRequest){
        val response = userAPI.signin(userRequest)
        Log.d(TAG,response.body().toString())

    }


}