package com.takenote.tomenota.controller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.takenote.tomenota.R;
import com.takenote.tomenota.model.entities.Anotacao;
import com.takenote.tomenota.model.helper.AnotacaoDAO;

public class NovaAnotacaoActivity extends AppCompatActivity {

    private EditText etTituloNovaAnotacao, etTextoNovaAnotacao;
    private Anotacao anotacao;
    private AnotacaoDAO anotacaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_anotacao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etTituloNovaAnotacao = findViewById(R.id.etTituloNovaAnotacao);
        etTextoNovaAnotacao = findViewById(R.id.etTextoNovaAnotacao);

        anotacao = (Anotacao) getIntent().getSerializableExtra("anotacao");

        if (anotacao != null) {
            etTituloNovaAnotacao.setText(anotacao.getTitulo());
            etTextoNovaAnotacao.setText(anotacao.getAnotacao());
        }

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
                String titulo = etTituloNovaAnotacao.getText().toString();
                String textoAnotacao = etTextoNovaAnotacao.getText().toString();
                if (anotacao != null) {
                    if (verificaCampos(titulo, textoAnotacao)) {
                        anotacaoDAO = new AnotacaoDAO(this);
                        anotacao.setTitulo(titulo);
                        anotacao.setAnotacao(textoAnotacao);
                        if (anotacaoDAO.atualizarAnotacao(anotacao)) {
                            Toast.makeText(this, "Anotação atualizada!", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(this, "Erro ao atualizar anotação!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    if (verificaCampos(titulo, textoAnotacao)) {
                        anotacao = new Anotacao(titulo, textoAnotacao);
                        anotacaoDAO = new AnotacaoDAO(this);
                        if (anotacaoDAO.salvarAnotacao(anotacao)) {
                            Toast.makeText(this, "Anotação salva!", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(this, "Erro ao salvar anotação!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                    }
                }
                break;

            case android.R.id.home:
                finish();
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    public boolean verificaCampos(String titulo, String anotacao) {
        if (titulo.equals("") || titulo == "") {
            return false;
        }
        if (anotacao.equals("") || anotacao == "") {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
