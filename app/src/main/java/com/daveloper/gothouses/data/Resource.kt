package com.daveloper.gothouses.data

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val message: String) : Resource<T>()
    data class Loading<out T>(val data: T?) : Resource<T>()
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Success(data)
        }

        fun <T> error(message: String): Resource<T> {
            return Error(message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Loading(data)
        }
    }
}