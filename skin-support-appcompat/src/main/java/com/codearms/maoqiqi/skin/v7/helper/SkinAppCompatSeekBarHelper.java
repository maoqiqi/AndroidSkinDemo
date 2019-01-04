package com.codearms.maoqiqi.skin.v7.helper;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.appcompat.R;
import com.codearms.maoqiqi.skin.helper.SkinHelper;
import com.codearms.maoqiqi.skin.v7.utils.AppCompatViewUtils;

/**
 * AppCompatSeekBar更新皮肤帮助类,AppCompatSeekBar及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/12/13 11:30
 */
public class SkinAppCompatSeekBarHelper extends SkinHelper<AppCompatSeekBar> {

    private int appTickMarkResId = INVALID_RESOURCES;
    private int appTickMarkTintResId = INVALID_RESOURCES;

    public SkinAppCompatSeekBarHelper(AppCompatSeekBar view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinAppCompatSeekBarHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinAppCompatSeekBarHelper_tickMark)) {
                appTickMarkResId = a.getResourceId(R.styleable.SkinAppCompatSeekBarHelper_tickMark, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinAppCompatSeekBarHelper_tickMarkTint)) {
                appTickMarkTintResId = a.getResourceId(R.styleable.SkinAppCompatSeekBarHelper_tickMarkTint, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
        init();
    }

    /**
     * 应用自定义命名空间TickMark
     */
    private void applySupportAppTickMark() {
        if (isInvalid(appTickMarkResId) || isFromAppcompat(appTickMarkResId)) return;
        String typeName = getTypeName(appTickMarkResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(appTickMarkResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(appTickMarkResId);
        }
        if (drawable == null) return;
        AppCompatViewUtils.setTickMark(view, drawable);
    }

    /**
     * 应用自定义命名空间TickMark着色
     */
    private void applySupportAppTickMarkTint() {
        if (isInvalid(appTickMarkTintResId)) return;
        String typeName = getTypeName(appTickMarkTintResId);
        if (isColor(typeName)) {
            ColorStateList colorStateList = getColorStateList(appTickMarkTintResId);
            if (colorStateList == null) return;
            AppCompatViewUtils.setTickMarkTintList(view, colorStateList);
        }
    }

    @Override
    public void updateSkin() {
        applySupportAppTickMark();
        applySupportAppTickMarkTint();
    }
}