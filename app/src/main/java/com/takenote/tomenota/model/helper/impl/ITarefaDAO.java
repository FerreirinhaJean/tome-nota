package com.takenote.tomenota.model.helper.impl;

import com.takenote.tomenota.model.entities.Tarefa;

import java.util.List;

public interface ITarefaDAO {

    public boolean salvarTarefa(Tarefa objTarefa);

    public boolean atualizaTarefa(Tarefa objTarefa);

    public boolean deletaTarefa(Tarefa objTarefa);

    public List<Tarefa> buscaTarefas();


}
