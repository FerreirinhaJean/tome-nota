package com.takenote.tomenota.model.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.takenote.tomenota.model.entities.Anotacao;
import com.takenote.tomenota.model.helper.db.Db;
import com.takenote.tomenota.model.helper.impl.IAnotacaoDao;

import java.util.List;

public class AnotacaoDAO implements IAnotacaoDao {

    SQLiteDatabase read;
    SQLiteDatabase write;

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
        return false;
    }

    @Override
    public boolean deletarAnotacao(Anotacao anotacao) {
        return false;
    }

    @Override
    public List<Anotacao> listaAnotacoes() {
        return null;
    }
}
