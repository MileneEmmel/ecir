package com.example.ecir;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecir.database.DatabaseHelper;

public class NovoRegistroEmbarqueActivity extends AppCompatActivity {
    private EditText numInscricao, nomeEmbarcacao, numeroInscricao, arqueacao, localEmbarque, dataEmbarque, categoria, funcao, tipoNavegacao;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novoregistroembarque);

        databaseHelper = new DatabaseHelper(this);

        numInscricao = findViewById(R.id.numInscricao);
        nomeEmbarcacao = findViewById(R.id.nomeEmbarcacao);
        numeroInscricao = findViewById(R.id.numeroInscricao);
        arqueacao = findViewById(R.id.arqueacao);
        localEmbarque = findViewById(R.id.localEmbarque);
        dataEmbarque = findViewById(R.id.dataEmbarque);
        categoria = findViewById(R.id.categoria);
        funcao = findViewById(R.id.funcao);
        tipoNavegacao = findViewById(R.id.tipoNavegacao);

        Button btnSalvarCertificado = findViewById(R.id.btnSalvarCertificado);
        btnSalvarCertificado.setOnClickListener(v -> salvarCertificado());
    }

    private void salvarCertificado() {
        String inscricao = numInscricao.getText().toString().trim();
        String nome = nomeEmbarcacao.getText().toString().trim();
        String numero = numeroInscricao.getText().toString().trim();
        String arqueacaoValor = arqueacao.getText().toString().trim();
        String local = localEmbarque.getText().toString().trim();
        String data = dataEmbarque.getText().toString().trim();
        String categoriaValor = categoria.getText().toString().trim();
        String funcaoValor = funcao.getText().toString().trim();
        String tipo = tipoNavegacao.getText().toString().trim();

        if (inscricao.isEmpty() || nome.isEmpty() || numero.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        Embarque embarque = new Embarque(0, inscricao, nome, numero, arqueacaoValor, local, data, categoriaValor, funcaoValor, tipo);

        boolean isSaved = databaseHelper.addEmbarque(embarque); // Método modificado para retornar boolean

        if (isSaved) {
            Toast.makeText(this, "Registro salvo com sucesso!", Toast.LENGTH_SHORT).show();
            finish(); // Volta para a tela anterior
        } else {
            Toast.makeText(this, "Erro ao salvar registro.", Toast.LENGTH_SHORT).show();
        }
    }
}
