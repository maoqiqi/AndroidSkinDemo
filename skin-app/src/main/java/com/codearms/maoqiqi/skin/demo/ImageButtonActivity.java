package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示ImageButton换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/21 13:00
 */
public class ImageButtonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_button);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ImageButton");
        setSupportActionBar(toolbar);
    }
}