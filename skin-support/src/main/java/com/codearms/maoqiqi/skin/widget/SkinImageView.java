package com.codearms.maoqiqi.skin.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.codearms.maoqiqi.skin.helper.SkinImageViewHelper;
import com.codearms.maoqiqi.skin.helper.SkinViewHelper;

/**
 * 定义SkinImageView继承ImageView,实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/9 20:27
 */
@SuppressLint("AppCompatCustomView")
public class SkinImageView extends ImageView implements Skinable {

    /**
     * View更新皮肤帮助类
     */
    private SkinViewHelper skinViewHelper;

    /**
     * ImageView更新皮肤帮助类
     */
    private SkinImageViewHelper skinImageViewHelper;

    public SkinImageView(Context context) {
        this(context, null);
    }

    public SkinImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        skinViewHelper = new SkinViewHelper(this);
        skinViewHelper.loadFromAttribute(attrs, defStyleAttr);
        skinImageViewHelper = new SkinImageViewHelper(this);
        skinImageViewHelper.loadFromAttribute(attrs, defStyleAttr);
    }

    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        if (skinViewHelper != null) {
            skinViewHelper.setSupportBackgroundResource(resId);
        }
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if (skinImageViewHelper != null) {
            skinImageViewHelper.setSupportImageResource(resId);
        }
    }

    @Override
    public void updateSkin() {
        if (skinViewHelper != null) {
            skinViewHelper.updateSkin();
        }
        if (skinImageViewHelper != null) {
            skinImageViewHelper.updateSkin();
        }
    }
}