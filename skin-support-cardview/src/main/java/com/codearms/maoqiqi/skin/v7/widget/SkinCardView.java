package com.codearms.maoqiqi.skin.v7.widget;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.helper.SkinViewHelper;
import com.codearms.maoqiqi.skin.v7.helper.SkinCardViewHelper;
import com.codearms.maoqiqi.skin.widget.Skinable;

/**
 * 定义SkinCardView继承CardView,实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/21 12:15
 */
public class SkinCardView extends CardView implements Skinable {

    /**
     * View更新皮肤帮助类
     */
    private SkinViewHelper skinViewHelper;

    /**
     * CardView更新皮肤帮助类
     */
    private SkinCardViewHelper skinCardViewHelper;

    public SkinCardView(@NonNull Context context) {
        this(context, null);
    }

    public SkinCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, android.support.v7.cardview.R.attr.cardViewStyle);
    }

    public SkinCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        skinViewHelper = new SkinViewHelper(this);
        skinViewHelper.loadFromAttribute(attrs, defStyleAttr);
        skinCardViewHelper = new SkinCardViewHelper(this);
        skinCardViewHelper.loadFromAttribute(attrs, defStyleAttr);
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
        if (skinCardViewHelper != null) {
            skinCardViewHelper.updateSkin();
        }
    }
}