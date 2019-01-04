package com.codearms.maoqiqi.skin.design.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DesignViewUtils {

    /**
     * 设置DefaultTextColorHint
     *
     * @param textInputLayout 视图
     * @param colorStateList  ColorStateList 资源
     */
    public static void setDefaultTextColorHint(TextInputLayout textInputLayout, ColorStateList colorStateList) {
        try {
            String name = "mDefaultTextColor";
            Field fDefaultTextColor = TextInputLayout.class.getDeclaredField(name);
            fDefaultTextColor.setAccessible(true);
            fDefaultTextColor.set(textInputLayout, colorStateList);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置FocusedTextColorHint
     *
     * @param textInputLayout 视图
     * @param colorStateList  ColorStateList 资源
     */
    public static void setFocusedTextColorHint(TextInputLayout textInputLayout, ColorStateList colorStateList) {
        try {
            String name = "mFocusedTextColor";
            Field fFocusedTextColor = TextInputLayout.class.getDeclaredField(name);
            fFocusedTextColor.setAccessible(true);
            fFocusedTextColor.set(textInputLayout, colorStateList);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置ErrorTextColor
     *
     * @param textInputLayout 视图
     * @param colorStateList  ColorStateList 资源
     */
    public static void setErrorTextColor(TextInputLayout textInputLayout, ColorStateList colorStateList) {
        try {
            Field fErrorView = TextInputLayout.class.getDeclaredField("mErrorView");
            fErrorView.setAccessible(true);
            TextView errorView = (TextView) fErrorView.get(textInputLayout);
            errorView.setTextColor(colorStateList);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置CounterTextColor
     *
     * @param textInputLayout          视图
     * @param counterTextColor         ColorStateList 资源
     * @param counterOverflowTextColor ColorStateList 资源
     */
    public static void setCounterTextColor(TextInputLayout textInputLayout, ColorStateList counterTextColor, ColorStateList counterOverflowTextColor) {
        if (textInputLayout.getEditText() == null) return;
        if (counterTextColor == null && counterOverflowTextColor == null) return;

        try {
            Field fCounterView = TextInputLayout.class.getDeclaredField("mCounterView");
            fCounterView.setAccessible(true);
            TextView counterView = (TextView) fCounterView.get(textInputLayout);

            if (textInputLayout.getEditText().getText().length() > textInputLayout.getCounterMaxLength()) {
                if (counterOverflowTextColor != null)
                    counterView.setTextColor(counterOverflowTextColor);
            } else {
                if (counterTextColor != null) counterView.setTextColor(counterTextColor);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置所有选项卡的背景
     *
     * @param tabLayout 视图
     * @param color     color 资源
     * @param drawable  Drawable 资源
     */
    public static void setTabBackground(TabLayout tabLayout, int color, Drawable drawable) {
        try {
            Field fTabs = TabLayout.class.getDeclaredField("mTabs");
            fTabs.setAccessible(true);
            ArrayList<TabLayout.Tab> tabs = (ArrayList<TabLayout.Tab>) fTabs.get(tabLayout);

            if (tabs == null) return;
            for (int i = 0; i < tabs.size(); i++) {
                setTabItemBackground(tabs.get(i), color, drawable);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置某一项选项卡的背景
     *
     * @param tab      选项卡
     * @param color    color 资源
     * @param drawable Drawable 资源
     */
    public static void setTabItemBackground(TabLayout.Tab tab, int color, Drawable drawable) {
        if (color == 0 && drawable == null) return;
        try {
            Field fView = TabLayout.Tab.class.getDeclaredField("mView");
            fView.setAccessible(true);
            LinearLayout tabView = (LinearLayout) fView.get(tab);

            if (tabView == null) return;

            if (color != 0) tabView.setBackgroundColor(color);
            if (drawable != null) ViewCompat.setBackground(tabView, drawable);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置InsetForeground
     *
     * @param scrimInsetsFrameLayout 视图
     * @param drawable               Drawable 资源
     */
    public static void setInsetForeground(ScrimInsetsFrameLayout scrimInsetsFrameLayout, Drawable drawable) {
        try {
            Field fInsetForeground = ScrimInsetsFrameLayout.class.getDeclaredField("mInsetForeground");
            fInsetForeground.setAccessible(true);
            fInsetForeground.set(scrimInsetsFrameLayout, drawable);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}