package com.codearms.maoqiqi.skin.helper;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CalendarView;

import com.codearms.maoqiqi.skin.R;

/**
 * CalendarView更新皮肤帮助类,CalendarView及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/12 22:15
 */
public class SkinCalendarViewHelper extends SkinHelper<CalendarView> {

    private boolean isApplyWeekDayTextAppearance = true;
    private boolean isApplyDateTextAppearance = true;

    private int weekDayTextAppearanceResId = INVALID_RESOURCES;
    private int dateTextAppearanceResId = INVALID_RESOURCES;

    public SkinCalendarViewHelper(CalendarView view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinCalendarViewHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinCalendarViewHelper_android_weekDayTextAppearance)) {
                weekDayTextAppearanceResId = a.getResourceId(R.styleable.SkinCalendarViewHelper_android_weekDayTextAppearance, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinCalendarViewHelper_android_dateTextAppearance)) {
                dateTextAppearanceResId = a.getResourceId(R.styleable.SkinCalendarViewHelper_android_dateTextAppearance, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 设置WeekDay文本样式
     *
     * @param resId resource id
     */
    public void setSupportWeekDayTextAppearance(int resId) {
        if (isApplyWeekDayTextAppearance) {
            weekDayTextAppearanceResId = resId;
            applySupportWeekDayTextAppearance();
        } else {
            isApplyWeekDayTextAppearance = true;
        }
    }

    /**
     * 设置Date文本样式
     *
     * @param resId resource id
     */
    public void setSupportDateTextAppearance(int resId) {
        if (isApplyDateTextAppearance) {
            dateTextAppearanceResId = resId;
            applySupportDateTextAppearance();
        } else {
            isApplyDateTextAppearance = true;
        }
    }

    /**
     * 应用WeekDay文本样式
     */
    private void applySupportWeekDayTextAppearance() {
        if (weekDayTextAppearanceResId == INVALID_RESOURCES) return;
        int id = getTargetResId(weekDayTextAppearanceResId);
        if (id == 0) id = weekDayTextAppearanceResId;
        isApplyWeekDayTextAppearance = false;
        if (IS_JELLY_BEAN) {
            view.setWeekDayTextAppearance(id);
        }
    }

    /**
     * 应用Date文本样式
     */
    private void applySupportDateTextAppearance() {
        if (dateTextAppearanceResId == INVALID_RESOURCES) return;
        int id = getTargetResId(dateTextAppearanceResId);
        if (id == 0) id = dateTextAppearanceResId;
        isApplyDateTextAppearance = false;
        if (IS_JELLY_BEAN) {
            view.setDateTextAppearance(id);
        }
    }

    @Override
    public void updateSkin() {
        applySupportWeekDayTextAppearance();
        applySupportDateTextAppearance();
    }
}