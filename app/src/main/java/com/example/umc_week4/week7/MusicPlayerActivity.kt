package com.example.umc_week4.week7

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.example.umc_week4.R
import com.example.umc_week4.databinding.ActivityMusicPlayerBinding

class MusicPlayerActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding: ActivityMusicPlayerBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()
    var isPause:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.play.setOnClickListener(this)
        binding.pause.setOnClickListener(this)
        setSeekBar()
    }

    private fun setSeekBar() {
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, i: Int, b: Boolean) {
                if (b) {
                    mediaPlayer.seekTo(i*1000)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                mediaPlayer.pause()
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                mediaPlayer.seekTo(binding.seekBar!!.progress)

                if ((binding.seekBar.progress > 0).and(binding.play.visibility == View.GONE)) {
                    mediaPlayer.start()
                }
            }

        })
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            //플레이 버튼 클릭
            binding.play.id -> {
                binding.play.visibility = View.GONE
                binding.pause.visibility = View.VISIBLE
                if (isPause) {
                    mediaPlayer.seekTo(mediaPlayer.currentPosition)
                    mediaPlayer.start()
                    isPause = false
                }
                else {
                    mediaPlayer = MediaPlayer.create(applicationContext,R.raw.picknick)
                    mediaPlayer.start()
                }
                Toast.makeText(this,"음악을 재생하고 있어요",Toast.LENGTH_SHORT).show()
                initializeSeekBar()
                mediaPlayer.setOnCompletionListener {
                    binding.play.visibility = View.VISIBLE
                    binding.pause.visibility = View.GONE
                    Toast.makeText(this,"음악을 다 들었어요",Toast.LENGTH_SHORT).show()
                }
            }
            //정지 버튼 클릭
            binding.pause.id -> {
                binding.play.visibility = View.VISIBLE
                binding.pause.visibility = View.GONE
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    isPause = true
                    Toast.makeText(this,"음악을 중지했어요",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initializeSeekBar() {
        binding.seekBar.max = mediaPlayer.seconds

        runnable = Runnable {
            binding.seekBar.progress = mediaPlayer.currentSeconds

            makeTimeStr(mediaPlayer.currentSeconds,binding.playTime)
            makeTimeStr(mediaPlayer.seconds,binding.totalTime)

            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable,1000)

    }

    val MediaPlayer.seconds:Int get() {
        return this.duration/1000
    }
    val MediaPlayer.currentSeconds:Int get() {
        return this.currentPosition/1000
    }
    fun makeTimeStr(time:Int, textView: TextView){
        val mStr = String.format("%02d",time/60)
        val mSec = String.format("%02d", time%60)
        textView.text = "$mStr:$mSec"
    }

    override fun onDestroy() {
        super.onDestroy()
        isPause = false
        mediaPlayer.release()
    }
}