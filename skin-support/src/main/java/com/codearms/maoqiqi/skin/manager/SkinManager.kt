package com.codearms.maoqiqi.skin.manager

import android.app.Activity
import android.app.Application
import android.util.Log
import com.codearms.maoqiqi.skin.app.SkinActivityLifecycleCallbacks
import com.codearms.maoqiqi.skin.app.SkinLayoutInflater
import com.codearms.maoqiqi.skin.loader.LoadPlugInSkinTask
import com.codearms.maoqiqi.skin.loader.LoadPlugInSkinTask.ILoadPlugInSkin
import com.codearms.maoqiqi.skin.observe.SkinObservable
import java.util.*

/**
 * 皮肤管理类,设置换肤参数,加载皮肤
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/2 21:32
 */
object SkinManager : SkinObservable() {

    /**
     * 缓存当前应用Application
     */
    lateinit var application: Application

    /**
     * 是否所有Activity都支持换肤,true:支持;false:不支持
     */
    var isSkinAllActivityEnable = true

    /**
     * 是否所有Activity的状态栏都支持换肤,true:支持;false:不支持
     */
    var isSkinAllStatusBarColorEnable = true

    /**
     * 所有Activity的状态栏都支持换肤,设置Activity的状态栏支持换肤
     */
    val skinStatusBarColorEnables = WeakHashMap<Activity, Boolean>()

    /**
     * 所有Activity的状态栏都支持换肤,设置Activity的状态栏不支持换肤
     */
    val skinStatusBarColorDisEnables = WeakHashMap<Activity, Boolean>()

    /**
     * 保存所有实现[SkinLayoutInflater]接口的实例
     */
    private val skinLayoutInflaters: MutableSet<SkinLayoutInflater> = HashSet()

    fun initialize(application: Application): SkinManager {
        this.application = application
        SkinActivityLifecycleCallbacks.init(application)
        SkinResourcesManager.init(application)
        return this
    }

    /**
     * 设置是否所有Activity都支持换肤
     *
     * @param skinAllActivityEnable true:支持;false:不支持
     * @return [SkinManager]实例对象
     */
    fun setSkinAllActivityEnable(skinAllActivityEnable: Boolean): SkinManager {
        isSkinAllActivityEnable = skinAllActivityEnable
        return this
    }

    /**
     * 设置是否所有Activity的状态栏都支持换肤(5.0以上有效)
     *
     * @param skinAllStatusBarColorEnable true:支持;false:不支持
     * @return [SkinManager]实例对象
     */
    fun setSkinAllStatusBarColorEnable(skinAllStatusBarColorEnable: Boolean): SkinManager {
        isSkinAllStatusBarColorEnable = skinAllStatusBarColorEnable
        return this
    }

    /**
     * 设置当前Activity支持状态栏换肤
     * @param activity the activity
     */
    fun setSkinStatusBarColorEnable(activity: Activity) {
        skinStatusBarColorEnables[activity] = true
    }

    /**
     * 设置当前Activity不支持状态栏换肤
     * @param activity the activity
     */
    fun setSkinStatusBarColorDisEnable(activity: Activity) {
        skinStatusBarColorDisEnables[activity] = true
    }

    /**
     * 返回所有实现SkinLayoutInflater接口的实例
     * @return 集合
     */
    fun getSkinLayoutInflaters(): Set<SkinLayoutInflater> = skinLayoutInflaters

    /**
     * 添加自定义layoutInflater实例
     * @param layoutInflater 自定义的layoutInflater实例
     * @return [SkinManager]实例对象
     */
    fun addLayoutInflater(layoutInflater: SkinLayoutInflater): SkinManager {
        skinLayoutInflaters.add(layoutInflater)
        return this
    }

    /**
     * 加载上次选择的皮肤,在Application中初始化换肤框架后调用。e:SkinManager.init(this).loadSkin();
     */
    fun loadSkin() {
        // 如果是内置皮肤
        if (SkinPreferencesManager.getInstance().isBuiltInSkin) {
            // 获取皮肤后缀名
            val skinSuffixName = SkinPreferencesManager.getInstance().skinSuffixName
            // 设置内置皮肤信息
            SkinResourcesManager.getInstance().setBuiltInSkinInfo(skinSuffixName)
            // 通知更新皮肤
            notifyUpdateSkin()
            return
        }

        // 如果是assets文件夹中插件皮肤
        if (SkinPreferencesManager.getInstance().isAssetsSkin) {
            // 获取外部插件皮肤路径
            val skinFilePath = SkinPreferencesManager.getInstance().skinFilePath
            // 获取assets文件夹中插件皮肤路径
            val skinAssetsFilePath = SkinPreferencesManager.getInstance().skinAssetsFilePath
            // 获取皮肤后缀名
            val skinSuffixName = SkinPreferencesManager.getInstance().skinSuffixName
            loadSkin(skinFilePath, skinAssetsFilePath, skinSuffixName, true, null)
            return
        }

        // 如果是外部插件皮肤
        if (SkinPreferencesManager.getInstance().isExternalSkin) {
            // 获取外部插件皮肤路径
            val skinFilePath = SkinPreferencesManager.getInstance().skinFilePath
            // 获取皮肤后缀名
            val skinSuffixName = SkinPreferencesManager.getInstance().skinSuffixName
            loadSkin(skinFilePath, null, skinSuffixName, false, null)
            return
        }

        // 如果是是自定义颜色
        if (SkinPreferencesManager.getInstance().isCustomSkin) {
            // 设置自定义颜色信息
            SkinResourcesManager.getInstance().setCustomSkinInfo()
            // 通知更新皮肤
            notifyUpdateSkin()
            return
        }

        // 如果都不是,说明没有使用皮肤,设置默认皮肤信息
        SkinResourcesManager.getInstance().setDefaultSkinInfo()
    }

    /**
     * 恢复默认
     */
    fun restoreDefault() {
        // 清除数据
        SkinPreferencesManager.getInstance().clearData()
        // 设置默认皮肤信息
        SkinResourcesManager.getInstance().setDefaultSkinInfo()
        // 通知更新皮肤
        notifyUpdateSkin()
    }

    /**
     * 加载内置皮肤
     *
     * @param skinSuffixName 皮肤后缀名
     */
    fun loadBuiltInSkin(skinSuffixName: String?) {
        // 保存为内置皮肤模式
        SkinPreferencesManager.getInstance().saveSkinModeBuiltIn()
        // 保存皮肤后缀名
        SkinPreferencesManager.getInstance().saveSkinSuffixName(skinSuffixName)
        // 设置内置皮肤信息
        SkinResourcesManager.getInstance().setBuiltInSkinInfo(skinSuffixName)
        // 通知更新皮肤
        notifyUpdateSkin()
    }
    /**
     * 加载assets文件夹中插件皮肤
     *
     * @param skinAssetsFilePath assets文件夹中插件皮肤路径
     * @param skinSuffixName     皮肤后缀名
     * @param loadPlugInSkin     加载插件皮肤任务监听
     */
    /**
     * 加载assets文件夹中插件皮肤
     *
     * @param skinAssetsFilePath assets文件夹中插件皮肤路径
     * @param skinSuffixName     皮肤后缀名
     */
    /**
     * 加载assets文件夹中插件皮肤
     *
     * @param skinAssetsFilePath assets文件夹中插件皮肤路径
     */
    @JvmOverloads
    fun loadAssetsSkin(
        skinAssetsFilePath: String?, skinSuffixName: String? = null,
        loadPlugInSkin: ILoadPlugInSkin? = null
    ) {
        loadSkin(null, skinAssetsFilePath, skinSuffixName, true, loadPlugInSkin)
    }
    /**
     * 加载外部插件皮肤
     *
     * @param skinFilePath   外部插件皮肤路径
     * @param skinSuffixName 皮肤后缀名
     * @param loadPlugInSkin 加载插件皮肤任务监听
     */
    /**
     * 加载外部插件皮肤
     *
     * @param skinFilePath   外部插件皮肤路径
     * @param skinSuffixName 皮肤后缀名
     */
    /**
     * 加载外部插件皮肤
     *
     * @param skinFilePath 外部插件皮肤路径
     */
    @JvmOverloads
    fun loadExternalSkin(
        skinFilePath: String?, skinSuffixName: String? = null,
        loadPlugInSkin: ILoadPlugInSkin? = null
    ) {
        loadSkin(skinFilePath, null, skinSuffixName, false, loadPlugInSkin)
    }

    /**
     * 加载插件皮肤
     *
     * @param skinFilePath       外部插件皮肤路径
     * @param skinAssetsFilePath assets文件夹中插件皮肤路径
     * @param skinSuffixName     皮肤后缀名
     * @param isAssetsFile       是否是来自assets文件夹中插件皮肤
     * @param loadPlugInSkin     加载插件皮肤任务监听
     */
    private fun loadSkin(
        skinFilePath: String?, skinAssetsFilePath: String?, skinSuffixName: String?,
        isAssetsFile: Boolean, loadPlugInSkin: ILoadPlugInSkin?
    ) {
        if (application.get() == null) {
            Log.e(TAG, "contextWR.get() == null")
            return
        }
        LoadPlugInSkinTask(
            application.get(), skinFilePath, skinAssetsFilePath, skinSuffixName,
            isAssetsFile, loadPlugInSkin
        ).execute()
    }

    /**
     * 记载定义颜色皮肤
     */
    fun loadCustomSkin() {
        // 保存为使用自定义颜色模式
        SkinPreferencesManager.getInstance().saveSkinModeCustom()
        // 设置自定义颜色信息
        SkinResourcesManager.getInstance().setCustomSkinInfo()
        // 通知更新皮肤
        notifyUpdateSkin()
    }

    companion object {
        /**
         * 日志标签
         */
        private val TAG = SkinManager::class.java.simpleName

        /**
         * 当前实例对象
         */
        private var instance: SkinManager? = null

        /**
         * 初始化,单例模式,只需要初始化一次,应该在Application的onCreate()方法中调用
         *
         * @param application the application
         * @return [SkinManager]实例对象
         */
        @JvmStatic
        fun init(application: Application): SkinManager? {
            if (instance == null) {
                synchronized(SkinManager::class.java) {
                    if (instance == null) {
                        instance = SkinManager(application)
                    }
                }
            }
            return instance
        }

        /**
         * 得到当前实例对象,必须先调用初始化函数[SkinManager.init]
         *
         * @return [SkinManager]实例对象
         */
        @JvmStatic
        fun getInstance(): SkinManager? {
            if (instance == null) throw NullPointerException("SkinManager must first call the init(Application application)")
            return instance
        }
    }
}