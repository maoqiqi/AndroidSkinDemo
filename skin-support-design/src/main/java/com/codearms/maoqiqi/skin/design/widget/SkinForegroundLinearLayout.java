package com.codearms.maoqiqi.skin.design.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.internal.ForegroundLinearLayout;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.helper.SkinLinearLayoutCompatHelper;
import com.codearms.maoqiqi.skin.helper.SkinViewHelper;
import com.codearms.maoqiqi.skin.widget.Skinable;

/**
 * 定义SkinForegroundLinearLayout继承ForegroundLinearLayout,实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/12/10 17:47
 */
@SuppressLint("RestrictedApi")
public class SkinForegroundLinearLayout extends ForegroundLinearLayout implements Skinable {

    /**
     * View更新皮肤帮助类
     */
    private SkinViewHelper skinViewHelper;

    /**
     * LinearLayoutCompat更新皮肤帮助类
     */
    private SkinLinearLayoutCompatHelper skinLinearLayoutCompatHelper;

    public SkinForegroundLinearLayout(Context context) {
        this(context, null);
    }

    public SkinForegroundLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinForegroundLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        skinViewHelper = new SkinViewHelper(this);
        skinViewHelper.loadFromAttribute(attrs, defStyle);
        skinLinearLayoutCompatHelper = new SkinLinearLayoutCompatHelper(this);
        skinLinearLayoutCompatHelper.loadFromAttribute(attrs, defStyle);
    }

    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        if (skinViewHelper != null) {
            skinViewHelper.setSupportBackgroundResource(resId);
        }
    }

    @Override
    public void updateSkin() {
        if (skinViewHelper != null) {
            skinViewHelper.updateSkin();
        }
        if (skinLinearLayoutCompatHelper != null) {
            skinLinearLayoutCompatHelper.updateSkin();
        }
    }
}