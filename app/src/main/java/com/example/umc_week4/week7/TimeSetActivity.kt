package com.example.umc_week4.week7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_week4.databinding.ActivityTimeSetBinding
import com.example.umc_week4.week5.MemoMain

class TimeSetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimeSetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimeSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBtn()
    }

    private fun setBtn() {
        binding.btn.setOnClickListener {
            val intent = Intent(this, TimerActivity::class.java).apply {
                var minute = binding.minute.text.toString()
                var second = binding.second.text.toString()

                if(minute.equals("")){
                    minute = "0"
                }
                if(second.equals("")){
                    second = "0"
                }
                putExtra("minute",minute)
                putExtra("second",second)
            }
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}