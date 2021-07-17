package com.example.youtube

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.databinding.ItemStorageContentListviewBinding

/*
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
*/
class DataStorageCategoryAdapter(context: Context, dataList : ArrayList<DataStorageCategory>) : RecyclerView.Adapter<DataStorageCategoryAdapter.ViewHolder>(){

    private var dataList = dataList
    private lateinit var binding : ItemStorageContentListviewBinding
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    class ViewHolder(binding : ItemStorageContentListviewBinding) : RecyclerView.ViewHolder(binding.root) {
        var mainLayout = binding.mainLayout
        var name = binding.tvCategory
        var icon = binding.icCategory
        var info = binding.tvMeta
        var premium = binding.icCheck
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemStorageContentListviewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = dataList[position].name
        holder.icon.setImageResource(dataList[position].icon)
        if (dataList[position].premium){
            holder.premium.setImageResource(R.drawable.ic_check_circle)
            holder.premium.visibility = View.VISIBLE
        }
        if (dataList[position].count > 0){
            holder.info.text = "동영상 " + dataList[position].count.toString() + "개"
            holder.info.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int = dataList.size
}