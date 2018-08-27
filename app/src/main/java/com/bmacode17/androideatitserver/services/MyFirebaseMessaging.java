package com.bmacode17.androideatitserver.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.bmacode17.androideatitserver.R;
import com.bmacode17.androideatitserver.activities.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by User on 16-Aug-18.
 */

public class MyFirebaseMessaging extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        sendNotification(remoteMessage);
    }

    private void sendNotification(RemoteMessage remoteMessage) {

        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),"CHANNEL_ID");
        builder.setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher_round);

        NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }
}

