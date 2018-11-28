package com.codearms.maoqiqi.skin.v7.helper;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.helper.SkinHelper;
import com.codearms.maoqiqi.skin.appcompat.R;

import java.lang.reflect.Field;

/**
 * Toolbar更新皮肤帮助类,Toolbar及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/21 10:20
 */
public class SkinToolbarHelper extends SkinHelper<Toolbar> {

    private int titleTextAppearanceResId = INVALID_RESOURCES;
    private int subtitleTextAppearanceResId = INVALID_RESOURCES;
    private int titleTextColorResId = INVALID_RESOURCES;
    private int subtitleTextColorResId = INVALID_RESOURCES;
    private int logoResId = INVALID_RESOURCES;
    private int navigationIconResId = INVALID_RESOURCES;
    private int collapseIconResId = INVALID_RESOURCES;
    private int popupThemeResId = INVALID_RESOURCES;

    public SkinToolbarHelper(Toolbar view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinToolbarHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinToolbarHelper_titleTextAppearance)) {
                titleTextAppearanceResId = a.getResourceId(R.styleable.SkinToolbarHelper_titleTextAppearance, INVALID_RESOURCES);
                obtainTitleTextAppearance();
            }
            if (a.hasValue(R.styleable.SkinToolbarHelper_subtitleTextAppearance)) {
                subtitleTextAppearanceResId = a.getResourceId(R.styleable.SkinToolbarHelper_subtitleTextAppearance, INVALID_RESOURCES);
                obtainSubtitleTextAppearance();
            }
            if (a.hasValue(R.styleable.SkinToolbarHelper_titleTextColor)) {
                titleTextColorResId = a.getResourceId(R.styleable.SkinToolbarHelper_titleTextColor, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinToolbarHelper_subtitleTextColor)) {
                subtitleTextColorResId = a.getResourceId(R.styleable.SkinToolbarHelper_subtitleTextColor, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinToolbarHelper_logo)) {
                logoResId = a.getResourceId(R.styleable.SkinToolbarHelper_logo, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinToolbarHelper_navigationIcon)) {
                navigationIconResId = a.getResourceId(R.styleable.SkinToolbarHelper_navigationIcon, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinToolbarHelper_collapseIcon)) {
                collapseIconResId = a.getResourceId(R.styleable.SkinToolbarHelper_collapseIcon, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinToolbarHelper_popupTheme)) {
                popupThemeResId = a.getResourceId(R.styleable.SkinToolbarHelper_popupTheme, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 解析TitleTextAppearance,获取属性值
     */
    private void obtainTitleTextAppearance() {
        if (titleTextAppearanceResId == INVALID_RESOURCES) return;
        TypedArray a = view.getContext().obtainStyledAttributes(titleTextAppearanceResId, R.styleable.SkinTextAppearance);
        try {
            if (a.hasValue(R.styleable.SkinTextAppearance_android_textColor)) {
                titleTextColorResId = a.getResourceId(R.styleable.SkinTextAppearance_android_textColor, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 解析SubtitleTextAppearance,获取属性值
     */
    private void obtainSubtitleTextAppearance() {
        if (subtitleTextAppearanceResId == INVALID_RESOURCES) return;
        TypedArray a = view.getContext().obtainStyledAttributes(subtitleTextAppearanceResId, R.styleable.SkinTextAppearance);
        try {
            if (a.hasValue(R.styleable.SkinTextAppearance_android_textColor)) {
                subtitleTextColorResId = a.getResourceId(R.styleable.SkinTextAppearance_android_textColor, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 设置Title文本样式
     *
     * @param resId resource id
     */
    public void setSupportTitleTextAppearance(int resId) {
        titleTextAppearanceResId = resId;
        obtainTitleTextAppearance();
        applySupportTitleTextColor();
    }

    /**
     * 设置Subtitle文本样式
     *
     * @param resId resource id
     */
    public void setSupportSubtitleTextAppearance(int resId) {
        subtitleTextAppearanceResId = resId;
        obtainSubtitleTextAppearance();
        applySupportSubtitleTextColor();
    }

    /**
     * 设置Logo
     *
     * @param resId resource id
     */
    public void setSupportLogo(int resId) {
        logoResId = resId;
        applySupportLogo();
    }

    /**
     * 设置Navigation
     *
     * @param resId resource id
     */
    public void setSupportNavigationIcon(int resId) {
        navigationIconResId = resId;
        applySupportNavigationIcon();
    }

    /**
     * 设置PopupTheme
     *
     * @param resId resource id
     */
    public void setSupportPopupTheme(int resId) {
        popupThemeResId = resId;
        applySupportPopupTheme();
    }

    /**
     * 应用Title文本颜色
     */
    private void applySupportTitleTextColor() {
        if (titleTextColorResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(titleTextColorResId);
        if (isColor(typeName) || isDrawable(typeName)) {
            ColorStateList colorStateList = getColorStateList(titleTextColorResId);
            if (colorStateList == null) return;
            view.setTitleTextColor(colorStateList.getDefaultColor());
        }
    }

    /**
     * 应用Subtitle文本颜色
     */
    private void applySupportSubtitleTextColor() {
        if (subtitleTextColorResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(subtitleTextColorResId);
        if (isColor(typeName) || isDrawable(typeName)) {
            ColorStateList colorStateList = getColorStateList(titleTextColorResId);
            if (colorStateList == null) return;
            view.setSubtitleTextColor(colorStateList.getDefaultColor());
        }
    }

    /**
     * 应用Logo
     */
    private void applySupportLogo() {
        if (logoResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(logoResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(logoResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(logoResId);
        }
        if (drawable == null) return;
        view.setLogo(drawable);
    }

    /**
     * 应用Navigation
     */
    private void applySupportNavigationIcon() {
        if (navigationIconResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(navigationIconResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(navigationIconResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(navigationIconResId);
        }
        if (drawable == null) return;
        view.setNavigationIcon(drawable);
    }

    /**
     * 设置CollapseIcon
     *
     * @param toolbar  视图
     * @param drawable drawable资源
     */
    private void setCollapseIcon(Toolbar toolbar, Drawable drawable) {
        try {
            String name = "mCollapseIcon";
            Field fCollapseIcon = Toolbar.class.getDeclaredField(name);
            fCollapseIcon.set(toolbar, drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用CollapseIcon
     */
    private void applySupportCollapseIcon() {
        if (collapseIconResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(collapseIconResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(collapseIconResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(collapseIconResId);
        }
        if (drawable == null) return;
        setCollapseIcon(view, drawable);
    }

    /**
     *
     */
    private void applySupportPopupTheme() {

    }

    @Override
    public void updateSkin() {
        applySupportTitleTextColor();
        applySupportSubtitleTextColor();
        applySupportLogo();
        applySupportNavigationIcon();
        applySupportCollapseIcon();
        applySupportPopupTheme();
    }
}