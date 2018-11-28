package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示TextInputEditText换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/7 11:45
 */
public class TextInputEditTextActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_edit_text);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("TextInputEditText");
        setSupportActionBar(toolbar);
    }
}