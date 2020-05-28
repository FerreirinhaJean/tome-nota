package com.takenote.tomenota.model.helper.impl;

import com.takenote.tomenota.model.entities.Anotacao;

import java.util.List;

public interface IAnotacaoDao {

    public boolean salvarAnotacao(Anotacao anotacao);

    public boolean atualizarAnotacao(Anotacao anotacao);

    public boolean deletarAnotacao(Anotacao anotacao);

    public List<Anotacao> listaAnotacoes();

}
