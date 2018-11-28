package com.codearms.maoqiqi.skin.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.CheckableImageButton;
import android.support.v7.widget.Toolbar;

/**
 * 演示CheckableImageButton换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/8 15:15
 */
public class CheckableImageButtonActivity extends BaseActivity {

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkable_image_button);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("CheckableImageButton");
        setSupportActionBar(toolbar);

        CheckableImageButton checkableImageButton1 = findViewById(R.id.checkable_image_button_1);
        CheckableImageButton checkableImageButton2 = findViewById(R.id.checkable_image_button_2);
        checkableImageButton1.setChecked(true);
        checkableImageButton2.setChecked(true);
    }
}