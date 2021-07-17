package com.example.youtube

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.youtube.databinding.ItemBottomSheetBinding

class DataBottomSheetOptionAdapter(context: Context, private val dataList : ArrayList<DataBottomSheetOption>) : BaseAdapter() {
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemBottomSheetBinding

    override fun getCount(): Int = dataList.size

    override fun getItem(position: Int): Any = dataList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        binding = ItemBottomSheetBinding.inflate(inflater, parent, false)

        binding.icon.setImageResource(dataList[position].icon)
        binding.text.text = dataList[position].text

        return binding.root
    }

}