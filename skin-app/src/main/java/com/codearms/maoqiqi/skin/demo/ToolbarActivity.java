package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示Toolbar,android.support.v7.widget.Toolbar换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/25 18:00
 */
public class ToolbarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Toolbar");
        setSupportActionBar(toolbar);
    }
}