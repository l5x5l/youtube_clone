package com.example.youtube.main.customAdapter

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
import com.example.youtube.main.data.VideoMeta

class AdapterVideo(private val context: Context, private var dataList : List<VideoMeta>) : RecyclerView.Adapter<AdapterVideo.ViewHolder>(){

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemVideoBinding
    private var profileData : Map<String, String> = mutableMapOf()

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
        holder.information.text = "조회수 " + getInformationString(dataList[position].statistics.viewCount)
        holder.userName.text = dataList[position].snippet.channelTitle
        if (profileData.containsKey(dataList[position].snippet.channelId)){
            Glide.with(context).load(profileData[dataList[position].snippet.channelId]).into(holder.userProfile)
        }
        else {
            holder.userProfile.setImageResource(R.drawable.ic_launcher_background) // 임시
        }
        holder.mainLayout.setOnLongClickListener {
            val bottomSheet =ClassBottomSheet()
            bottomSheet.show((context as MainActivity).supportFragmentManager, bottomSheet.tag)
            true
        }
        holder.mainLayout.setOnClickListener {
            goToVideoActivity(dataList[position].id, dataList[position].snippet.channelId)
        }
    }

    override fun getItemCount(): Int = dataList.size

    private fun getInformationString(watchCount : Long) : String {
        return when(watchCount / 1000){
            0L -> watchCount.toString() + "회 "
            in 1..9 -> (watchCount / 1000).toString() + "천회 "
            else -> (watchCount / 10000).toString() + "만회 "
        }
    }

    private fun goToVideoActivity(videoId : String, userId : String) {
        val intent = Intent(context as MainActivity, VideoActivity::class.java)
        intent.putExtra("videoId", videoId)
        intent.putExtra("userId", userId)
        context.startActivity(intent)
    }

    fun changeDataList(newData : List<VideoMeta>, newProfile : Map<String, String>? = null){
        dataList = newData
        if (newProfile != null){
            profileData = newProfile
        }
        notifyDataSetChanged()
    }
}