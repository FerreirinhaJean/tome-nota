package com.takenote.tomenota.model.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.takenote.tomenota.model.entities.Tarefa;
import com.takenote.tomenota.model.enums.Prioridade;
import com.takenote.tomenota.model.helper.db.Db;
import com.takenote.tomenota.model.helper.impl.ITarefaDAO;
import com.takenote.tomenota.model.util.DataFormatada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {

    private SQLiteDatabase read;
    private SQLiteDatabase write;

    public TarefaDAO(Context context) {
        Db dataBase = new Db(context);
        this.read = dataBase.getReadableDatabase();
        this.write = dataBase.getWritableDatabase();
    }

    @Override
    public boolean salvarTarefa(Tarefa objTarefa) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("tarefa", objTarefa.getNome());
            cv.put("prioridade", objTarefa.getEnumPrioridade().toString());
            if (objTarefa.getLembrete() != null) {
                cv.put("lembrete", DataFormatada.formataDataparaTexto(objTarefa.getLembrete()));
            }
            write.insert(Db.TABELA_TAREFAS, null, cv);
        } catch (Exception e) {
            Log.i("INFO", "Erro ao salvar tarefa " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizaTarefa(Tarefa objTarefa) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("tarefa", objTarefa.getNome());
            cv.put("prioridade", objTarefa.getEnumPrioridade().toString());
            if (objTarefa.getLembrete() != null) {
                cv.put("lembrete", DataFormatada.formataDataparaTexto(objTarefa.getLembrete()));
            }
            String[] args = {String.valueOf(objTarefa.getId())};
            write.update(Db.TABELA_TAREFAS, cv, "id = ?", args);
        } catch (Exception e) {
            Log.i("INFO", "Erro ao atualizar tarefa " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletaTarefa(Tarefa objTarefa) {
        try {
            write.delete(Db.TABELA_TAREFAS, "id = ?", new String[]{String.valueOf(objTarefa.getId())});
        } catch (Exception e) {
            Log.i("INFO", "Erro ao deletar tarefa " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Tarefa> buscaTarefas() {
        List<Tarefa> listaTarefas = new ArrayList<>();
        String sql = "SELECT * FROM " + Db.TABELA_TAREFAS + ";";
        Cursor cursor = read.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String tarefa = cursor.getString(cursor.getColumnIndex("tarefa"));
            Prioridade enumPrioridade = Prioridade.valueOf(cursor.getString(cursor.getColumnIndex("prioridade")).toUpperCase());
            Date lembrete = DataFormatada.formadaTextoParaData(cursor.getString(cursor.getColumnIndex("lembrete")));

            if (lembrete == null) {
                listaTarefas.add(new Tarefa(id, tarefa, enumPrioridade));
            } else {
                listaTarefas.add(new Tarefa(id, tarefa, enumPrioridade, lembrete));
            }
        }
        return listaTarefas;
    }
}
