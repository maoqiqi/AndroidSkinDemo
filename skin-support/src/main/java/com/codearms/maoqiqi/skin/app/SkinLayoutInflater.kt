package com.codearms.maoqiqi.skin.app

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Custom SkinLayoutInflater interface to create the view.
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/2 20:53
 */
interface SkinLayoutInflater {
    fun createView(parent: View, name: String, context: Context, attrs: AttributeSet): View?
}