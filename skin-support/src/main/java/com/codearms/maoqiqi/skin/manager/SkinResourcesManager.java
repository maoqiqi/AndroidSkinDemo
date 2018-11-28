package com.codearms.maoqiqi.skin.manager;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * 资源管理类,根据当前的皮肤模式,加载相应的资源
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/2 19:43
 */
public class SkinResourcesManager {

    /**
     * 日志标签
     */
    private static final String TAG = SkinResourcesManager.class.getSimpleName();

    /**
     * 无效的资源
     */
    private static final int INVALID_RESOURCES = 0;

    /**
     * 当前应用的上下文
     */
    private WeakReference<Context> contextWR;

    /**
     * 当前应用的包名
     */
    private String packageName;

    /**
     * 当前应用的资源对象
     */
    private Resources resources;

    /**
     * 当前实例
     */
    private static SkinResourcesManager instance;

    /**
     * 皮肤后缀名
     */
    private String skinSuffixName;

    /**
     * 加载皮肤资源的包名
     */
    private String skinPackageName;

    /**
     * 加载皮肤资源的对象
     */
    private Resources skinResources;

    /**
     * 是否是使用默认的(当前APP的)资源对象加载对应的皮肤资源
     */
    private boolean isUseDefaultResources;

    /**
     * color缓存
     */
    private HashMap<String, Integer> colorCaches;

    /**
     * Drawable缓存
     */
    private HashMap<String, Drawable.ConstantState> drawableCaches;

    /**
     * ColorStateList缓存
     */
    private HashMap<String, ColorStateList> colorStateListCaches;

    /**
     * 私有构造函数
     *
     * @param context the context
     */
    private SkinResourcesManager(Context context) {
        this.contextWR = new WeakReference<>(context);
        this.packageName = context.getPackageName();
        this.resources = context.getResources();
    }

    /**
     * 初始化,单例模式,只需要初始化一次,已在{@link SkinManager#SkinManager}中初始化。
     *
     * @param context the context
     */
    public static void init(Context context) {
        if (instance == null) {
            synchronized (SkinResourcesManager.class) {
                if (instance == null) {
                    instance = new SkinResourcesManager(context);
                }
            }
        }
    }

    /**
     * 得到当前实例对象,必须先调用初始化函数{@link SkinResourcesManager#init(Context)}
     *
     * @return {@link SkinResourcesManager}实例对象
     */
    public static SkinResourcesManager getInstance() {
        if (instance == null)
            throw new NullPointerException("SkinResourcesManager must first call the init(Context context)");

        return instance;
    }

    /**
     * 设置默认皮肤信息
     */
    public void setDefaultSkinInfo() {
        this.skinSuffixName = "";
        this.skinPackageName = packageName;
        this.skinResources = resources;
        this.isUseDefaultResources = true;
        clearCaches();
    }

    /**
     * 设置内置皮肤信息
     *
     * @param skinSuffixName 皮肤后缀名
     */
    public void setBuiltInSkinInfo(String skinSuffixName) {
        this.skinSuffixName = skinSuffixName;
        this.skinPackageName = packageName;
        this.skinResources = resources;
        this.isUseDefaultResources = SkinPreferencesManager.getInstance().isDefaultSkin();
        clearCaches();
    }

    /**
     * 设置插件皮肤信息(assets文件夹中插件皮肤和外部插件皮肤都调用该方法)
     *
     * @param skinSuffixName  皮肤后缀名
     * @param skinPackageName 加载皮肤资源的包名
     * @param skinResources   加载皮肤资源的对象
     */
    public void setPlugInSkinInfo(String skinSuffixName, String skinPackageName, Resources skinResources) {
        this.skinSuffixName = skinSuffixName;
        this.skinPackageName = skinPackageName;
        this.skinResources = skinResources;
        this.isUseDefaultResources = false;
        clearCaches();
    }

    /**
     * 设置自定义颜色信息
     */
    public void setCustomSkinInfo() {
        this.skinSuffixName = "";
        this.skinPackageName = packageName;
        this.skinResources = resources;
        this.isUseDefaultResources = true;
        clearCaches();
        addCustomColorToCache();
    }

    /**
     * 将所有自定义color添加到缓存中
     */
    private void addCustomColorToCache() {
        // 将表示colorPrimary的id和color添加到缓存中
        int colorPrimaryId = getColoIdByEntryName(SkinPreferencesManager.getInstance().getSkinColorPrimaryName());
        String skinColorPrimary = SkinPreferencesManager.getInstance().getSkinColorPrimary();
        addCustomColorToCache(colorPrimaryId, skinColorPrimary);

        // 将表示colorPrimaryDark的id和color添加到缓存中
        int colorPrimaryDarkId = getColoIdByEntryName(SkinPreferencesManager.getInstance().getSkinColorPrimaryDarkName());
        String skinColorPrimaryDark = SkinPreferencesManager.getInstance().getSkinColorPrimaryDark();
        addCustomColorToCache(colorPrimaryDarkId, skinColorPrimaryDark);

        // 将表示colorAccent的id和color添加到缓存中
        int colorAccentId = getColoIdByEntryName(SkinPreferencesManager.getInstance().getSkinColorAccentName());
        String skinColorAccent = SkinPreferencesManager.getInstance().getSkinColorAccent();
        addCustomColorToCache(colorAccentId, skinColorAccent);
    }

    /**
     * 将colorStr转换成color,并添加到缓存中
     *
     * @param colorId  color id
     * @param colorStr color string value
     */
    private void addCustomColorToCache(int colorId, String colorStr) {
        if (colorId <= INVALID_RESOURCES) return;
        try {
            if (!colorStr.equals("")) {
                int color = Color.parseColor(colorStr);
                addColorToCache(getKeyByResId(colorId), color);
            }
        } catch (IllegalArgumentException e) {
            // color string 转换为color失败
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * 得到缓存中的color
     *
     * @param key key
     * @return color
     */
    private Integer getColorFromCache(String key) {
        if (colorCaches == null) return INVALID_RESOURCES;
        Integer color = colorCaches.get(key);
        return color == null ? 0 : color;
    }

    /**
     * 添加color到缓存中
     *
     * @param key   key
     * @param color color
     */
    private void addColorToCache(String key, int color) {
        if (colorCaches == null) colorCaches = new HashMap<>();
        colorCaches.put(key, color);
    }

    /**
     * 得到缓存中的Drawable
     *
     * @param key key
     * @return Drawable
     */
    private Drawable getDrawableFromCache(String key) {
        if (drawableCaches == null) return null;
        Drawable.ConstantState constantState = drawableCaches.get(key);
        return constantState == null ? null : constantState.newDrawable();
    }

    /**
     * 添加Drawable到缓存中
     *
     * @param key      key
     * @param drawable Drawable
     */
    private void addDrawableToCache(String key, Drawable drawable) {
        if (drawableCaches == null) drawableCaches = new HashMap<>();
        drawableCaches.put(key, drawable.getConstantState());
    }

    /**
     * 得到缓存中的ColorStateList
     *
     * @param key key
     * @return ColorStateList
     */
    private ColorStateList getColorStateListFromCache(String key) {
        if (colorStateListCaches == null) return null;
        return colorStateListCaches.get(key);
    }

    /**
     * 添加ColorStateList到缓存中
     *
     * @param key            key
     * @param colorStateList colorStateList
     */
    private void addColorStateListToCache(String key, ColorStateList colorStateList) {
        if (colorStateListCaches == null) colorStateListCaches = new HashMap<>();
        colorStateListCaches.put(key, colorStateList);
    }

    /**
     * 根据资源Id获取key
     *
     * @param resId resource id
     * @return key
     */
    private String getKeyByResId(int resId) {
        return "resId:" + resId;
    }

    /**
     * 根据属性Id获取key
     *
     * @param attr the attribute
     * @return key
     */
    private String getKeyByAttr(int attr) {
        return "attr:" + attr;
    }

    /**
     * 清除缓存信息
     */
    private void clearCaches() {
        Log.e(TAG, "-------------------------------------------------------------------------");
        if (colorCaches != null) {
            for (String key : colorCaches.keySet()) {
                Integer value = colorCaches.get(key);
                Log.e(TAG, "key:" + key + ",value:" + value);
            }
            colorCaches.clear();
        }
        Log.e(TAG, "-------------------------------------------------------------------------");
        if (drawableCaches != null) {
            for (String key : drawableCaches.keySet()) {
                Drawable.ConstantState drawable = drawableCaches.get(key);
                Log.e(TAG, "key:" + key + ",value:" + drawable.getClass().getName());
            }
            drawableCaches.clear();
        }
        Log.e(TAG, "-------------------------------------------------------------------------");
        if (colorStateListCaches != null) {
            for (String key : colorStateListCaches.keySet()) {
                ColorStateList colorStateList = colorStateListCaches.get(key);
                Log.e(TAG, "key:" + key + ",value:" + colorStateList.getClass().getName());
            }
            colorStateListCaches.clear();
        }
        Log.e(TAG, "-------------------------------------------------------------------------");
    }

    /**
     * 追加皮肤后缀名
     *
     * @param entryName 资源名称
     * @return 追加后的资源名称
     */
    private String appendSkinSuffixName(String entryName) {
        if (skinSuffixName == null || skinSuffixName.equals("")) {
            return entryName;
        }
        return entryName + skinSuffixName;
    }

    /**
     * 根据entryName得到颜色Id
     *
     * @param entryName resource entry name
     * @return color id
     */
    private int getColoIdByEntryName(String entryName) {
        String typeName = "color";
        return resources.getIdentifier(entryName, typeName, packageName);
    }

    /**
     * 得到资源id的entryName
     *
     * @param resId resource id
     * @return entryName
     */
    public String getEntryName(int resId) {
        return resources.getResourceEntryName(resId);
    }

    /**
     * 得到资源id的typeName
     *
     * @param resId resource id
     * @return typeName
     */
    public String getTypeName(int resId) {
        return resources.getResourceTypeName(resId);
    }

    /**
     * 获取目标资源Id
     *
     * @param resId resource id
     * @return resource id
     */
    public int getTargetResId(int resId) {
        if (resId <= INVALID_RESOURCES) return INVALID_RESOURCES;
        if (isUseDefaultResources) return resId;
        try {
            String entryName = resources.getResourceEntryName(resId);
            String typeName = resources.getResourceTypeName(resId);
            return skinResources.getIdentifier(appendSkinSuffixName(entryName), typeName, skinPackageName);
        } catch (Exception e) {
            // 换肤失败不至于应用崩溃
            return INVALID_RESOURCES;
        }
    }

    /**
     * 根据resId加载对应的color
     *
     * @param resId resource id
     * @return color
     */
    public int getColor(int resId) {
        if (resId <= INVALID_RESOURCES) return INVALID_RESOURCES;
        int color = getColorFromCache(getKeyByResId(resId));
        if (color == 0) {
            if (isUseDefaultResources) {
                color = resources.getColor(resId);
            } else {
                int targetResId = getTargetResId(resId);
                if (targetResId == INVALID_RESOURCES) {
                    color = resources.getColor(resId);
                } else {
                    color = skinResources.getColor(targetResId);
                }
            }
            if (color != INVALID_RESOURCES) {
                addColorToCache(getKeyByResId(resId), color);
            }
        }
        return color;
    }

    /**
     * 根据resId加载对应的Drawable
     *
     * @param resId resource id
     * @return Drawable
     */
    public Drawable getDrawable(int resId) {
        if (resId <= INVALID_RESOURCES) return null;
        Drawable drawable = getDrawableFromCache(getKeyByResId(resId));
        if (drawable == null) {
            if (isUseDefaultResources) {
                try {
                    drawable = resources.getDrawable(resId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                int targetResId = getTargetResId(resId);
                if (targetResId == INVALID_RESOURCES) {
                    try {
                        drawable = resources.getDrawable(resId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        drawable = skinResources.getDrawable(targetResId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (drawable != null) {
                addDrawableToCache(getKeyByResId(resId), drawable);
            }
        }
        return drawable;
    }

    /**
     * 根据resId加载对应的ColorStateList
     *
     * @param resId resource id
     * @return ColorStateList
     */
    public ColorStateList getColorStateList(int resId) {
        if (resId <= INVALID_RESOURCES) return null;
        ColorStateList colorStateList = getColorStateListFromCache(getKeyByResId(resId));
        if (colorStateList == null) {
            if (isUseDefaultResources) {
                colorStateList = resources.getColorStateList(resId);
            } else {
                int targetResId = getTargetResId(resId);
                if (targetResId == INVALID_RESOURCES) {
                    colorStateList = resources.getColorStateList(resId);
                } else {
                    colorStateList = skinResources.getColorStateList(targetResId);
                }
            }
            if (colorStateList != null) {
                addColorStateListToCache(getKeyByResId(resId), colorStateList);
            }
        }
        return colorStateList;
    }

    /**
     * 根据属性获取颜色
     *
     * @param activity the activity
     * @param attr     the attribute
     * @return color
     */
    private int getColorByAttr(Activity activity, int attr) {
        int color = getColorFromCache(getKeyByAttr(attr));
        if (color == 0) {
            TypedValue typedValue = new TypedValue();
            activity.getTheme().resolveAttribute(android.R.attr.statusBarColor, typedValue, true);
            color = getColor(typedValue.resourceId);
            if (color != INVALID_RESOURCES) {
                addColorToCache(getKeyByAttr(attr), color);
            }
        }
        return color;
    }

    /**
     * 获取应用的主色
     *
     * @param activity the activity
     * @return colorPrimary
     */
    public int getColorPrimary(Activity activity) {
        return getColorByAttr(activity, android.R.attr.colorPrimary);
    }

    /**
     * 获取应用的主暗色
     *
     * @param activity the activity
     * @return colorPrimaryDark
     */
    public int getDarkColorPrimary(Activity activity) {
        return getColorByAttr(activity, android.R.attr.colorPrimaryDark);
    }

    /**
     * 获取应用的强调色
     *
     * @param activity the activity
     * @return colorAccent
     */
    public int getColorAccent(Activity activity) {
        return getColorByAttr(activity, android.R.attr.colorAccent);
    }

    /**
     * 获取应用的状态栏颜色
     *
     * @param activity the activity
     * @return statusBarColor
     */
    public int getStatusBarColor(Activity activity) {
        return getColorByAttr(activity, android.R.attr.statusBarColor);
    }
}