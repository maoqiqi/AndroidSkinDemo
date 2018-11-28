package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示SeekBar换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/20 21:21
 */
public class SeekBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("SeekBar");
        setSupportActionBar(toolbar);
    }
}