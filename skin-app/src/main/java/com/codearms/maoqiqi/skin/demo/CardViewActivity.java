package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示CardView换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/5 16:45
 */
public class CardViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("CardView");
        setSupportActionBar(toolbar);
    }
}