package com.codearms.maoqiqi.skin.manager

import android.content.Context
import android.content.SharedPreferences

/**
 * 保存当前使用的皮肤信息,使下次打开程序还是使用的上次选择的皮肤
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/2 18:13
 */
object SkinPreferencesManager {

    /**
     * SharedPreferences文件名称
     */
    private const val PREFERENCE_NAME = "skin_preferences"

    // ---------------------------------------------------------------------------------------------
    /**
     * 皮肤模式的名称(0:默认皮肤;1:内置皮肤;2:使用assets文件夹中插件皮肤;3:使用外部插件皮肤;4:自定义颜色)
     * 自选颜色(选择colorPrimary、colorPrimaryDark、colorAccent)
     */
    private const val SKIN_MODE = "skin_mode"

    /**
     * 默认皮肤模式
     */
    private const val SKIN_MODE_DEFAULT = 0

    /**
     * 内置皮肤模式
     */
    private const val SKIN_MODE_BUILT_IN = 1

    /**
     * 使用assets文件夹中插件皮肤模式
     */
    private const val SKIN_MODE_ASSETS = 2

    /**
     * 使用外部插件皮肤模式
     */
    private const val SKIN_MODE_EXTERNAL = 3

    /**
     * 使用自定义颜色模式
     */
    private const val SKIN_MODE_CUSTOM = 4

    // ---------------------------------------------------------------------------------------------
    /**
     * 存储 皮肤后缀名 的名称
     */
    private const val SKIN_SUFFIX_NAME = "skin_suffix_name"

    /**
     * 默认 皮肤后缀名
     */
    private const val SKIN_SUFFIX_NAME_DEFAULT = ""

    /**
     * 存储 assets文件夹中插件皮肤路径 的名称
     */
    private const val SKIN_ASSETS_FILE_PATH = "skin_assets_file_path"

    /**
     * 默认 assets文件夹中插件皮肤路径
     */
    private const val SKIN_ASSETS_FILE_PATH_DEFAULT = ""

    /**
     * 存储 外部插件皮肤 的名称
     */
    private const val SKIN_FILE_PATH = "skin_file_path"

    /**
     * 默认 外部插件皮肤路径
     */
    private const val SKIN_FILE_PATH_DEFAULT = ""

    // ---------------------------------------------------------------------------------------------
    /**
     * 存储 colorPrimary entry name 的名称
     */
    private const val SKIN_COLOR_PRIMARY_NAME = "color_primary_name"

    /**
     * 默认 colorPrimary entry name
     */
    private const val SKIN_COLOR_PRIMARY_NAME_DEFAULT = "colorPrimary"

    /**
     * 存储 colorPrimaryDark entry name 的名称
     */
    private const val SKIN_COLOR_PRIMARY_DARK_NAME = "color_primary_dark_name"

    /**
     * 默认 colorPrimaryDark entry name
     */
    private const val SKIN_COLOR_PRIMARY_DARK_NAME_DEFAULT = "colorPrimaryDark"

    /**
     * 存储 colorAccent entry name 的名称
     */
    private const val SKIN_COLOR_ACCENT_NAME = "color_accent_name"

    /**
     * 默认 colorAccent entry name
     */
    private const val SKIN_COLOR_ACCENT_NAME_DEFAULT = "colorAccent"

    // ---------------------------------------------------------------------------------------------
    /**
     * 存储 colorPrimary value 的名称
     */
    private const val SKIN_COLOR_PRIMARY = "color_primary"

    /**
     * 存储 colorPrimaryDark value 的名称
     */
    private const val SKIN_COLOR_PRIMARY_DARK = "color_primary_dark"

    /**
     * 存储 colorAccent value 的名称
     */
    private const val SKIN_COLOR_ACCENT = "color_accent"

    /**
     * 默认 color value
     */
    private const val SKIN_COLOR_DEFAULT = ""

    // ---------------------------------------------------------------------------------------------
    /**
     * SharedPreferences文件存储对象
     */
    private val sharedPreferences: SharedPreferences by lazy {
        SkinManager.application.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * 保存皮肤模式(私有化,提供三个方法,防止传入坏数据)
     * @param skinMode 皮肤模式
     */
    private fun saveMode(skinMode: Int) = sharedPreferences.edit().apply { putInt(SKIN_MODE, skinMode) }.apply()

    /**
     * 保存为内置皮肤模式
     */
    fun saveSkinModeBuiltIn() = saveMode(SKIN_MODE_BUILT_IN)

    /**
     * 保存为使用assets文件夹中插件皮肤模式
     */
    fun saveSkinModeAssets() = saveMode(SKIN_MODE_ASSETS)

    /**
     * 保存为使用外部插件皮肤模式
     */
    fun saveSkinModeExternal() = saveMode(SKIN_MODE_EXTERNAL)

    /**
     * 保存为使用自定义颜色模式
     */
    fun saveSkinModeCustom() = saveMode(SKIN_MODE_CUSTOM)

    /**
     * 获取皮肤模式(私有化,提供四个方法)
     */
    private val skinMode: Int = sharedPreferences.getInt(SKIN_MODE, SKIN_MODE_DEFAULT)

    /**
     * 是否是默认皮肤,true or false
     */
    val isDefaultSkin: Boolean = SKIN_MODE_DEFAULT == skinMode

    /**
     * 是否是内置皮肤,true or false
     */
    val isBuiltInSkin: Boolean = SKIN_MODE_BUILT_IN == skinMode

    /**
     * 是否是assets文件夹中插件皮肤,true or false
     */
    val isAssetsSkin: Boolean = SKIN_MODE_ASSETS == skinMode

    /**
     * 是否是外部插件皮肤,true or false
     */
    val isExternalSkin: Boolean = SKIN_MODE_EXTERNAL == skinMode

    /**
     * 是否是自定义颜色,true or false
     */
    val isCustomSkin: Boolean = SKIN_MODE_CUSTOM == skinMode

    /**
     * 保存皮肤后缀名
     * @param skinSuffixName 皮肤后缀名
     */
    fun saveSuffixName(skinSuffixName: String) = sharedPreferences.edit().apply { putString(SKIN_SUFFIX_NAME, skinSuffixName) }.apply()

    /**
     * 获取皮肤后缀名
     */
    val skinSuffixName: String = sharedPreferences.getString(SKIN_SUFFIX_NAME, SKIN_SUFFIX_NAME_DEFAULT) ?: SKIN_SUFFIX_NAME_DEFAULT

    /**
     * 保存assets文件夹中插件皮肤路径
     * @param skinAssetsFilePath 路径
     */
    fun saveAssetsPath(skinAssetsFilePath: String) =
        sharedPreferences.edit().apply { putString(SKIN_ASSETS_FILE_PATH, skinAssetsFilePath) }.apply()

    /**
     * 获取assets文件夹中插件皮肤路径
     */
    val skinAssetsFilePath: String =
        sharedPreferences.getString(SKIN_ASSETS_FILE_PATH, SKIN_ASSETS_FILE_PATH_DEFAULT) ?: SKIN_ASSETS_FILE_PATH_DEFAULT

    /**
     * 保存外部插件皮肤路径
     * @param skinFilePath 路径
     */
    fun saveFilePath(skinFilePath: String?) = sharedPreferences.edit().apply { putString(SKIN_FILE_PATH, skinFilePath) }.apply()

    /**
     * 获取外部插件皮肤路径
     * @return 路径
     */
    val skinFilePath: String = sharedPreferences.getString(SKIN_FILE_PATH, SKIN_FILE_PATH_DEFAULT) ?: SKIN_FILE_PATH_DEFAULT

    /**
     * 保存 colorPrimary entry name 名称
     * @param colorPrimaryName colorPrimary entry name
     */
    fun saveColorPrimaryName(colorPrimaryName: String?) =
        sharedPreferences.edit().apply { putString(SKIN_COLOR_PRIMARY_NAME, colorPrimaryName) }.apply()

    /**
     * 获取colorPrimary entry name
     * @return colorPrimary entry name
     */
    val skinColorPrimaryName: String = sharedPreferences.getString(SKIN_COLOR_PRIMARY_NAME, SKIN_COLOR_PRIMARY_NAME_DEFAULT)
        ?: SKIN_COLOR_PRIMARY_NAME_DEFAULT

    /**
     * 保存 colorPrimaryDark entry name 名称
     * @param colorPrimaryDarkName colorPrimaryDark entry name
     */
    fun saveColorPrimaryDarkName(colorPrimaryDarkName: String?) =
        sharedPreferences.edit().apply { putString(SKIN_COLOR_PRIMARY_DARK_NAME, colorPrimaryDarkName) }.apply()

    /**
     * 获取 colorPrimaryDark entry name
     * @return colorPrimaryDark entry name
     */
    val skinColorPrimaryDarkName: String = sharedPreferences.getString(SKIN_COLOR_PRIMARY_DARK_NAME, SKIN_COLOR_PRIMARY_DARK_NAME_DEFAULT)
        ?: SKIN_COLOR_PRIMARY_DARK_NAME_DEFAULT

    /**
     * 保存 colorAccent entry name 名称
     * @param colorAccentName colorAccent entry name
     */
    fun saveColorAccentName(colorAccentName: String?) =
        sharedPreferences.edit().apply { putString(SKIN_COLOR_ACCENT_NAME, colorAccentName) }.apply()

    /**
     * 获取 colorAccent entry name
     * @return colorAccent entry name
     */
    val skinColorAccentName: String =
        sharedPreferences.getString(SKIN_COLOR_ACCENT_NAME, SKIN_COLOR_ACCENT_NAME_DEFAULT) ?: SKIN_COLOR_ACCENT_NAME_DEFAULT

    /**
     * 保存colorPrimary颜色
     * @param colorPrimary 颜色
     */
    fun saveSkinColorPrimary(colorPrimary: String?) = sharedPreferences.edit().apply { putString(SKIN_COLOR_PRIMARY, colorPrimary) }.apply()

    /**
     * 获取colorPrimary颜色
     * @return colorPrimary
     */
    val skinColorPrimary: String? = sharedPreferences.getString(SKIN_COLOR_PRIMARY, SKIN_COLOR_DEFAULT)

    /**
     * 保存colorPrimaryDark颜色
     * @param colorPrimaryDark 颜色
     */
    fun saveSkinColorPrimaryDark(colorPrimaryDark: String?) =
        sharedPreferences.edit().apply { putString(SKIN_COLOR_PRIMARY_DARK, colorPrimaryDark) }.apply()

    /**
     * 获取colorPrimaryDark颜色
     * @return colorPrimaryDark
     */
    val skinColorPrimaryDark: String?
        get() = sharedPreferences.getString(SKIN_COLOR_PRIMARY_DARK, SKIN_COLOR_DEFAULT)

    /**
     * 保存colorAccent颜色
     * @param colorAccent 颜色
     */
    fun saveSkinColorAccent(colorAccent: String?) = sharedPreferences.edit().apply { putString(SKIN_COLOR_ACCENT, colorAccent) }.apply()

    /**
     * 获取colorAccent颜色
     * @return colorAccent
     */
    /**
     * 获取colorAccent颜色
     * @return colorAccent
     */
    val skinColorAccent: String? = sharedPreferences.getString(SKIN_COLOR_ACCENT, SKIN_COLOR_DEFAULT)

    /**
     * 清除数据,恢复默认皮肤
     */
    fun clearData() {
        saveMode(SKIN_MODE_DEFAULT)
        saveSuffixName(SKIN_SUFFIX_NAME_DEFAULT)
        saveAssetsPath(SKIN_ASSETS_FILE_PATH_DEFAULT)
        saveFilePath(SKIN_FILE_PATH_DEFAULT)
        saveColorPrimaryName(SKIN_COLOR_PRIMARY_NAME_DEFAULT)
        saveColorPrimaryDarkName(SKIN_COLOR_PRIMARY_DARK_NAME_DEFAULT)
        saveColorAccentName(SKIN_COLOR_ACCENT_NAME_DEFAULT)
    }
}