package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckedTextView;

/**
 * 演示CheckedTextView换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/19 11:32
 */
public class CheckedTextViewActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked_text_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("CheckedTextView");
        setSupportActionBar(toolbar);

        findViewById(R.id.ctv_a_1).setOnClickListener(this);
        findViewById(R.id.ctv_b_1).setOnClickListener(this);
        findViewById(R.id.ctv_c_1).setOnClickListener(this);
        findViewById(R.id.ctv_d_1).setOnClickListener(this);
        findViewById(R.id.ctv_e_1).setOnClickListener(this);
        findViewById(R.id.ctv_f_1).setOnClickListener(this);
        findViewById(R.id.ctv_a_2).setOnClickListener(this);
        findViewById(R.id.ctv_b_2).setOnClickListener(this);
        findViewById(R.id.ctv_c_2).setOnClickListener(this);
        findViewById(R.id.ctv_d_2).setOnClickListener(this);
        findViewById(R.id.ctv_e_2).setOnClickListener(this);
        findViewById(R.id.ctv_f_2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof CheckedTextView) {
            CheckedTextView checkedTextView = (CheckedTextView) v;
            checkedTextView.toggle();
        }
    }
}