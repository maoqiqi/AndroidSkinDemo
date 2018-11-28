package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示BaselineLayout换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/8 14:23
 */
public class BaselineLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseline_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("BaselineLayout");
        setSupportActionBar(toolbar);
    }
}