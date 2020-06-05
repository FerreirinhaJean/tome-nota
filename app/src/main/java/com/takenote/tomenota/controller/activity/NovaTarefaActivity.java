package com.takenote.tomenota.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.takenote.tomenota.R;
import com.takenote.tomenota.controller.fragment.DataPickerFragment;
import com.takenote.tomenota.controller.fragment.TimePickerFragment;

public class NovaTarefaActivity extends AppCompatActivity {

    private Button btnLembrete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);

        btnLembrete = findViewById(R.id.btnLembrete);
        btnLembrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DataPickerFragment(btnLembrete);
                datePicker.show(getSupportFragmentManager(), "data");

            }
        });

    }
}
