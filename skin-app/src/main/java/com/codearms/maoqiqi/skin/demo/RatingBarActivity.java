package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示ProgressBar换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/20 16:27
 */
public class RatingBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("RatingBar");
        setSupportActionBar(toolbar);
    }
}