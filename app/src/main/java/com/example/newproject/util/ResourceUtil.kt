package com.example.newproject.util

import com.example.newproject.App


object ResourceUtil {
    private fun getDP() = App.instance.resources.displayMetrics.density

    internal fun dipf(f: Float) = f * getDP()

    internal fun dipf(i: Int) = i * getDP()

    internal fun dip(i: Int) = (i * getDP()).toInt()

    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId: Int =
            App.instance.getResources().getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = App.instance.getResources().getDimensionPixelSize(resourceId)
        }
        return result
    }
}