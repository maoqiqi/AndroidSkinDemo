package com.codearms.maoqiqi.skin.demo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.codearms.maoqiqi.skin.manager.SkinManager;

/**
 * 演示CoordinatorLayout,AppBarLayout,CollapsingToolbarLayout换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/7 11:45
 */
public class CoordinatorLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            SkinManager.getInstance().setSkinStatusBarColorDisEnable(this);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("CoordinatorLayout");
        setSupportActionBar(toolbar);
    }
}