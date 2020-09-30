package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.support.v7.widget.Toolbar;

/**
 * 演示TextView换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/11 17:48
 */
public class TextViewActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("TextView");
        setSupportActionBar(toolbar);
    }
}