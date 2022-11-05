package com.example.umc_week4.week4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_week4.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("memo")){
            binding.text.text = intent.getStringExtra("memo")
        }
    }


}