package com.codearms.maoqiqi.skin.widget;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.codearms.maoqiqi.skin.helper.SkinLinearLayoutHelper;
import com.codearms.maoqiqi.skin.helper.SkinViewHelper;

/**
 * 定义SkinLinearLayout继承LinearLayout,实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/10 20:39
 */
public class SkinLinearLayout extends LinearLayout implements Skinable {

    /**
     * View更新皮肤帮助类
     */
    private SkinViewHelper skinViewHelper;

    /**
     * LinearLayout更新皮肤帮助类
     */
    private SkinLinearLayoutHelper skinLinearLayoutHelper;

    public SkinLinearLayout(Context context) {
        this(context, null);
    }

    public SkinLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        skinViewHelper = new SkinViewHelper(this);
        skinViewHelper.loadFromAttribute(attrs, defStyleAttr);
        skinLinearLayoutHelper = new SkinLinearLayoutHelper(this);
        skinLinearLayoutHelper.loadFromAttribute(attrs, defStyleAttr);
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
        if (skinLinearLayoutHelper != null) {
            skinLinearLayoutHelper.updateSkin();
        }
    }
}