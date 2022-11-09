package com.example.umc_week4.week5

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_week4.databinding.MemoItemBinding

class MemoAdapter(private val listData: ArrayList<Memo>): RecyclerView.Adapter<MemoAdapter.DataViewHolder>() {

    private lateinit var context: Context
    inner class DataViewHolder(private val viewBinding: MemoItemBinding): RecyclerView.ViewHolder(viewBinding.root) {

        fun bind (memo: Memo) {
            viewBinding.title.setText(memo.title)
            viewBinding.date.text = memo.date
            viewBinding.content.setText(memo.content)
            viewBinding.memoSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
                if (b) {
                    listData[adapterPosition].isSwitch = false
                }
                else {
                    listData[adapterPosition].isSwitch =true
                }
            })
            viewBinding.root.setOnClickListener(View.OnClickListener {
                viewBinding.title.isEnabled = true
                viewBinding.content.isEnabled = true
                viewBinding.title.requestFocus()
            })
            viewBinding.root.setOnLongClickListener(View.OnLongClickListener {
                listData.remove(listData[adapterPosition])
                notifyItemRemoved(adapterPosition)
                Toast.makeText(context,"삭제되었습니다",Toast.LENGTH_SHORT).show()

                return@OnLongClickListener(true)
            })
            viewBinding.title.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    listData[adapterPosition].title = p0.toString().trim()
                }

            })
            viewBinding.content.addTextChangedListener(object  : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {                }

                override fun afterTextChanged(p0: Editable?) {
                    listData[adapterPosition].content = p0.toString().trim()
                }

            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = MemoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        context = parent.context
        return DataViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(listData.get(position))
    }

    override fun getItemCount(): Int = listData.size

}