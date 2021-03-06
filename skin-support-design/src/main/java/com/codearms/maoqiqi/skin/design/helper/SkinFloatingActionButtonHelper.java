package com.codearms.maoqiqi.skin.design.helper;

import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.design.R;
import com.codearms.maoqiqi.skin.helper.SkinHelper;

/**
 * FloatingActionButton更新皮肤帮助类,FloatingActionButton及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/09 10:50
 */
public class SkinFloatingActionButtonHelper extends SkinHelper<FloatingActionButton> {

    private int rippleColorResId = INVALID_RESOURCES;

    public SkinFloatingActionButtonHelper(FloatingActionButton view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinFloatingActionButtonHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinFloatingActionButtonHelper_rippleColor)) {
                rippleColorResId = a.getResourceId(R.styleable.SkinFloatingActionButtonHelper_rippleColor, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 应用水波纹颜色
     */
    private void applySupportRippleColor() {
        if (rippleColorResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(rippleColorResId);
        if (isColor(typeName)) {
            int color = getColor(rippleColorResId);
            if (color == 0) return;
            view.setRippleColor(color);
        }
    }

    @Override
    public void updateSkin() {
        applySupportRippleColor();
    }
}