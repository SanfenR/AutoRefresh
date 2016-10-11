package com.mz.sanfen.autorefreshdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.mz.sanfen.autorefreshdemo.helper.ProgressResponseBody;
import com.mz.sanfen.autorefreshdemo.helper.ProgressResponseListener;
import com.mz.sanfen.autorefreshdemo.helper.RetrofitHelper;
import com.mz.sanfen.autorefreshdemo.utils.FileUtils;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownLoadService extends Service {

    private String mDownLoadUrl;

    private NotificationManager mNotificationManager;

    private Notification mNotification;

    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

    private String fileName;

    public DownLoadService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationManager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
    }

    @Override
    public IBinder onBind(Intent intent) {
       return  null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent == null){
            stopSelf();
        } else {
            mDownLoadUrl = intent.getStringExtra("appUrl");
            fileName = intent.getStringExtra("filename");
            downLoadFile(fileName);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void downLoadFile(final String fileName) {
        RetrofitHelper.getInstanse()
                .downloadFile(mDownLoadUrl, new ProgressResponseListener() {
                    @Override
                    public void onResponseProgress(long bytesRead, long contentLength, boolean done) {
                        notifyMsg("AutoRefreshDemo", "下载中", (int) (100 * bytesRead / contentLength));
                    }
                })
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body() != null){
                            FileUtils.write(fileName, (response.body().byteStream()));
                            startActivity(getInstallIntent(fileName));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("error", t.getMessage() + t.toString());
                    }
                });
    }


    /**
     * 显示下载信息到通知栏
     * @param title
     * @param content
     * @param progress
     */
    private void notifyMsg(String title, String content, int progress){
        builder.setSmallIcon(R.mipmap.ic_launcher);
        if (progress > 0 && progress < 100){
            builder.setProgress(100, progress, false);
        } else {
            builder.setProgress(0, 0 , false);
        }

        builder.setAutoCancel(true);
        builder.setWhen(System.currentTimeMillis());
        builder.setContentText(content);

        mNotificationManager.notify(0, builder.build());

    }


    /**
     * 安装apk
     * @return
     * @param fileName
     */
    private Intent getInstallIntent(String fileName){
        File file = new File(fileName);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + file.getAbsolutePath()), "application/vnd.android.package-archive");
        return intent;
    }



}
