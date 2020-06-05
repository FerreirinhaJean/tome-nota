package com.takenote.tomenota.controller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.takenote.tomenota.R;
import com.takenote.tomenota.model.entities.Tarefa;
import com.takenote.tomenota.model.util.DataFormatada;

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
        Tarefa tarefa = listaTarefas.get(position);
        holder.tvTituloTarefaAdapter.setText(tarefa.getNome());

        if (tarefa.getEnumPrioridade().toString().equals("ALTA")) {
            holder.imgPrioridadeAdapter.setImageResource(R.drawable.ic_prioridade_alta_24dp);
        } else if (tarefa.getEnumPrioridade().toString().equals("MEDIA")) {
            holder.imgPrioridadeAdapter.setImageResource(R.drawable.ic_prioridade_media_24dp);
        } else if (tarefa.getEnumPrioridade().toString().equals("BAIXA")) {
            holder.imgPrioridadeAdapter.setImageResource(R.drawable.ic_prioridade_baixa_24dp);
        }

        if (tarefa.getLembrete() == null) {
            holder.tvLembreteTarefaAdapter.setText("Sem lembrete");
        } else {
            holder.tvLembreteTarefaAdapter.setText(DataFormatada.formataDataparaTexto(tarefa.getLembrete()));
        }

    }

    @Override
    public int getItemCount() {
        return listaTarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTituloTarefaAdapter, tvLembreteTarefaAdapter;
        ImageView imgPrioridadeAdapter;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            tvTituloTarefaAdapter = itemView.findViewById(R.id.tvTituloTarefaAdapter);
            tvLembreteTarefaAdapter = itemView.findViewById(R.id.tvLembreteTarefaAdapter);
            imgPrioridadeAdapter = itemView.findViewById(R.id.imgPrioridadeAdapter);

        }
    }

}
