package com.example.youtube

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.databinding.ItemPlaylistBinding

class DataPlaylistAdapter(context: Context, private val dataList : ArrayList<DataPlaylist>) : RecyclerView.Adapter<DataPlaylistAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemPlaylistBinding

    class ViewHolder(private val binding : ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root) {
        var displayImg = binding.icPlaylist
        var title = binding.tvPlaylist
        var info = binding.tvMeta
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPlaylistBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.displayImg.setImageResource(dataList[position].display)
        holder.title.text = dataList[position].name
        if (dataList[position].count > 0) {
            holder.info.visibility = View.VISIBLE
            holder.info.text = "동영상 " + dataList[position].count.toString() + "개"
        }
    }

    override fun getItemCount(): Int = dataList.size
}