package com.codearms.maoqiqi.skin.helper;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.codearms.maoqiqi.skin.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * View更新皮肤帮助类,View及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/4 20:16
 */
public class SkinViewHelper extends SkinHelper<View> {

    private int backgroundResId = INVALID_RESOURCES;
    private int backgroundTintResId = INVALID_RESOURCES;
    private int foregroundResId = INVALID_RESOURCES;
    private int foregroundTintResId = INVALID_RESOURCES;
    private int scrollbarThumbVerticalResId = INVALID_RESOURCES;
    private int scrollbarTrackVerticalResId = INVALID_RESOURCES;
    private int scrollbarThumbHorizontalResId = INVALID_RESOURCES;
    private int scrollbarTrackHorizontalResId = INVALID_RESOURCES;

    public SkinViewHelper(View view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinViewHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinViewHelper_android_background)) {
                backgroundResId = a.getResourceId(R.styleable.SkinViewHelper_android_background, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinViewHelper_android_backgroundTint)) {
                backgroundTintResId = a.getResourceId(R.styleable.SkinViewHelper_android_backgroundTint, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinViewHelper_android_foreground)) {
                foregroundResId = a.getResourceId(R.styleable.SkinViewHelper_android_foreground, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinViewHelper_android_foregroundTint)) {
                foregroundTintResId = a.getResourceId(R.styleable.SkinViewHelper_android_foregroundTint, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinViewHelper_android_scrollbarThumbVertical)) {
                scrollbarThumbVerticalResId = a.getResourceId(R.styleable.SkinViewHelper_android_scrollbarThumbVertical, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinViewHelper_android_scrollbarTrackVertical)) {
                scrollbarTrackVerticalResId = a.getResourceId(R.styleable.SkinViewHelper_android_scrollbarTrackVertical, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinViewHelper_android_scrollbarThumbHorizontal)) {
                scrollbarThumbHorizontalResId = a.getResourceId(R.styleable.SkinViewHelper_android_scrollbarThumbHorizontal, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinViewHelper_android_scrollbarTrackHorizontal)) {
                scrollbarTrackHorizontalResId = a.getResourceId(R.styleable.SkinViewHelper_android_scrollbarTrackHorizontal, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 设置背景资源
     *
     * @param resId resource id
     */
    public void setSupportBackgroundResource(int resId) {
        backgroundResId = resId;
        applySupportBackground();
    }

    /**
     * 应用背景资源
     */
    private void applySupportBackground() {
        if (backgroundResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(backgroundResId);
        if (isColor(typeName)) {
            int color = getColor(backgroundResId);
            if (color == 0) return;
            view.setBackgroundColor(color);
        } else if (isDrawable(typeName)) {
            Drawable drawable = getDrawable(backgroundResId);
            if (drawable == null) return;
            ViewCompat.setBackground(view, drawable);
        }
    }

    /**
     * 应用背景着色
     */
    private void applySupportBackgroundTint() {
        if (backgroundTintResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(backgroundTintResId);
        if (isColor(typeName)) {
            ColorStateList colorStateList = getColorStateList(backgroundTintResId);
            if (colorStateList == null) return;
            ViewCompat.setBackgroundTintList(view, colorStateList);
        }
    }

    /**
     * 应用前景资源
     */
    private void applySupportForeground() {
        if (foregroundResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(foregroundResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(foregroundResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(foregroundResId);
        }
        if (drawable == null) return;
        if (view instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) view;
            frameLayout.setForeground(drawable);
        } else {
            if (IS_MARSHMALLOW) {
                view.setForeground(drawable);
            }
        }
    }

    /**
     * 应用前景着色
     */
    private void applySupportForegroundTint() {
        if (foregroundTintResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(foregroundTintResId);
        if (isColor(typeName)) {
            ColorStateList colorStateList = getColorStateList(foregroundTintResId);
            if (colorStateList == null) return;
            if (view instanceof FrameLayout) {
                FrameLayout frameLayout = (FrameLayout) view;
                if (IS_LOLLIPOP) {
                    frameLayout.setForegroundTintList(colorStateList);
                }
            } else {
                if (IS_MARSHMALLOW) {
                    view.setForegroundTintList(colorStateList);
                }
            }
        }
    }

    /**
     * 设置滚动条资源
     *
     * @param view     视图
     * @param index    方法名称索引
     *                 0:setVerticalThumbDrawable
     *                 1:setVerticalTrackDrawable
     *                 2:setHorizontalThumbDrawable
     *                 3:setHorizontalTrackDrawable
     * @param drawable 需要赋值的资源
     */
    private void setScrollBarDrawable(View view, int index, Drawable drawable) {
        String[] names = {"setVerticalThumbDrawable", "setVerticalTrackDrawable",
                "setHorizontalThumbDrawable", "setHorizontalTrackDrawable"};
        try {
            String name = "mScrollCache";
            Field fScrollCache = View.class.getDeclaredField(name);
            fScrollCache.setAccessible(true);
            Object scrollCache = fScrollCache.get(view);

            Field fScrollBar = scrollCache.getClass().getDeclaredField("scrollBar");
            fScrollBar.setAccessible(true);
            Object scrollBar = fScrollBar.get(scrollCache);

            Class<?> clazz = scrollBar.getClass();
            Method method = clazz.getMethod(names[index], Drawable.class);
            method.invoke(scrollBar, drawable);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用垂直滚动条的drawable
     */
    private void applySupportScrollBarVerticalThumbDrawable() {
        if (scrollbarThumbVerticalResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(scrollbarThumbVerticalResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(scrollbarThumbVerticalResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(scrollbarThumbVerticalResId);
        }
        if (drawable != null) {
            setScrollBarDrawable(view, 0, drawable);
        }
    }

    /**
     * 应用垂直滚动条背景(轨迹)的drawable
     */
    private void applySupportScrollBarVerticalTrackDrawable() {
        if (scrollbarTrackVerticalResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(scrollbarTrackVerticalResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(scrollbarTrackVerticalResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(scrollbarTrackVerticalResId);
        }
        if (drawable != null) {
            setScrollBarDrawable(view, 1, drawable);
        }
    }

    /**
     * 应用水平滚动条的drawable
     */
    private void applySupportScrollBarHorizontalThumbDrawable() {
        if (scrollbarThumbHorizontalResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(scrollbarThumbHorizontalResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(scrollbarThumbHorizontalResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(scrollbarThumbHorizontalResId);
        }
        if (drawable != null) {
            setScrollBarDrawable(view, 2, drawable);
        }
    }

    /**
     * 应用水平滚动条背景(轨迹)的drawable
     */
    private void applySupportScrollBarHorizontalTrackDrawable() {
        if (scrollbarTrackHorizontalResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(scrollbarTrackHorizontalResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(scrollbarTrackHorizontalResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(scrollbarTrackHorizontalResId);
        }
        if (drawable != null) {
            setScrollBarDrawable(view, 3, drawable);
        }
    }

    @Override
    public void updateSkin() {
        applySupportBackground();
        applySupportBackgroundTint();
        applySupportForeground();
        applySupportForegroundTint();
        applySupportScrollBarVerticalThumbDrawable();
        applySupportScrollBarVerticalTrackDrawable();
        applySupportScrollBarHorizontalThumbDrawable();
        applySupportScrollBarHorizontalTrackDrawable();
    }
}