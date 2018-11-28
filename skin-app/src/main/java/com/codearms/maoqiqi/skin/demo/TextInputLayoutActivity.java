package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * 演示TextInputLayout换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/7 11:45
 */
public class TextInputLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("TextInputLayout");
        setSupportActionBar(toolbar);

        final TextInputLayout textInputLayout = findViewById(R.id.text_input_layout);
        final TextInputEditText editText = findViewById(R.id.edit_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > textInputLayout.getCounterMaxLength()) {
                    textInputLayout.setError("超出限定字数了...");
                    editText.setError("超出限定字数了...");
                } else {
                    textInputLayout.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}