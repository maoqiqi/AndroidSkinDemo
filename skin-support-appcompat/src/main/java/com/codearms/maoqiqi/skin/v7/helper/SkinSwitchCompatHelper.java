package com.codearms.maoqiqi.skin.v7.helper;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.helper.SkinHelper;
import com.codearms.maoqiqi.skin.appcompat.R;

import java.lang.reflect.Field;

/**
 * SwitchCompat更新皮肤帮助类,SwitchCompat及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/19 14:10
 */
public class SkinSwitchCompatHelper extends SkinHelper<SwitchCompat> {

    private int switchTextAppearanceResId = INVALID_RESOURCES;
    private int switchTextColorResId = INVALID_RESOURCES;
    private int thumbResId = INVALID_RESOURCES;
    private int thumbTintResId = INVALID_RESOURCES;
    private int trackResId = INVALID_RESOURCES;
    private int trackTintResId = INVALID_RESOURCES;

    public SkinSwitchCompatHelper(SwitchCompat view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinSwitchCompatHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinSwitchCompatHelper_switchTextAppearance)) {
                switchTextAppearanceResId = a.getResourceId(R.styleable.SkinSwitchCompatHelper_switchTextAppearance, INVALID_RESOURCES);
                obtainTextAppearance();
            }
            if (a.hasValue(R.styleable.SkinSwitchCompatHelper_android_thumb)) {
                thumbResId = a.getResourceId(R.styleable.SkinSwitchCompatHelper_android_thumb, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinSwitchCompatHelper_thumbTint)) {
                thumbTintResId = a.getResourceId(R.styleable.SkinSwitchCompatHelper_thumbTint, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinSwitchCompatHelper_track)) {
                trackResId = a.getResourceId(R.styleable.SkinSwitchCompatHelper_track, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinSwitchCompatHelper_trackTint)) {
                trackTintResId = a.getResourceId(R.styleable.SkinSwitchCompatHelper_trackTint, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 解析SkinTextAppearance,获取属性值
     */
    private void obtainTextAppearance() {
        if (switchTextAppearanceResId == INVALID_RESOURCES) return;
        TypedArray a = view.getContext().obtainStyledAttributes(switchTextAppearanceResId, com.codearms.maoqiqi.skin.R.styleable.SkinTextAppearance);
        try {
            if (a.hasValue(com.codearms.maoqiqi.skin.R.styleable.SkinTextAppearance_android_textColor)) {
                switchTextColorResId = a.getResourceId(com.codearms.maoqiqi.skin.R.styleable.SkinTextAppearance_android_textColor, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 设置Switch文本样式文本样式
     *
     * @param resId resource id
     */
    public void setSupportSwitchTextAppearance(int resId) {
        switchTextAppearanceResId = resId;
        if (switchTextAppearanceResId == INVALID_RESOURCES) return;
        obtainTextAppearance();
        applySupportSwitchTextColor();
    }

    /**
     * 设置Thumb资源
     *
     * @param resId resource id
     */
    public void setSupportThumbResource(int resId) {
        thumbResId = resId;
        applySupportThumb();
    }

    /**
     * 设置Track资源
     *
     * @param resId resource id
     */
    public void setSupportTrackResource(int resId) {
        trackResId = resId;
        applySupportTrack();
    }

    /**
     * 设置Switch文本颜色
     *
     * @param switchCompat   视图
     * @param colorStateList 需要赋值的资源
     */
    private void setSwitchTextColor(SwitchCompat switchCompat, ColorStateList colorStateList) {
        try {
            String name = "mTextColors";
            Field fTextColors = SwitchCompat.class.getDeclaredField(name);
            fTextColors.setAccessible(true);
            fTextColors.set(switchCompat, colorStateList);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用Switch文本颜色
     */
    private void applySupportSwitchTextColor() {
        if (switchTextColorResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(switchTextColorResId);
        if (isColor(typeName) || isDrawable(typeName)) {
            ColorStateList colorStateList = getColorStateList(switchTextColorResId);
            if (colorStateList == null) return;
            setSwitchTextColor(view, colorStateList);
        }
    }

    /**
     * 应用Thumb
     */
    private void applySupportThumb() {
        if (thumbResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(thumbResId);
        if (isDrawable(typeName)) {
            Drawable drawable = getDrawable(thumbResId);
            if (drawable == null) return;
            view.setThumbDrawable(drawable);
        }
    }

    /**
     * 应用Track
     */
    private void applySupportTrack() {
        if (trackResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(trackResId);
        if (isDrawable(typeName)) {
            Drawable drawable = getDrawable(trackResId);
            if (drawable == null) return;
            view.setTrackDrawable(drawable);
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
            view.setThumbTintList(colorStateList);
        }
    }

    /**
     * 应用Track着色
     */
    private void applySupportTrackTint() {
        if (trackTintResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(trackTintResId);
        if (isColor(typeName)) {
            ColorStateList colorStateList = getColorStateList(trackTintResId);
            if (colorStateList == null) return;
            view.setTrackTintList(colorStateList);
        }
    }

    @Override
    public void updateSkin() {
        applySupportSwitchTextColor();
        applySupportThumb();
        applySupportTrack();
        applySupportThumbTint();
        applySupportTrackTint();
    }
}