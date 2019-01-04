package com.codearms.maoqiqi.skin.v7.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;

import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AppCompatViewUtils {

    /**
     * 设置 SwitchCompat 文本颜色
     *
     * @param switchCompat   视图
     * @param colorStateList ColorStateList 资源
     */
    public static void setSwitchTextColor(SwitchCompat switchCompat, ColorStateList colorStateList) {
        try {
            String name = "mTextColors";
            Field fTextColors = SwitchCompat.class.getDeclaredField(name);
            fTextColors.setAccessible(true);
            fTextColors.set(switchCompat, colorStateList);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置AppCompatSeekBar自定义命名空间TickMark Drawable
     *
     * @param appCompatSeekBar 视图
     * @param drawable         Drawable 资源
     */
    public static void setTickMark(AppCompatSeekBar appCompatSeekBar, Drawable drawable) {
        try {
            Field fAppCompatSeekBarHelper = AppCompatSeekBar.class.getDeclaredField("mAppCompatSeekBarHelper");
            fAppCompatSeekBarHelper.setAccessible(true);
            Object appCompatSeekBarHelper = fAppCompatSeekBarHelper.get(appCompatSeekBar);

            Method setTickMarkMethod = appCompatSeekBarHelper.getClass().getDeclaredMethod("setTickMark", Drawable.class);
            setTickMarkMethod.setAccessible(true);
            setTickMarkMethod.invoke(appCompatSeekBarHelper, drawable);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置AppCompatSeekBar自定义命名空间TickMark Drawable tint
     *
     * @param appCompatSeekBar 视图
     * @param colorStateList   ColorStateList 资源
     */
    public static void setTickMarkTintList(AppCompatSeekBar appCompatSeekBar, ColorStateList colorStateList) {
        try {
            Field fAppCompatSeekBarHelper = AppCompatSeekBar.class.getDeclaredField("mAppCompatSeekBarHelper");
            fAppCompatSeekBarHelper.setAccessible(true);
            Object appCompatSeekBarHelper = fAppCompatSeekBarHelper.get(appCompatSeekBar);

            Method setTickMarkTintListMethod = appCompatSeekBarHelper.getClass().getDeclaredMethod("setTickMarkTintList", ColorStateList.class);
            setTickMarkTintListMethod.setAccessible(true);
            setTickMarkTintListMethod.invoke(appCompatSeekBarHelper, colorStateList);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView Query背景
     *
     * @param searchView 视图
     * @param drawable   Drawable 资源
     */
    public static void setQueryBackground(SearchView searchView, Drawable drawable) {
        try {
            String name = "mSearchPlate";
            Field fSearchPlate = SearchView.class.getDeclaredField(name);
            fSearchPlate.setAccessible(true);
            View view = (View) fSearchPlate.get(searchView);
            ViewCompat.setBackground(view, drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView Submit背景
     *
     * @param searchView 视图
     * @param drawable   Drawable 资源
     */
    public static void setSubmitBackground(SearchView searchView, Drawable drawable) {
        try {
            String name = "mSubmitArea";
            Field fSubmitArea = SearchView.class.getDeclaredField(name);
            fSubmitArea.setAccessible(true);
            View view = (View) fSubmitArea.get(searchView);
            ViewCompat.setBackground(view, drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView搜索Icon
     *
     * @param searchView 视图
     * @param drawable   Drawable 资源
     */
    public static void setSearchIcon(SearchView searchView, Drawable drawable) {
        try {
            String name = "mSearchButton";
            Field fSearchButton = SearchView.class.getDeclaredField(name);
            fSearchButton.setAccessible(true);
            ImageView searchButton = (ImageView) fSearchButton.get(searchView);
            searchButton.setImageDrawable(drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView搜索提示Icon
     *
     * @param searchView 视图
     * @param drawable   Drawable 资源
     */
    public static void setSearchHintIcon(SearchView searchView, Drawable drawable) {
        try {
            String name = "mSearchSrcTextView";
            Field fSearchSrcTextView = SearchView.class.getDeclaredField(name);
            fSearchSrcTextView.setAccessible(true);
            TextView searchSrcTextView = (TextView) fSearchSrcTextView.get(searchView);

            final int textSize = (int) (searchSrcTextView.getTextSize() * 1.25);
            drawable.setBounds(0, 0, textSize, textSize);

            final CharSequence hint = searchView.getQueryHint();

            final SpannableStringBuilder ssb = new SpannableStringBuilder("   ");
            ssb.setSpan(new ImageSpan(drawable), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.append(hint == null ? "" : hint);
            searchSrcTextView.setHint(ssb);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView CloseIcon
     *
     * @param searchView 视图
     * @param drawable   Drawable 资源
     */
    public static void setCloseIcon(SearchView searchView, Drawable drawable) {
        try {
            String name = "mCloseButton";
            Field fCloseButton = SearchView.class.getDeclaredField(name);
            fCloseButton.setAccessible(true);
            ImageView closeButton = (ImageView) fCloseButton.get(searchView);
            closeButton.setImageDrawable(drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView GoIcon
     *
     * @param searchView 视图
     * @param drawable   Drawable 资源
     */
    public static void setGoIcon(SearchView searchView, Drawable drawable) {
        try {
            String name = "mGoButton";
            Field fGoButton = SearchView.class.getDeclaredField(name);
            fGoButton.setAccessible(true);
            ImageView goButton = (ImageView) fGoButton.get(searchView);
            goButton.setImageDrawable(drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView VoiceIcon
     *
     * @param searchView 视图
     * @param drawable   drawable资源
     */
    public static void setVoiceIcon(SearchView searchView, Drawable drawable) {
        try {
            String name = "mVoiceButton";
            Field fVoiceButton = SearchView.class.getDeclaredField(name);
            fVoiceButton.setAccessible(true);
            ImageView voiceButton = (ImageView) fVoiceButton.get(searchView);
            voiceButton.setImageDrawable(drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView CommitIcon
     *
     * @param searchView 视图
     * @param resId      resource id
     */
    public static void setCommitIcon(SearchView searchView, int resId) {
        try {
            String name = "mSuggestionCommitIconResId";
            Field fSuggestionCommitIconResId = SearchView.class.getDeclaredField(name);
            fSuggestionCommitIconResId.setAccessible(true);
            fSuggestionCommitIconResId.set(searchView, resId);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置CollapseIcon
     *
     * @param toolbar  视图
     * @param drawable drawable资源
     */
    public static void setCollapseIcon(Toolbar toolbar, Drawable drawable) {
        try {
            String name = "mCollapseIcon";
            Field fCollapseIcon = Toolbar.class.getDeclaredField(name);
            fCollapseIcon.setAccessible(true);
            fCollapseIcon.set(toolbar, drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}