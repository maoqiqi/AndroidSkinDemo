package com.codearms.maoqiqi.skin.app

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.core.view.ViewCompat
import com.codearms.maoqiqi.skin.manager.SkinManager
import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * 创建视图
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/3 20:02
 */
class AppCompatViewInflater {

    private val classPrefixList: Array<String> = arrayOf("android.widget.", "android.view.", "android.webkit.")
    private val constructorSignature: Array<Class<*>> = arrayOf(Context::class.java, AttributeSet::class.java)
    private val constructorArgs: Array<Any?> = arrayOf(2)
    private val constructorMap: MutableMap<String, Constructor<out View>?> = hashMapOf()
    private val onClickAttrs: IntArray = intArrayOf(android.R.attr.onClick)

    fun createView(parent: View, name: String, context: Context, attrs: AttributeSet): View? {
        // 优先使用自定义LayoutInflater创建视图
        var view: View? = createViewByLayoutInflater(parent, name, context, attrs)
        if (view == null) view = createViewFromTag(context, name, attrs)
        view?.let { checkOnClickListener(it, attrs) }
        return view
    }

    /**
     * 根据自定义LayoutInflater创建视图
     */
    private fun createViewByLayoutInflater(parent: View, name: String, context: Context, attrs: AttributeSet): View? {
        val skinLayoutInflaters = SkinManager.getInstance().skinLayoutInflaters
        if (skinLayoutInflaters.isNotEmpty()) {
            var view: View?
            for (layoutInflater in skinLayoutInflaters) {
                view = layoutInflater.createView(parent, name, context, attrs)
                if (view != null) return view
            }
        }
        return null
    }

    /**
     * 根据标签名创建视图
     */
    private fun createViewFromTag(context: Context, name: String, attrs: AttributeSet): View? {
        val viewName = if (name == "view") attrs.getAttributeValue(null, "class") else name
        return try {
            constructorArgs[0] = context
            constructorArgs[1] = attrs

            if (-1 == viewName.indexOf('.')) {
                // 系统内部控件,通过补全包名,反射创建View
                for (classPrefix in classPrefixList) {
                    val view = createViewByPrefix(context, viewName, classPrefix)
                    if (view != null) return view
                }
                null
            } else createViewByPrefix(context, viewName, null)
        } catch (e: Exception) {
            null
        } finally {
            // 不要保留上下文上的引用
            constructorArgs[0] = null
            constructorArgs[1] = null
        }
    }

    private fun createViewByPrefix(context: Context, name: String, prefix: String?): View? {
        var constructor = constructorMap[name]
        return try {
            if (constructor == null) {
                // 没有在缓存中找到,试图创建
                val className = if (prefix != null) prefix + name else name
                val clazz = context.classLoader.loadClass(className).asSubclass(View::class.java)
                constructor = clazz.getConstructor(*constructorSignature)
                constructorMap[name] = constructor
            }
            constructor!!.isAccessible = true
            constructor.newInstance(*constructorArgs)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * onClick不处理context的视图。此方法支持新的框架功能，以遍历上下文包装器以找到合适的目标。
     */
    private fun checkOnClickListener(view: View, attrs: AttributeSet) {
        val context = view.context
        if (context !is ContextWrapper ||
            Build.VERSION.SDK_INT >= 15 && !ViewCompat.hasOnClickListeners(view)
        ) {
            // Skip our compat functionality if: the Context isn't a ContextWrapper, or
            // the view doesn't have an OnClickListener (we can only rely on this on API 15+ so
            // always use our compat code on older devices)
            return
        }
        val a = context.obtainStyledAttributes(attrs, onClickAttrs)
        val handlerName = a.getString(0)
        if (handlerName != null) {
            view.setOnClickListener(DeclaredOnClickListener(view, handlerName))
        }
        a.recycle()
    }

    /**
     * OnClickListener的一种实现，它试图从父或父上下文延迟加载指定的单击处理方法。
     */
    private class DeclaredOnClickListener(private val mHostView: View, private val mMethodName: String) : View.OnClickListener {
        private var mResolvedMethod: Method? = null
        private var mResolvedContext: Context? = null
        override fun onClick(v: View) {
            if (mResolvedMethod == null) {
                resolveMethod(mHostView.context, mMethodName)
            }
            try {
                mResolvedMethod!!.invoke(mResolvedContext, v)
            } catch (e: IllegalAccessException) {
                throw IllegalStateException("Could not execute non-public method for android:onClick", e)
            } catch (e: InvocationTargetException) {
                throw IllegalStateException("Could not execute method for android:onClick", e)
            }
        }

        private fun resolveMethod(context: Context?, name: String) {
            var ctx = context
            while (ctx != null) {
                try {
                    if (!ctx.isRestricted) {
                        val method = ctx.javaClass.getMethod(name, View::class.java)
                        mResolvedMethod = method
                        mResolvedContext = ctx
                        return
                    }
                } catch (e: NoSuchMethodException) {
                    // Failed to find method, keep searching up the hierarchy.
                }
                // Can't search up the hierarchy, null out and fail.
                ctx = if (ctx is ContextWrapper) ctx.baseContext else null
            }
            val id = mHostView.id
            val idText = if (id == View.NO_ID) "" else " with id '" + mHostView.context.resources.getResourceEntryName(id) + "'"
            throw java.lang.IllegalStateException("Could not find method $name(View) in a parent or ancestor Context for android:onClick attribute defined on view ${mHostView.javaClass}$idText")
        }
    }
}