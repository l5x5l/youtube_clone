package com.example.youtube

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.databinding.ItemCategoryBinding

class DataCategoryAdapter(var context: Context, private val dataList : ArrayList<DataCategory> ) : RecyclerView.Adapter<DataCategoryAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemCategoryBinding

    class ViewHolder(private val binding : ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root){
        var name = binding.tvCategory
        var icon = binding.icCategory
        var background = binding.background
        var main = binding.mainLayout
    }

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCategoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = dataList[position].name
        holder.icon.setImageResource(dataList[position].icon)
        holder.background.setImageResource(dataList[position].backgroundSrc)
        holder.main.setOnClickListener{
            (context as MainActivity).showMovieFragment()
        }
    }
}