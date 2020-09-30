package com.codearms.maoqiqi.skin.observe

import java.util.*

/**
 * 用于添加、删除实现SkinObserver接口的类。
 * 提供一个方法调用所有实现SkinObserver接口的实例执行[SkinObserver.updateSkin]方法。
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/2 17:35
 */
open class SkinObservable {

    /**
     * 保存所有实现SkinObserver接口的实现类
     */
    private val skinObservers: MutableList<SkinObserver> by lazy { ArrayList() }

    /**
     * 添加一个实现皮肤监听接口的类
     * @param observer 实现皮肤监听接口的类
     */
    @Synchronized
    fun addObserver(observer: SkinObserver) {
        if (!skinObservers.contains(observer)) {
            skinObservers.add(observer)
        }
    }

    /**
     * 删除一个实现皮肤监听接口的类
     * @param observer 实现皮肤监听接口的类
     */
    @Synchronized
    fun removeObserver(observer: SkinObserver) {
        skinObservers.remove(observer)
    }

    /**
     * 调用所有实现SkinObserver接口的实例执行[SkinObserver.updateSkin]方法
     */
    fun notifyUpdateSkin() {
        var tempList: List<SkinObserver>
        synchronized(this) { tempList = skinObservers }
        tempList.forEach { observer -> observer.updateSkin() }
    }
}