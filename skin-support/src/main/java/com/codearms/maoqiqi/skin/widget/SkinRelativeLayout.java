package com.codearms.maoqiqi.skin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.codearms.maoqiqi.skin.helper.SkinViewHelper;

/**
 * 定义SkinRelativeLayout继承RelativeLayout,实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/10 20:41
 */
public class SkinRelativeLayout extends RelativeLayout implements Skinable {

    /**
     * View更新皮肤帮助类
     */
    private SkinViewHelper skinViewHelper;

    public SkinRelativeLayout(Context context) {
        this(context, null);
    }

    public SkinRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        skinViewHelper = new SkinViewHelper(this);
        skinViewHelper.loadFromAttribute(attrs, defStyleAttr);
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