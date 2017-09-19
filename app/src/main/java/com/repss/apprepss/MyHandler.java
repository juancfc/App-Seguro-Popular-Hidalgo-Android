package com.repss.apprepss;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.microsoft.windowsazure.notifications.NotificationsHandler;

/**
 * Created by maste on 14/10/2016.
 */
public class MyHandler extends NotificationsHandler {

    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    Context ctx;

    @Override
    public void onReceive(Context context, Bundle bundle) {
        ctx = context;
        String nhMessage = bundle.getString("message");
        String nhTitle = bundle.getString("title");
        sendNotification(nhMessage,nhTitle);

    }

    private void sendNotification(String msg,String title) {

        Intent intent = new Intent(ctx, NotificacionDetalles.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Titulo",title);
        intent.putExtra("Descripcion",msg);
        intent.putExtra("NuevaNotificacion",true);

        mNotificationManager = (NotificationManager)
                ctx.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0,
                intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
                        .setSmallIcon(R.drawable.segpop)
                        .setContentTitle("Seguro Popular Hidalgo")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(title))
                        .setSound(defaultSoundUri)
                        .setColor(Color.parseColor("#4B8A08"))
                        .setContentText(title);

        mBuilder.setContentIntent(contentIntent).setAutoCancel(true);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

}
