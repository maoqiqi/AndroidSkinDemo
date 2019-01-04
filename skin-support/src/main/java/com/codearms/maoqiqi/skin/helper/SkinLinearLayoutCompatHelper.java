package com.codearms.maoqiqi.skin.helper;

import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.R;

/**
 * LinearLayoutCompat更新皮肤帮助类,LinearLayoutCompat及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/14 21:58
 */
public class SkinLinearLayoutCompatHelper extends SkinHelper<LinearLayoutCompat> {

    private int dividerResId = INVALID_RESOURCES;

    public SkinLinearLayoutCompatHelper(LinearLayoutCompat view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinLinearLayoutCompatHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinLinearLayoutCompatHelper_divider)) {
                dividerResId = a.getResourceId(R.styleable.SkinLinearLayoutCompatHelper_divider, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
        init();
    }

    /**
     * 应用divider资源
     */
    private void applySupportDivider() {
        if (isInvalid(dividerResId)) return;
        String typeName = getTypeName(dividerResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(dividerResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(dividerResId);
        }
        if (drawable == null) return;
        view.setDividerDrawable(drawable);
    }

    @Override
    public void updateSkin() {
        applySupportDivider();
    }
}