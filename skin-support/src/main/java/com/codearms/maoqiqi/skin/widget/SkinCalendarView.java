package com.codearms.maoqiqi.skin.widget;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.CalendarView;

import com.codearms.maoqiqi.skin.helper.SkinCalendarViewHelper;
import com.codearms.maoqiqi.skin.helper.SkinViewHelper;

/**
 * 定义SkinCalendarView继承CalendarView,实现Skinable接口,实现更新皮肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/12 22:16
 */
public class SkinCalendarView extends CalendarView implements Skinable {

    /**
     * View更新皮肤帮助类
     */
    private SkinViewHelper skinViewHelper;

    private SkinCalendarViewHelper skinCalendarViewHelper;

    public SkinCalendarView(@NonNull Context context) {
        this(context, null);
    }

    public SkinCalendarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, android.R.attr.calendarViewStyle);
    }

    public SkinCalendarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        skinViewHelper = new SkinViewHelper(this);
        skinViewHelper.loadFromAttribute(attrs, defStyleAttr);
        skinCalendarViewHelper = new SkinCalendarViewHelper(this);
        skinCalendarViewHelper.loadFromAttribute(attrs, defStyleAttr);
    }

    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        if (skinViewHelper != null) {
            skinViewHelper.setSupportBackgroundResource(resId);
        }
    }

    @Override
    public void setWeekDayTextAppearance(int resourceId) {
        super.setWeekDayTextAppearance(resourceId);
        if (skinCalendarViewHelper != null) {
            skinCalendarViewHelper.setSupportWeekDayTextAppearance(resourceId);
        }
    }

    @Override
    public void setDateTextAppearance(int resourceId) {
        super.setDateTextAppearance(resourceId);
        if (skinCalendarViewHelper != null) {
            skinCalendarViewHelper.setSupportDateTextAppearance(resourceId);
        }
    }

    @Override
    public void updateSkin() {
        if (skinViewHelper != null) {
            skinViewHelper.updateSkin();
        }
        if (skinCalendarViewHelper != null) {
            skinCalendarViewHelper.updateSkin();
        }
    }
}