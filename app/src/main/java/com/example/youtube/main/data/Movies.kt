package com.example.youtube.main.data

data class MovieMeta(val title : String = "__DEFAULT__", val poster_path : String = "__DEFAULT__")
data class Movie(val results : List<MovieMeta>)