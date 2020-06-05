package com.takenote.tomenota.controller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.takenote.tomenota.R;
import com.takenote.tomenota.model.entities.Tarefa;

import java.util.List;

public class AdapterTarefas extends RecyclerView.Adapter<AdapterTarefas.MyViewHolder> {

    private List<Tarefa> listaTarefas;

    public AdapterTarefas(List<Tarefa> listaTarefas) {
        this.listaTarefas = listaTarefas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_tarefas_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listaTarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
