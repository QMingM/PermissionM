package com.permissionm.mk

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

//typealias关键字可以用于给任意类型制定一个别名
typealias PermissionCallback = (Boolean,List<String>) -> Unit

class InvisibleFragment : Fragment() {

    //声明函数类型变量，作为运行时权限申请结果回调通知方式
    private var callback: PermissionCallback? = null

    //方法接受两个参数，cd 函数类型变量；permission 可变长度的参数
    fun requestNow(cb: PermissionCallback, vararg permission: String) {
        callback = cb
        requestPermissions(permission, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
       if (requestCode == 1){
           //定义一个被所有被用户拒绝的权限
           val deniedList = ArrayList<String>()
           for ((index,result) in grantResults.withIndex()){
               if (result != PackageManager.PERMISSION_GRANTED){
                   deniedList.add(permissions[index])
               }
           }
           //定义一个变量，标识所有权限是否均已被授权
           val allGranted = deniedList.isEmpty()
           callback?.let { it(allGranted,deniedList) }
       }
    }
}