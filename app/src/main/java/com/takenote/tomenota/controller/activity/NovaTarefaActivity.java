package com.takenote.tomenota.controller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.takenote.tomenota.R;
import com.takenote.tomenota.controller.fragment.DataPickerFragment;
import com.takenote.tomenota.controller.fragment.TimePickerFragment;
import com.takenote.tomenota.model.entities.Tarefa;
import com.takenote.tomenota.model.enums.Prioridade;
import com.takenote.tomenota.model.helper.TarefaDAO;
import com.takenote.tomenota.model.helper.receiver.AlarmeReceiver;
import com.takenote.tomenota.model.util.AlarmeUtil;
import com.takenote.tomenota.model.util.DataFormatada;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class NovaTarefaActivity extends AppCompatActivity {

    private Button btnLembrete;
    private Spinner spPrioridade;
    private EditText etTarefa;
    private Tarefa objTarefa;
    private TarefaDAO tarefaDAO;
    private ImageButton imgCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        spPrioridade = findViewById(R.id.spPrioridade);
        etTarefa = findViewById(R.id.etTarefa);

        imgCancelar = findViewById(R.id.imgCancelar);
        imgCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLembrete.setText("Adicionar lembrete?  ");
                imgCancelar.setVisibility(View.INVISIBLE);
            }
        });


        btnLembrete = findViewById(R.id.btnLembrete);
        btnLembrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DataPickerFragment(btnLembrete, imgCancelar);
                datePicker.setCancelable(false);
                datePicker.show(getSupportFragmentManager(), "data");
            }
        });

        objTarefa = (Tarefa) getIntent().getSerializableExtra("tarefa");

        if (objTarefa != null) {
            etTarefa.setText(objTarefa.getNome());
            spPrioridade.setSelection(objTarefa.getEnumPrioridade().ordinal());
            if (objTarefa.getLembrete() != null) {
                btnLembrete.setText(DataFormatada.formataDataparaTexto(objTarefa.getLembrete()));
            }
        }


        visibilidadeBtnCancelar();

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
                if (objTarefa != null) {
                    if (verificaCampos(tarefa)) {
                        String lembrete = btnLembrete.getText().toString();
                        String prioridade = spPrioridade.getSelectedItem().toString();
                        Prioridade enumPrioridade = Prioridade.valueOf(prioridade.toUpperCase().replace("É", "E"));
                        objTarefa.setNome(tarefa);
                        objTarefa.setEnumPrioridade(enumPrioridade);
                        if (lembrete.equals("Adicionar lembrete?  ")) {
                            objTarefa.setLembrete(null);
                        } else {
                            objTarefa.setLembrete(DataFormatada.formadaTextoParaData(lembrete));
                        }
                        tarefaDAO = new TarefaDAO(this);
                        if (tarefaDAO.atualizaTarefa(objTarefa)) {
                            Toast.makeText(this, "Tarefa atualizada!", Toast.LENGTH_LONG).show();
                            if (objTarefa.getLembrete() != null)
                                agendaAlarme(objTarefa);
                            finish();
                        } else {
                            Toast.makeText(this, "Erro ao atualizar tarefa!", Toast.LENGTH_LONG).show();
                        }
                    }

                } else {
                    if (verificaCampos(tarefa)) {
                        String lembrete = btnLembrete.getText().toString();
                        String prioridade = spPrioridade.getSelectedItem().toString();
                        Prioridade enumPrioridade = Prioridade.valueOf(prioridade.toUpperCase().replace("É", "E"));
                        Tarefa novaTarefa = instanciaTarefa(tarefa, enumPrioridade, lembrete);
                        tarefaDAO = new TarefaDAO(this);
                        if (tarefaDAO.salvarTarefa(novaTarefa)) {
                            Toast.makeText(this, "Tarefa salva!", Toast.LENGTH_LONG).show();
                            if (novaTarefa.getLembrete() != null)
                                agendaAlarme(novaTarefa);
                            finish();
                        } else {
                            Toast.makeText(this, "Erro ao salvar tarefa!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "Preencha a tarefa!", Toast.LENGTH_LONG).show();
                    }
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

        if (lembrete.equals("Adicionar lembrete?  ")) {
            objTarefa = new Tarefa(tarefa, enumPrioridade);
        } else {
            Date dataLembrete = DataFormatada.formadaTextoParaData(lembrete);
            objTarefa = new Tarefa(tarefa, enumPrioridade, dataLembrete);
        }

        return objTarefa;
    }

    public void visibilidadeBtnCancelar() {
        if (!btnLembrete.getText().toString().equals("Adicionar lembrete?  ")) {
            imgCancelar.setVisibility(View.VISIBLE);
        } else {
            imgCancelar.setVisibility(View.INVISIBLE);
        }
    }

    public void agendaAlarme(Tarefa tarefa) {
        Intent intent = new Intent(AlarmeReceiver.ALARME);
        intent.putExtra("tarefaLembrete", tarefa.getNome());
        AlarmeUtil.agendaAlarme(this, intent, getTime(tarefa));
    }

    public long getTime(Tarefa objTarefa) {
        Calendar c = Calendar.getInstance();
        c.setTime(objTarefa.getLembrete());
        //  c.setTimeInMillis(System.currentTimeMillis());
        //  c.add(Calendar.SECOND, 30);
        long time = c.getTimeInMillis();
        return time;
    }

}
