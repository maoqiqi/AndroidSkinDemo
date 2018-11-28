package com.codearms.maoqiqi.skin.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

/**
 * 演示ExpandableListView换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/3 15:32
 */
public class ExpandableListViewActivity extends BaseActivity {

    public String[] groupStrings = {"西游记", "水浒传", "三国演义", "红楼梦"};
    public String[][] childStrings = {
            {"唐三藏", "孙悟空", "猪八戒", "沙和尚"},
            {"宋江", "林冲", "李逵", "鲁智深"},
            {"曹操", "刘备", "孙权", "诸葛亮", "周瑜"},
            {"贾宝玉", "林黛玉", "薛宝钗", "王熙凤"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ExpandableListView");
        setSupportActionBar(toolbar);

        ExpandableListView expandableListView = findViewById(R.id.expandable_list_view);
        expandableListView.setAdapter(new MyAdapter(this));
    }

    private final class MyAdapter extends BaseExpandableListAdapter {

        private Context context;

        MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return groupStrings.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childStrings[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupStrings[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childStrings[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupViewHolder groupViewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_expand_group, parent, false);
                groupViewHolder = new GroupViewHolder();
                groupViewHolder.tv = convertView.findViewById(R.id.tv);
                convertView.setTag(groupViewHolder);
            } else {
                groupViewHolder = (GroupViewHolder) convertView.getTag();
            }
            groupViewHolder.tv.setText(groupStrings[groupPosition]);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildViewHolder childViewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_expand_child, parent, false);
                childViewHolder = new ChildViewHolder();
                childViewHolder.tv = convertView.findViewById(R.id.tv);
                convertView.setTag(childViewHolder);
            } else {
                childViewHolder = (ChildViewHolder) convertView.getTag();
            }
            childViewHolder.tv.setText(childStrings[groupPosition][childPosition]);
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    private static class GroupViewHolder {
        TextView tv;
    }

    private static class ChildViewHolder {
        TextView tv;
    }
}