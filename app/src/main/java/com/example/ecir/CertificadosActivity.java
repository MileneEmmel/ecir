package com.example.ecir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CertificadosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificados);

        // Inicialize o botão de voltar
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Fecha a atividade atual e retorna para a HomeActivity
            }
        });

        // Inicialize o botão de novo
        ImageButton novoButton = findViewById(R.id.novoButton);
        novoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia a NovoCertificadoActivity
                Intent intent = new Intent(CertificadosActivity.this, NovoCertificadoActivity.class);
                startActivity(intent);
            }
        });
    }
}
