package com.takenote.tomenota.model.entities;

public class Anotacao {

    private int id;
    private String titulo;
    private String anotacao;

    public Anotacao(String titulo, String anotacao) {
        this.titulo = titulo;
        this.anotacao = anotacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }
}
