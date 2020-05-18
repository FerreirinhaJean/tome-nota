package com.takenote.tomenota.model.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final String NOME_ARQUIVO = "preferencias";
    private final String CHAVE_PREFERENCIA = "primeirosPassos";


    public Preference(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void salvarPreferencia(boolean preferencia) {
        editor.putBoolean(CHAVE_PREFERENCIA, preferencia);
        editor.commit();
    }

    public boolean recuperarPreferencia() {
        return preferences.getBoolean(CHAVE_PREFERENCIA, false);
    }
}
