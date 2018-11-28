package com.codearms.maoqiqi.skin.helper;

import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.codearms.maoqiqi.skin.R;

import java.lang.reflect.Field;

/**
 * SearchView更新皮肤帮助类,SearchView及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/17 11:11
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SkinSearchViewHelper extends SkinHelper<SearchView> {

    private int queryBackgroundResId = INVALID_RESOURCES;
    private int submitBackgroundResId = INVALID_RESOURCES;
    private int searchIconResId = INVALID_RESOURCES;
    private int searchHintIconResId = INVALID_RESOURCES;
    private int closeIconResId = INVALID_RESOURCES;
    private int goIconResId = INVALID_RESOURCES;
    private int voiceIconResId = INVALID_RESOURCES;
    private int commitIconResId = INVALID_RESOURCES;

    public SkinSearchViewHelper(SearchView view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinSearchViewHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinSearchViewHelper_android_queryBackground)) {
                queryBackgroundResId = a.getResourceId(R.styleable.SkinSearchViewHelper_android_queryBackground, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinSearchViewHelper_android_submitBackground)) {
                submitBackgroundResId = a.getResourceId(R.styleable.SkinSearchViewHelper_android_submitBackground, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinSearchViewHelper_android_searchIcon)) {
                searchIconResId = a.getResourceId(R.styleable.SkinSearchViewHelper_android_searchIcon, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinSearchViewHelper_android_searchHintIcon)) {
                searchHintIconResId = a.getResourceId(R.styleable.SkinSearchViewHelper_android_searchHintIcon, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinSearchViewHelper_android_closeIcon)) {
                closeIconResId = a.getResourceId(R.styleable.SkinSearchViewHelper_android_closeIcon, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinSearchViewHelper_android_goIcon)) {
                goIconResId = a.getResourceId(R.styleable.SkinSearchViewHelper_android_goIcon, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinSearchViewHelper_android_voiceIcon)) {
                voiceIconResId = a.getResourceId(R.styleable.SkinSearchViewHelper_android_voiceIcon, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinSearchViewHelper_android_commitIcon)) {
                commitIconResId = a.getResourceId(R.styleable.SkinSearchViewHelper_android_commitIcon, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 设置Query背景
     *
     * @param searchView 视图
     * @param drawable   drawable资源
     */
    private void setQueryBackground(SearchView searchView, Drawable drawable) {
        try {
            String name = "mSearchPlate";
            Field fSearchPlate = SearchView.class.getDeclaredField(name);
            View view = (View) fSearchPlate.get(searchView);
            view.setBackground(drawable);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用Query背景
     */
    private void applySupportQueryBackground() {
        if (queryBackgroundResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(queryBackgroundResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(queryBackgroundResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(queryBackgroundResId);
        }
        if (drawable == null) return;
        setQueryBackground(view, drawable);
    }

    /**
     * 设置Submit背景
     *
     * @param searchView 视图
     * @param drawable   drawable资源
     */
    private void setSubmitBackground(SearchView searchView, Drawable drawable) {
        try {
            String name = "mSubmitArea";
            Field fSubmitArea = SearchView.class.getDeclaredField(name);
            View view = (View) fSubmitArea.get(searchView);
            view.setBackground(drawable);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用Submit背景
     */
    private void applySupportSubmitBackground() {
        if (submitBackgroundResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(submitBackgroundResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(submitBackgroundResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(submitBackgroundResId);
        }
        if (drawable == null) return;
        setSubmitBackground(view, drawable);
    }

    /**
     * 设置搜索Icon
     *
     * @param searchView 视图
     * @param drawable   drawable资源
     */
    private void setSearchIcon(SearchView searchView, Drawable drawable) {
        try {
            String name = "mSearchButton";
            Field fSearchButton = SearchView.class.getDeclaredField(name);
            ImageView searchButton = (ImageView) fSearchButton.get(searchView);
            searchButton.setImageDrawable(drawable);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用搜索Icon
     */
    private void applySupportSearchIcon() {
        if (searchIconResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(searchIconResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(searchIconResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(searchIconResId);
        }
        if (drawable == null) return;
        setSearchIcon(view, drawable);
    }

    /**
     * 设置搜索提示Icon
     *
     * @param searchView 视图
     * @param drawable   drawable资源
     */
    private void setSearchHintIcon(SearchView searchView, Drawable drawable) {
        try {
            String name = "mSearchHintIcon";
            Field fSearchHintIcon = SearchView.class.getDeclaredField(name);
            fSearchHintIcon.set(searchView, drawable);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用搜索提示Icon
     */
    private void applySupportSearchHintIcon() {
        if (searchHintIconResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(searchHintIconResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(searchHintIconResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(searchHintIconResId);
        }
        if (drawable == null) return;
        setSearchHintIcon(view, drawable);
    }

    /**
     * 设置CloseIcon
     *
     * @param searchView 视图
     * @param drawable   drawable资源
     */
    private void setCloseIcon(SearchView searchView, Drawable drawable) {
        try {
            String name = "mCloseButton";
            Field fCloseButton = SearchView.class.getDeclaredField(name);
            ImageView closeButton = (ImageView) fCloseButton.get(searchView);
            closeButton.setImageDrawable(drawable);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用CloseIcon
     */
    private void applySupportCloseIcon() {
        if (closeIconResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(closeIconResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(closeIconResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(closeIconResId);
        }
        if (drawable == null) return;
        setCloseIcon(view, drawable);
    }

    /**
     * 设置GoIcon
     *
     * @param searchView 视图
     * @param drawable   drawable资源
     */
    private void setGoIcon(SearchView searchView, Drawable drawable) {
        try {
            String name = "mGoButton";
            Field fGoButton = SearchView.class.getDeclaredField(name);
            ImageView goButton = (ImageView) fGoButton.get(searchView);
            goButton.setImageDrawable(drawable);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用GoIcon
     */
    private void applySupportGoIcon() {
        if (goIconResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(goIconResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(goIconResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(goIconResId);
        }
        if (drawable == null) return;
        setGoIcon(view, drawable);
    }

    /**
     * 设置VoiceIcon
     *
     * @param searchView 视图
     * @param drawable   drawable资源
     */
    private void setVoiceIcon(SearchView searchView, Drawable drawable) {
        try {
            String name = "mVoiceButton";
            Field fVoiceButton = SearchView.class.getDeclaredField(name);
            ImageView voiceButton = (ImageView) fVoiceButton.get(searchView);
            voiceButton.setImageDrawable(drawable);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用VoiceIcon
     */
    private void applySupportVoiceIcon() {
        if (voiceIconResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(voiceIconResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(voiceIconResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(voiceIconResId);
        }
        if (drawable == null) return;
        setVoiceIcon(view, drawable);
    }

    /**
     * 设置CommitIcon
     *
     * @param searchView 视图
     * @param resId      resource id
     */
    private void setCommitIcon(SearchView searchView, int resId) {

        try {
            String name = "mSuggestionCommitIconResId";
            Field fSuggestionCommitIconResId = SearchView.class.getDeclaredField(name);
            fSuggestionCommitIconResId.set(searchView, resId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用CommitIcon
     */
    private void applySupportCommitIcon() {
        if (commitIconResId == INVALID_RESOURCES) return;
        int id = getTargetResId(commitIconResId);
        if (id == 0) id = commitIconResId;
        setCommitIcon(view, id);
    }

    @Override
    public void updateSkin() {
        applySupportQueryBackground();
        applySupportSubmitBackground();
        applySupportSearchIcon();
        applySupportSearchHintIcon();
        applySupportCloseIcon();
        applySupportGoIcon();
        applySupportVoiceIcon();
        applySupportCommitIcon();
    }
}