package com.codearms.maoqiqi.skin.v7.widget;

import android.content.Context;
import androidx.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.helper.SkinViewHelper;
import com.codearms.maoqiqi.skin.v7.helper.SkinToolbarHelper;
import com.codearms.maoqiqi.skin.widget.Skinable;

/**
 * 定义SkinToolbar继承Toolbar,实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/21 10:28
 */
public class SkinToolbar extends Toolbar implements Skinable {

    /**
     * View更新皮肤帮助类
     */
    private SkinViewHelper skinViewHelper;

    /**
     * Toolbar更新皮肤帮助类
     */
    private SkinToolbarHelper skinToolbarHelper;

    public SkinToolbar(Context context) {
        this(context, null);
    }

    public SkinToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.toolbarStyle);
    }

    public SkinToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        skinViewHelper = new SkinViewHelper(this);
        skinViewHelper.loadFromAttribute(attrs, defStyleAttr);
        skinToolbarHelper = new SkinToolbarHelper(this);
        skinToolbarHelper.loadFromAttribute(attrs, defStyleAttr);
    }

    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        if (skinViewHelper != null) {
            skinViewHelper.setSupportBackgroundResource(resId);
        }
    }

    @Override
    public void setTitleTextAppearance(Context context, int resId) {
        super.setTitleTextAppearance(context, resId);
        if (skinToolbarHelper != null) {
            skinToolbarHelper.setSupportTitleTextAppearance(resId);
        }
    }

    @Override
    public void setSubtitleTextAppearance(Context context, int resId) {
        super.setSubtitleTextAppearance(context, resId);
        if (skinToolbarHelper != null) {
            skinToolbarHelper.setSupportSubtitleTextAppearance(resId);
        }
    }

    @Override
    public void setLogo(int resId) {
        super.setLogo(resId);
        if (skinToolbarHelper != null) {
            skinToolbarHelper.setSupportLogo(resId);
        }
    }

    @Override
    public void setNavigationIcon(int resId) {
        super.setNavigationIcon(resId);
        if (skinToolbarHelper != null) {
            skinToolbarHelper.setSupportNavigationIcon(resId);
        }
    }

    @Override
    public void setPopupTheme(int resId) {
        super.setPopupTheme(resId);
        if (skinToolbarHelper != null) {
            skinToolbarHelper.setSupportPopupTheme(resId);
        }
    }

    @Override
    public void updateSkin() {
        if (skinViewHelper != null) {
            skinViewHelper.updateSkin();
        }
        if (skinToolbarHelper != null) {
            skinToolbarHelper.updateSkin();
        }
    }
}