package com.example.ecir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Referenciando seu layout XML

        // Inicializando as views
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        TextView forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);
        TextView registerPromptTextView = findViewById(R.id.registerPromptTextView);
        Button registerButton = findViewById(R.id.registerButton);

        // Ação do botão de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        // Ação do texto "Esqueceu a senha"
        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleForgotPassword();
            }
        });

        // Ação do botão de registro
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRegister();
            }
        });
    }

    // Lógica para lidar com o login
    private void handleLogin() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Credenciais de login de exemplo para teste
        String correctUsername = "admin";
        String correctPassword = "1234";

        if (username.equals(correctUsername) && password.equals(correctPassword)) {
            // Navega para HomeActivity em caso de login bem-sucedido
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish(); // Fecha a LoginActivity
        } else {
            // Mostra uma mensagem de erro se as credenciais estiverem incorretas
            Toast.makeText(LoginActivity.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
        }
    }

    // Ação ao clicar em "Esqueceu a senha" (atualmente mostra apenas um Toast)
    private void handleForgotPassword() {
        Toast.makeText(LoginActivity.this, "Esqueceu a senha clicado", Toast.LENGTH_SHORT).show();
    }

    // Ação ao clicar em "Registrar"
    private void handleRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent); // Navega para RegisterActivity
    }
}



