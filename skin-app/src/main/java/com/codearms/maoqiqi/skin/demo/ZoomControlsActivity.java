package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.support.v7.widget.Toolbar;

/**
 * 演示ZoomControls换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/2 22:28
 */
public class ZoomControlsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_controls);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ZoomControls");
        setSupportActionBar(toolbar);
    }
}