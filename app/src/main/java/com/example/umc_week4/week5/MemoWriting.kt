package com.example.umc_week4.week5

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.umc_week4.R
import com.example.umc_week4.databinding.ActivityMemoWritingBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MemoWriting : AppCompatActivity() {

    private lateinit var binding: ActivityMemoWritingBinding
    private var date:String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoWritingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDate()
        setBtn()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDate() {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM월 dd일")
        date = current.format(formatter).toString()
    }

    private fun setBtn() {
        binding.addBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MemoMain::class.java).apply {
                putExtra("title",binding.title.text.toString())
                putExtra("content",binding.content.text.toString())
                putExtra("date",date)
            }
            setResult(RESULT_OK,intent)
            finish()
        })

    }
}