package com.codearms.maoqiqi.skin.design.helper;

import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.design.R;
import com.codearms.maoqiqi.skin.design.utils.DesignViewUtils;
import com.codearms.maoqiqi.skin.helper.SkinHelper;

/**
 * ScrimInsetsFrameLayout更新皮肤帮助类,ScrimInsetsFrameLayout及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/09 11:14
 */
public class SkinScrimInsetsFrameLayoutHelper extends SkinHelper<ScrimInsetsFrameLayout> {

    private int insetForegroundResId = INVALID_RESOURCES;

    public SkinScrimInsetsFrameLayoutHelper(ScrimInsetsFrameLayout view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinScrimInsetsFrameLayoutHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinScrimInsetsFrameLayoutHelper_insetForeground)) {
                insetForegroundResId = a.getResourceId(R.styleable.SkinScrimInsetsFrameLayoutHelper_insetForeground, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
        init();
    }

    /**
     * 应用InsetForeground
     */
    private void applyInsetForeground() {
        if (isInvalid(insetForegroundResId)) return;
        String typeName = getTypeName(insetForegroundResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(insetForegroundResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(insetForegroundResId);
        }
        if (drawable == null) return;
        DesignViewUtils.setInsetForeground(view, drawable);
    }

    @Override
    public void updateSkin() {
        applyInsetForeground();
    }
}