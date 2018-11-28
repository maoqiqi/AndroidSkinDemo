package com.codearms.maoqiqi.skin.demo;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.codearms.maoqiqi.skin.app.SkinSupportLayoutInflater;
import com.codearms.maoqiqi.skin.constraint.app.SkinSupportConstraintLayoutInflater;
import com.codearms.maoqiqi.skin.design.app.SkinSupportDesignLayoutInflater;
import com.codearms.maoqiqi.skin.manager.SkinManager;
import com.codearms.maoqiqi.skin.thirdpart.app.SkinSupportThirdPartLayoutInflater;
import com.codearms.maoqiqi.skin.v4.app.SkinSupportV4LayoutInflater;
import com.codearms.maoqiqi.skin.v7.app.SkinSupportCardLayoutInflater;
import com.codearms.maoqiqi.skin.v7.app.SkinSupportRecyclerLayoutInflater;
import com.codearms.maoqiqi.skin.v7.app.SkinSupportV7LayoutInflater;

/**
 * 初始化
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/11 17:40
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        SkinManager.init(this)
                .addLayoutInflater(new SkinSupportLayoutInflater())
                .addLayoutInflater(new SkinSupportV7LayoutInflater())
                .addLayoutInflater(new SkinSupportCardLayoutInflater())
                .addLayoutInflater(new SkinSupportRecyclerLayoutInflater())
                .addLayoutInflater(new SkinSupportV4LayoutInflater())
                .addLayoutInflater(new SkinSupportConstraintLayoutInflater())
                .addLayoutInflater(new SkinSupportDesignLayoutInflater())
                .addLayoutInflater(new SkinSupportThirdPartLayoutInflater())
                .loadSkin();
    }
}