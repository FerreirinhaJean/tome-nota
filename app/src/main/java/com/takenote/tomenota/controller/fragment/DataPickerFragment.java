package com.takenote.tomenota.controller.fragment;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;

import com.takenote.tomenota.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataPickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private Button btnLembrete;
    private ImageButton imgCancelar;

    public DataPickerFragment() {

    }

    public DataPickerFragment(Button btnLembrete, ImageButton imgCancelar) {
        this.btnLembrete = btnLembrete;
        this.imgCancelar = imgCancelar;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int ano = calendar.get(Calendar.YEAR);

        return new DatePickerDialog(getActivity(), this, ano, mes, dia);

    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        btnLembrete.setText("Adicionar lembrete?  ");
        imgCancelar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String data = String.format("%02d", dayOfMonth) + "/" + String.format("%02d", month + 1) + "/" + year;
        Log.i("Data", data);
        DialogFragment timepicker = new TimePickerFragment(data, btnLembrete,imgCancelar);
        timepicker.setCancelable(false);
        timepicker.show(getFragmentManager(), "time");


    }
}
