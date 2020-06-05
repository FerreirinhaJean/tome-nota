package com.takenote.tomenota.controller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.takenote.tomenota.R;
import com.takenote.tomenota.controller.fragment.DataPickerFragment;
import com.takenote.tomenota.controller.fragment.TimePickerFragment;
import com.takenote.tomenota.model.entities.Tarefa;
import com.takenote.tomenota.model.enums.Prioridade;
import com.takenote.tomenota.model.util.DataFormatada;

import java.util.Date;

public class NovaTarefaActivity extends AppCompatActivity {

    private Button btnLembrete;
    private Spinner spPrioridade;
    private EditText etTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spPrioridade = findViewById(R.id.spPrioridade);
        etTarefa = findViewById(R.id.etTarefa);

        btnLembrete = findViewById(R.id.btnLembrete);
        btnLembrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DataPickerFragment(btnLembrete);
                datePicker.show(getSupportFragmentManager(), "data");

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuConfirmar:
                String tarefa = etTarefa.getText().toString();
                if (verificaCampos(tarefa)) {
                    String lembrete = btnLembrete.getText().toString();
                    String prioridade = spPrioridade.getSelectedItem().toString();
                    Prioridade enumPrioridade = Prioridade.valueOf(prioridade.toUpperCase().replace("É", "E"));
                    Tarefa novaTarefa = instanciaTarefa(tarefa, enumPrioridade, lembrete);
                } else {
                    Toast.makeText(this, "Preencha a tarefa!", Toast.LENGTH_LONG).show();
                }

                break;
            case android.R.id.home:
                finish();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    public boolean verificaCampos(String tarefa) {
        return !tarefa.equals("") && tarefa != null;
    }

    public Tarefa instanciaTarefa(String tarefa, Prioridade enumPrioridade, String lembrete) {
        Tarefa novaTarefa = null;
        if (lembrete.equals("Adicionar lembrete?  ")) {
            novaTarefa = new Tarefa(tarefa, enumPrioridade);
        } else {
            Date dataLembrete = DataFormatada.formadaTextoParaData(lembrete);
            novaTarefa = new Tarefa(tarefa, enumPrioridade, dataLembrete);
        }

        return novaTarefa;
    }

}
