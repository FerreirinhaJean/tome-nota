package com.takenote.tomenota.model.helper.receiver;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.takenote.tomenota.controller.activity.PrincipalActivity;
import com.takenote.tomenota.model.util.NotificationUtil;

public class AlarmeReceiver extends BroadcastReceiver {

    public static final String ALARME = "com.takenote.tomenota.ALARME";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ON RECEIVE", "Broadcast recebido");
        Intent notifIntent = new Intent(context, PrincipalActivity.class);
        String textoTarefa = intent.getStringExtra("tarefaLembrete");
        NotificationUtil notificationUtil = new NotificationUtil(context);

        NotificationCompat.Builder nb = notificationUtil.create(context, notifIntent, textoTarefa);

        notificationUtil.getManager().notify(10, nb.build());
    }
}
