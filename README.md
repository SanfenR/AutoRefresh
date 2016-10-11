# AutoRefresh

### 使用retrofit2.0实现apk下载并安装

##### MainActivity 中跳转 DownLoadService 并开始下载

```java
 Intent intent = new Intent(this, DownLoadService.class);
 intent.putExtra("appUrl", url);
 intent.putExtra("filename", this.getExternalCacheDir() + "/" + "xxx.apk");
 startService(intent);
```
