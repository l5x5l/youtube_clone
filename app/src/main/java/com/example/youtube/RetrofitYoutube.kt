package com.example.youtube

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
}