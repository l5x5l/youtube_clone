package com.example.youtube.main.movieFragment

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class HorizontalLinearManager(context: Context)  : LinearLayoutManager(context) {
    init{
        this.orientation = LinearLayoutManager.HORIZONTAL
    }
}