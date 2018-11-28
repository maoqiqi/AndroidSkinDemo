package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import java.lang.ref.WeakReference;

/**
 * 演示ViewAnimator,ViewSwitcher,TextSwitcher,ImageSwitcher,ViewFlipper换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/2 23:08
 */
public class ViewAnimatorActivity extends BaseActivity {

    private static final int WHAT_VIEW_ANIMATOR = 1;
    private static final int WHAT_VIEW_SWITCHER = 2;
    private static final int WHAT_TEXT_SWITCHER = 3;
    private static final int WHAT_IMAGE_SWITCHER = 4;

    private static final int DELAY_MILLIS = 2000;

    private ViewAnimator viewAnimator;
    private ViewSwitcher viewSwitcher;
    private TextSwitcher textSwitcher;
    private ImageSwitcher imageSwitcher;
    private ViewFlipper viewFlipper;

    private String[] data;
    private int[] imageIds = {R.drawable.banner_1, R.drawable.banner_2, R.drawable.banner_3,
            R.drawable.banner_4, R.drawable.banner_5};

    private MyHandler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animator);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ViewAnimator");
        setSupportActionBar(toolbar);

        viewAnimator = findViewById(R.id.view_animator);
        viewSwitcher = findViewById(R.id.view_switcher);
        textSwitcher = findViewById(R.id.text_switcher);
        imageSwitcher = findViewById(R.id.image_switcher);
        viewFlipper = findViewById(R.id.view_flipper);

        data = getResources().getStringArray(R.array.hot_data);

        viewAnimator.setInAnimation(this, R.anim.switcher_enter);
        viewAnimator.setOutAnimation(this, R.anim.switcher_exit);
        viewAnimator.setAnimateFirstView(false);
        for (int i = 0; i < data.length; i++) {
            View view = View.inflate(this, R.layout.item_view_switcher, null);
            ((TextView) view.findViewById(R.id.tv)).setText(data[i]);
            viewAnimator.addView(view);
        }

        viewSwitcher.setInAnimation(this, R.anim.switcher_enter);
        viewSwitcher.setOutAnimation(this, R.anim.switcher_exit);
        viewSwitcher.setAnimateFirstView(false);
        viewSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return View.inflate(ViewAnimatorActivity.this, R.layout.item_view_switcher, null);
            }
        });

        textSwitcher.setInAnimation(this, R.anim.switcher_enter);
        textSwitcher.setOutAnimation(this, R.anim.switcher_exit);
        textSwitcher.setAnimateFirstView(false);
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return View.inflate(ViewAnimatorActivity.this, R.layout.item_text, null);
            }
        });

        imageSwitcher.setInAnimation(this, R.anim.switcher_enter);
        imageSwitcher.setOutAnimation(this, R.anim.switcher_exit);
        imageSwitcher.setAnimateFirstView(false);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return View.inflate(ViewAnimatorActivity.this, R.layout.item_image_switcher, null);
            }
        });

        viewFlipper.setFlipInterval(DELAY_MILLIS);
        viewFlipper.setInAnimation(this, R.anim.switcher_enter);
        viewFlipper.setOutAnimation(this, R.anim.switcher_exit);
        viewFlipper.setAnimateFirstView(false);
        viewFlipper.setAutoStart(true);
        for (int i = 0; i < data.length; i++) {
            View view = View.inflate(this, R.layout.item_view_switcher, null);
            ((TextView) view.findViewById(R.id.tv)).setText(data[i]);
            viewFlipper.addView(view);
        }

        handler = new MyHandler(this);
        handler.sendEmptyMessageDelayed(WHAT_VIEW_ANIMATOR, DELAY_MILLIS);
        handler.sendEmptyMessage(WHAT_VIEW_SWITCHER);
        handler.sendEmptyMessage(WHAT_TEXT_SWITCHER);
        handler.sendEmptyMessage(WHAT_IMAGE_SWITCHER);
    }

    private static final class MyHandler extends Handler {

        private WeakReference<ViewAnimatorActivity> weakReference;

        private int viewIndex;
        private int textIndex;
        private int imageIndex;

        MyHandler(ViewAnimatorActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_VIEW_ANIMATOR:
                    if (weakReference.get() != null) {
                        ViewAnimatorActivity activity = weakReference.get();
                        activity.viewAnimator.showNext();
                        activity.handler.sendEmptyMessageDelayed(WHAT_VIEW_ANIMATOR, DELAY_MILLIS);
                    }
                    break;
                case WHAT_VIEW_SWITCHER:
                    if (weakReference.get() != null) {
                        ViewAnimatorActivity activity = weakReference.get();
                        View view = activity.viewSwitcher.getNextView();
                        ((TextView) view.findViewById(R.id.tv)).setText(activity.data[viewIndex]);
                        activity.viewSwitcher.showNext();
                        viewIndex++;
                        if (viewIndex == activity.data.length) viewIndex = 0;
                        activity.handler.sendEmptyMessageDelayed(WHAT_VIEW_SWITCHER, DELAY_MILLIS);
                    }
                    break;
                case WHAT_TEXT_SWITCHER:
                    if (weakReference.get() != null) {
                        ViewAnimatorActivity activity = weakReference.get();
                        // activity.textSwitcher.setText(activity.data[textIndex]); // 等价于下面两行
                        ((TextView) activity.textSwitcher.getNextView()).setText(activity.data[textIndex]);
                        activity.textSwitcher.showNext();
                        textIndex++;
                        if (textIndex == activity.data.length) textIndex = 0;
                        activity.handler.sendEmptyMessageDelayed(WHAT_TEXT_SWITCHER, DELAY_MILLIS);
                    }
                    break;
                case WHAT_IMAGE_SWITCHER:
                    ViewAnimatorActivity activity = weakReference.get();
                    activity.imageSwitcher.setImageResource(activity.imageIds[imageIndex]);
                    imageIndex++;
                    if (imageIndex == activity.imageIds.length) imageIndex = 0;
                    activity.handler.sendEmptyMessageDelayed(WHAT_IMAGE_SWITCHER, DELAY_MILLIS);
                    break;
            }
        }
    }
}