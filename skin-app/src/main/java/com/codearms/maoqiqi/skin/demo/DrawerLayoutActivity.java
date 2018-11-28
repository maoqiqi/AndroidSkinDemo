package com.codearms.maoqiqi.skin.demo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;

import com.codearms.maoqiqi.skin.manager.SkinManager;

/**
 * 演示DrawerLayout换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/6 16:45
 */
public class DrawerLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        SkinManager.getInstance().setSkinStatusBarColorDisEnable(this);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("DrawerLayout");

        // 定义drawerArrowDrawable
        DrawerArrowDrawable drawerArrowDrawable = new DrawerArrowDrawable(this);
        drawerArrowDrawable.setColor(ContextCompat.getColor(this, R.color.white));

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        // 设置drawerArrowDrawable
        toggle.setDrawerArrowDrawable(drawerArrowDrawable);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
        // 设置状态栏的颜色
        drawerLayout.setStatusBarBackground(R.color.color_primary_dark);
    }
}