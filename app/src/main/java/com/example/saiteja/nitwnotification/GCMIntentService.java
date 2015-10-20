package com.example.saiteja.nitwnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.gcm.GcmListenerService;

public class GCMIntentService extends GcmListenerService  {

	private static final String TAG = "GCMIntentService";

    @Override
    public void onCreate() {
        Toast.makeText(this,"created",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMessageSent(String msgId) {
        Toast.makeText(this,"created1412",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
        //Toast.makeText(this,"getting message",Toast.LENGTH_LONG).show();
        Log.d("me","created");

        String message = data.getString("message");
        generateNotification(this,message);
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);
    }

    /**
     * Issues a notification to inform the user that server has sent a message.
     */
    private  void generateNotification(Context context, String message) {
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Technozion")
                        .setContentText(message);
        Intent resultIntent = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);
        long[] pattern = {500,500,500,500,500,500,500,500,500};
        mBuilder.setVibrate(pattern);
        mNotificationManager.notify(0, mBuilder.build());

    }

}
