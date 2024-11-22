package com.example.ecir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditarEmbarqueActivity extends AppCompatActivity {

    private EditText editNumInscricao, editNomeEmbarcacao, editNumeroInscricao, editArqueacao,
            editLocalEmbarque, editDataEmbarque, editCategoria, editFuncao, editTipoNavegacao;
    private Button btnEditarCertificado;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_embarque);

        // Inicializando os elementos da interface
        editNumInscricao = findViewById(R.id.editNumInscricao);
        editNomeEmbarcacao = findViewById(R.id.editNomeEmbarcacao);
        editNumeroInscricao = findViewById(R.id.editNumeroInscricao);
        editArqueacao = findViewById(R.id.editArqueacao);
        editLocalEmbarque = findViewById(R.id.editLocalEmbarque);
        editDataEmbarque = findViewById(R.id.editDataEmbarque);
        editCategoria = findViewById(R.id.editCategoria);
        editFuncao = findViewById(R.id.editFuncao);
        editTipoNavegacao = findViewById(R.id.editTipoNavegacao);
        btnEditarCertificado = findViewById(R.id.btnEditarCertificado);
        backButton = findViewById(R.id.backButton);

        // Recuperar dados enviados (se aplicável)
        Intent intent = getIntent();
        if (intent != null) {
            editNumInscricao.setText(intent.getStringExtra("numInscricao"));
            editNomeEmbarcacao.setText(intent.getStringExtra("nomeEmbarcacao"));
            editNumeroInscricao.setText(intent.getStringExtra("numeroInscricao"));
            editArqueacao.setText(intent.getStringExtra("arqueacao"));
            editLocalEmbarque.setText(intent.getStringExtra("localEmbarque"));
            editDataEmbarque.setText(intent.getStringExtra("dataEmbarque"));
            editCategoria.setText(intent.getStringExtra("categoria"));
            editFuncao.setText(intent.getStringExtra("funcao"));
            editTipoNavegacao.setText(intent.getStringExtra("tipoNavegacao"));
        }

        // Ação do botão de voltar
        backButton.setOnClickListener(v -> finish());

        // Ação do botão de salvar alterações
        btnEditarCertificado.setOnClickListener(v -> salvarAlteracoes());
    }

    private void salvarAlteracoes() {
        // Recuperar os dados dos campos
        String numInscricao = editNumInscricao.getText().toString().trim();
        String nomeEmbarcacao = editNomeEmbarcacao.getText().toString().trim();
        String numeroInscricao = editNumeroInscricao.getText().toString().trim();
        String arqueacao = editArqueacao.getText().toString().trim();
        String localEmbarque = editLocalEmbarque.getText().toString().trim();
        String dataEmbarque = editDataEmbarque.getText().toString().trim();
        String categoria = editCategoria.getText().toString().trim();
        String funcao = editFuncao.getText().toString().trim();
        String tipoNavegacao = editTipoNavegacao.getText().toString().trim();

        // Validar os campos (exemplo básico)
        if (numInscricao.isEmpty() || nomeEmbarcacao.isEmpty() || numeroInscricao.isEmpty() ||
                arqueacao.isEmpty() || localEmbarque.isEmpty() || dataEmbarque.isEmpty() ||
                categoria.isEmpty() || funcao.isEmpty() || tipoNavegacao.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Exemplo de salvar ou enviar dados
        // Aqui, você pode implementar lógica para salvar no banco de dados ou enviar a um servidor
        Toast.makeText(this, "Alterações salvas com sucesso!", Toast.LENGTH_SHORT).show();

        // Finalizar a atividade e retornar os dados
        Intent resultIntent = new Intent();
        resultIntent.putExtra("numInscricao", numInscricao);
        resultIntent.putExtra("nomeEmbarcacao", nomeEmbarcacao);
        resultIntent.putExtra("numeroInscricao", numeroInscricao);
        resultIntent.putExtra("arqueacao", arqueacao);
        resultIntent.putExtra("localEmbarque", localEmbarque);
        resultIntent.putExtra("dataEmbarque", dataEmbarque);
        resultIntent.putExtra("categoria", categoria);
        resultIntent.putExtra("funcao", funcao);
        resultIntent.putExtra("tipoNavegacao", tipoNavegacao);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
