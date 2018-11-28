package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示CompoundButton换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/16 18:10
 */
public class CompoundButtonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound_button);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("CompoundButton");
        setSupportActionBar(toolbar);
    }
}