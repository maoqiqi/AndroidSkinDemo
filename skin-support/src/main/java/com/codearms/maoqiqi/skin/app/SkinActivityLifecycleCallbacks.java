package com.codearms.maoqiqi.skin.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;

import com.codearms.maoqiqi.skin.annotation.Skin;
import com.codearms.maoqiqi.skin.listener.OnSkinChangeListener;
import com.codearms.maoqiqi.skin.manager.SkinManager;
import com.codearms.maoqiqi.skin.observe.SkinObserver;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.WeakHashMap;

/**
 * 监控应用生命周期
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/2 22:44
 */
public class SkinActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    /**
     * 保存当前显示的Activity
     */
    private WeakReference<Activity> currentActivityRef;

    /**
     * 存储自定义LayoutInflater Factory
     */
    private WeakHashMap<Activity, SkinLayoutInflaterFactory> skinLayoutInflaterFactoryMap;

    /**
     * 存储LazySkinObserver
     */
    private WeakHashMap<Activity, LazySkinObserver> skinObserverMap;

    /**
     * 当前实例对象
     */
    private static SkinActivityLifecycleCallbacks instance;

    /**
     * 私有化构造函数
     *
     * @param application the application
     */
    private SkinActivityLifecycleCallbacks(Application application) {
        super();
        application.registerActivityLifecycleCallbacks(this);
    }

    /**
     * 初始化,单例模式,只需要初始化一次,已在{@link SkinManager#SkinManager}中初始化。
     *
     * @param application the application
     */
    public static void init(Application application) {
        if (instance == null) {
            synchronized (SkinActivityLifecycleCallbacks.class) {
                if (instance == null) {
                    instance = new SkinActivityLifecycleCallbacks(application);
                }
            }
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (isSkinEnable(activity)) {
            setFactory(activity);
            // 订阅皮肤更新通知
            SkinManager.getInstance().addObserver(getSkinObserver(activity));
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (isSkinEnable(activity)) {
            currentActivityRef = new WeakReference<>(activity);
            // 如果需要,更新皮肤
            getSkinObserver(activity).updateSkinIfNeeded();
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (isSkinEnable(activity)) {
            getSkinLayoutInflaterFactory(activity).clearData();
            skinLayoutInflaterFactoryMap.remove(activity);
            // 取消订阅皮肤更新通知
            SkinManager.getInstance().removeObserver(getSkinObserver(activity));
            skinObserverMap.remove(activity);
        }
    }

    /**
     * 是否需要换肤功能:
     * 1.SkinManager.getInstance().isSkinAllActivityEnable():所有Activity都支持换肤;
     * 2.activity.getClass().getAnnotation(Skin.class) != null:添加了Skin注解;
     * 3.activity instanceof OnSkinChangeListener:实现了OnSkinChangeListener接口;
     * 以三个条件满足任何一个表示都需要开启换肤功能。
     *
     * @param activity the activity
     * @return true:需要;false:不需要
     */
    private boolean isSkinEnable(final Activity activity) {
        return SkinManager.getInstance().isSkinAllActivityEnable() ||
                activity.getClass().getAnnotation(Skin.class) != null ||
                activity instanceof OnSkinChangeListener;
    }

    /**
     * 使用自己实现系统提供的LayoutInflater.Factory2接口,替换系统默认的来创建视图
     *
     * @param activity the activity
     */
    private void setFactory(final Activity activity) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        // 判断是否已经LayoutInflater已经存在Factory
        if (inflater.getFactory() != null) {
            try {
                String name = "mFactorySet";
                // 通过反射得到mFactorySet这个私有属性
                Field field = LayoutInflater.class.getDeclaredField(name);
                // 值为true则指示反射的对象在使用时应该取消Java语言访问检查
                field.setAccessible(true);
                // 更改私有属性的值
                field.setBoolean(inflater, false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        LayoutInflaterCompat.setFactory2(inflater, getSkinLayoutInflaterFactory(activity));
    }

    /**
     * 根据Activity得到自己实现LayoutInflater.Factory2接口的实例
     *
     * @param activity the activity
     * @return 自定义LayoutInflater Factory
     */
    private SkinLayoutInflaterFactory getSkinLayoutInflaterFactory(final Activity activity) {
        if (skinLayoutInflaterFactoryMap == null) {
            skinLayoutInflaterFactoryMap = new WeakHashMap<>();
        }

        SkinLayoutInflaterFactory skinLayoutInflaterFactory = skinLayoutInflaterFactoryMap.get(activity);
        if (skinLayoutInflaterFactory == null) {
            skinLayoutInflaterFactory = SkinLayoutInflaterFactory.create(activity);
            skinLayoutInflaterFactoryMap.put(activity, skinLayoutInflaterFactory);
        }
        return skinLayoutInflaterFactory;
    }

    /**
     * 根据Activity得到LazySkinObserver
     *
     * @param activity the activity
     * @return LazySkinObserver
     */
    private LazySkinObserver getSkinObserver(final Activity activity) {
        if (skinObserverMap == null) {
            skinObserverMap = new WeakHashMap<>();
        }

        LazySkinObserver skinObserver = skinObserverMap.get(activity);
        if (skinObserver == null) {
            skinObserver = new LazySkinObserver(activity);
            skinObserverMap.put(activity, skinObserver);
        }
        return skinObserver;
    }

    /**
     * 懒加载,当前显示的Activity立即更新皮肤,其他Activity显示的时候再更新皮肤
     */
    private class LazySkinObserver implements SkinObserver {

        /**
         * the Activity
         */
        private final Activity activity;

        /**
         * true:currentActivityRef==null或者Activity不是当前显示的Activity
         * false:Activity是当前显示的Activity
         */
        private boolean needUpdateSkin;

        LazySkinObserver(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void updateSkin() {
            // Activity是当前显示的Activity,立即更新皮肤
            if (currentActivityRef.get() == null || activity == currentActivityRef.get()) {
                updateSkinForce();
            } else {
                needUpdateSkin = true;
            }
        }

        /**
         * 当needUpdateSkin==tru时,需要更新皮肤。
         * 在{@link SkinActivityLifecycleCallbacks#onActivityResumed(Activity)}中调用。
         */
        void updateSkinIfNeeded() {
            if (needUpdateSkin) {
                updateSkinForce();
            }
        }

        /**
         * 更新具体内容
         */
        private void updateSkinForce() {
            if (activity == null) return;
            getSkinLayoutInflaterFactory(activity).updateStatusBarColor();
            getSkinLayoutInflaterFactory(activity).updateSkin();
            getSkinLayoutInflaterFactory(activity).skinChangedCallBack();
        }
    }
}