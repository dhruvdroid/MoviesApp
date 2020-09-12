package com.androidarchitecture.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.androidarchitecture.R

//
// Created by Dhruv on 16/08/20.
//
class ListDecorator : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = parent.context.resources
            .getDimension(R.dimen.settings_item_bottom_margin).toInt()
        outRect.left = parent.context.resources
            .getDimension(R.dimen.settings_item_bottom_margin).toInt()
    }
}