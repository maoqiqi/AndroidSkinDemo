package com.codearms.maoqiqi.skin.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

import com.codearms.maoqiqi.skin.helper.SkinCheckedTextViewHelper;
import com.codearms.maoqiqi.skin.helper.SkinTextViewHelper;
import com.codearms.maoqiqi.skin.helper.SkinViewHelper;

/**
 * 定义SkinCheckedTextView继承CheckedTextView实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/8 20:12
 */
@SuppressLint("AppCompatCustomView")
public class SkinCheckedTextView extends CheckedTextView implements Skinable {

    /**
     * View更新皮肤帮助类
     */
    private SkinViewHelper skinViewHelper;

    /**
     * TextView更新皮肤帮助类
     */
    private SkinTextViewHelper skinTextViewHelper;

    /**
     * CheckedTextView更新皮肤帮助类
     */
    private SkinCheckedTextViewHelper skinCheckedTextViewHelper;

    public SkinCheckedTextView(Context context) {
        this(context, null);
    }

    public SkinCheckedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.checkedTextViewStyle);
    }

    public SkinCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SkinCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        skinViewHelper = new SkinViewHelper(this);
        skinViewHelper.loadFromAttribute(attrs, defStyleAttr);
        skinTextViewHelper = new SkinTextViewHelper(this);
        skinTextViewHelper.loadFromAttribute(attrs, defStyleAttr);
        skinCheckedTextViewHelper = new SkinCheckedTextViewHelper(this);
        skinCheckedTextViewHelper.loadFromAttribute(attrs, defStyleAttr);
    }

    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        if (skinViewHelper != null) {
            skinViewHelper.setSupportBackgroundResource(resId);
        }
    }

    @Override
    public void setTextAppearance(int resId) {
        super.setTextAppearance(resId);
        if (skinTextViewHelper != null) {
            skinTextViewHelper.setSupportTextAppearance(resId);
        }
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(int left, int top, int right, int bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        if (skinTextViewHelper != null) {
            skinTextViewHelper.setSupportCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        }
    }

    @Override
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int start, int top, int end, int bottom) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        if (skinTextViewHelper != null) {
            skinTextViewHelper.setSupportCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        }
    }

    @Override
    public void setCheckMarkDrawable(int resId) {
        super.setCheckMarkDrawable(resId);
        if (skinCheckedTextViewHelper != null) {
            skinCheckedTextViewHelper.setSupportCheckMarkDrawable(resId);
        }
    }

    @Override
    public void updateSkin() {
        if (skinViewHelper != null) {
            skinViewHelper.updateSkin();
        }
        if (skinTextViewHelper != null) {
            skinTextViewHelper.updateSkin();
        }
        if (skinCheckedTextViewHelper != null) {
            skinCheckedTextViewHelper.updateSkin();
        }
    }
}