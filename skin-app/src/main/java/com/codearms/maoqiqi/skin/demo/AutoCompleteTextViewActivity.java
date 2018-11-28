package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

/**
 * 演示AutoCompleteTextView换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/11 18:48
 */
public class AutoCompleteTextViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("AutoCompleteTextView");
        setSupportActionBar(toolbar);

        AutoCompleteTextView act1 = findViewById(R.id.act_1);
        AutoCompleteTextView act2 = findViewById(R.id.act_2);
        AutoCompleteTextView act3 = findViewById(R.id.act_3);
        AppCompatAutoCompleteTextView appcompatAct1 = findViewById(R.id.appcompat_act_1);
        AppCompatAutoCompleteTextView appcompatAct2 = findViewById(R.id.appcompat_act_2);
        AppCompatAutoCompleteTextView appcompatAct3 = findViewById(R.id.appcompat_act_3);
        MultiAutoCompleteTextView multiAct1 = findViewById(R.id.multi_act_1);
        MultiAutoCompleteTextView multiAct2 = findViewById(R.id.multi_act_2);
        MultiAutoCompleteTextView multiAct3 = findViewById(R.id.multi_act_3);
        AppCompatMultiAutoCompleteTextView appcompatMultiAct1 = findViewById(R.id.appcompat_multi_act_1);
        AppCompatMultiAutoCompleteTextView appcompatMultiAct2 = findViewById(R.id.appcompat_multi_act_2);
        AppCompatMultiAutoCompleteTextView appcompatMultiAct3 = findViewById(R.id.appcompat_multi_act_3);


        String[] schools = getResources().getStringArray(R.array.schools);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_text, R.id.tv, schools);

        act1.setAdapter(adapter);
        act2.setAdapter(adapter);
        act3.setAdapter(adapter);
        appcompatAct1.setAdapter(adapter);
        appcompatAct2.setAdapter(adapter);
        appcompatAct3.setAdapter(adapter);
        multiAct1.setAdapter(adapter);
        multiAct2.setAdapter(adapter);
        multiAct3.setAdapter(adapter);
        appcompatMultiAct1.setAdapter(adapter);
        appcompatMultiAct2.setAdapter(adapter);
        appcompatMultiAct3.setAdapter(adapter);

        multiAct1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        multiAct2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        multiAct3.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        appcompatMultiAct1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        appcompatMultiAct2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        appcompatMultiAct3.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}