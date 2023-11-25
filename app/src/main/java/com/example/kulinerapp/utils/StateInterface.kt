package com.example.kulinerapp.utils

sealed class StateInterface<out T: Any?> {
    object Loading : StateInterface<Nothing>()
    data class Success<out T: Any>(val data: T) : StateInterface<T>()
    data class Error(val message: String) : StateInterface<Nothing>()
}