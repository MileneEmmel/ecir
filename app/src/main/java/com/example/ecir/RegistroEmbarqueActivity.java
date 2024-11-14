package com.example.ecir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RegistroEmbarqueActivity extends AppCompatActivity {
    private RecyclerView recyclerViewEmbarques;
    private LinearLayout emptyStateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registroembarques);

        // Inicializando a RecyclerView e o layout de estado vazio
        recyclerViewEmbarques = findViewById(R.id.recyclerViewEmbarques);
        emptyStateLayout = findViewById(R.id.emptyStateLayout);

        // Inicializando o botão de voltar
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Inicializando o botão de novo
        ImageButton novoButton = findViewById(R.id.novoButton);
        novoButton.setOnClickListener(v -> {
            Intent intent = new Intent(RegistroEmbarqueActivity.this, NovoRegistroEmbarqueActivity.class);
            startActivity(intent);
        });

        // Carrega e exibe a lista de embarques
        loadBoardings();
    }

    private void loadBoardings() {
        Embarque.initializeEmbarques();
        // Obtém a lista de embarques
        List<Embarque> embarques = Embarque.getEmbarques();

        if (embarques.isEmpty()) {
            // Se não houver embarques, exibe o estado vazio
            recyclerViewEmbarques.setVisibility(View.GONE);
            emptyStateLayout.setVisibility(View.VISIBLE);
        } else {
            // Exibe a lista de embarques
            recyclerViewEmbarques.setVisibility(View.VISIBLE);
            emptyStateLayout.setVisibility(View.GONE);

            EmbarqueAdapter adapter = new EmbarqueAdapter(embarques);
            recyclerViewEmbarques.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewEmbarques.setAdapter(adapter);
        }
    }
}
