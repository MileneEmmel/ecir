package com.example.ecir;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecir.database.DatabaseHelper;

public class EditarEmbarqueActivity extends AppCompatActivity {

    private EditText editNomeEmbarcacao, editNumeroInscricao, editArqueacao,
            editLocalEmbarque, editDataEmbarque, editCategoria, editFuncao, editTipoNavegacao;
    private int embarqueId;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_embarque);

        // Inicializando a instância do banco de dados
        databaseHelper = new DatabaseHelper(this);

        // Inicializando os elementos da interface
        editNomeEmbarcacao = findViewById(R.id.editNomeEmbarcacao);
        editNumeroInscricao = findViewById(R.id.editNumeroInscricao);
        editArqueacao = findViewById(R.id.editArqueacao);
        editLocalEmbarque = findViewById(R.id.editLocalEmbarque);
        editDataEmbarque = findViewById(R.id.editDataEmbarque);
        editCategoria = findViewById(R.id.editCategoria);
        editFuncao = findViewById(R.id.editFuncao);
        editTipoNavegacao = findViewById(R.id.editTipoNavegacao);
        Button btnSalvarAlteracoes = findViewById(R.id.btnSalvarAlteracoes);
        ImageButton backButton = findViewById(R.id.backButton);

        // Recuperar os dados enviados pela Intent
        Intent intent = getIntent();
        if (intent != null) {
            embarqueId = intent.getIntExtra("embarqueId", -1); // Recupera o ID do embarque
            if (embarqueId == -1) {
                Toast.makeText(this, "ID do embarque inválido", Toast.LENGTH_SHORT).show();
                finish(); // Finaliza a activity se o ID for inválido
                return;
            }

            // Recupera os outros dados
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
        btnSalvarAlteracoes.setOnClickListener(v -> salvarAlteracoes());
    }

    private void salvarAlteracoes() {
        // Recupera os dados dos campos
        String nomeEmbarcacao = editNomeEmbarcacao.getText().toString().trim();
        String numeroInscricao = editNumeroInscricao.getText().toString().trim();
        String arqueacao = editArqueacao.getText().toString().trim();
        String localEmbarque = editLocalEmbarque.getText().toString().trim();
        String dataEmbarque = editDataEmbarque.getText().toString().trim();
        String categoria = editCategoria.getText().toString().trim();
        String funcao = editFuncao.getText().toString().trim();
        String tipoNavegacao = editTipoNavegacao.getText().toString().trim();

        // Valida se todos os campos foram preenchidos
        if (nomeEmbarcacao.isEmpty() || numeroInscricao.isEmpty() ||
                arqueacao.isEmpty() || localEmbarque.isEmpty() || dataEmbarque.isEmpty() ||
                categoria.isEmpty() || funcao.isEmpty() || tipoNavegacao.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Atualizar os dados no banco de dados
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nomeEmbarcacao", nomeEmbarcacao);
        values.put("numeroInscricao", numeroInscricao);
        values.put("arqueacao", arqueacao);
        values.put("localEmbarque", localEmbarque);
        values.put("dataEmbarque", dataEmbarque);
        values.put("categoria", categoria);
        values.put("funcao", funcao);
        values.put("tipoNavegacao", tipoNavegacao);

        int rowsAffected = db.update("embarques", values, "id = ?", new String[]{String.valueOf(embarqueId)});
        db.close();

        if (rowsAffected > 0) {
            Toast.makeText(this, "Alterações salvas com sucesso!", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK); // Retorna para a atividade anterior com sucesso
            finish(); // Fecha a activity
        } else {
            Toast.makeText(this, "Erro ao salvar alterações. Nenhuma linha foi atualizada.", Toast.LENGTH_SHORT).show();
        }
    }
}
