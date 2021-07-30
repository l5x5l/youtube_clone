package com.example.youtube.main.homeFragment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtube.ClassBottomSheet
import com.example.youtube.MainActivity
import com.example.youtube.R
import com.example.youtube.VideoActivity
import com.example.youtube.databinding.ItemVideoBinding
import com.example.youtube.main.data.SearchVideoMeta

class AdapterSearchVideo (private val context: Context, private var dataList : List<SearchVideoMeta>) : RecyclerView.Adapter<AdapterSearchVideo.ViewHolder>(){

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemVideoBinding

    class ViewHolder(private val binding : ItemVideoBinding) : RecyclerView.ViewHolder(binding.root){
        val videoTitle = binding.videoTitle
        val thumbnail = binding.thumbnail
        val information = binding.videoInformation
        val userProfile = binding.profileSrc
        val userName = binding.userName
        val progress = binding.progress
        val mainLayout = binding.mainLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemVideoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.videoTitle.text = dataList[position].snippet.title
        Glide.with(context).load(dataList[position].snippet.thumbnails.high.url).into(holder.thumbnail)
        holder.information.text = "임시 조회수"
        holder.userName.text = dataList[position].snippet.channelTitle
        holder.userProfile.setImageResource(R.drawable.ic_launcher_background) // 임시
        holder.mainLayout.setOnLongClickListener {
            val bottomSheet = ClassBottomSheet()
            bottomSheet.show((context as MainActivity).supportFragmentManager, bottomSheet.tag)
            true
        }
        holder.mainLayout.setOnClickListener {
            goToVideoActivity(dataList[position].id.videoId, dataList[position].snippet.channelId)
        }
    }

    override fun getItemCount(): Int = dataList.size

    private fun goToVideoActivity(videoId : String, userId : String) {
        val intent = Intent(context as MainActivity, VideoActivity::class.java)
        intent.putExtra("videoId", videoId)
        intent.putExtra("userId", userId)
        context.startActivity(intent)
    }

    fun changeDataList(newData : List<SearchVideoMeta>){
        dataList = newData
        notifyDataSetChanged()
    }
}