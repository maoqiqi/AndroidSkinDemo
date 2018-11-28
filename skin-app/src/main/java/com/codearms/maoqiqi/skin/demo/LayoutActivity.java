package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示ScrollView,LinearLayout,RelativeLayout,FrameLayout,RadioGroup,HorizontalScrollView,LinearLayoutCompat换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/22 14:45
 */
public class LayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Layout");
        setSupportActionBar(toolbar);
    }
}