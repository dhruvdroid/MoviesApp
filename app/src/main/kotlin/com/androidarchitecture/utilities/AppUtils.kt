package com.androidarchitecture.utilities

import android.content.Context
import android.util.DisplayMetrics


//
// Created by Dhruv on 15/08/20.
//

object AppUtils {
    fun calculateNoOfColumns(
        context: Context,
        columnWidthDp: Float
    ): Int { // For example columnWidthdp=180
        val displayMetrics: DisplayMetrics = context.getResources().getDisplayMetrics()
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt()
    }

}