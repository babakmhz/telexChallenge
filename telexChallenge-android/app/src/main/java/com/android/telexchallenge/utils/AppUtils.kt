package com.android.telexchallenge.utils

class AppUtils {
    companion object {
        fun isVerticalList(item: String): Boolean {
            return item.toLowerCase().contains("vertical")
        }
    }
}