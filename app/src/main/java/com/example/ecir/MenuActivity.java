package com.example.ecir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button loginButton = findViewById(R.id.loginButton);
        Button empresaButton = findViewById(R.id.empresaButton);
        Button registerButton = findViewById(R.id.registerButton);
        TextView registerPromptText = findViewById(R.id.registerPromptText);

        // Set onClickListener for "Entrar"
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        // Set onClickListener for "Entrar como Empresa"
        empresaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent empresaIntent = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(empresaIntent);
            }
        });

        // Set onClickListener for "Cadastre-se!"
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MenuActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        // Optional: Set onClickListener for "Primeira vez aqui?" if needed
        registerPromptText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click action for "Primeira vez aqui?"
            }
        });
    }
}

