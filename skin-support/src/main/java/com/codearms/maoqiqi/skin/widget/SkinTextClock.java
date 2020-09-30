package com.codearms.maoqiqi.skin.widget;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextClock;

import com.codearms.maoqiqi.skin.helper.SkinTextViewHelper;
import com.codearms.maoqiqi.skin.helper.SkinViewHelper;

/**
 * 定义SkinTextClock继承TextClock,实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/9 16:34
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class SkinTextClock extends TextClock implements Skinable {

    /**
     * View更新皮肤帮助类
     */
    private SkinViewHelper skinViewHelper;

    /**
     * TextView更新皮肤帮助类
     */
    private SkinTextViewHelper skinTextViewHelper;

    public SkinTextClock(Context context) {
        this(context, null);
    }

    public SkinTextClock(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinTextClock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        skinViewHelper = new SkinViewHelper(this);
        skinViewHelper.loadFromAttribute(attrs, defStyleAttr);
        skinTextViewHelper = new SkinTextViewHelper(this);
        skinTextViewHelper.loadFromAttribute(attrs, defStyleAttr);
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
    public void updateSkin() {
        if (skinViewHelper != null) {
            skinViewHelper.updateSkin();
        }
        if (skinTextViewHelper != null) {
            skinTextViewHelper.updateSkin();
        }
    }
}