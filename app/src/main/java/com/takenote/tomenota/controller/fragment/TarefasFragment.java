package com.takenote.tomenota.controller.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takenote.tomenota.R;
import com.takenote.tomenota.controller.adapter.AdapterTarefas;
import com.takenote.tomenota.model.entities.Tarefa;
import com.takenote.tomenota.model.helper.TarefaDAO;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TarefasFragment extends Fragment {

    private RecyclerView recyclerTarefas;
    private AdapterTarefas adapterTarefas;
    private List<Tarefa> listaTarefas;
    private TarefaDAO tarefaDAO;


    public TarefasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tarefas, container, false);

        recyclerTarefas = view.findViewById(R.id.recyclerTarefas);

        return view;

    }

}
