package com.example.youtube

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.youtube.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityVideoBinding
    private var stopPosition : Int = 0
    private var percentage : Float = 0.0f
    private var videoLength : Int = 0
    private var userOrder : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val videoId = intent.getIntExtra("id", 0)
        val userIcon = intent.getIntExtra("user", 0)
        var userName = intent.getStringExtra("userName")
        val subText = intent.getStringExtra("subText")
        val videoTitle = intent.getStringExtra("videoTitle")
        val videoPath = "android.resource://$packageName/$videoId"
        var videoUri : Uri = Uri.parse(videoPath)


        binding.videoTitle.text = videoTitle
        binding.userName.text = userName
        binding.subText.text = subText
        binding.userProfileSrc.setImageResource(userIcon)

        binding.videoView.setOnClickListener{videoViewOnClick()}
        binding.videoView.setVideoURI(videoUri)
        binding.videoView.setOnPreparedListener{
            videoLength = binding.videoView.duration
            binding.videoView.start()
        }
    }

    override fun onResume() {
        //여기 이상하다
        super.onResume()

        binding.videoView.seekTo(stopPosition)
        binding.videoView.start()


    }

    override fun onPause() {
        super.onPause()

        binding.videoView.pause()
        stopPosition = binding.videoView.currentPosition
        //percentage = (stopPosition.toFloat() / videoLength)
        //Toast.makeText(this, percentage.toString(), Toast.LENGTH_SHORT).show()

    }

    override fun onDestroy() {
        percentage = (stopPosition.toFloat() / videoLength)
        percentage*= 100
        //Toast.makeText(this,  "전체 영상 중 "+percentage.toInt().toString() + "% 가 실행됨", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    private fun videoViewOnClick(){
        if (binding.videoView.isPlaying){
            binding.videoView.pause()
        }
        else {
            binding.videoView.start()
        }
    }
}