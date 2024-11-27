package com.example.ecir;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecir.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DadosPessoaisActivity extends AppCompatActivity {

    private boolean isEditing = false; // Flag para alternar entre editar e salvar
    private final List<EditText> editFields = new ArrayList<>(); // Lista para agrupar os campos de texto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dadospessoais);

        // Inicialize o botão de voltar
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Inicialize o botão de editar
        ImageButton editButton = findViewById(R.id.editButton);

        // Referência aos campos de texto
        EditText numInscricao = findViewById(R.id.numInscricaoEditText);
        EditText nome = findViewById(R.id.nomeEditText);
        EditText naturalidade = findViewById(R.id.naturalidadeEditText);
        EditText nacionalidade = findViewById(R.id.nacionalidadeEditText);
        EditText dataNascimento = findViewById(R.id.dataNascimentoEditText);
        EditText omEmissao = findViewById(R.id.omEmissaoEditText);

        // Adiciona os campos na lista
        editFields.add(numInscricao);
        editFields.add(nome);
        editFields.add(naturalidade);
        editFields.add(nacionalidade);
        editFields.add(dataNascimento);
        editFields.add(omEmissao);

        // Desabilitar os campos inicialmente
        toggleFields(false);

        // Configura o clique no botão de editar
        editButton.setOnClickListener(v -> {
            isEditing = !isEditing; // Alternar entre editar e salvar
            toggleFields(isEditing);

            if (isEditing) {
                editButton.setImageResource(R.drawable.salvar);
                Toast.makeText(this, "Modo de edição ativado", Toast.LENGTH_SHORT).show();
            } else {
                editButton.setImageResource(R.drawable.editar);
                if (validateFields()) {
                    saveData();
                }
            }
        });
    }

    /**
     * Alterna entre habilitar e desabilitar os campos de texto.
     */
    private void toggleFields(boolean enabled) {
        for (EditText field : editFields) {
            field.setEnabled(enabled);
            field.setFocusable(enabled);
            field.setFocusableInTouchMode(enabled);
        }
    }

    /**
     * Salva os dados preenchidos (implemente a lógica de salvar aqui).
     */
    private void saveData() {
        DadosPessoais dados = new DadosPessoais(
                editFields.get(0).getText().toString(),
                editFields.get(1).getText().toString(),
                editFields.get(2).getText().toString(),
                editFields.get(3).getText().toString(),
                editFields.get(4).getText().toString(),
                editFields.get(5).getText().toString()
        );

        Log.d("DadosPessoaisActivity", "Dados a serem salvos: " +
                "Num Inscrição: " + dados.getNumInscricao() +
                ", Nome: " + dados.getNome() +
                ", Naturalidade: " + dados.getNaturalidade() +
                ", Nacionalidade: " + dados.getNacionalidade() +
                ", Data Nascimento: " + dados.getDataNascimento() +
                ", OM Emissão: " + dados.getOmEmissao());


        // Usando try-with-resources para garantir que o banco de dados seja fechado
        try (DatabaseHelper dbHelper = new DatabaseHelper(this)) {
            boolean success = dbHelper.saveDadosPessoais(dados);

            if (success) {
                Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao salvar os dados.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // Usando o Log para registrar a exceção com uma tag personalizada
            Log.e("DadosPessoaisActivity", "Erro ao salvar os dados.", e);
            Toast.makeText(this, "Erro ao salvar os dados.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Valida os campos antes de salvar.
     */
    private boolean validateFields() {
        for (EditText field : editFields) {
            if (field.getText().toString().trim().isEmpty()) {
                field.setError("Este campo é obrigatório");
                field.requestFocus();
                return false;
            }
        }
        return true;
    }
}
