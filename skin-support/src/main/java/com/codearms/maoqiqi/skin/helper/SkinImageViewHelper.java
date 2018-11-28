package com.codearms.maoqiqi.skin.helper;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.codearms.maoqiqi.skin.R;

/**
 * ImageView更新皮肤帮助类,ImageView及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/9 20:18
 */
public class SkinImageViewHelper extends SkinHelper<ImageView> {

    private int srcResId = INVALID_RESOURCES;
    private int tintResId = INVALID_RESOURCES;
    private int srcCompatResId = INVALID_RESOURCES;

    public SkinImageViewHelper(ImageView view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinImageViewHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinImageViewHelper_android_src)) {
                srcResId = a.getResourceId(R.styleable.SkinImageViewHelper_android_src, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinImageViewHelper_android_tint)) {
                tintResId = a.getResourceId(R.styleable.SkinImageViewHelper_android_tint, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinImageViewHelper_srcCompat)) {
                srcCompatResId = a.getResourceId(R.styleable.SkinImageViewHelper_srcCompat, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 设置Image资源
     *
     * @param resId resource id
     */
    public void setSupportImageResource(int resId) {
        srcResId = resId;
        applySupportSrc();
    }

    /**
     * 应用图片
     */
    private void applySupportSrc() {
        if (srcResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(srcResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(srcResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(srcResId);
        }
        if (drawable == null) return;
        view.setImageDrawable(drawable);
    }

    /**
     * 应用图片
     */
    private void applySupportSrcCompat() {
        if (srcCompatResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(srcCompatResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(srcCompatResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(srcCompatResId);
        }
        if (drawable == null) return;
        view.setImageDrawable(drawable);
    }

    /**
     * 应用图片着色
     */
    private void applySupportTint() {
        if (tintResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(tintResId);
        if (isColor(typeName)) {
            ColorStateList colorStateList = getColorStateList(tintResId);
            if (colorStateList == null) return;
            if (IS_LOLLIPOP) {
                view.setImageTintList(colorStateList);
            }
        }
    }

    @Override
    public void updateSkin() {
        applySupportSrc();
        applySupportSrcCompat();
        applySupportTint();
    }
}