package com.takenote.tomenota.model.entities;

import com.takenote.tomenota.model.enums.Prioridade;

import java.util.Date;

public class Tarefa {

    private int id;
    private String nome;
    private Prioridade enumPrioridade;
    private Date lembrete;


    public Tarefa(String nome, Prioridade enumPrioridade) {
        this.nome = nome;
        this.enumPrioridade = enumPrioridade;
    }

    public Tarefa(String nome, Prioridade enumPrioridade, Date lembrete) {
        this.nome = nome;
        this.enumPrioridade = enumPrioridade;
        this.lembrete = lembrete;
    }

    public Tarefa(int id, String nome, Prioridade enumPrioridade, Date lembrete) {
        this.id = id;
        this.nome = nome;
        this.enumPrioridade = enumPrioridade;
        this.lembrete = lembrete;
    }

    public Tarefa(int id, String nome, Prioridade enumPrioridade) {
        this.id = id;
        this.nome = nome;
        this.enumPrioridade = enumPrioridade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Prioridade getEnumPrioridade() {
        return enumPrioridade;
    }

    public Date getLembrete() {
        return lembrete;
    }
}
