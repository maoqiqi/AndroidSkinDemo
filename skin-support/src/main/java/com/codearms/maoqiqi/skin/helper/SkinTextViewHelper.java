package com.codearms.maoqiqi.skin.helper;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TextViewCompat;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import com.codearms.maoqiqi.skin.R;

import java.lang.reflect.Field;

/**
 * TextView更新皮肤帮助类,TextView及子类都可以使用该帮助类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/5 20:56
 */
public class SkinTextViewHelper extends SkinHelper<TextView> {

    private int textAppearanceResId = INVALID_RESOURCES;
    private int textColorResId = INVALID_RESOURCES;
    private int textColorHintResId = INVALID_RESOURCES;
    private int textColorLinkResId = INVALID_RESOURCES;
    private int textColorHighlightResId = INVALID_RESOURCES;
    private int textCursorDrawableResId = INVALID_RESOURCES;
    private int textSelectHandleResId = INVALID_RESOURCES;
    private int textSelectHandleLeftResId = INVALID_RESOURCES;
    private int textSelectHandleRightResId = INVALID_RESOURCES;
    private int drawableLeftResId = INVALID_RESOURCES;
    private int drawableTopResId = INVALID_RESOURCES;
    private int drawableRightResId = INVALID_RESOURCES;
    private int drawableBottomResId = INVALID_RESOURCES;
    private int drawableStartResId = INVALID_RESOURCES;
    private int drawableEndResId = INVALID_RESOURCES;
    private int drawableTintResId = INVALID_RESOURCES;

    public SkinTextViewHelper(TextView view) {
        super(view);
    }

    @Override
    public void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SkinTextViewHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_textAppearance)) {
                textAppearanceResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_textAppearance, INVALID_RESOURCES);
                obtainTextAppearance();
            }
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_textColor)) {
                textColorResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_textColor, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_textColorHint)) {
                textColorHintResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_textColorHint, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_textColorLink)) {
                textColorLinkResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_textColorLink, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_textColorHighlight)) {
                textColorHighlightResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_textColorHighlight, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_textCursorDrawable)) {
                textCursorDrawableResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_textCursorDrawable, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_textSelectHandle)) {
                textSelectHandleResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_textSelectHandle, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_textSelectHandleLeft)) {
                textSelectHandleLeftResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_textSelectHandleLeft, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_textSelectHandleRight)) {
                textSelectHandleRightResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_textSelectHandleRight, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_drawableLeft)) {
                drawableLeftResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_drawableLeft, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_drawableTop)) {
                drawableTopResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_drawableTop, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_drawableRight)) {
                drawableRightResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_drawableRight, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_drawableBottom)) {
                drawableBottomResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_drawableBottom, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_drawableStart)) {
                drawableStartResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_drawableStart, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_drawableEnd)) {
                drawableEndResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_drawableEnd, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextViewHelper_android_drawableTint)) {
                drawableTintResId = a.getResourceId(R.styleable.SkinTextViewHelper_android_drawableTint, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 解析SkinTextAppearance,获取属性值
     */
    private void obtainTextAppearance() {
        if (textAppearanceResId == INVALID_RESOURCES) return;
        TypedArray a = view.getContext().obtainStyledAttributes(textAppearanceResId, R.styleable.SkinTextAppearance);
        try {
            if (a.hasValue(R.styleable.SkinTextAppearance_android_textColor)) {
                textColorResId = a.getResourceId(R.styleable.SkinTextAppearance_android_textColor, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextAppearance_android_textColorHint)) {
                textColorHintResId = a.getResourceId(R.styleable.SkinTextAppearance_android_textColorHint, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextAppearance_android_textColorLink)) {
                textColorLinkResId = a.getResourceId(R.styleable.SkinTextAppearance_android_textColorLink, INVALID_RESOURCES);
            }
            if (a.hasValue(R.styleable.SkinTextAppearance_android_textColorHighlight)) {
                textColorHighlightResId = a.getResourceId(R.styleable.SkinTextAppearance_android_textColorHighlight, INVALID_RESOURCES);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * 设置文本样式
     *
     * @param resId resource id
     */
    public void setSupportTextAppearance(int resId) {
        textAppearanceResId = resId;
        if (textAppearanceResId == INVALID_RESOURCES) return;
        obtainTextAppearance();
        applySupportTextColor();
        applySupportTextColorHint();
        applySupportTextColorLink();
        applySupportTextColorHighlight();
    }

    /**
     * 设置左上右下Drawable,宽高是按drawable固定的宽高
     *
     * @param left   左边drawable resource id
     * @param top    上边drawable resource id
     * @param right  右边drawable resource id
     * @param bottom 下边drawable resource id
     */
    public void setSupportCompoundDrawablesWithIntrinsicBounds(int left, int top, int right, int bottom) {
        drawableLeftResId = left;
        drawableTopResId = top;
        drawableRightResId = right;
        drawableBottomResId = bottom;
        applySupportCompoundDrawables();
    }

    /**
     * 设置左上右下Drawable,宽高是按drawable固定的宽高
     * Relative只固定了top、bottom两个位置的drawable,start、end两个位置,可以根据显示方向的不同,调整左、右分布
     *
     * @param start  左边drawable resource id
     * @param top    上边drawable resource id
     * @param end    右边drawable resource id
     * @param bottom 下边drawable resource id
     */
    public void setSupportCompoundDrawablesRelativeWithIntrinsicBounds(int start, int top, int end, int bottom) {
        drawableStartResId = start;
        drawableTopResId = top;
        drawableEndResId = end;
        drawableBottomResId = bottom;
        applySupportCompoundDrawablesRelativeIfNeeded();
    }

    /**
     * 应用文本颜色
     */
    private void applySupportTextColor() {
        if (textColorResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(textColorResId);
        if (isColor(typeName) || isDrawable(typeName)) {
            ColorStateList colorStateList = getColorStateList(textColorResId);
            if (colorStateList == null) return;
            view.setTextColor(colorStateList);
        }
    }

    /**
     * 应用提示文本颜色
     */
    private void applySupportTextColorHint() {
        if (textColorHintResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(textColorHintResId);
        if (isColor(typeName) || isDrawable(typeName)) {
            ColorStateList colorStateList = getColorStateList(textColorHintResId);
            if (colorStateList == null) return;
            view.setHintTextColor(colorStateList);
        }
    }

    /**
     * 应用链接文本颜色
     */
    private void applySupportTextColorLink() {
        if (textColorLinkResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(textColorLinkResId);
        if (isColor(typeName) || isDrawable(typeName)) {
            ColorStateList colorStateList = getColorStateList(textColorLinkResId);
            if (colorStateList == null) return;
            view.setLinkTextColor(colorStateList);
        }
    }

    /**
     * 设置选中文本颜色
     */
    private void applySupportTextColorHighlight() {
        if (textColorHighlightResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(textColorHighlightResId);
        if (isColor(typeName) || isDrawable(typeName)) {
            ColorStateList colorStateList = getColorStateList(textColorHighlightResId);
            if (colorStateList == null) return;
            view.setHighlightColor(colorStateList.getDefaultColor());
        }
    }

    /**
     * 设置文本光标Drawable
     *
     * @param editText EditText视图
     * @param drawable 资源
     */
    private void setTextCursorDrawable(EditText editText, Drawable drawable) {
        try {
            String name = "mEditor";
            Field fEditor = TextView.class.getDeclaredField(name);
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);

            Field fCursorDrawable = editor.getClass().getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);
            fCursorDrawable.set(editor, new Drawable[]{drawable});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用文本光标Drawable
     */
    private void applySupportTextCursorDrawable() {
        if (!(view instanceof EditText)) return;
        if (textCursorDrawableResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(textCursorDrawableResId);
        Drawable drawable = null;
        if (isColor(typeName)) {
            int color = getColor(textColorHighlightResId);
            if (color == 0) return;
            drawable = new ColorDrawable(color);
        } else if (isDrawable(typeName)) {
            drawable = getDrawable(textCursorDrawableResId);
        }
        if (drawable == null) return;
        setTextCursorDrawable((EditText) view, drawable);
    }

    /**
     * 设置文本选中资源
     *
     * @param editText 视图
     * @param index    属性名称索引
     * @param drawable 需要赋值的资源
     */
    private void setTextSelectHandle(EditText editText, int index, Drawable drawable) {
        String[] names = {"mSelectHandleCenter", "mSelectHandleLeft", "mSelectHandleRight"};
        try {
            String name = "mEditor";
            Field fEditor = TextView.class.getDeclaredField(name);
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);

            Field fSelectHandle = editor.getClass().getDeclaredField(names[index]);
            fSelectHandle.setAccessible(true);
            fSelectHandle.set(editor, drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用文本选中资源
     */
    private void applySupportTextSelectHandle() {
        if (!(view instanceof EditText)) return;
        if (textSelectHandleResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(textSelectHandleResId);
        if (isDrawable(typeName)) {
            Drawable drawable = getDrawable(textSelectHandleResId);
            if (drawable == null) return;
            setTextSelectHandle((EditText) view, 0, drawable);
        }
    }

    /**
     * 应用文本选中资源
     */
    private void applySupportTextSelectHandleLeft() {
        if (!(view instanceof EditText)) return;
        if (textSelectHandleLeftResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(textSelectHandleLeftResId);
        if (isDrawable(typeName)) {
            Drawable drawable = getDrawable(textSelectHandleLeftResId);
            if (drawable == null) return;
            setTextSelectHandle((EditText) view, 1, drawable);
        }
    }

    /**
     * 应用文本选中资源
     */
    private void applySupportTextSelectHandleRight() {
        if (!(view instanceof EditText)) return;
        if (textSelectHandleRightResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(textSelectHandleRightResId);
        if (isDrawable(typeName)) {
            Drawable drawable = getDrawable(textSelectHandleRightResId);
            if (drawable == null) return;
            setTextSelectHandle((EditText) view, 2, drawable);
        }
    }

    /**
     * 应用Drawables
     */
    private void applySupportCompoundDrawables() {
        if (drawableLeftResId == INVALID_RESOURCES && drawableTopResId == INVALID_RESOURCES &&
                drawableRightResId == INVALID_RESOURCES && drawableBottomResId == INVALID_RESOURCES)
            return;

        Drawable drawableLeft = null, drawableTop = null, drawableRight = null, drawableBottom = null;
        if (drawableLeftResId != INVALID_RESOURCES) {
            String typeName = getTypeName(drawableLeftResId);
            if (isDrawable(typeName)) drawableLeft = getDrawable(drawableLeftResId);
        }
        if (drawableTopResId != INVALID_RESOURCES) {
            String typeName = getTypeName(drawableTopResId);
            if (isDrawable(typeName)) drawableTop = getDrawable(drawableTopResId);
        }
        if (drawableRightResId != INVALID_RESOURCES) {
            String typeName = getTypeName(drawableRightResId);
            if (isDrawable(typeName)) drawableRight = getDrawable(drawableRightResId);
        }
        if (drawableBottomResId != INVALID_RESOURCES) {
            String typeName = getTypeName(drawableBottomResId);
            if (isDrawable(typeName)) drawableBottom = getDrawable(drawableBottomResId);
        }
        view.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

    /**
     * 应用Drawables
     */
    private void applySupportCompoundDrawablesRelativeIfNeeded() {
        if (drawableStartResId == INVALID_RESOURCES && drawableEndResId == INVALID_RESOURCES)
            return;

        Drawable drawableStart = null, drawableTop = null, drawableEnd = null, drawableBottom = null;
        if (drawableStartResId != INVALID_RESOURCES) {
            String typeName = getTypeName(drawableStartResId);
            if (isDrawable(typeName)) drawableStart = getDrawable(drawableStartResId);
        }
        if (drawableEndResId != INVALID_RESOURCES) {
            String typeName = getTypeName(drawableEndResId);
            if (isDrawable(typeName)) drawableEnd = getDrawable(drawableEndResId);
        }
        if (drawableTopResId != INVALID_RESOURCES) {
            String typeName = getTypeName(drawableTopResId);
            if (isDrawable(typeName)) drawableTop = getDrawable(drawableTopResId);
        }
        if (drawableBottomResId != INVALID_RESOURCES) {
            String typeName = getTypeName(drawableBottomResId);
            if (isDrawable(typeName)) drawableBottom = getDrawable(drawableBottomResId);
        }
        TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(view, drawableStart, drawableTop, drawableEnd, drawableBottom);
    }

    /**
     * 应用Drawables着色
     */
    private void applySupportDrawableTint() {
        if (drawableTintResId == INVALID_RESOURCES) return;
        String typeName = getTypeName(drawableTintResId);
        if (isColor(typeName)) {
            ColorStateList colorStateList = getColorStateList(drawableTintResId);
            if (colorStateList == null) return;
            if (IS_MARSHMALLOW) {
                view.setCompoundDrawableTintList(colorStateList);
            }
        }
    }

    @Override
    public void updateSkin() {
        applySupportTextColor();
        applySupportTextColorHint();
        applySupportTextColorLink();
        applySupportTextColorHighlight();
        applySupportTextCursorDrawable();
        applySupportTextSelectHandle();
        applySupportTextSelectHandleLeft();
        applySupportTextSelectHandleRight();
        applySupportCompoundDrawables();
        applySupportCompoundDrawablesRelativeIfNeeded();
        applySupportDrawableTint();
    }
}