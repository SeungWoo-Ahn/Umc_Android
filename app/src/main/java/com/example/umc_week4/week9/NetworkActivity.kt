package com.example.umc_week4.week9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.umc_week4.databinding.ActivityNetworkBinding
import com.example.umc_week4.week9.constant.WeatherInfo
import com.example.umc_week4.week9.response.Response
import com.example.umc_week4.week9.response.WeatherResponse
import com.example.umc_week4.week9.service.ApiService
import com.example.umc_week4.week9.service.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.max
import kotlin.math.min

class NetworkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNetworkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetworkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCurrentWeather()
    }


    //날씨 api 받아오기
    private fun getCurrentWeather(){
        val retrofit = RetrofitClient.dataServie
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(
            WeatherInfo.lat,
            WeatherInfo.lon,
            WeatherInfo.API_KEY
            )

        call.enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: retrofit2.Response<WeatherResponse>
            ) {
                if(response.isSuccessful) {
                    val weatherResponse = response.body()
                    Log.d("weatherResponse", "result: "+weatherResponse.toString())
                    if(weatherResponse != null){
                        var cTemp =  weatherResponse.main!!.temp - 273.15  //켈빈을 섭씨로 변환
                        var minTemp = weatherResponse.main!!.temp_min - 273.15
                        var maxTemp = weatherResponse.main!!.temp_max - 273.15

                        binding.location.text = weatherResponse.name
                        binding.description.text =
                            "'"+"${weatherResponse.weather.get(0).description}"+"'"
                        binding.currentTemp.text = makeTempString(cTemp)
                        binding.maxTemp.text = makeTempString(maxTemp)
                        binding.minTemp.text = makeTempString(minTemp)

                    }

                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("weatherResponse","result: "+t.message)
            }

        })

    }

    fun makeTempString(temp: Double): String{
        return String.format("%.2f 도", temp)
    }



    //강의 예시 코드
    private fun retrofitExample() {
        //웹 브라우저를 열기
        val retrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build() // . 으로 구분해주는 빌더 패턴

        //어떤 주소를 입력함, 그 주소는 APiService안에
        val apiService = retrofit.create(ApiService::class.java)

        //입력한 주소 중에 하나로 연결 시도
        apiService.getCheckEmail("abc@abc.com").enqueue(
            object : Callback<Response> {
                override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
                ) {
                    if (response.isSuccessful) {
                        val  responseData = response.body()

                        if (responseData != null) {
                            Log.d("Retrofit", "Respone\n ${responseData}")
                        }
                    }

                }

                override fun onFailure(call: Call<Response>, t: Throwable) {
                    Log.e("Retrofit","Error!",t)
                }

            }
        )
    }


}