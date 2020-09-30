package com.codearms.maoqiqi.skin.demo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示ViewPager换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/6 16:45
 */
public class ViewPagerActivity extends BaseActivity {

    private int[] imageIds = {R.drawable.banner_1, R.drawable.banner_2, R.drawable.banner_3,
            R.drawable.banner_4, R.drawable.banner_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ViewPager");
        setSupportActionBar(toolbar);

        ViewPager viewPager1 = findViewById(R.id.view_pager_1);
        ViewPager viewPager2 = findViewById(R.id.view_pager_2);
        PagerTabStrip pagerTabStrip = findViewById(R.id.pager_tab_strip);
        pagerTabStrip.setTabIndicatorColorResource(R.color.color_5);

        List<View> list = new ArrayList<>();
        for (int imageId : imageIds) {
            ImageView imageView = (ImageView) View.inflate(this, R.layout.item_image_switcher, null);
            imageView.setImageResource(imageId);
            list.add(imageView);
        }

        viewPager1.setAdapter(new ViewPagerAdapter1(list));
        viewPager2.setAdapter(new ViewPagerAdapter2(list));
    }

    private class ViewPagerAdapter1 extends PagerAdapter {

        private List<View> views;

        ViewPagerAdapter1(List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views == null ? 0 : views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = views.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "title:" + position;
        }
    }

    private class ViewPagerAdapter2 extends PagerAdapter {

        private List<View> views;

        ViewPagerAdapter2(List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views == null ? 0 : views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = views.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            SpannableStringBuilder ssb = new SpannableStringBuilder("  " + position);
            Drawable drawable = ContextCompat.getDrawable(ViewPagerActivity.this, R.drawable.ic_star);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            }
            ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);

            ForegroundColorSpan fcs = new ForegroundColorSpan(ContextCompat.getColor(ViewPagerActivity.this, R.color.color_5));
            // 设置图标
            ssb.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            // 设置字体颜色
            ssb.setSpan(fcs, 1, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.setSpan(new RelativeSizeSpan(1.2f), 1, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ssb;
        }
    }
}