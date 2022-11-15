package com.it.testprojects.notesapp.api

import com.it.testprojects.notesapp.models.UserRequest
import com.it.testprojects.notesapp.models.UserResponse
import dagger.Provides
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Singleton


interface UserAPI {

    @POST("users/signup")
    suspend fun signup(@Body userRequest: UserRequest) : Response<UserResponse>

    @POST("users/signin")
    suspend fun signin(@Body userRequest: UserRequest) : Response<UserResponse>

}