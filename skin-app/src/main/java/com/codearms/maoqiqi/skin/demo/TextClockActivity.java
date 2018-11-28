package com.codearms.maoqiqi.skin.demo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

/**
 * 演示TextClock换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/19 11:32
 */
public class TextClockActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Toast.makeText(this, "版本太低了...", Toast.LENGTH_SHORT).show();
            return;
        }

        setContentView(R.layout.activity_text_clock);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("TextClock");
        setSupportActionBar(toolbar);
    }
}