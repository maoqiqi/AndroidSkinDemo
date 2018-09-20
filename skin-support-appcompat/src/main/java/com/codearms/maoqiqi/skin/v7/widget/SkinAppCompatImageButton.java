package com.codearms.maoqiqi.skin.v7.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.helper.SkinImageViewHelper;
import com.codearms.maoqiqi.skin.helper.SkinViewHelper;
import com.codearms.maoqiqi.skin.widget.Skinable;

/**
 * 定义SkinAppCompatImageButton继承AppCompatImageButton,实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/20 22:42
 */
public class SkinAppCompatImageButton extends AppCompatImageButton implements Skinable {

    /**
     * View更新皮肤帮助类
     */
    private SkinViewHelper skinViewHelper;

    /**
     * ImageView更新皮肤帮助类
     */
    private SkinImageViewHelper skinImageViewHelper;

    public SkinAppCompatImageButton(Context context) {
        this(context, null);
    }

    public SkinAppCompatImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.imageButtonStyle);
    }

    public SkinAppCompatImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        skinViewHelper = new SkinViewHelper(this);
        skinViewHelper.loadFromAttribute(attrs, defStyleAttr);
        skinImageViewHelper = new SkinImageViewHelper(this);
        skinImageViewHelper.loadFromAttribute(attrs, defStyleAttr);
    }

    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        if (skinViewHelper != null) {
            skinViewHelper.setSupportBackgroundResource(resId);
        }
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if (skinImageViewHelper != null) {
            skinImageViewHelper.setSupportImageResource(resId);
        }
    }

    @Override
    public void updateSkin() {
        if (skinViewHelper != null) {
            skinViewHelper.updateSkin();
        }
        if (skinImageViewHelper != null) {
            skinImageViewHelper.updateSkin();
        }
    }
}