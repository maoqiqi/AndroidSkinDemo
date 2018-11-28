package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示TimePicker换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/23 14:35
 */
public class TimePickerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("TimePicker");
        setSupportActionBar(toolbar);
    }
}