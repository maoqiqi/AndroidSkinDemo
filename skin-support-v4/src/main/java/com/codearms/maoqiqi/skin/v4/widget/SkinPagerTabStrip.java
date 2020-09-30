package com.codearms.maoqiqi.skin.v4.widget;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.support.v4.view.PagerTabStrip;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.helper.SkinViewHelper;
import com.codearms.maoqiqi.skin.widget.Skinable;

/**
 * 定义SkinPagerTabStrip继承PagerTabStrip,实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/7 0:23
 */
public class SkinPagerTabStrip extends PagerTabStrip implements Skinable {

    /**
     * View更新皮肤帮助类
     */
    private SkinViewHelper skinViewHelper;

    public SkinPagerTabStrip(@NonNull Context context) {
        this(context, null);
    }

    public SkinPagerTabStrip(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        skinViewHelper = new SkinViewHelper(this);
        skinViewHelper.loadFromAttribute(attrs, 0);
    }

    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        if (skinViewHelper != null) {
            skinViewHelper.setSupportBackgroundResource(resId);
        }
    }

    @Override
    public void updateSkin() {
        if (skinViewHelper != null) {
            skinViewHelper.updateSkin();
        }
    }
}