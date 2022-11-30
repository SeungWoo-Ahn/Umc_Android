package com.example.umc_week4.week9.response

import com.example.umc_week4.week9.dto.MainDto
import com.example.umc_week4.week9.dto.WeatherDto
import com.google.gson.annotations.SerializedName

class WeatherResponse() {
    @SerializedName("weather") var weather = ArrayList<WeatherDto>()
    @SerializedName("main") var main: MainDto? = null
    @SerializedName("name") var name: String ?= null
}