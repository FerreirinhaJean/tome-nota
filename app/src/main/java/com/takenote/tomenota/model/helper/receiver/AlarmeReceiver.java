package com.takenote.tomenota.model.helper.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.takenote.tomenota.controller.activity.PrincipalActivity;
import com.takenote.tomenota.model.util.NotificationUtil;

public class AlarmeReceiver extends BroadcastReceiver {

    public static final String ALARME = "com.takenote.tomenota.ALARME";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ON RECEIVE", "Broadcast recebido");
        NotificationUtil.create(context,intent,"Broadcast","Alarme broadcast notification",1);
    }
}
