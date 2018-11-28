package com.codearms.maoqiqi.skin.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;

/**
 * 演示Gallery换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/3 15:32
 */
public class GalleryActivity extends BaseActivity {

    private Integer[] imageIds = {R.drawable.banner_1, R.drawable.banner_2, R.drawable.banner_3,
            R.drawable.banner_4, R.drawable.banner_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Gallery");
        setSupportActionBar(toolbar);

        Gallery gallery = findViewById(R.id.gallery);
        gallery.setAdapter(new MyAdapter(this, Arrays.asList(imageIds)));
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
                convertView = View.inflate(context, R.layout.item_image_switcher, null);
                holder.imageView = (ImageView) convertView;
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