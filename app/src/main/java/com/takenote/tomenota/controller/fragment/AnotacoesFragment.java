package com.takenote.tomenota.controller.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.takenote.tomenota.R;
import com.takenote.tomenota.controller.activity.MainActivity;
import com.takenote.tomenota.controller.activity.NovaAnotacaoActivity;
import com.takenote.tomenota.controller.adapter.AdapterAnotacoes;
import com.takenote.tomenota.controller.adapter.RecyclerItemClickListener;
import com.takenote.tomenota.model.entities.Anotacao;
import com.takenote.tomenota.model.helper.AnotacaoDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnotacoesFragment extends Fragment {

    private RecyclerView recyclerAnotacoes;
    private AdapterAnotacoes adapterAnotacoes;
    private List<Anotacao> listaAnotacoes;
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

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        carregaAnotacoes();
    }

    public void carregaAnotacoes() {

        AnotacaoDAO anotacaoDAO = new AnotacaoDAO(getContext());
        listaAnotacoes = anotacaoDAO.listaAnotacoes();

        adapterAnotacoes = new AdapterAnotacoes(listaAnotacoes);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerAnotacoes.setLayoutManager(layoutManager);
        recyclerAnotacoes.setHasFixedSize(true);
        recyclerAnotacoes.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerAnotacoes.setAdapter(adapterAnotacoes);
    }

}
