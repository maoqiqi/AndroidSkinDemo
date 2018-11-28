package com.codearms.maoqiqi.skin.helper;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.codearms.maoqiqi.skin.R;

/**
 * ProgressBar更新皮肤帮助类,ProgressBar及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/9 16:48
 */
public class SkinProgressBarHelper extends SkinHelper<ProgressBar> {

    private int progressDrawableResId = INVALID_RESOURCES;
    private int indeterminateDrawableResId = INVALID_RESOURCES;
    private int progressTintResId = INVALID_RESOURCES;
    private int secondaryProgressTintResId = INVALID_RESOURCES;
    private int progressBackgroundTintResId = INVALID_RESOURCES;
    private int indeterminateTintResId = INVALID_RESOURCES;

    public SkinProgressBarHelper(ProgressBar view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinProgressBarHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinProgressBarHelper_android_progressDrawable)) {
                progressDrawableResId = a.getResourceId(R.styleable.SkinProgressBarHelper_android_progressDrawable, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinProgressBarHelper_android_indeterminateDrawable)) {
                indeterminateDrawableResId = a.getResourceId(R.styleable.SkinProgressBarHelper_android_indeterminateDrawable, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinProgressBarHelper_android_progressTint)) {
                progressTintResId = a.getResourceId(R.styleable.SkinProgressBarHelper_android_progressTint, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinProgressBarHelper_android_secondaryProgressTint)) {
                secondaryProgressTintResId = a.getResourceId(R.styleable.SkinProgressBarHelper_android_secondaryProgressTint, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinProgressBarHelper_android_progressBackgroundTint)) {
                progressBackgroundTintResId = a.getResourceId(R.styleable.SkinProgressBarHelper_android_progressBackgroundTint, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinProgressBarHelper_android_indeterminateTint)) {
                indeterminateTintResId = a.getResourceId(R.styleable.SkinProgressBarHelper_android_indeterminateTint, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 应用Progress
     */
    private void applySupportProgressDrawable() {
        if (progressDrawableResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(progressDrawableResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(progressDrawableResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(progressDrawableResId);
        }
        if (drawable == null) return;
        view.setProgressDrawable(drawable);
    }

    /**
     * 应用Indeterminate
     */
    private void applySupportIndeterminateDrawable() {
        if (indeterminateDrawableResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(indeterminateDrawableResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(indeterminateDrawableResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(indeterminateDrawableResId);
        }
        if (drawable == null) return;
        view.setIndeterminateDrawable(drawable);
    }

    /**
     * 应用Progress着色
     */
    private void applySupportProgressTint() {
        if (progressTintResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(progressTintResId);
        if (isColor(typeName)) {
            ColorStateList colorStateList = getColorStateList(progressTintResId);
            if (colorStateList == null) return;
            if (IS_LOLLIPOP) {
                view.setProgressTintList(colorStateList);
            }
        }
    }

    /**
     * 应用SecondaryProgress着色
     */
    private void applySupportSecondaryProgressTint() {
        if (secondaryProgressTintResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(secondaryProgressTintResId);
        if (isColor(typeName)) {
            ColorStateList colorStateList = getColorStateList(secondaryProgressTintResId);
            if (colorStateList == null) return;
            if (IS_LOLLIPOP) {
                view.setSecondaryProgressTintList(colorStateList);
            }
        }
    }

    /**
     * 应用ProgressBackground着色
     */
    private void applySupportProgressBackgroundTint() {
        if (progressBackgroundTintResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(progressBackgroundTintResId);
        if (isColor(typeName)) {
            ColorStateList colorStateList = getColorStateList(progressBackgroundTintResId);
            if (colorStateList == null) return;
            if (IS_LOLLIPOP) {
                view.setProgressBackgroundTintList(colorStateList);
            }
        }
    }

    /**
     * 应用Indeterminate着色
     */
    private void applySupportIndeterminateTint() {
        if (indeterminateTintResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(indeterminateTintResId);
        if (isColor(typeName)) {
            ColorStateList colorStateList = getColorStateList(indeterminateTintResId);
            if (colorStateList == null) return;
            if (IS_LOLLIPOP) {
                view.setIndeterminateTintList(colorStateList);
            }
        }
    }

    @Override
    public void updateSkin() {
        applySupportProgressDrawable();
        applySupportIndeterminateDrawable();
        applySupportProgressTint();
        applySupportSecondaryProgressTint();
        applySupportProgressBackgroundTint();
        applySupportIndeterminateTint();
    }
}