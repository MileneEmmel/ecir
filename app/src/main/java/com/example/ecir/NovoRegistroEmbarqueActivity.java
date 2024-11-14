package com.example.ecir;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NovoRegistroEmbarqueActivity extends AppCompatActivity {

    private EditText numInscricao, nomeEmbarcacao, numeroInscricao, arqueacao, localEmbarque, dataEmbarque, categoria, funcao, tipoNavegacao;
    private Button btnSalvarCertificado;
    private ImageButton backButton;
    private List<Embarque> embarqueList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novoregistroembarque);

        // Referências para os elementos do layout
        numInscricao = findViewById(R.id.numInscricao);
        nomeEmbarcacao = findViewById(R.id.nomeEmbarcacao);
        numeroInscricao = findViewById(R.id.numeroInscricao);
        arqueacao = findViewById(R.id.arqueacao);
        localEmbarque = findViewById(R.id.localEmbarque);
        dataEmbarque = findViewById(R.id.dataEmbarque);
        categoria = findViewById(R.id.categoria);
        funcao = findViewById(R.id.funcao);
        tipoNavegacao = findViewById(R.id.tipoNavegacao);

        // Configuração do botão de voltar
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());  // Volta para a atividade anterior

        // Configuração do botão de salvar
        btnSalvarCertificado = findViewById(R.id.btnSalvarCertificado);
        btnSalvarCertificado.setOnClickListener(v -> salvarCertificado());  // Chama o método para salvar
    }

    private void salvarCertificado() {
        // Obtém os valores dos campos
        String inscricao = numInscricao.getText().toString();
        String nome = nomeEmbarcacao.getText().toString();
        String numero = numeroInscricao.getText().toString();
        String arqueacaoValor = arqueacao.getText().toString();
        String local = localEmbarque.getText().toString();
        String data = dataEmbarque.getText().toString();
        String categoriaValor = categoria.getText().toString();
        String funcaoValor = funcao.getText().toString();
        String tipo = tipoNavegacao.getText().toString();

        // Exemplo de validação simples
        if (inscricao.isEmpty() || nome.isEmpty() || numero.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show();
        } else {
            // Criar novo objeto Embarque e adicionar à lista
            Embarque embarque = new Embarque(inscricao, nome, numero, arqueacaoValor, local, data, categoriaValor, funcaoValor, tipo);
            embarqueList.add(embarque);  // Adiciona à lista de embarques

            // Confirmação para o usuário
            Toast.makeText(NovoRegistroEmbarqueActivity.this, "Embarque salvo com sucesso!", Toast.LENGTH_SHORT).show();

            // Limpar campos após salvar
            numInscricao.setText("");
            nomeEmbarcacao.setText("");
            numeroInscricao.setText("");
            arqueacao.setText("");
            localEmbarque.setText("");
            dataEmbarque.setText("");
            categoria.setText("");
            funcao.setText("");
            tipoNavegacao.setText("");
        }
    }
}
