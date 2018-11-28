package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示Button换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/15 16:22
 */
public class ButtonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Button");
        setSupportActionBar(toolbar);
    }
}