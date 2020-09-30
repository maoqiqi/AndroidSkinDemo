package com.codearms.maoqiqi.skin.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.codearms.maoqiqi.skin.helper.SkinTextViewHelper
import com.codearms.maoqiqi.skin.helper.SkinViewHelper

/**
 * 定义SkinEditText继承EditText,实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/6 20:47
 */
class SkinEditText @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr), Skinable {

    /**
     * View更新皮肤帮助类
     */
    private val skinViewHelper: SkinViewHelper = SkinViewHelper(this)

    /**
     * TextView更新皮肤帮助类
     */
    private val skinTextViewHelper: SkinTextViewHelper = SkinTextViewHelper(this)

    init {
        skinViewHelper.loadFromAttribute(attrs, defStyleAttr)
        skinTextViewHelper.loadFromAttribute(attrs, defStyleAttr)
    }

    override fun setBackgroundResource(resId: Int) {
        super.setBackgroundResource(resId)
        skinViewHelper.setSupportBackgroundResource(resId)
    }

    override fun setTextAppearance(resId: Int) {
        super.setTextAppearance(resId)
        skinTextViewHelper.setSupportTextAppearance(resId)
    }

    override fun setCompoundDrawablesWithIntrinsicBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
        skinTextViewHelper.setSupportCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
    }

    override fun setCompoundDrawablesRelativeWithIntrinsicBounds(start: Int, top: Int, end: Int, bottom: Int) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)
        skinTextViewHelper.setSupportCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)
    }

    override fun updateSkin() {
        skinViewHelper.updateSkin()
        skinTextViewHelper.updateSkin()
    }
}