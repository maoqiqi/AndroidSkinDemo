package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示DatePicker换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/23 14:15
 */
public class DatePickerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("DatePicker");
        setSupportActionBar(toolbar);
    }
}