package com.example.user.myapplication;
import android.content.BroadcastReceiver;
import android.app.NotificationManager
import android.app.TaskStackBuilder
import android.app.PendingIntent
import android.content.Context
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.os.Vibrator;

public class AlarmBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context,Intent intent){
        NotificationManager notiMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder noti = new NotificationCompat.Builder(this)
                .setcontentTitle("通知")
                .setContentText("您的預約時間已到");
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra("NOTIFICATION_ID", NOTIF_ID);

        TaskStackBuilder stackBuilder =TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationActivity.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent pIntent = stackBuilder.getPendingIntent(0,PendingIntent.Flag_UPDATE_CURRENT);
        noti.setContentIntent(pIntent);
        notiMgr.notify(NOTIF_ID,noti.build());

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate([100,100],0);
    }

}