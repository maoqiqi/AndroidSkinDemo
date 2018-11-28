package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示Switch换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/18 18:40
 */
public class SwitchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Switch");
        setSupportActionBar(toolbar);
    }
}