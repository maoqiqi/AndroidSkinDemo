package com.codearms.maoqiqi.skin.v7.helper;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.codearms.maoqiqi.skin.helper.SkinHelper;
import com.codearms.maoqiqi.skin.recyclerview.R;
import com.codearms.maoqiqi.skin.v7.utils.RecyclerViewUtils;

/**
 * RecyclerView更新皮肤帮助类,RecyclerView及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/27 23:52
 */
public class SkinRecyclerViewHelper extends SkinHelper<RecyclerView> {

    private boolean fastScrollEnabled;
    private int fastScrollHorizontalThumbDrawableResId = INVALID_RESOURCES;
    private int fastScrollHorizontalTrackDrawableResId = INVALID_RESOURCES;
    private int fastScrollVerticalThumbDrawableResId = INVALID_RESOURCES;
    private int fastScrollVerticalTrackDrawableResId = INVALID_RESOURCES;

    public SkinRecyclerViewHelper(RecyclerView view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinRecyclerViewHelper, defStyleAttr, 0);
        try {
            fastScrollEnabled = a.getBoolean(R.styleable.SkinRecyclerViewHelper_fastScrollEnabled, false);
            if (fastScrollEnabled) {
                if (a.hasValue(R.styleable.SkinRecyclerViewHelper_fastScrollHorizontalThumbDrawable)) {
                    fastScrollHorizontalThumbDrawableResId = a.getResourceId(R.styleable.SkinRecyclerViewHelper_fastScrollHorizontalThumbDrawable, INVALID_RESOURCES);
                }
                if (a.hasValue(R.styleable.SkinRecyclerViewHelper_fastScrollHorizontalTrackDrawable)) {
                    fastScrollHorizontalTrackDrawableResId = a.getResourceId(R.styleable.SkinRecyclerViewHelper_fastScrollHorizontalTrackDrawable, INVALID_RESOURCES);
                }
                if (a.hasValue(R.styleable.SkinRecyclerViewHelper_fastScrollVerticalThumbDrawable)) {
                    fastScrollVerticalThumbDrawableResId = a.getResourceId(R.styleable.SkinRecyclerViewHelper_fastScrollVerticalThumbDrawable, INVALID_RESOURCES);
                }
                if (a.hasValue(R.styleable.SkinRecyclerViewHelper_fastScrollVerticalTrackDrawable)) {
                    fastScrollVerticalTrackDrawableResId = a.getResourceId(R.styleable.SkinRecyclerViewHelper_fastScrollVerticalTrackDrawable, INVALID_RESOURCES);
                }
            }
        } finally {
            a.recycle();
        }
        init();
    }

    /**
     * 应用滚动条
     */
    private void applySupportFastScrollDrawable() {
        if (!fastScrollEnabled || isInvalid(fastScrollHorizontalThumbDrawableResId) ||
                isInvalid(fastScrollHorizontalTrackDrawableResId) ||
                isInvalid(fastScrollVerticalThumbDrawableResId) ||
                isInvalid(fastScrollVerticalTrackDrawableResId)) return;

        Drawable horizontalThumbDrawable = null;
        Drawable horizontalTrackDrawable = null;
        Drawable verticalThumbDrawable = null;
        Drawable verticalTrackDrawable = null;

        String typeName = getTypeName(fastScrollHorizontalThumbDrawableResId);
        if (isDrawable(typeName)) {
            horizontalThumbDrawable = getDrawable(fastScrollHorizontalThumbDrawableResId);
        }

        typeName = getTypeName(fastScrollHorizontalTrackDrawableResId);
        if (isDrawable(typeName)) {
            horizontalTrackDrawable = getDrawable(fastScrollHorizontalTrackDrawableResId);
        }

        typeName = getTypeName(fastScrollVerticalThumbDrawableResId);
        if (isDrawable(typeName)) {
            verticalThumbDrawable = getDrawable(fastScrollVerticalThumbDrawableResId);
        }

        typeName = getTypeName(fastScrollVerticalTrackDrawableResId);
        if (isDrawable(typeName)) {
            verticalTrackDrawable = getDrawable(fastScrollVerticalTrackDrawableResId);
        }

        RecyclerViewUtils.setFastScrollDrawable(view, verticalThumbDrawable, verticalTrackDrawable,
                horizontalThumbDrawable, horizontalTrackDrawable);
    }

    @Override
    public void updateSkin() {
        applySupportFastScrollDrawable();
    }
}