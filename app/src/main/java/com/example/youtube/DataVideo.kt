package com.example.youtube

data class DataVideo(val video_title : String, val thumbnail: Int, val user_name : String, val user_profile : Int,
                     val watch_count : Int, val day_count : String, val dataId : Int,
                     val good_count : Int = 0, val bad_count : Int = 0, val progress : Int = 0)
