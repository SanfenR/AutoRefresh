package com.mz.sanfen.autorefreshdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.os.storage.StorageManager;
import android.support.v4.app.NotificationCompat;

import java.io.File;

public class DownLoadService extends Service {



    private String mDownLoadUrl;

    private NotificationManager mNotificationManager;

    private Notification mNotification;


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

        }

        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 显示下载信息到通知栏
     * @param title
     * @param content
     * @param progress
     */
    private void notifyMsg(String title, String content, int progress){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        if (progress > 0 && progress < 100){
            builder.setProgress(100, progress, false);
        } else {
            builder.setProgress(0, 0 , false);
        }

        builder.setAutoCancel(true);
        builder.setWhen(System.currentTimeMillis());
        builder.setContentText(content);

        if (progress >= 100){
            builder.setContentIntent(getInstallIntent());
        }


    }


    /**
     * 安装apk
     * @return
     */
    private PendingIntent getInstallIntent(){
        File file = new File("file");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + file.getAbsolutePath()), "application/vnd.android.package-archive");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }


    private void downloadFile(){
        stopSelf();

    }



}
