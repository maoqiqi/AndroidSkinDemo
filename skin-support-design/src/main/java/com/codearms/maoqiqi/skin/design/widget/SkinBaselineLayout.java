package com.codearms.maoqiqi.skin.design.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.internal.BaselineLayout;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.helper.SkinViewHelper;
import com.codearms.maoqiqi.skin.widget.Skinable;

/**
 * 定义SkinBaselineLayout继承BaselineLayout,实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/12/11 10:21
 */
@SuppressLint("RestrictedApi")
public class SkinBaselineLayout extends BaselineLayout implements Skinable {

    /**
     * View更新皮肤帮助类
     */
    private SkinViewHelper skinViewHelper;

    public SkinBaselineLayout(Context context) {
        this(context, null);
    }

    public SkinBaselineLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinBaselineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        skinViewHelper = new SkinViewHelper(this);
        skinViewHelper.loadFromAttribute(attrs, defStyleAttr);
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
    }
}