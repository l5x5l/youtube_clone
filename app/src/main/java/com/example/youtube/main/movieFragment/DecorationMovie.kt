package com.example.youtube.main.movieFragment

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.dpToPx

class DecorationMovie(context: Context) : RecyclerView.ItemDecoration() {
    private var size12 : Int = dpToPx(context, 12)
    private var size6 : Int = dpToPx(context, 6)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val last = parent.childCount

        when (position) {
            0 -> {
                outRect.left = size12
                outRect.right = size6
            }
            last - 1 -> {
                outRect.left = size6
                outRect.right = size12
            }
            else -> {
                outRect.left = size6
                outRect.right = size6
            }
        }
    }
}