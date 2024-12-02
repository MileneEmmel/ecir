
package com.example.ecir;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ecir.database.DatabaseHelper;
import java.util.ArrayList;
import java.util.List;

public class RegistroEmbarqueActivity extends AppCompatActivity {
    private RecyclerView recyclerViewEmbarques;
    private LinearLayout emptyStateLayout;
    private DatabaseHelper databaseHelper;
    private List<Embarque> embarqueList;
    private ItemEmbarqueAdapter adapter;
    private static final int REQUEST_CODE_EDITAR = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registroembarques);

        recyclerViewEmbarques = findViewById(R.id.recyclerViewEmbarques);
        emptyStateLayout = findViewById(R.id.emptyStateLayout);
        recyclerViewEmbarques.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);
        embarqueList = new ArrayList<>();

        adapter = new ItemEmbarqueAdapter(this, embarqueList, databaseHelper, this::carregarEmbarques);
        recyclerViewEmbarques.setAdapter(adapter);


        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        ImageButton novoButton = findViewById(R.id.novoButton);
        novoButton.setOnClickListener(v -> {
            Intent intent = new Intent(RegistroEmbarqueActivity.this, NovoRegistroEmbarqueActivity.class);
            startActivityForResult(intent, REQUEST_CODE_EDITAR);
        });

        carregarEmbarques();
    }

    private void carregarEmbarques() {
        embarqueList.clear();
        embarqueList.addAll(databaseHelper.getAllEmbarques());
        adapter.notifyDataSetChanged();

        if (embarqueList.isEmpty()) {
            recyclerViewEmbarques.setVisibility(View.GONE);
            emptyStateLayout.setVisibility(View.VISIBLE);
        } else {
            recyclerViewEmbarques.setVisibility(View.VISIBLE);
            emptyStateLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarEmbarques(); // Atualiza a lista ao retornar para a tela
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_EDITAR && resultCode == RESULT_OK) {
            carregarEmbarques();
        }
    }

}
