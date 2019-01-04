package com.codearms.maoqiqi.skin.v4.helper;

import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.helper.SkinHelper;

/**
 * DrawerLayout更新皮肤帮助类,DrawerLayout及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/27 23:32
 */
public class SkinDrawerLayoutHelper extends SkinHelper<DrawerLayout> {

    private int statusBarBackgroundResId = INVALID_RESOURCES;

    public SkinDrawerLayoutHelper(DrawerLayout view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        // 没有该属性,只能通过代码设置
    }

    public void setSupportStatusBarBackground(int resId) {
        statusBarBackgroundResId = resId;
        applySupportStatusBarBackground();
    }

    /**
     * 应用StatusBarBackground
     */
    private void applySupportStatusBarBackground() {
        if (isInvalid(statusBarBackgroundResId)) return;
        String typeName = getTypeName(statusBarBackgroundResId);
        if (isColor(typeName)) {
            int color = getColor(statusBarBackgroundResId);
            if (color == 0) return;
            view.setStatusBarBackgroundColor(color);
        } else if (isDrawable(typeName)) {
            Drawable drawable = getDrawable(statusBarBackgroundResId);
            if (drawable == null) return;
            view.setStatusBarBackground(drawable);
        }
    }

    @Override
    public void updateSkin() {
        applySupportStatusBarBackground();
    }
}