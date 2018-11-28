package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

/**
 * 演示View换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/11 17:30
 */
public class ViewActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("View");
        setSupportActionBar(toolbar);
    }
}