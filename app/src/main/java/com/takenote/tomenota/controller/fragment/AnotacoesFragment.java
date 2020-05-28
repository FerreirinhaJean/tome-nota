package com.takenote.tomenota.controller.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.takenote.tomenota.R;
import com.takenote.tomenota.controller.activity.MainActivity;
import com.takenote.tomenota.controller.adapter.AdapterAnotacoes;
import com.takenote.tomenota.model.entities.Anotacao;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnotacoesFragment extends Fragment {

    private RecyclerView recyclerAnotacoes;
    private AdapterAnotacoes adapterAnotacoes;


    public AnotacoesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_anotacoes, container, false);
        recyclerAnotacoes = view.findViewById(R.id.recyclerAnotacoes);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        carregaAnotacoes();
    }

    public void carregaAnotacoes() {
        List<Anotacao> listaAnotacoes = new ArrayList<>();
        listaAnotacoes.add(new Anotacao("Mercado", "Ir ao mercado"));
        listaAnotacoes.add(new Anotacao("Estudar", "Ir estudar"));
        listaAnotacoes.add(new Anotacao("Dormir", "Ir dormir"));
        listaAnotacoes.add(new Anotacao("Comer", "Ir comer"));

        adapterAnotacoes = new AdapterAnotacoes(listaAnotacoes);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerAnotacoes.setLayoutManager(layoutManager);
        recyclerAnotacoes.setHasFixedSize(true);
        recyclerAnotacoes.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerAnotacoes.setAdapter(adapterAnotacoes);
    }

}
