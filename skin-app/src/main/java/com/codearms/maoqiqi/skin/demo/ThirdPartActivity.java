package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示第三方空间换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/12/8 17:57
 */
public class ThirdPartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_part);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ThirdPart");
        setSupportActionBar(toolbar);
    }
}