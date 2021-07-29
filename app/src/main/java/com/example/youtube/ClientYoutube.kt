package com.example.youtube

//import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientYoutube {
    private var instance : Retrofit? = null
    //private val gson = GsonBuilder().setLenient().create()

    fun getInstance() : Retrofit{
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return instance!!
    }
}