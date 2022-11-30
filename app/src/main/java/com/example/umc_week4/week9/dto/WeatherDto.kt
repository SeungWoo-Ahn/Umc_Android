package com.example.umc_week4.week9.dto

import com.google.gson.annotations.SerializedName

class WeatherDto {
    @SerializedName("id") var id: Int = 0
    @SerializedName("main") var main : String? = null
    @SerializedName("description") var description: String? = null
    @SerializedName("icon") var icon : String? = null
}