package com.example.retrofittuto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittuto.api.Constant
import com.example.retrofittuto.api.NetworkResponse
import com.example.retrofittuto.api.RetrofitInstance
import com.example.retrofittuto.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi

    //Live data

    //WeatherData is stored in WeatherModel/models
    //Hence we provide it here
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    //Converting above info to LIVE DATA
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String){
        //Loading
        _weatherResult.value = NetworkResponse.Loading

        //Async Program
        viewModelScope.launch {
            //Api Call
            val response = weatherApi.getWeather(Constant.apikey, city)

            //Error Handling
            try {
                if(response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                }else{
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }
            }
            catch (e : Exception){
                _weatherResult.value = NetworkResponse.Error("Failed to load data")
            }
        }
    }
}