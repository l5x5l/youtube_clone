package com.example.youtube

import com.example.youtube.main.data.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitTMDB {
    @GET("movie/popular")
    fun getPopularMovie(
            @Query("api_key") api_key : String = "--",
            @Query("language") language : String = "ko"
    ) : Call<Movie>

    @GET("movie/top_rated")
    fun getRateMovie(
            @Query("api_key") api_key : String = "--",
            @Query("language") language : String = "ko"
    ) : Call<Movie>

    @GET("movie/upcoming")
    fun getUpcomingMovie(
            @Query("api_key") api_key : String = "--",
            @Query("language") language : String = "ko"
    ) : Call<Movie>
}