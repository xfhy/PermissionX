package com.permissionx.xfhydev

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * @author : xfhy
 * Create time : 2020-05-04 18:38
 * Description : 拿来申请权限的fragment,没得界面
 */

//给回调指定一个别名
typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment() {

    companion object {
        const val REQUEST_CODE = 1
        fun getInstance(): InvisibleFragment {
            return InvisibleFragment()
        }
    }

    /**
     * 回调
     * param1: 是否全部同意  param2: 被拒绝的权限列表
     */
    private var callback: PermissionCallback? = null

    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE) {
            //被拒绝的
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()

            callback?.invoke(allGranted, deniedList)
        }
    }

}