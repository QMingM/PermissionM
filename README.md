# PermissionM 一个简化运行时权限申请的库

添加如下配置将PermissionM引入到你的项目中：

```groovy
dependencies {
 ...
 implementation 'com.android.permission:permission-m:1.0.0'
}
```


然后就可以使用如下语法结构来申请运行时权限了

需要先在配置文件申请需要的权限
```groovy
 PermissionM.request(
                this,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) { allGranted, deniedList ->
                if (allGranted) {
                     Toast.makeText(this, "all permission are granted", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "you denied $deniedList", Toast.LENGTH_LONG).show()
                }
              }
```
