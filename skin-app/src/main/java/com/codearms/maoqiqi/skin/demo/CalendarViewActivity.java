package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示CalendarView换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/23 12:35
 */
public class CalendarViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("CalendarView");
        setSupportActionBar(toolbar);
    }
}