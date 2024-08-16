package com.example.retrofittuto.api

sealed class NetworkResponse<out T> {
    //1. Success
    data class Success <out T> (val data: T): NetworkResponse<T>()

    //2. Failure
    data class Error (val message: String): NetworkResponse<Nothing>()

    //3. Loading Screen
    data object Loading : NetworkResponse<Nothing>()
}