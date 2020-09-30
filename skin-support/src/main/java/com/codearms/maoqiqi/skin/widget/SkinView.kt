package com.codearms.maoqiqi.skin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.codearms.maoqiqi.skin.helper.SkinViewHelper

/**
 * 定义SkinView继承View,实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/5 20:24
 */
class SkinView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), Skinable {

    /**
     * View更新皮肤帮助类
     */
    private val skinViewHelper: SkinViewHelper = SkinViewHelper(this)

    init {
        skinViewHelper.loadFromAttribute(attrs, defStyleAttr)
    }

    override fun setBackgroundResource(resId: Int) {
        super.setBackgroundResource(resId)
        skinViewHelper.setSupportBackgroundResource(resId)
    }

    override fun updateSkin() {
        skinViewHelper.updateSkin()
    }
}