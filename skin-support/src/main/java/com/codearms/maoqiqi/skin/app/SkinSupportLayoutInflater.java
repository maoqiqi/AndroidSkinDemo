package com.codearms.maoqiqi.skin.app;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.codearms.maoqiqi.skin.widget.SkinAdapterViewFlipper;
import com.codearms.maoqiqi.skin.widget.SkinAutoCompleteTextView;
import com.codearms.maoqiqi.skin.widget.SkinButton;
import com.codearms.maoqiqi.skin.widget.SkinCalendarView;
import com.codearms.maoqiqi.skin.widget.SkinCheckBox;
import com.codearms.maoqiqi.skin.widget.SkinCheckedTextView;
import com.codearms.maoqiqi.skin.widget.SkinDatePicker;
import com.codearms.maoqiqi.skin.widget.SkinEditText;
import com.codearms.maoqiqi.skin.widget.SkinFrameLayout;
import com.codearms.maoqiqi.skin.widget.SkinGallery;
import com.codearms.maoqiqi.skin.widget.SkinGridLayout;
import com.codearms.maoqiqi.skin.widget.SkinGridView;
import com.codearms.maoqiqi.skin.widget.SkinHorizontalScrollView;
import com.codearms.maoqiqi.skin.widget.SkinImageButton;
import com.codearms.maoqiqi.skin.widget.SkinImageSwitcher;
import com.codearms.maoqiqi.skin.widget.SkinImageView;
import com.codearms.maoqiqi.skin.widget.SkinLinearLayout;
import com.codearms.maoqiqi.skin.widget.SkinListView;
import com.codearms.maoqiqi.skin.widget.SkinMultiAutoCompleteTextView;
import com.codearms.maoqiqi.skin.widget.SkinNumberPicker;
import com.codearms.maoqiqi.skin.widget.SkinProgressBar;
import com.codearms.maoqiqi.skin.widget.SkinRadioButton;
import com.codearms.maoqiqi.skin.widget.SkinRadioGroup;
import com.codearms.maoqiqi.skin.widget.SkinRatingBar;
import com.codearms.maoqiqi.skin.widget.SkinRelativeLayout;
import com.codearms.maoqiqi.skin.widget.SkinScrollView;
import com.codearms.maoqiqi.skin.widget.SkinSearchView;
import com.codearms.maoqiqi.skin.widget.SkinSeekBar;
import com.codearms.maoqiqi.skin.widget.SkinSpinner;
import com.codearms.maoqiqi.skin.widget.SkinStackView;
import com.codearms.maoqiqi.skin.widget.SkinSwitch;
import com.codearms.maoqiqi.skin.widget.SkinTableLayout;
import com.codearms.maoqiqi.skin.widget.SkinTableRow;
import com.codearms.maoqiqi.skin.widget.SkinTextClock;
import com.codearms.maoqiqi.skin.widget.SkinTextSwitcher;
import com.codearms.maoqiqi.skin.widget.SkinTextView;
import com.codearms.maoqiqi.skin.widget.SkinTimePicker;
import com.codearms.maoqiqi.skin.widget.SkinToggleButton;
import com.codearms.maoqiqi.skin.widget.SkinToolbar;
import com.codearms.maoqiqi.skin.widget.SkinView;
import com.codearms.maoqiqi.skin.widget.SkinViewAnimator;
import com.codearms.maoqiqi.skin.widget.SkinViewFlipper;
import com.codearms.maoqiqi.skin.widget.SkinViewSwitcher;
import com.codearms.maoqiqi.skin.widget.SkinZoomControls;

/**
 * 实现自定义SkinLayoutInflater接口来创建视图
 * 使用自定义SkinView替换系统对应View,而不是通过反射创建对应的视图
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/5 20:45
 */
public class SkinSupportLayoutInflater implements SkinLayoutInflater {

    @Override
    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        if (name.contains(".")) return null;
        View view = null;
        switch (name) {
            case "View":
                view = new SkinView(context, attrs);
                break;
            case "TextView":
                view = new SkinTextView(context, attrs);
                break;
            case "EditText":
                view = new SkinEditText(context, attrs);
                break;
            case "AutoCompleteTextView":
                view = new SkinAutoCompleteTextView(context, attrs);
                break;
            case "MultiAutoCompleteTextView":
                view = new SkinMultiAutoCompleteTextView(context, attrs);
                break;
            case "Button":
                view = new SkinButton(context, attrs);
                break;
            case "RadioButton":
                view = new SkinRadioButton(context, attrs);
                break;
            case "CheckBox":
                view = new SkinCheckBox(context, attrs);
                break;
            case "ToggleButton":
                view = new SkinToggleButton(context, attrs);
                break;
            case "Switch":
                view = new SkinSwitch(context, attrs);
                break;
            case "CheckedTextView":
                view = new SkinCheckedTextView(context, attrs);
                break;
            case "TextClock":
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    view = new SkinTextClock(context, attrs);
                }
                break;
            case "ProgressBar":
                view = new SkinProgressBar(context, attrs);
                break;
            case "RatingBar":
                view = new SkinRatingBar(context, attrs);
                break;
            case "SeekBar":
                view = new SkinSeekBar(context, attrs);
                break;
            case "ImageView":
                view = new SkinImageView(context, attrs);
                break;
            case "ImageButton":
                view = new SkinImageButton(context, attrs);
                break;
            case "LinearLayout":
                view = new SkinLinearLayout(context, attrs);
                break;
            case "TableLayout":
                view = new SkinTableLayout(context, attrs);
                break;
            case "TableRow":
                view = new SkinTableRow(context, attrs);
                break;
            case "RadioGroup":
                view = new SkinRadioGroup(context, attrs);
                break;
            case "NumberPicker":
                view = new SkinNumberPicker(context, attrs);
                break;
            case "SearchView":
                view = new SkinSearchView(context, attrs);
                break;
            case "ZoomControls":
                view = new SkinZoomControls(context, attrs);
                break;
            case "RelativeLayout":
                view = new SkinRelativeLayout(context, attrs);
                break;
            case "FrameLayout":
                view = new SkinFrameLayout(context, attrs);
                break;
            case "ScrollView":
                view = new SkinScrollView(context, attrs);
                break;
            case "HorizontalScrollView":
                view = new SkinHorizontalScrollView(context, attrs);
                break;
            case "ViewAnimator":
                view = new SkinViewAnimator(context, attrs);
                break;
            case "ViewSwitcher":
                view = new SkinViewSwitcher(context, attrs);
                break;
            case "TextSwitcher":
                view = new SkinTextSwitcher(context, attrs);
                break;
            case "ImageSwitcher":
                view = new SkinImageSwitcher(context, attrs);
                break;
            case "ViewFlipper":
                view = new SkinViewFlipper(context, attrs);
                break;
            case "DatePicker":
                view = new SkinDatePicker(context, attrs);
                break;
            case "TimePicker":
                view = new SkinTimePicker(context, attrs);
                break;
            case "CalendarView":
                view = new SkinCalendarView(context, attrs);
                break;
            case "Spinner":
                view = new SkinSpinner(context, attrs);
                break;
            case "Gallery":
                view = new SkinGallery(context, attrs);
                break;
            case "ListView":
                view = new SkinListView(context, attrs);
                break;
            case "GridView":
                view = new SkinGridView(context, attrs);
                break;
            case "StackView":
                view = new SkinStackView(context, attrs);
                break;
            case "AdapterViewFlipper":
                view = new SkinAdapterViewFlipper(context, attrs);
                break;
            case "GridLayout":
                view = new SkinGridLayout(context, attrs);
                break;
            case "Toolbar":
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view = new SkinToolbar(context, attrs);
                }
                break;
        }
        return view;
    }
}