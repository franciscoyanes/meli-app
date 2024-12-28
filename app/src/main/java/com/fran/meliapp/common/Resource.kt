package com.fran.meliapp.common

/*
    * This is a generic State class useful for UI state handling.
    * I will use it for handling network responses.
*/
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data:T? = null) : Resource<T>(data)
}
