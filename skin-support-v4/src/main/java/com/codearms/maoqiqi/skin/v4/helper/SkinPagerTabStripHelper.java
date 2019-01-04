package com.codearms.maoqiqi.skin.v4.helper;

import android.support.v4.view.PagerTabStrip;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.helper.SkinHelper;

/**
 * PagerTabStrip更新皮肤帮助类,PagerTabStrip及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/12/13 18:32
 */
public class SkinPagerTabStripHelper extends SkinHelper<PagerTabStrip> {

    private int tabIndicatorColorResId = INVALID_RESOURCES;

    public SkinPagerTabStripHelper(PagerTabStrip view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        // 没有该属性,只能通过代码设置
    }

    public void setSupportTabIndicatorColorResource(int resId) {
        tabIndicatorColorResId = resId;
        applySupportTabIndicatorColor();
    }

    /**
     * 应用TabIndicatorColor
     */
    private void applySupportTabIndicatorColor() {
        if (isInvalid(tabIndicatorColorResId)) return;
        String typeName = getTypeName(tabIndicatorColorResId);
        if (isColor(typeName)) {
            int color = getColor(tabIndicatorColorResId);
            if (color == 0) return;
            view.setTabIndicatorColor(color);
        }
    }

    @Override
    public void updateSkin() {
        applySupportTabIndicatorColor();
    }
}