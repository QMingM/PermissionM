package com.permissionm.mk

import androidx.fragment.app.FragmentActivity

//单例类
object PermissionM {
    private const val TAG = "InvisibleFragment"

    fun request(
        activity: FragmentActivity,
        vararg permission: String,
        callback: PermissionCallback
    ) {
        val fragmentManager = activity.supportFragmentManager
        //定义变量existedFragment，判断传入的activity参数中是否已经包含指定TAG的Fragment
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback, *permission)
    }
}