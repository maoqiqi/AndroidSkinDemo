package com.codearms.maoqiqi.skin.v4.widget;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.helper.SkinProgressBarHelper;
import com.codearms.maoqiqi.skin.helper.SkinViewHelper;
import com.codearms.maoqiqi.skin.widget.Skinable;

/**
 * 定义SkinContentLoadingProgressBar继承ContentLoadingProgressBar,实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/22 0:12
 */
public class SkinContentLoadingProgressBar extends ContentLoadingProgressBar implements Skinable {

    /**
     * View更新皮肤帮助类
     */
    private SkinViewHelper skinViewHelper;

    /**
     * ProgressBar更新皮肤帮助类
     */
    private SkinProgressBarHelper skinProgressBarHelper;

    public SkinContentLoadingProgressBar(@NonNull Context context) {
        this(context, null);
    }

    public SkinContentLoadingProgressBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        skinViewHelper = new SkinViewHelper(this);
        skinViewHelper.loadFromAttribute(attrs, 0);
        skinProgressBarHelper = new SkinProgressBarHelper(this);
        skinProgressBarHelper.loadFromAttribute(attrs, 0);
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
        if (skinProgressBarHelper != null) {
            skinProgressBarHelper.updateSkin();
        }
    }
}