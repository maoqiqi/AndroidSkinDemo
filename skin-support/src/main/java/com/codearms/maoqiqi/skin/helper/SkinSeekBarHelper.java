package com.codearms.maoqiqi.skin.helper;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

import com.codearms.maoqiqi.skin.R;

/**
 * SeekBar更新皮肤帮助类,SeekBar及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/9 19:57
 */
public class SkinSeekBarHelper extends SkinHelper<SeekBar> {

    private int thumbResId = INVALID_RESOURCES;
    private int thumbTintResId = INVALID_RESOURCES;
    private int tickMarkResId = INVALID_RESOURCES;
    private int tickMarkTintResId = INVALID_RESOURCES;

    public SkinSeekBarHelper(SeekBar view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinSeekBarHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinSeekBarHelper_android_thumb)) {
                thumbResId = a.getResourceId(R.styleable.SkinSeekBarHelper_android_thumb, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinSeekBarHelper_android_thumbTint)) {
                thumbTintResId = a.getResourceId(R.styleable.SkinSeekBarHelper_android_thumbTint, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinSeekBarHelper_android_tickMark)) {
                tickMarkResId = a.getResourceId(R.styleable.SkinSeekBarHelper_android_tickMark, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinSeekBarHelper_android_tickMarkTint)) {
                tickMarkTintResId = a.getResourceId(R.styleable.SkinSeekBarHelper_android_tickMarkTint, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 应用Thumb
     */
    private void applySupportThumb() {
        if (thumbResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(thumbResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(thumbResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(thumbResId);
        }
        if (drawable == null) return;
        view.setThumb(drawable);
    }

    /**
     * 应用TickMark
     */
    private void applySupportTickMark() {
        if (tickMarkResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(tickMarkResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(tickMarkResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(tickMarkResId);
        }
        if (drawable == null) return;
        if (IS_NOUGAT) {
            view.setTickMark(drawable);
        }
    }

    /**
     * 应用Thumb着色
     */
    private void applySupportThumbTint() {
        if (thumbTintResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(thumbTintResId);
        if (isColor(typeName)) {
            ColorStateList colorStateList = getColorStateList(thumbTintResId);
            if (colorStateList == null) return;
            if (IS_LOLLIPOP) {
                view.setThumbTintList(colorStateList);
            }
        }
    }

    /**
     * 应用TickMark着色
     */
    private void applySupportTickMarkTint() {
        if (tickMarkTintResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(tickMarkTintResId);
        if (isColor(typeName)) {
            ColorStateList colorStateList = getColorStateList(tickMarkTintResId);
            if (colorStateList == null) return;
            if (IS_NOUGAT) {
                view.setTickMarkTintList(colorStateList);
            }
        }
    }

    @Override
    public void updateSkin() {
        applySupportThumb();
        applySupportTickMark();
        applySupportThumbTint();
        applySupportTickMarkTint();
    }
}