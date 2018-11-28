package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * 演示SearchView,android.support.v7.widget.SearchView换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/25 22:02
 */
public class SearchViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("SearchView");
        setSupportActionBar(toolbar);
    }
}