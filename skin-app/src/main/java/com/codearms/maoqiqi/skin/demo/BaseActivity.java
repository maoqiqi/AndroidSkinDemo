package com.codearms.maoqiqi.skin.demo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.codearms.maoqiqi.skin.manager.SkinManager;

/**
 * Activity基类,提供返回和换肤功能
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/11 17:30
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_color_picker, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.menu_color_picker) {
            createDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createDialog() {
        final String[] colors = new String[]{"默认", "蓝色主题", "绿色主题"};
        final String[] suffixNames = new String[]{"", "_blue", "_green"};
        new AlertDialog.Builder(this)
                .setTitle("选择主题")
                .setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            SkinManager.getInstance().restoreDefault();
                        } else {
                            SkinManager.getInstance().loadBuiltInSkin(suffixNames[which]);
                        }
                    }
                })
                .setCancelable(false)
                .create().show();
    }
}