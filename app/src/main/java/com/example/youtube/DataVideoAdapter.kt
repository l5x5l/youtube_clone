package com.example.youtube

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.databinding.ItemVideoBinding

// onClick : (String, Int, Int, String, String) -> Unit
class DataVideoAdapter (context: Context, private val dataList : ArrayList<DataVideo>) : RecyclerView.Adapter<DataVideoAdapter.ViewHolder>(){

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemVideoBinding

    // 일단 context 추가
    private val context = context
    //private val onClick = onClick

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
            //onClick(dataList[position].user_name, dataList[position].user_profile, dataList[position].dataId, dataList[position].video_title, info)
            goToVideoActivity(dataList[position].user_name, dataList[position].user_profile, dataList[position].dataId, dataList[position].video_title, info)
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
            in 1..9 -> (watchCount / 1000).toString() + "천회 "
            else -> (watchCount / 10000).toString() + "만회 "
        }
    }

    fun goToVideoActivity(user_name : String, user_profile : Int, video_id : Int, video_title : String, video_info : String) : Unit {
        val intent = Intent(context as MainActivity, VideoActivity::class.java)
        intent.putExtra("id", video_id)
        intent.putExtra("user", user_profile)
        intent.putExtra("userName", user_name)
        intent.putExtra("subText", video_info)
        intent.putExtra("videoTitle", video_title)
        context.startActivity(intent)

    }
}