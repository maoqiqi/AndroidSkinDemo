package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示ForegroundLinearLayout换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/8 14:58
 */
public class ForegroundLinearLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground_linear_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ForegroundLinearLayout");
        setSupportActionBar(toolbar);
    }
}