package com.takenote.tomenota.model.util;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.takenote.tomenota.R;

public class NotificationUtil {

    private static PendingIntent getPendingIntent(Context context, Intent intent, int id) {
        PendingIntent p = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(intent.getComponent());
            stackBuilder.addNextIntent(intent);
            p = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
        }
        return p;
    }


    public static void create(Context context, Intent intent, String titulo, String text, int id) {
      //  PendingIntent p = getPendingIntent(context, intent, id);

        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL);
        b.setSmallIcon(R.drawable.logo);
        b.setContentTitle(titulo);
        b.setContentText(text);
        //b.setContentIntent(p);
        b.setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(id, b.build());
    }

}
