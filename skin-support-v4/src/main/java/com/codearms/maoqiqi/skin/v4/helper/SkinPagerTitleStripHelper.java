package com.codearms.maoqiqi.skin.v4.helper;

import android.support.v4.view.PagerTitleStrip;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.helper.SkinHelper;

/**
 * PagerTitleStrip更新皮肤帮助类,PagerTitleStrip及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/12/13 18:45
 */
public class SkinPagerTitleStripHelper extends SkinHelper<PagerTitleStrip> {

    private int textColorResId = INVALID_RESOURCES;

    public SkinPagerTitleStripHelper(PagerTitleStrip view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        // 没有该属性,只能通过代码设置
    }

    public void setSupportTextColorResource(int resId) {
        textColorResId = resId;
        applySupportTextColor();
    }

    /**
     * 设置TextColor
     */
    private void applySupportTextColor() {
        if (isInvalid(textColorResId)) return;
        String typeName = getTypeName(textColorResId);
        if (isColor(typeName)) {
            int color = getColor(textColorResId);
            if (color == 0) return;
            view.setTextColor(color);
        }
    }

    @Override
    public void updateSkin() {
        applySupportTextColor();
    }
}