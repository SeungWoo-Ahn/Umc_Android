package com.example.umc_week4.week5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_week4.databinding.ActivityMemoMainBinding

class MemoMain : AppCompatActivity() {

    private lateinit var binding: ActivityMemoMainBinding
    private lateinit var getMemoData: ActivityResultLauncher<Intent>
    private val dataList: ArrayList<Memo> = arrayListOf()
    private val adapter = MemoAdapter(dataList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        resultLauncher()
        setAddBtn()
        setRecycler()
    }

    private fun setRecycler() {
        dataList.apply {
            add(Memo("Title","1","10월 22일",false))
            add(Memo("Title","2","10월 22일",false))
            add(Memo("Title","3","10월 22일",false))
            add(Memo("Title","4","10월 22일",false))
        }
        binding.memoRc.adapter = adapter
        binding.memoRc.layoutManager = LinearLayoutManager(this)

    }

    private fun resultLauncher() {
        getMemoData = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {result ->
            if (result.resultCode == RESULT_OK){
                val title = result.data?.getStringExtra("title")
                val date = result.data?.getStringExtra("date")
                val content = result.data?.getStringExtra("content")
                dataList.add(Memo(title,content,date,false))
                adapter.notifyItemInserted(adapter.itemCount)

            }
        }
    }

    private fun setAddBtn() {
        binding.addBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MemoWriting::class.java)
            getMemoData.launch(intent)
        })
    }

    override fun onResume() {
        super.onResume()
        if(adapter.itemCount == 0) {
            binding.emptyView.visibility = View.VISIBLE
        }
        else {
            binding.emptyView.visibility = View.INVISIBLE
        }
        binding.memoCnt.text = adapter.itemCount.toString() + "개의 메모"
    }
}