package com.example.umc_week4.week4

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.umc_week4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var memoSave : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener(View.OnClickListener {

            var memo : String = binding.editTxt.text.toString().trim()

            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("memo",memo)
            startActivity(intent)

        })
    }

    override fun onResume() {
        super.onResume()
        if(memoSave.equals("")){
            return
        }
        else binding.editTxt.setText(memoSave)
    }

    override fun onPause() {
        super.onPause()
        memoSave = binding.editTxt.text.toString().trim()
    }

    override fun onRestart() {
        super.onRestart()
        showDialog()
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("알림")
            .setMessage("이어서 작성하시겠어요?")
            .setPositiveButton("예",DialogInterface.OnClickListener { dialog, id ->
                dialog.dismiss()
            })
            .setNegativeButton("아니오",DialogInterface.OnClickListener { dialog, id ->
                binding.editTxt.setText("")
                memoSave = ""
                dialog.dismiss()
            })

        builder.show()
    }
}