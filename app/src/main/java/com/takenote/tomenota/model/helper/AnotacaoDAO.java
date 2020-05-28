package com.takenote.tomenota.model.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.takenote.tomenota.model.entities.Anotacao;
import com.takenote.tomenota.model.helper.db.Db;
import com.takenote.tomenota.model.helper.impl.IAnotacaoDao;

import java.util.ArrayList;
import java.util.List;

public class AnotacaoDAO implements IAnotacaoDao {

    private SQLiteDatabase read;
    private SQLiteDatabase write;

    public AnotacaoDAO(Context context) {
        Db dataBase = new Db(context);
        read = dataBase.getReadableDatabase();
        write = dataBase.getWritableDatabase();
    }

    @Override
    public boolean salvarAnotacao(Anotacao anotacao) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("titulo", anotacao.getTitulo());
            cv.put("anotacao", anotacao.getAnotacao());
            write.insert(Db.TABELA_ANOTACOES, null, cv);
        } catch (Exception e) {
            Log.i("INFO", "Erro ao salvar anotacao " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizarAnotacao(Anotacao anotacao) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("titulo", anotacao.getTitulo());
            cv.put("anotacao", anotacao.getAnotacao());
            String[] args = {String.valueOf(anotacao.getId())};
            write.update(Db.TABELA_ANOTACOES, cv, "id = ?", args);
        } catch (Exception e) {
            Log.i("INFO", "Erro ao atualizar anotacao " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean deletarAnotacao(Anotacao anotacao) {
        try {
            write.delete(Db.TABELA_ANOTACOES, "id = ?", new String[]{String.valueOf(anotacao.getId())});
        } catch (Exception e) {
            Log.e("INFO", "Erro ao deletar anotacao " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<Anotacao> listaAnotacoes() {

        List<Anotacao> listaAnotacoes = new ArrayList<>();
        String sql = "SELECT * FROM " + Db.TABELA_ANOTACOES + ";";
        Cursor cursor = read.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            String anotacao = cursor.getString(cursor.getColumnIndex("anotacao"));

            listaAnotacoes.add(new Anotacao(id, titulo, anotacao));

        }

        return listaAnotacoes;
    }
}
