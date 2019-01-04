package com.codearms.maoqiqi.skin.v7.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.helper.SkinViewHelper;
import com.codearms.maoqiqi.skin.v7.helper.SkinLinearLayoutCompatHelper;
import com.codearms.maoqiqi.skin.widget.Skinable;

/**
 * 定义SkinLinearLayoutCompat继承LinearLayoutCompat,实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/14 22:04
 */
public class SkinLinearLayoutCompat extends LinearLayoutCompat implements Skinable {

    /**
     * View更新皮肤帮助类
     */
    private SkinViewHelper skinViewHelper;

    /**
     * LinearLayoutCompat更新皮肤帮助类
     */
    private SkinLinearLayoutCompatHelper skinLinearLayoutCompatHelper;

    public SkinLinearLayoutCompat(Context context) {
        this(context, null);
    }

    public SkinLinearLayoutCompat(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinLinearLayoutCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        skinViewHelper = new SkinViewHelper(this);
        skinViewHelper.loadFromAttribute(attrs, defStyleAttr);
        skinLinearLayoutCompatHelper = new SkinLinearLayoutCompatHelper(this);
        skinLinearLayoutCompatHelper.loadFromAttribute(attrs, defStyleAttr);
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