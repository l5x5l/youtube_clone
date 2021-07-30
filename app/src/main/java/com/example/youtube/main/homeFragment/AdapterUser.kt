package com.example.youtube.main.homeFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtube.databinding.ItemSubscribeUserBinding
import com.example.youtube.main.data.UserMeta

class AdapterUser(private val context: Context, private var dataList : List<UserMeta>) : RecyclerView.Adapter<AdapterUser.ViewHolder>(){

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemSubscribeUserBinding

    class ViewHolder(private val binding : ItemSubscribeUserBinding) : RecyclerView.ViewHolder(binding.root) {
        val img = binding.profileImg
        val userName = binding.profileName
        var userId = "--"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemSubscribeUserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList[position].snippet.thumbnails.high.url).into(holder.img)
        holder.userName.text = dataList[position].snippet.title
        holder.userId = dataList[position].snippet.resourceId.channelId
    }

    override fun getItemCount(): Int = dataList.size
}