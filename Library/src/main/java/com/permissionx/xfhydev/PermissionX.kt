package com.permissionx.xfhydev

import androidx.fragment.app.FragmentActivity

/**
 * @author : xfhy
 * Create time : 2020-05-04 22:00
 * Description : API
 */
object PermissionX {

    private const val TAG = "InvisibleFragment"

    /**
     * 请求权限
     */
    fun request(activity: FragmentActivity, vararg permissions: String, callback: PermissionCallback) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment == null) {
            val fragment = InvisibleFragment.getInstance()
            //必须立马commit
            fragmentManager.beginTransaction().add(fragment, TAG).commitNow()
            fragment
        } else {
            existedFragment as InvisibleFragment
        }

        //开始请求权限
                                    //* 这里是将数组转换成可变长度参数传递过去
        fragment.requestNow(callback, *permissions)
    }

}