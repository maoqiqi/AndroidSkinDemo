package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示TabLayout换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/7 11:45
 */
public class TabLayoutActivity extends BaseActivity {

    private String[] titles = {"推荐", "热点", "社会", "娱乐", "情感", "故事", "小说", "星座", "科技", "财经", "体育", "军事", "教育", "历史", "搞笑", "奇闻", "游戏", "时尚", "养生", "美食", "旅行"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("TabLayout");
        setSupportActionBar(toolbar);

        TabLayout tabLayout1 = findViewById(R.id.tab_layout_1);
        TabLayout tabLayout2 = findViewById(R.id.tab_layout_2);
        TabLayout tabLayout3 = findViewById(R.id.tab_layout_3);
        TabLayout tabLayout4 = findViewById(R.id.tab_layout_4);
        ViewPager viewPager = findViewById(R.id.view_pager);

        tabLayout1.addTab(tabLayout1.newTab().setText(R.string.tab_1));
        tabLayout1.addTab(tabLayout1.newTab().setText(R.string.tab_2));
        tabLayout1.addTab(tabLayout1.newTab().setText(R.string.tab_3));

        tabLayout2.addTab(tabLayout2.newTab().setText(R.string.tab_1).setIcon(R.drawable.ic_triangle));
        tabLayout2.addTab(tabLayout2.newTab().setText(R.string.tab_2).setIcon(R.drawable.ic_rectangle));
        tabLayout2.addTab(tabLayout2.newTab().setText(R.string.tab_3).setIcon(R.drawable.ic_star));

        tabLayout3.addTab(tabLayout3.newTab().setText(R.string.tab_1).setIcon(R.drawable.ic_triangle));
        tabLayout3.addTab(tabLayout3.newTab().setText(R.string.tab_2).setIcon(R.drawable.ic_rectangle));
        tabLayout3.addTab(tabLayout3.newTab().setText(R.string.tab_3).setIcon(R.drawable.ic_star));

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        for (String title : titles) {
            adapter.addFragment(title, getFragment(title));
        }

        // 设置ViewPager
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setCurrentItem(0);

        tabLayout4.setupWithViewPager(viewPager);
    }

    private Fragment getFragment(String title) {
        return MyFragment.newInstance(title);
    }

    public static class MyFragment extends Fragment {

        public static MyFragment newInstance(String title) {
            Bundle args = new Bundle();
            args.putString("title", title);
            MyFragment fragment = new MyFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            TextView textView = new TextView(getActivity());
            textView.setText(getArguments().getString("title"));
            textView.setTextColor(getResources().getColor(R.color.white));
            textView.setTextSize(18);
            return textView;
        }
    }

    private final class SectionsPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;
        private List<String> fragmentTitles;

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<>();
            fragmentTitles = new ArrayList<>();
        }

        void addFragment(String title, Fragment fragment) {
            fragmentTitles.add(title);
            fragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }
    }
}