package com.takenote.tomenota.controller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.takenote.tomenota.R;
import com.takenote.tomenota.model.entities.Anotacao;

import java.util.List;

public class AdapterAnotacoes extends RecyclerView.Adapter<AdapterAnotacoes.MyViewHolder> {

    private List<Anotacao> listaAnotacoes;

    public AdapterAnotacoes(List<Anotacao> listaAnotacoes) {
        this.listaAnotacoes = listaAnotacoes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_anotacoes_adapter, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Anotacao anotacao = listaAnotacoes.get(position);
        if (anotacao != null) {
            holder.tvTitulo.setText(anotacao.getTitulo());
            holder.tvAnotacao.setText(anotacao.getAnotacao());
        }

    }

    @Override
    public int getItemCount() {
        return listaAnotacoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitulo, tvAnotacao;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvAnotacao = itemView.findViewById(R.id.tvAnotacao);
        }
    }
}
