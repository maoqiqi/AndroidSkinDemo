package com.codearms.maoqiqi.skin.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 程序主入口,演示控件兼容性和更新皮肤
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/11 17:30
 */
public class MainActivity extends AppCompatActivity {

    public String[] groupStrings = {"View", "TextView", "ImageView", "ProgressBar", "ViewGroup", "Design Support Library"};
    public String[][] childStrings = {
            {"View"},
            {"TextView", "EditText", "AutoCompleteTextView", "Button", "CompoundButton", "Switch", "CheckedTextView", "TextClock"},
            {"ImageView", "ImageButton"},
            {"ProgressBar", "RatingBar", "SeekBar"},
            {"Layout", "TableLayout", "NumberPicker", "ZoomControls", "SearchView", "NestedScrollView",
                    "ViewAnimator", "CalendarView", "DatePicker", "TimePicker", "CardView",
                    "Spinner", "Gallery", "ListView", "ExpandableListView",
                    "GridView", "GridLayout", "AdapterViewAnimator",
                    "Toolbar", "DrawerLayout", "SlidingPaneLayout", "SwipeRefreshLayout", "ViewPager",
                    "RecyclerView"},
            {"TextInputEditText", "FloatingActionButton", "TextInputLayout", "TabLayout",
                    "NavigationView", "BottomNavigationView",
                    "CoordinatorLayout", "BaselineLayout", "ForegroundLinearLayout",
                    "CheckableImageButton"
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ExpandableListView expandableListView = findViewById(R.id.expandable_list_view);
        expandableListView.setAdapter(new MyAdapter(this));
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (parent.isGroupExpanded(groupPosition)) {
                    parent.collapseGroup(groupPosition);
                } else {
                    // 第二个参数false表示展开时是否触发默认滚动动画
                    parent.expandGroup(groupPosition, false);
                }
                return true;
            }
        });
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
                convertView = LayoutInflater.from(context).inflate(R.layout.item_main_expand_group, parent, false);
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
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildViewHolder childViewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_main_expand_child, parent, false);
                childViewHolder = new ChildViewHolder();
                childViewHolder.tv = convertView.findViewById(R.id.tv);
                convertView.setTag(childViewHolder);
            } else {
                childViewHolder = (ChildViewHolder) convertView.getTag();
            }
            childViewHolder.tv.setText(childStrings[groupPosition][childPosition]);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tag = childStrings[groupPosition][childPosition] + "Activity";
                    String name = getPackageName() + "." + tag;
                    try {
                        Class<? extends AppCompatActivity> clazz = Class.forName(name).asSubclass(AppCompatActivity.class);
                        startActivity(new Intent(MainActivity.this, clazz));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, tag + " not exist", Toast.LENGTH_SHORT).show();
                    }
                }
            });
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