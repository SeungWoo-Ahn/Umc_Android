package com.example.umc_week4.week9.service

import com.example.umc_week4.week9.response.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //예시
    @GET("") //Api 주소
    fun getCheckEmail(
        @Query("email") email : String
    ): Call<Response> // call로 response를 받아와야함
}