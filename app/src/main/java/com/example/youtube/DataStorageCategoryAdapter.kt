package com.example.youtube

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.youtube.databinding.ItemStorageContentListviewBinding

class DataStorageCategoryAdapter(context: Context, private val dataList : ArrayList<DataStorageCategory>) : BaseAdapter() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemStorageContentListviewBinding

    override fun getCount(): Int = dataList.size

    override fun getItem(position: Int):Any = dataList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        binding = ItemStorageContentListviewBinding.inflate(inflater, parent, false)


        binding.icCategory.setImageResource(dataList[position].icon)
        binding.tvCategory.text = dataList[position].name
        if (dataList[position].count > 0){
            binding.tvMeta.text = "동영상 " + dataList[position].count.toString() + "개"
            binding.tvMeta.visibility = View.VISIBLE
        }
        if (dataList[position].premium){
            binding.icCheck.setImageResource(R.drawable.ic_check_circle)
            binding.icCheck.visibility = View.VISIBLE
        }
        return binding.root
    }
}