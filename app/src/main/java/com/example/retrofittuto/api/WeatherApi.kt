package com.example.retrofittuto.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("https://api.weatherbit.io/v2.0/current")
    suspend fun getWeather(
        @Query("key") apikey: String,
        @Query("city") city: String
    ): Response<WeatherModel>
}