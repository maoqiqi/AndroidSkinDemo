package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示ProgressBar换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/20 16:57
 */
public class ProgressBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ProgressBar");
        setSupportActionBar(toolbar);
    }
}