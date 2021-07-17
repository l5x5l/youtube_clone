package com.example.youtube

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.databinding.ItemVideoBinding

class DataVideoAdapter (context: Context, private val dataList : ArrayList<DataVideo>, onClick : (String, Int, Int, String, String) -> Unit) : RecyclerView.Adapter<DataVideoAdapter.ViewHolder>(){

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemVideoBinding

    // 일단 context 추가
    private val context = context
    private val onClick = onClick

    class ViewHolder(private val binding : ItemVideoBinding) : RecyclerView.ViewHolder(binding.root){
        var videoTitle = binding.videoTitle
        var videoThumbnail = binding.thumbnail
        var videoInformation = binding.videoInformation
        var userProfile = binding.profileSrc
        var userName = binding.userName
        var progress = binding.progress
        var mainLayout = binding.mainLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemVideoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = "조회수 " + getInformationString(dataList[position].watch_count) + '\u00b7' + " " + dataList[position].day_count
        holder.videoThumbnail.setImageResource(dataList[position].thumbnail)
        holder.videoTitle.text = dataList[position].video_title
        holder.videoInformation.text = info
        holder.userName.text = dataList[position].user_name
        holder.userProfile.setImageResource(dataList[position].user_profile)
        holder.progress.progress = dataList[position].progress
        holder.mainLayout.setOnClickListener {
            onClick(dataList[position].user_name, dataList[position].user_profile, R.raw.ani, dataList[position].video_title, info)
        }
        holder.mainLayout.setOnLongClickListener {
            val bottomSheet = ClassBottomSheet()
            bottomSheet.show((context as MainActivity).supportFragmentManager, bottomSheet.tag)
            true
        }
    }

    override fun getItemCount(): Int = dataList.size

    private fun getInformationString(watchCount : Int) : String {
        return when(watchCount / 1000){
            0 -> watchCount.toString() + "회 "
            in 1..10 -> (watchCount / 1000).toString() + "천회 "
            else -> (watchCount / 10000).toString() + "만회 "
        }
    }
}