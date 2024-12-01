package com.example.ecir;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ecir.database.DatabaseHelper;

public class EditarEmbarqueActivity extends AppCompatActivity {

    private EditText editNumInscricao, editNomeEmbarcacao, editNumeroInscricao, editArqueacao,
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
        editNumInscricao = findViewById(R.id.editNumInscricao);
        editNomeEmbarcacao = findViewById(R.id.editNomeEmbarcacao);
        editNumeroInscricao = findViewById(R.id.editNumeroInscricao);
        editArqueacao = findViewById(R.id.editArqueacao);
        editLocalEmbarque = findViewById(R.id.editLocalEmbarque);
        editDataEmbarque = findViewById(R.id.editDataEmbarque);
        editCategoria = findViewById(R.id.editCategoria);
        editFuncao = findViewById(R.id.editFuncao);
        editTipoNavegacao = findViewById(R.id.editTipoNavegacao);
        Button salvarButton = findViewById(R.id.btnSalvarAlteracoes);

        // Obtendo o ID do embarque
        embarqueId = getIntent().getIntExtra("EMBARQUE_ID", -1);
        preencherCamposComDados();

        salvarButton.setOnClickListener(v -> salvarAlteracoes());
    }

    private void preencherCamposComDados() {
        Embarque embarque = databaseHelper.getEmbarqueById(embarqueId);
        if (embarque != null) {
            editNumInscricao.setText(embarque.getNumInscricao());
            editNomeEmbarcacao.setText(embarque.getNomeEmbarcacao());
            editNumeroInscricao.setText(embarque.getNumeroInscricao());
            editArqueacao.setText(embarque.getArqueacao());
            editLocalEmbarque.setText(embarque.getLocalEmbarque());
            editDataEmbarque.setText(embarque.getDataEmbarque());
            editCategoria.setText(embarque.getCategoria());
            editFuncao.setText(embarque.getFuncao());
            editTipoNavegacao.setText(embarque.getTipoNavegacao());
        }
    }

    private void salvarAlteracoes() {
        // Recuperando os dados atualizados
        String numInscricao = editNumInscricao.getText().toString();
        String nomeEmbarcacao = editNomeEmbarcacao.getText().toString();
        String numeroInscricao = editNumeroInscricao.getText().toString();
        String arqueacao = editArqueacao.getText().toString();
        String localEmbarque = editLocalEmbarque.getText().toString();
        String dataEmbarque = editDataEmbarque.getText().toString();
        String categoria = editCategoria.getText().toString();
        String funcao = editFuncao.getText().toString();
        String tipoNavegacao = editTipoNavegacao.getText().toString();

        // Atualizando o embarque no banco de dados
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("numInscricao", numInscricao);
        contentValues.put("nomeEmbarcacao", nomeEmbarcacao);
        contentValues.put("numeroInscricao", numeroInscricao);
        contentValues.put("arqueacao", arqueacao);
        contentValues.put("localEmbarque", localEmbarque);
        contentValues.put("dataEmbarque", dataEmbarque);
        contentValues.put("categoria", categoria);
        contentValues.put("funcao", funcao);
        contentValues.put("tipoNavegacao", tipoNavegacao);

        int rowsUpdated = db.update("Embarques", contentValues, "id = ?", new String[]{String.valueOf(embarqueId)});

        if (rowsUpdated > 0) {
            Toast.makeText(this, "Embarque atualizado com sucesso!", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);  // Retorna para a atividade anterior com resultado
            finish();
        } else {
            Toast.makeText(this, "Erro ao atualizar embarque", Toast.LENGTH_SHORT).show();
        }
    }
}
