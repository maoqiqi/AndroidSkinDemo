package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示EditText换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/11 18:00
 */
public class EditTextActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("EditText");
        setSupportActionBar(toolbar);
    }
}