package com.example.ecir;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecir.database.DatabaseHelper;
import com.example.ecir.database.DatabaseHelper.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText, emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar as views
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button registerButton = findViewById(R.id.registerButton);

        // Ação do botão de registro
        registerButton.setOnClickListener(v -> handleRegister());
    }

    // Lida com o registro do usuário
    private void handleRegister() {
        String username = usernameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (validateFields(username, email, password)) {
            // Criação do objeto User
            User user = new User(username, email, password);

            // Interação com o banco de dados
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            boolean isInserted = dbHelper.insertUser(user);

            if (isInserted) {
                Toast.makeText(this, "Registro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                finish(); // Fecha a RegisterActivity
            } else {
                Toast.makeText(this, "Erro ao salvar os dados.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Valida os campos de entrada
    private boolean validateFields(String username, String email, String password) {
        if (username.isEmpty()) {
            usernameEditText.setError("Nome de usuário obrigatório");
            usernameEditText.requestFocus();
            return false;
        }
        if (email.isEmpty()) {
            emailEditText.setError("E-mail obrigatório");
            emailEditText.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            passwordEditText.setError("Senha obrigatória");
            passwordEditText.requestFocus();
            return false;
        }
        return true;
    }
}
