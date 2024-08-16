package com.example.retrofittuto.api

import com.example.retrofittuto.api.models.Data

data class WeatherModel(
    val count: Int,
    val `data`: List<Data>,
    val minutely: List<String>
)