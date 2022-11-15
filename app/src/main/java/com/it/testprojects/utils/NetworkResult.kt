package com.it.testprojects.utils

sealed class NetworkResult<T>(val data: T? = null,val errorMessage:String? = null ) {
    class Loading<T> : NetworkResult<T>()
    class Success<T>(data: T?=null) : NetworkResult<T>(data)
    class Error<T>(errorMessage: String) : NetworkResult<T>(errorMessage = errorMessage)
}