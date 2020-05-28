package com.takenote.tomenota.model.helper.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Db extends SQLiteOpenHelper {

    public static int VERSAO = 1;
    public static String NOME_DB = "DB_TOME_NOTA";

    public static String TABELA_ANOTACOES = "anotacoes";
    public static String TABELA_TAREFAS = "tarefas";

    public Db(@Nullable Context context) {
        super(context, NOME_DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCriaTabelaAnotacoes = "CREATE IF NOT EXISTS " + TABELA_ANOTACOES +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL, anotacao TEXT NOT NULL)";

        String sqlCriarTabalaTarefas = "CREATE IF NOT EXISTS " + TABELA_TAREFAS +
                "(id INTEGER PRIMATY KEY AUTOINCREMENT, tarefa TEXT NOT NULL, prioridade INTEGER NOT NULL," +
                "lembrete TEXT)";

        try {
            db.execSQL(sqlCriaTabelaAnotacoes);
            db.execSQL(sqlCriarTabalaTarefas);
        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao criar a tabela " + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
