package com.example.ecir;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalhesEmbarqueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_embarque); // Aponta para o XML fornecido

        // Toolbar - Botão de voltar
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Recuperando dados da Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Campos do layout
            TextView nomeEmbarcacaoDetalhe = findViewById(R.id.nomeEmbarcacaoDetalhe);
            TextView numInscricaoDetalhe = findViewById(R.id.numInscricaoDetalhe);
            TextView arqueacaoDetalhe = findViewById(R.id.arqueacaoDetalhe);
            TextView localEmbarqueDetalhe = findViewById(R.id.localEmbarqueDetalhe);
            TextView dataEmbarqueDetalhe = findViewById(R.id.dataEmbarqueDetalhe);
            TextView categoriaDetalhe = findViewById(R.id.categoriaDetalhe);
            TextView funcaoDetalhe = findViewById(R.id.funcaoDetalhe);
            TextView tipoNavegacaoDetalhe = findViewById(R.id.tipoNavegacaoDetalhe);

            // Preenchendo os dados
            nomeEmbarcacaoDetalhe.setText(extras.getString("nomeEmbarcacao", "N/A"));
            numInscricaoDetalhe.setText(extras.getString("numeroInscricao", "N/A"));
            arqueacaoDetalhe.setText(extras.getString("arqueacao", "N/A"));
            localEmbarqueDetalhe.setText(extras.getString("localEmbarque", "N/A"));
            dataEmbarqueDetalhe.setText(extras.getString("dataEmbarque", "N/A"));
            categoriaDetalhe.setText(extras.getString("categoria", "N/A"));
            funcaoDetalhe.setText(extras.getString("funcao", "N/A"));
            tipoNavegacaoDetalhe.setText(extras.getString("tipoNavegacao", "N/A"));
        }
    }
}
