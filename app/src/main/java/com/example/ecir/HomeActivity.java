package com.example.ecir;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inicializando os botões
        Button personalDataButton = findViewById(R.id.personalDataButton);
        Button documentsButton = findViewById(R.id.documentsButton);
        Button boardingButton = findViewById(R.id.boardingButton);
        Button calendarButton = findViewById(R.id.calendarButton);
        ImageButton topMenuButton = findViewById(R.id.topMenuButton);

        // Definindo as ações dos botões
        personalDataButton.setOnClickListener(v -> openPersonalDataActivity());
        documentsButton.setOnClickListener(v -> openDocumentsActivity());
        boardingButton.setOnClickListener(v -> openBoardingActivity());
        calendarButton.setOnClickListener(v -> openCalendarActivity());
        topMenuButton.setOnClickListener(v -> openSettingsActivity());
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
