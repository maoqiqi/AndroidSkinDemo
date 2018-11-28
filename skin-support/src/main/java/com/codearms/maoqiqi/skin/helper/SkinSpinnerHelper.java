package com.codearms.maoqiqi.skin.helper;

import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ListPopupWindow;
import android.widget.Spinner;

import com.codearms.maoqiqi.skin.R;

import java.lang.reflect.Field;

/**
 * Spinner更新皮肤帮助类,Spinner及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/15 16:42
 */
public class SkinSpinnerHelper extends SkinHelper<Spinner> {

    private int popupThemeResId = INVALID_RESOURCES;
    private int popupBackgroundResId = INVALID_RESOURCES;
    private int dropDownSelectorResId = INVALID_RESOURCES;

    public SkinSpinnerHelper(Spinner view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinSpinnerHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinSpinnerHelper_android_popupTheme)) {
                popupThemeResId = a.getResourceId(R.styleable.SkinSpinnerHelper_android_popupTheme, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinSpinnerHelper_android_popupBackground)) {
                popupBackgroundResId = a.getResourceId(R.styleable.SkinSpinnerHelper_android_popupBackground, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinSpinnerHelper_android_dropDownSelector)) {
                dropDownSelectorResId = a.getResourceId(R.styleable.SkinSpinnerHelper_android_dropDownSelector, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 设置popup背景
     *
     * @param resId resource id
     */
    public void setSupportPopupBackgroundResource(int resId) {
        popupBackgroundResId = resId;
        applySupportPopupBackground();
    }

    /**
     *
     */
    private void applySupportPopupTheme() {

    }

    /**
     * 应用popup背景
     */
    private void applySupportPopupBackground() {
        if (popupBackgroundResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(popupBackgroundResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(popupBackgroundResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(popupBackgroundResId);
        }
        if (drawable == null) return;
        if (IS_JELLY_BEAN) {
            view.setPopupBackgroundDrawable(drawable);
        }
    }

    /**
     * 设置popup项的背景
     *
     * @param view     视图
     * @param drawable 需要赋值的资源
     */
    private void setDropDownSelector(Spinner view, Drawable drawable) {
        try {
            String name = "mPopup";
            Field fPopup = Spinner.class.getDeclaredField(name);
            fPopup.setAccessible(true);
            ListPopupWindow popup = (ListPopupWindow) fPopup.get(view);
            popup.setListSelector(drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用popup项的背景
     */
    private void applySupportDropDownSelector() {
        if (dropDownSelectorResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(dropDownSelectorResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(dropDownSelectorResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(dropDownSelectorResId);
        }
        if (drawable == null) return;
        setDropDownSelector(view, drawable);
    }

    @Override
    public void updateSkin() {
        applySupportPopupTheme();
        applySupportPopupBackground();
        applySupportDropDownSelector();
    }
}
