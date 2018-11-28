package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示NestedScrollView换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/5 16:32
 */
public class NestedScrollViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("NestedScrollView");
        setSupportActionBar(toolbar);
    }
}