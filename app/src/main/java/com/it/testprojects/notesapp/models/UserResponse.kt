package com.it.testprojects.notesapp.models

import com.it.testprojects.notesapp.models.User

data class UserResponse(
    val token: String,
    val user: User
)