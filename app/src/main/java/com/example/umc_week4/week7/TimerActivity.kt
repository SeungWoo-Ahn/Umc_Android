package com.example.umc_week4.week7

import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.umc_week4.R
import com.example.umc_week4.databinding.ActivityTimerBinding
import java.util.*

class TimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding
    private lateinit var getTimeData: ActivityResultLauncher<Intent>
    private var soundPool:SoundPool? = null
    private val soundId = 1
    var total = 0
    var isRunning = false
    var timerTask: Timer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        soundPool = SoundPool(6,AudioManager.STREAM_MUSIC,0)
        soundPool!!.load(baseContext,R.raw.end_sound,1)
        resultLauncher()
        goSetTime()
        setTimer()
    }

    private fun setTimer() {
        //시작 버튼
        binding.startBtn.setOnClickListener {
            if(total != 0){
                isRunning = !isRunning
                if(isRunning) start() else pause()
            }
        }
        //일시정지 버튼
        binding.resetBtn.setOnClickListener {
            reset()
        }
    }

    private fun goSetTime() {
        binding.timeTxt.setOnClickListener {
            val intent = Intent(this,TimeSetActivity::class.java)
            getTimeData.launch(intent)
        }
    }

    private fun resultLauncher() {
        getTimeData = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {result ->
            if (result.resultCode == RESULT_OK){
                val minute = result.data?.getStringExtra("minute")?.toInt()
                val second = result.data?.getStringExtra("second")?.toInt()
                if (minute != null && second != null) {
                    total = minute*60 + second
                }
                setTimeTxt(total/60,total%60,binding.timeTxt)
            }
        }
    }

    private fun start() {
        binding.startBtn.text = "일시정지"
        timerTask = kotlin.concurrent.timer(period = 1000) {
            total--
            runOnUiThread {
                setTimeTxt(total/60,total%60,binding.timeTxt)
                if(total == 0) {
                    makeBell()
                    reset()
                }
            }
        }
    }

    private fun makeBell() {
        Toast.makeText(applicationContext,"종료되었습니다",Toast.LENGTH_SHORT).show()
        soundPool?.play(soundId,1F,1F,0,0,1F)
    }

    private fun pause() {
        binding.startBtn.text = "재시작"
        timerTask?.cancel()
    }

    private fun reset() {
        timerTask?.cancel()
        total = 0
        isRunning = false
        setTimeTxt(0,0,binding.timeTxt)
        binding.startBtn.text = "시작"
    }
    private fun setTimeTxt(min:Int , sec:Int, time:TextView){
        val mStr = String.format("%02d",total/60)
        val mSec = String.format("%02d", total%60)
        time.text = "$mStr:$mSec"
    }
}