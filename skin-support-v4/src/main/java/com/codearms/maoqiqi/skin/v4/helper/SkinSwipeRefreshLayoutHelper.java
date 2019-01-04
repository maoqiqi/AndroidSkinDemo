package com.codearms.maoqiqi.skin.v4.helper;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.helper.SkinHelper;

/**
 * SwipeRefreshLayout更新皮肤帮助类,SwipeRefreshLayout及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/12/14 14:42
 */
public class SkinSwipeRefreshLayoutHelper extends SkinHelper<SwipeRefreshLayout> {

    private int progressBackgroundColorSchemeResourceResId = INVALID_RESOURCES;
    private int[] colorSchemeResourceResId;

    public SkinSwipeRefreshLayoutHelper(SwipeRefreshLayout view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        // 没有该属性,只能通过代码设置
    }

    public void setSupportProgressBackgroundColorSchemeResource(int colorRes) {
        progressBackgroundColorSchemeResourceResId = colorRes;
        applySupportProgressBackgroundColorSchemeResource();
    }

    public void setSupportColorSchemeResources(int... colorResIds) {
        colorSchemeResourceResId = colorResIds;
        applySupportColorSchemeResources();
    }

    /**
     * 应用ProgressBackgroundColorSchemeResource
     */
    private void applySupportProgressBackgroundColorSchemeResource() {
        if (isInvalid(progressBackgroundColorSchemeResourceResId)) return;
        String typeName = getTypeName(progressBackgroundColorSchemeResourceResId);
        if (isColor(typeName)) {
            int color = getColor(progressBackgroundColorSchemeResourceResId);
            if (color == 0) return;
            view.setProgressBackgroundColorSchemeColor(color);
        }
    }

    /**
     * 应用ColorSchemeResources
     */
    private void applySupportColorSchemeResources() {
        if (colorSchemeResourceResId == null || colorSchemeResourceResId.length == 0) return;
        int[] colors = new int[colorSchemeResourceResId.length];
        String typeName;
        int color;
        for (int i = 0; i < colorSchemeResourceResId.length; i++) {
            typeName = getTypeName(colorSchemeResourceResId[i]);
            if (isColor(typeName)) {
                color = getColor(colorSchemeResourceResId[i]);
                if (color != 0) colors[i] = color;
            }
        }
        view.setColorSchemeColors(colors);
    }

    @Override
    public void updateSkin() {
        applySupportProgressBackgroundColorSchemeResource();
        applySupportColorSchemeResources();
    }
}