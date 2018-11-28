package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示TableLayout,TableRow换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/22 16:45
 */
public class TableLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("TableLayout");
        setSupportActionBar(toolbar);
    }
}