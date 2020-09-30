package com.codearms.maoqiqi.skin.utils;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toolbar;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过放射来设置视图资源
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/29 22:07
 */
public class ViewUtils {

    /**
     * 设置
     *
     * @param view     视图
     * @param drawable Drawable 资源
     * @return 是否设置成功
     */
    public static boolean isSetForeground(View view, Drawable drawable) {
        // TODO: 18/12/13 初始化可以,第二次调用没有显示.
        try {
            Class<?> c = Class.forName("android.support.design.internal.ForegroundLinearLayout");
            view.getClass().asSubclass(c);
            Method method = c.getDeclaredMethod("setForeground", Drawable.class);
            method.setAccessible(true);
            method.invoke(view, drawable);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置滚动条 Drawable
     *
     * @param view     视图
     * @param index    方法名对应索引
     *                 0:setVerticalThumbDrawable
     *                 1:setVerticalTrackDrawable
     *                 2:setHorizontalThumbDrawable
     *                 3:setHorizontalTrackDrawable
     * @param drawable Drawable 资源
     */
    public static void setScrollBarDrawable(View view, int index, Drawable drawable) {
        String[] names = {"setVerticalThumbDrawable", "setVerticalTrackDrawable",
                "setHorizontalThumbDrawable", "setHorizontalTrackDrawable"};
        try {
            String name = "mScrollCache";
            Field fScrollCache = View.class.getDeclaredField(name);
            fScrollCache.setAccessible(true);
            Object scrollCache = fScrollCache.get(view);

            Field fScrollBar = scrollCache.getClass().getDeclaredField("scrollBar");
            fScrollBar.setAccessible(true);
            Object scrollBar = fScrollBar.get(scrollCache);

            Method method = scrollBar.getClass().getDeclaredMethod(names[index], Drawable.class);
            method.setAccessible(true);
            method.invoke(scrollBar, drawable);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置文本光标 Drawable
     *
     * @param editText 视图
     * @param drawable Drawable 资源
     */
    public static void setTextCursorDrawable(EditText editText, Drawable drawable) {
        try {
            String name = "mEditor";
            Field fEditor = TextView.class.getDeclaredField(name);
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);

            Field fCursorDrawable = editor.getClass().getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);

            Drawable[] drawables = new Drawable[2];
            drawables[0] = drawable;
            drawables[1] = drawable;

            fCursorDrawable.set(editor, drawables);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置文本选中 Drawable
     *
     * @param editText 视图
     * @param index    属性名对应索引
     *                 0:mSelectHandleCenter
     *                 1:mSelectHandleLeft
     *                 2:mSelectHandleRight
     * @param drawable Drawable 资源
     */
    public static void setTextSelectHandle(EditText editText, int index, Drawable drawable) {
        String[] names = {"mSelectHandleCenter", "mSelectHandleLeft", "mSelectHandleRight"};
        try {
            String name = "mEditor";
            Field fEditor = TextView.class.getDeclaredField(name);
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);

            Field field = editor.getClass().getDeclaredField(names[index]);
            field.setAccessible(true);
            field.set(editor, drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重置mInsertionPointCursorController变量,重新创建SelectHandleCenter
     *
     * @param editText 视图
     */
    public static void resetInsertionPointCursorController(EditText editText) {
        try {
            String name = "mEditor";
            Field fEditor = TextView.class.getDeclaredField(name);
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);

            Field field = editor.getClass().getDeclaredField("mInsertionPointCursorController");
            field.setAccessible(true);
            field.set(editor, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重置mSelectionModifierCursorController变量,重新创建SelectHandleLeft,SelectHandleRight
     *
     * @param editText 视图
     */
    public static void resetSelectionModifierCursorController(EditText editText) {
        try {
            String name = "mEditor";
            Field fEditor = TextView.class.getDeclaredField(name);
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);

            Field field = editor.getClass().getDeclaredField("mSelectionModifierCursorController");
            field.setAccessible(true);
            field.set(editor, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置AutoCompleteTextView下拉选项背景Drawable
     *
     * @param autoCompleteTextView 视图
     * @param drawable             Drawable 资源
     */
    public static void setDropDownSelector(AutoCompleteTextView autoCompleteTextView, Drawable drawable) {
        try {
            String name = "mPopup";
            Field fPopup = AutoCompleteTextView.class.getDeclaredField(name);
            fPopup.setAccessible(true);
            ListPopupWindow popup = (ListPopupWindow) fPopup.get(autoCompleteTextView);
            popup.setListSelector(drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置 Switch 文本颜色
     *
     * @param s              视图
     * @param colorStateList ColorStateList 资源
     */
    public static void setSwitchTextColor(Switch s, ColorStateList colorStateList) {
        try {
            String name = "mTextColors";
            Field fTextColors = Switch.class.getDeclaredField(name);
            fTextColors.setAccessible(true);
            fTextColors.set(s, colorStateList);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置进度条 Drawable(兼容LOLLIPOP之前版本)
     *
     * @param progressBar 视图
     * @param drawable    Drawable 资源
     */
    public static void setProgressDrawableTiled(ProgressBar progressBar, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressBar.setProgressDrawableTiled(drawable);
        } else {
            try {
                String name = "tileify";
                Class<?> clazz = ProgressBar.class;

                Class<?> c[] = new Class[2];
                c[0] = Drawable.class;
                c[1] = boolean.class;
                Object[] o = new Object[2];
                o[0] = drawable;
                o[1] = false;

                Method method = clazz.getDeclaredMethod(name, c);
                method.setAccessible(true);
                drawable = (Drawable) method.invoke(progressBar, o);
                progressBar.setProgressDrawable(drawable);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置solidColor
     *
     * @param numberPicker 视图
     * @param color        color 资源
     */
    public static void setSolidColor(NumberPicker numberPicker, int color) {
        try {
            String name = "mSolidColor";
            Field fVirtualButtonPressedDrawable = NumberPicker.class.getDeclaredField(name);
            fVirtualButtonPressedDrawable.setAccessible(true);
            fVirtualButtonPressedDrawable.set(numberPicker, color);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView Query背景
     *
     * @param searchView 视图
     * @param drawable   Drawable 资源
     */
    public static void setQueryBackground(SearchView searchView, Drawable drawable) {
        try {
            String name = "mSearchPlate";
            Field fSearchPlate = SearchView.class.getDeclaredField(name);
            fSearchPlate.setAccessible(true);
            View view = (View) fSearchPlate.get(searchView);
            ViewCompat.setBackground(view, drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView Submit背景
     *
     * @param searchView 视图
     * @param drawable   Drawable 资源
     */
    public static void setSubmitBackground(SearchView searchView, Drawable drawable) {
        try {
            String name = "mSubmitArea";
            Field fSubmitArea = SearchView.class.getDeclaredField(name);
            fSubmitArea.setAccessible(true);
            View view = (View) fSubmitArea.get(searchView);
            ViewCompat.setBackground(view, drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView搜索Icon
     *
     * @param searchView 视图
     * @param drawable   Drawable 资源
     */
    public static void setSearchIcon(SearchView searchView, Drawable drawable) {
        try {
            String name = "mSearchButton";
            Field fSearchButton = SearchView.class.getDeclaredField(name);
            fSearchButton.setAccessible(true);
            ImageView searchButton = (ImageView) fSearchButton.get(searchView);
            searchButton.setImageDrawable(drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView搜索提示Icon
     *
     * @param searchView 视图
     * @param drawable   Drawable 资源
     */
    public static void setSearchHintIcon(SearchView searchView, Drawable drawable) {
        try {
            String name = "mSearchSrcTextView";
            Field fSearchSrcTextView = SearchView.class.getDeclaredField(name);
            fSearchSrcTextView.setAccessible(true);
            TextView searchSrcTextView = (TextView) fSearchSrcTextView.get(searchView);

            final int textSize = (int) (searchSrcTextView.getTextSize() * 1.25);
            drawable.setBounds(0, 0, textSize, textSize);

            CharSequence hint = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                hint = searchView.getQueryHint();
            }

            final SpannableStringBuilder ssb = new SpannableStringBuilder("   ");
            ssb.setSpan(new ImageSpan(drawable), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.append(hint == null ? "" : hint);
            searchSrcTextView.setHint(ssb);

            // TODO: 18/11/22 还有问题,根android.support.v7.widget.SearchView不一样
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView CloseIcon
     *
     * @param searchView 视图
     * @param drawable   Drawable 资源
     */
    public static void setCloseIcon(SearchView searchView, Drawable drawable) {
        try {
            String name = "mCloseButton";
            Field fCloseButton = SearchView.class.getDeclaredField(name);
            fCloseButton.setAccessible(true);
            ImageView closeButton = (ImageView) fCloseButton.get(searchView);
            closeButton.setImageDrawable(drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView GoIcon
     *
     * @param searchView 视图
     * @param drawable   Drawable 资源
     */
    public static void setGoIcon(SearchView searchView, Drawable drawable) {
        try {
            String name = "mGoButton";
            Field fGoButton = SearchView.class.getDeclaredField(name);
            fGoButton.setAccessible(true);
            ImageView goButton = (ImageView) fGoButton.get(searchView);
            goButton.setImageDrawable(drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView VoiceIcon
     *
     * @param searchView 视图
     * @param drawable   drawable资源
     */
    public static void setVoiceIcon(SearchView searchView, Drawable drawable) {
        try {
            String name = "mVoiceButton";
            Field fVoiceButton = SearchView.class.getDeclaredField(name);
            fVoiceButton.setAccessible(true);
            ImageView voiceButton = (ImageView) fVoiceButton.get(searchView);
            voiceButton.setImageDrawable(drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SearchView CommitIcon
     *
     * @param searchView 视图
     * @param resId      resource id
     */
    public static void setCommitIcon(SearchView searchView, int resId) {
        try {
            String name = "mSuggestionCommitIconResId";
            Field fSuggestionCommitIconResId = SearchView.class.getDeclaredField(name);
            fSuggestionCommitIconResId.setAccessible(true);
            fSuggestionCommitIconResId.set(searchView, resId);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置头部背景
     *
     * @param datePicker 视图
     * @param drawable   背景资源
     */
    public static void setHeaderBackground(DatePicker datePicker, Drawable drawable) {
        try {
            Resources resources = datePicker.getResources();
            int id = resources.getIdentifier("date_picker_header", "id", "android");
            View header = datePicker.findViewById(id);
            header.setBackgroundColor(Color.BLUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置头部背景
     *
     * @param timePicker 视图
     * @param drawable   背景资源
     */
    public static void setHeaderBackground(TimePicker timePicker, Drawable drawable) {
        try {
            Resources resources = timePicker.getResources();
            int id = resources.getIdentifier("time_header", "id", "android");
            View timeHeader = timePicker.findViewById(id);
            ViewCompat.setBackground(timeHeader, drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置CollapseIcon
     *
     * @param toolbar  视图
     * @param drawable drawable资源
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setCollapseIcon(Toolbar toolbar, Drawable drawable) {
        try {
            String name = "mCollapseIcon";
            Field fCollapseIcon = Toolbar.class.getDeclaredField(name);
            fCollapseIcon.setAccessible(true);
            fCollapseIcon.set(toolbar, drawable);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除私有API调用的警告
     */
    public static void removeWarning() {
        try {
            @SuppressLint("PrivateApi")
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);

            Field fHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            fHiddenApiWarningShown.setAccessible(true);
            fHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}