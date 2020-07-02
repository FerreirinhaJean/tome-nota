package com.takenote.tomenota.model.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.takenote.tomenota.model.entities.Tarefa;

public class AlarmeUtil {

    public static void agendaAlarme(Context context, Intent intent, long time, Tarefa objTarefa) {
        PendingIntent p = PendingIntent.getBroadcast(context, objTarefa.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, p);
        Log.i("ALARME UTIL", "Alarme agendado!");
    }

    public static void cancelaAlarme(Context context, Intent intent, Tarefa objTarefa) {
        PendingIntent p = PendingIntent.getBroadcast(context, objTarefa.getId(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(p);
        Log.i("ALARME UTIL", "Alarme cancelado");

    }


}
