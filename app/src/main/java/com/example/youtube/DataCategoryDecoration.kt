package com.example.youtube

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DataCategoryDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private var size12 : Int = dpToPx(context, 12)
    private var size6 : Int = dpToPx(context, 6)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val lp : GridLayoutManager.LayoutParams = view.layoutParams as GridLayoutManager.LayoutParams
        val xIndex = lp.spanIndex

        if (position == 0 || position == 1){
            outRect.top = size12
            outRect.bottom = size12
        } else {
            outRect.bottom = size12
        }

        if (xIndex == 0){
            outRect.left = size12
            outRect.right = size12
        } else {
            outRect.right = size12
        }
    }
}