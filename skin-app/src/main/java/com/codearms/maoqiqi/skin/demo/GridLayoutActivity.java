package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示GridLayout换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/3 15:32
 */
public class GridLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("GridLayout");
        setSupportActionBar(toolbar);
    }
}