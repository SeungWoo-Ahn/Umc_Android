package com.example.umc_week4.week6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_week4.R
import com.example.umc_week4.databinding.ActivityWeek6Binding

class Week6Activity : AppCompatActivity() {

    private lateinit var binding: ActivityWeek6Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeek6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavigation()
    }

    private fun setNavigation() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.frame.id,HomeFragment())
            .commitAllowingStateLoss()

        binding.navView.run {
            setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(binding.frame.id, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.user -> {
                        supportFragmentManager.beginTransaction()
                            .replace(binding.frame.id, UserFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.setting -> {
                        supportFragmentManager.beginTransaction()
                            .replace(binding.frame.id, SettingFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.home

        }
    }
}