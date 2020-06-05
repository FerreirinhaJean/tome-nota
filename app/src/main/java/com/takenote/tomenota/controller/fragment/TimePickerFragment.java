package com.takenote.tomenota.controller.fragment;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.takenote.tomenota.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private String data;
    private Button btnLembrete;
    private ImageButton imgCancelar;

    public TimePickerFragment() {

    }

    public TimePickerFragment(String data, Button btnLembrete, ImageButton imgCancelar) {
        this.data = data;
        this.btnLembrete = btnLembrete;
        this.imgCancelar = imgCancelar;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minutos = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hora, minutos, DateFormat.is24HourFormat(getActivity()));

    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        btnLembrete.setText("Adicionar lembrete?  ");
        imgCancelar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        data += " " + String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);
        btnLembrete.setText(data);
        imgCancelar.setVisibility(View.VISIBLE);
    }
}
