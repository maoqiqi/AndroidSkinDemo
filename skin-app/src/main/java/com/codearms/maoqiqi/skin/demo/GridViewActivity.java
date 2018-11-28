package com.codearms.maoqiqi.skin.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示GridView换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/3 15:32
 */
public class GridViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("GridView");
        setSupportActionBar(toolbar);

        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            data.add(R.drawable.ic_triangle);
            data.add(R.drawable.ic_rectangle);
            data.add(R.drawable.ic_star);
            data.add(R.drawable.ic_circle);
        }

        GridView gridView = findViewById(R.id.grid_view);
        gridView.setAdapter(new MyAdapter(this, data));
    }

    private final class MyAdapter extends BaseAdapter {

        private Context context;
        private List<Integer> data;

        MyAdapter(Context context, List<Integer> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(context, R.layout.item_grid_view, null);
                holder.imageView = convertView.findViewById(R.id.iv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.imageView.setImageResource(data.get(position));
            return convertView;
        }
    }

    private final class ViewHolder {
        ImageView imageView;
    }
}