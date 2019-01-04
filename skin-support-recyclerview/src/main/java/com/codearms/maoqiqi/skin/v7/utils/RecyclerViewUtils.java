package com.codearms.maoqiqi.skin.v7.utils;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RecyclerViewUtils {

    public static void setFastScrollDrawable(RecyclerView recyclerView, Drawable verticalThumbDrawable,
                                             Drawable verticalTrackDrawable, Drawable horizontalThumbDrawable, Drawable horizontalTrackDrawable) {
        // TODO: 18/11/26 无效,暂时没有好的解决办法
        Class<?> c[] = new Class[]{Drawable.class, Drawable.class, Drawable.class, Drawable.class};
        try {
            String name = "initFastScroller";
            Method initFastScrollerMethod = RecyclerView.class.getDeclaredMethod(name, c);
            initFastScrollerMethod.setAccessible(true);
            initFastScrollerMethod.invoke(recyclerView, verticalThumbDrawable, verticalTrackDrawable,
                    horizontalThumbDrawable, horizontalTrackDrawable);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}