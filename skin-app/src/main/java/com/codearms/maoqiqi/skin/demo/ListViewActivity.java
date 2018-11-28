package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 演示ListView换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/3 15:32
 */
public class ListViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ListView");
        setSupportActionBar(toolbar);

        ListView listView = findViewById(R.id.list_view);
        String[] data = getResources().getStringArray(R.array.schools);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.addAll(Arrays.asList(data));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_text, R.id.tv, list);
        listView.setAdapter(adapter);
    }
}