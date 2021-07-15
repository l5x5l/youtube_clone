package com.example.youtube

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.databinding.ItemSubscribeUserBinding

class DataUserAdapter(context: Context, private val dataList : ArrayList<DataUser>) : RecyclerView.Adapter<DataUserAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemSubscribeUserBinding

    class ViewHolder(private val binding : ItemSubscribeUserBinding) : RecyclerView.ViewHolder(binding.root) {
        var profileImg = binding.profileImg
        var profileName = binding.profileName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemSubscribeUserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.profileName.text = dataList[position].name
        holder.profileImg.setImageResource(dataList[position].profileImg)
    }

    override fun getItemCount(): Int = dataList.size
}