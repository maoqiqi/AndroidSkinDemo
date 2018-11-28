package com.codearms.maoqiqi.skin.app;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.codearms.maoqiqi.skin.listener.OnSkinChangeListener;
import com.codearms.maoqiqi.skin.manager.SkinManager;
import com.codearms.maoqiqi.skin.manager.SkinResourcesManager;
import com.codearms.maoqiqi.skin.widget.Skinable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现系统提供的LayoutInflater.Factory2接口,替换系统默认的来创建视图
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/2 22:35
 */
public class SkinLayoutInflaterFactory implements LayoutInflater.Factory2 {

    /**
     * the Activity
     */
    private Activity activity;

    /**
     * 对Context进行包装
     */
    private SkinContextWrapper skinContextWrapper;

    /**
     * 创建视图
     */
    private AppCompatViewInflater appCompatViewInflater;

    /**
     * 保存所有实现Skinable接口的实例
     */
    private List<WeakReference<Skinable>> skinWeakReferenceSkinableList = new ArrayList<>();

    /**
     * 私有化构造函数
     *
     * @param activity the activity
     */
    private SkinLayoutInflaterFactory(Activity activity) {
        super();
        this.activity = activity;
    }

    /**
     * 创建对象当前实例对象
     *
     * @param activity the activity
     * @return {@link SkinLayoutInflaterFactory}实例对象
     */
    public static SkinLayoutInflaterFactory create(Activity activity) {
        return new SkinLayoutInflaterFactory(activity);
    }

    /**
     * 参考AppCompatDelegateImplV9{@link android.support.v7.app.AppCompatDelegateImplV9}
     */
    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if (skinContextWrapper == null) {
            skinContextWrapper = new SkinContextWrapper();
        }
        context = skinContextWrapper.wrapContext(parent, name, context, attrs);

        if (appCompatViewInflater == null) {
            appCompatViewInflater = new AppCompatViewInflater();
        }

        View view = appCompatViewInflater.createView(parent, name, context, attrs);
        if (view != null) {
            if (view instanceof Skinable) {
                skinWeakReferenceSkinableList.add(new WeakReference<>((Skinable) view));
            }
        }
        return view;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return null;
    }

    /**
     * 更新状态栏
     */
    public void updateStatusBarColor() {
        if (activity == null) return;

        if ((SkinManager.getInstance().isSkinAllStatusBarColorEnable())) {
            if (SkinManager.getInstance().getSkinStatusBarColorDisEnables().containsKey(activity)) {
                return;
            }
        } else {
            if (!SkinManager.getInstance().getSkinStatusBarColorEnables().containsKey(activity)) {
                return;
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(SkinResourcesManager.getInstance().getStatusBarColor(activity));
        }
    }

    /**
     * 更新皮肤
     */
    public void updateSkin() {
        if (!skinWeakReferenceSkinableList.isEmpty()) {
            for (WeakReference<Skinable> weakReference : skinWeakReferenceSkinableList) {
                if (weakReference.get() != null) weakReference.get().updateSkin();
            }
        }
    }

    /**
     * 如果实现了OnSkinChangeListener接口,调用{@link OnSkinChangeListener#onSkinChanged()}回调方法
     */
    public void skinChangedCallBack() {
        if (activity != null) {
            if (activity instanceof OnSkinChangeListener) {
                ((OnSkinChangeListener) activity).onSkinChanged();
            }
        }
    }

    /**
     * 清除数据
     */
    public void clearData() {
        if (activity != null) {
            activity = null;
        }

        if (!skinWeakReferenceSkinableList.isEmpty()) {
            skinWeakReferenceSkinableList.clear();
        }
    }
}