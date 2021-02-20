package com.android.telexchallenge

import com.android.telexchallenge.utils.AppUtils
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AppUtils {

    @Test
    fun smokeTest(){
        assertTrue(true)
    }

    @Test
    fun isVerticalShouldReturnTrue(){
        val input = "this is vertical"
        val input2 = "vertical___"
        val input3 = "thisIsVertical"
        assertTrue(AppUtils.isVerticalList(input))
        assertTrue(AppUtils.isVerticalList(input2))
        assertTrue(AppUtils.isVerticalList(input3))
    }
    @Test
    fun isVerticalShouldReturnFalse(){
        val input = "this is verstsdical"
        assertFalse(AppUtils.isVerticalList(input))
    }
}