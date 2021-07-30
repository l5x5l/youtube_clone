package com.example.youtube

import com.example.youtube.main.data.Users
import com.example.youtube.main.data.Videos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitYoutube {
    @GET("videos")
    fun getVideosPopular(
        @Query("key") key : String = "--",
        @Query("part") part : String = "snippet, statistics",
        @Query("chart") chart : String = "mostPopular",
        @Query("regionCode") regionCode : String = "KR",
        @Query("maxResults") maxResults : Int = 10
    ) : Call<Videos>

    @GET("videos")
    fun getVideosDetail(
        @Query("key") key : String = "--",
        @Query("part") part : String = "snippet, statistics",
        @Query("id") id : String
    ) : Call<Videos>

    @GET("subscriptions")
    fun getSubscribes(
            @Query("key") key : String = "--",
            @Query("part") part : String = "snippet",
            @Query("maxResults") maxResult : Int = 50,
            @Query("channelId") channelId : String = "UCzEJft60ZckvPHoJt1Dgf3A"
    ) : Call<Users>

    @GET("search")
    fun getChannelVideo(
            @Query("key") key : String = "--",
            @Query("part") part : String = "snippet",
            @Query("type") type : String = "video",
            @Query("order") order : String = "date",
            @Query("channelId") channelId : String
    ) : Call<Videos>
}
