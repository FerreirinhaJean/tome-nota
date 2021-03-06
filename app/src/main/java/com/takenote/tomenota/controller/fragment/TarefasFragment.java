package com.takenote.tomenota.controller.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.takenote.tomenota.R;
import com.takenote.tomenota.controller.activity.NovaTarefaActivity;
import com.takenote.tomenota.controller.adapter.AdapterTarefas;
import com.takenote.tomenota.controller.adapter.RecyclerItemClickListener;
import com.takenote.tomenota.model.entities.Anotacao;
import com.takenote.tomenota.model.entities.Tarefa;
import com.takenote.tomenota.model.helper.TarefaDAO;
import com.takenote.tomenota.model.helper.receiver.AlarmeReceiver;
import com.takenote.tomenota.model.util.AlarmeUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TarefasFragment extends Fragment {

    private RecyclerView recyclerTarefas;
    private AdapterTarefas adapterTarefas;
    private List<Tarefa> listaTarefas = new ArrayList<>();
    private TarefaDAO tarefaDAO;
    private TextView tvSemTarefas;


    public TarefasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tarefas, container, false);

        recyclerTarefas = view.findViewById(R.id.recyclerTarefas);
        tvSemTarefas = view.findViewById(R.id.tvSemTarefas);

        recyclerTarefas.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerTarefas, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Tarefa tarefa = listaTarefas.get(position);
                Intent intent = new Intent(getContext(), NovaTarefaActivity.class);
                intent.putExtra("tarefa", (Serializable) tarefa);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        carregaTarefas();
    }

    public void swipe() {
        ItemTouchHelper.Callback itemTouch = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.ACTION_STATE_IDLE;
                int swipeFlags = ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                deletaTarefa(viewHolder);
            }
        };
        new ItemTouchHelper(itemTouch).attachToRecyclerView(recyclerTarefas);
    }

    public void deletaTarefa(final RecyclerView.ViewHolder viewHolder) {

        AlertDialog.Builder alerBuilder = new AlertDialog.Builder(getContext());
        alerBuilder.setTitle("Tarefa concluída?");
        alerBuilder.setMessage("Você tem certeza que deseja excluir a tarefa?");
        alerBuilder.setCancelable(false);


        alerBuilder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int position = viewHolder.getAdapterPosition();
                Tarefa tarefa = listaTarefas.get(position);
                if (tarefaDAO.deletaTarefa(tarefa)) {
                    Toast.makeText(getContext(), "Tarefa excluída!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AlarmeReceiver.ALARME);
                    intent.setClass(getContext(), AlarmeReceiver.class);
                    AlarmeUtil.cancelaAlarme(getContext(), intent, tarefa);
                    listaTarefas.remove(position);
                    adapterTarefas.notifyItemRemoved(position);
                    carregaTarefas();
                } else {
                    Toast.makeText(getContext(), "Erro ao excluir tarefa!", Toast.LENGTH_LONG).show();
                }

            }
        });

        alerBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Cancelado", Toast.LENGTH_LONG).show();
                adapterTarefas.notifyDataSetChanged();
            }
        });

        AlertDialog alert = alerBuilder.create();
        alert.show();

    }


    public void carregaTarefas() {

        buscaTarefas();

        if (!listaTarefas.isEmpty()) {
            tvSemTarefas.setVisibility(View.GONE);
            recyclerTarefas.setVisibility(View.VISIBLE);

            adapterTarefas = new AdapterTarefas(listaTarefas);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerTarefas.setLayoutManager(layoutManager);
            recyclerTarefas.setHasFixedSize(true);
            recyclerTarefas.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
            recyclerTarefas.setAdapter(adapterTarefas);
        } else {
            tvSemTarefas.setVisibility(View.VISIBLE);
            recyclerTarefas.setVisibility(View.GONE);
        }

    }


    public void buscaTarefas() {
        tarefaDAO = new TarefaDAO(getContext());
        listaTarefas = tarefaDAO.buscaTarefas();
    }


}
