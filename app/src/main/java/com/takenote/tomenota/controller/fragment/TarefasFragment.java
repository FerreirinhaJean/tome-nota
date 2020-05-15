package com.takenote.tomenota.controller.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takenote.tomenota.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TarefasFragment extends Fragment {


    public TarefasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tarefas, container, false);
    }

}
