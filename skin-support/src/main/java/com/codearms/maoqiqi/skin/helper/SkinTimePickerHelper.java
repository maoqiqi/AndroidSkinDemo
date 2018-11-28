package com.codearms.maoqiqi.skin.helper;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.codearms.maoqiqi.skin.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * TimePicker更新皮肤帮助类,TimePicker及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/12 22:08
 */
public class SkinTimePickerHelper extends SkinHelper<TimePicker> {

    private int headerBackgroundResId = INVALID_RESOURCES;
    private int headerTextColorResId = INVALID_RESOURCES;

    public SkinTimePickerHelper(TimePicker view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinTimePickerHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinTimePickerHelper_headerBackground)) {
                headerBackgroundResId = a.getResourceId(R.styleable.SkinTimePickerHelper_headerBackground, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTimePickerHelper_headerTextColor)) {
                headerTextColorResId = a.getResourceId(R.styleable.SkinTimePickerHelper_headerTextColor, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 得到设置的模式
     *
     * @param view 视图
     * @return 模式
     */
    private int getMode(TimePicker view) {
        try {
            String name = "getMode";
            Method method = TimePicker.class.getMethod(name);
            return (int) method.invoke(view);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 设置头部背景
     *
     * @param view     视图
     * @param drawable 背景资源
     */
    private void setHeaderBackground(TimePicker view, Drawable drawable) {
        try {
            String name = "mDelegate";
            Field fDelegate = TimePicker.class.getDeclaredField(name);
            fDelegate.setAccessible(true);
            Object delegate = fDelegate.get(view);

            if (getMode(view) == 2) {
                Field fRadialTimePickerHeader = delegate.getClass().getDeclaredField("mRadialTimePickerHeader");
                fRadialTimePickerHeader.setAccessible(true);
                View radialTimePickerHeader = (View) fRadialTimePickerHeader.get(delegate);

                Field fTextInputPickerHeader = delegate.getClass().getDeclaredField("mTextInputPickerHeader");
                fTextInputPickerHeader.setAccessible(true);
                View textInputPickerHeader = (View) fTextInputPickerHeader.get(delegate);

                ViewCompat.setBackground(radialTimePickerHeader, drawable);
                ViewCompat.setBackground(textInputPickerHeader, drawable);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用头部背景
     */
    private void applySupportHeaderBackground() {
        if (headerBackgroundResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(headerBackgroundResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(headerBackgroundResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(headerBackgroundResId);
        }
        if (drawable == null) return;
        setHeaderBackground(view, drawable);
    }

    /**
     * 设置头部文本颜色
     *
     * @param view           视图
     * @param colorStateList 文本颜色
     */
    private void setHeaderTextColor(TimePicker view, ColorStateList colorStateList) {
        try {
            String name = "mDelegate";
            Field fDelegate = TimePicker.class.getDeclaredField(name);
            fDelegate.setAccessible(true);
            Object delegate = fDelegate.get(view);

            if (getMode(view) == 2) {
                Field fHourView = delegate.getClass().getDeclaredField("mHourView");
                fHourView.setAccessible(true);
                TextView sourView = (TextView) fHourView.get(delegate);

                Field fSeparatorView = delegate.getClass().getDeclaredField("mSeparatorView");
                fSeparatorView.setAccessible(true);
                TextView separatorView = (TextView) fSeparatorView.get(delegate);

                Field fMinuteView = delegate.getClass().getDeclaredField("mMinuteView");
                fMinuteView.setAccessible(true);
                TextView minuteView = (TextView) fMinuteView.get(delegate);

                Field fAmLabel = delegate.getClass().getDeclaredField("mAmLabel");
                fAmLabel.setAccessible(true);
                TextView amLabel = (TextView) fAmLabel.get(delegate);

                Field fPmLabel = delegate.getClass().getDeclaredField("mPmLabel");
                fPmLabel.setAccessible(true);
                TextView pmLabel = (TextView) fPmLabel.get(delegate);

                sourView.setTextColor(colorStateList);
                separatorView.setTextColor(colorStateList);
                minuteView.setTextColor(colorStateList);
                amLabel.setTextColor(colorStateList);
                pmLabel.setTextColor(colorStateList);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用头部文本颜色
     */
    private void applySupportHeaderTextColor() {
        if (headerTextColorResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(headerTextColorResId);
        if (isColor(typeName) || isDrawable(typeName)) {
            ColorStateList colorStateList = getColorStateList(headerTextColorResId);
            if (colorStateList == null) return;
            setHeaderTextColor(view, colorStateList);
        }
    }

    @Override
    public void updateSkin() {
        applySupportHeaderBackground();
        applySupportHeaderTextColor();
    }
}