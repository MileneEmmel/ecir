package com.example.ecir;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inicializando os LinearLayouts como botões
        LinearLayout personalDataButton = findViewById(R.id.personalDataButton);
        LinearLayout documentsButton = findViewById(R.id.documentsButton);
        LinearLayout boardingButton = findViewById(R.id.boardingButton);
        LinearLayout calendarButton = findViewById(R.id.calendarButton);
        ImageButton topMenuButton = findViewById(R.id.topMenuButton);

        // Verificando se os botões foram encontrados no layout
        if (personalDataButton != null) {
            personalDataButton.setOnClickListener(v -> openPersonalDataActivity());
        }
        if (documentsButton != null) {
            documentsButton.setOnClickListener(v -> openDocumentsActivity());
        }
        if (boardingButton != null) {
            boardingButton.setOnClickListener(v -> openBoardingActivity());
        }
        if (calendarButton != null) {
            calendarButton.setOnClickListener(v -> openCalendarActivity());
        }
        if (topMenuButton != null) {
            topMenuButton.setOnClickListener(v -> openSettingsActivity());
        }
    }

    private void openPersonalDataActivity() {
        Intent intent = new Intent(HomeActivity.this, DadosPessoaisActivity.class);
        startActivity(intent);
    }

    private void openDocumentsActivity() {
        Intent intent = new Intent(HomeActivity.this, CertificadosActivity.class);
        startActivity(intent);
    }

    private void openBoardingActivity() {
        Intent intent = new Intent(HomeActivity.this, RegistroEmbarqueActivity.class);
        startActivity(intent);
    }

    private void openCalendarActivity() {
        Intent intent = new Intent(HomeActivity.this, CalendarioActivity.class);
        startActivity(intent);
    }

    private void openSettingsActivity() {
        Intent intent = new Intent(HomeActivity.this, ConfiguracoesActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}