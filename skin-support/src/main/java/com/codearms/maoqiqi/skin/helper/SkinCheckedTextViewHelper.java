package com.codearms.maoqiqi.skin.helper;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

import com.codearms.maoqiqi.skin.R;

/**
 * CheckedTextView更新皮肤帮助类,CheckedTextView及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/8 19:38
 */
public class SkinCheckedTextViewHelper extends SkinHelper<CheckedTextView> {

    private int checkMarkResId = INVALID_RESOURCES;
    private int checkMarkTintResId = INVALID_RESOURCES;

    public SkinCheckedTextViewHelper(CheckedTextView view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinCheckedTextViewHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinCheckedTextViewHelper_android_checkMark)) {
                checkMarkResId = a.getResourceId(R.styleable.SkinCheckedTextViewHelper_android_checkMark, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinCheckedTextViewHelper_android_checkMarkTint)) {
                checkMarkTintResId = a.getResourceId(R.styleable.SkinCheckedTextViewHelper_android_checkMarkTint, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 设置CheckMark资源
     *
     * @param resId resource id
     */
    public void setSupportCheckMarkDrawable(int resId) {
        checkMarkResId = resId;
        applyCheckMark();
    }

    /**
     * 应用CheckMark
     */
    private void applyCheckMark() {
        if (checkMarkResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(checkMarkResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(checkMarkResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(checkMarkResId);
        }
        if (drawable == null) return;
        view.setCheckMarkDrawable(drawable);
    }

    /**
     * 应用CheckMark着色
     */
    private void applyCheckMarkTint() {
        if (checkMarkTintResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(checkMarkTintResId);
        if (isColor(typeName)) {
            ColorStateList colorStateList = getColorStateList(checkMarkTintResId);
            if (colorStateList == null) return;
            if (IS_LOLLIPOP) {
                view.setCheckMarkTintList(colorStateList);
            }
        }
    }

    @Override
    public void updateSkin() {
        applyCheckMark();
        applyCheckMarkTint();
    }
}