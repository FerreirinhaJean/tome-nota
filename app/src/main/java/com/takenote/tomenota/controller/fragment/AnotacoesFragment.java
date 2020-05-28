package com.takenote.tomenota.controller.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.takenote.tomenota.R;
import com.takenote.tomenota.controller.activity.NovaAnotacaoActivity;
import com.takenote.tomenota.controller.adapter.AdapterAnotacoes;
import com.takenote.tomenota.controller.adapter.RecyclerItemClickListener;
import com.takenote.tomenota.model.entities.Anotacao;
import com.takenote.tomenota.model.helper.AnotacaoDAO;

import java.io.Serializable;
import java.util.List;


public class AnotacoesFragment extends Fragment {

    private RecyclerView recyclerAnotacoes;
    private AdapterAnotacoes adapterAnotacoes;
    private List<Anotacao> listaAnotacoes;
    private AnotacaoDAO anotacaoDAO;
    ;


    public AnotacoesFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_anotacoes, container, false);
        recyclerAnotacoes = view.findViewById(R.id.recyclerAnotacoes);


        recyclerAnotacoes.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerAnotacoes, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Anotacao anotacao = listaAnotacoes.get(position);
                Intent intent = new Intent(getContext(), NovaAnotacaoActivity.class);
                intent.putExtra("anotacao", (Serializable) anotacao);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

        swipe();

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        carregaAnotacoes();
    }

    public void swipe() {
        ItemTouchHelper.Callback itemTouch = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.ACTION_STATE_IDLE;
                int swipeFlags = ItemTouchHelper.START;
                return makeMovementFlags(dragFlags, swipeFlags);

            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                deletarAnotacao(viewHolder);
            }
        };
        new ItemTouchHelper(itemTouch).attachToRecyclerView(recyclerAnotacoes);
    }

    public void deletarAnotacao(final RecyclerView.ViewHolder viewHolder) {

        AlertDialog.Builder alerBuilder = new AlertDialog.Builder(getContext());
        alerBuilder.setTitle("Excluir Anotação");
        alerBuilder.setMessage("Você tem certeza que deseja excluir a anotação?");
        alerBuilder.setCancelable(false);

        alerBuilder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int position = viewHolder.getAdapterPosition();
                Anotacao anotacao = listaAnotacoes.get(position);
                if (anotacaoDAO.deletarAnotacao(anotacao)) {
                    Toast.makeText(getContext(), "Anotação excluída!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Erro ao excluir anotação!", Toast.LENGTH_LONG).show();
                }
                listaAnotacoes.remove(position);
                adapterAnotacoes.notifyItemRemoved(position);
            }
        });

        alerBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Cancelado", Toast.LENGTH_LONG).show();
                adapterAnotacoes.notifyDataSetChanged();
            }
        });

        AlertDialog alert = alerBuilder.create();
        alert.show();

    }


    public void carregaAnotacoes() {

        anotacaoDAO = new AnotacaoDAO(getContext());
        listaAnotacoes = anotacaoDAO.listaAnotacoes();

        adapterAnotacoes = new AdapterAnotacoes(listaAnotacoes);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerAnotacoes.setLayoutManager(layoutManager);
        recyclerAnotacoes.setHasFixedSize(true);
        recyclerAnotacoes.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerAnotacoes.setAdapter(adapterAnotacoes);
    }

}
