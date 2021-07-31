package com.example.youtube.main.data

data class MovieMeta(val title : String, val poster_path : String)
data class Movie(val results : List<MovieMeta>)