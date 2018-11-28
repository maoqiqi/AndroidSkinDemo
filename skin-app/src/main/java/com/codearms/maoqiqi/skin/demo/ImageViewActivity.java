package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示ImageView换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/20 22:22
 */
public class ImageViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ImageView");
        setSupportActionBar(toolbar);
    }
}