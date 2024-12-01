package com.example.ecir;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ecir.database.DatabaseHelper;

public class DetalhesEmbarqueActivity extends AppCompatActivity {
    private TextView numInscricaoDetalhe, nomeEmbarcacaoDetalhe, numeroInscricaoDetalhe, arqueacaoDetalhe;
    private TextView localEmbarqueDetalhe, dataEmbarqueDetalhe, categoriaDetalhe, funcaoDetalhe, tipoNavegacaoDetalhe;
    private DatabaseHelper databaseHelper;
    private int embarqueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_embarque); // Nome do XML do layout

        // Inicializar o banco de dados
        databaseHelper = new DatabaseHelper(this);

        // Obter o ID do embarque da Intent
        embarqueId = getIntent().getIntExtra("EMBARQUE_ID", -1);

        // Inicializar os TextViews
        numInscricaoDetalhe = findViewById(R.id.numInscricaoDetalhe);
        nomeEmbarcacaoDetalhe = findViewById(R.id.nomeEmbarcacaoDetalhe);
        numeroInscricaoDetalhe = findViewById(R.id.numeroInscricaoDetalhe);
        arqueacaoDetalhe = findViewById(R.id.arqueacaoDetalhe);
        localEmbarqueDetalhe = findViewById(R.id.localEmbarqueDetalhe);
        dataEmbarqueDetalhe = findViewById(R.id.dataEmbarqueDetalhe);
        categoriaDetalhe = findViewById(R.id.categoriaDetalhe);
        funcaoDetalhe = findViewById(R.id.funcaoDetalhe);
        tipoNavegacaoDetalhe = findViewById(R.id.tipoNavegacaoDetalhe);

        // Carregar os detalhes do embarque
        carregarDetalhes();
    }

    private void carregarDetalhes() {
        // Buscar o embarque pelo ID
        Embarque embarque = databaseHelper.getEmbarqueById(embarqueId);

        if (embarque != null) {
            // Exibir os detalhes do embarque nos TextViews
            numInscricaoDetalhe.setText(embarque.getNumInscricao());
            nomeEmbarcacaoDetalhe.setText(embarque.getNomeEmbarcacao());
            numeroInscricaoDetalhe.setText(embarque.getNumeroInscricao());
            arqueacaoDetalhe.setText(embarque.getArqueacao());
            localEmbarqueDetalhe.setText(embarque.getLocalEmbarque());
            dataEmbarqueDetalhe.setText(embarque.getDataEmbarque());
            categoriaDetalhe.setText(embarque.getCategoria());
            funcaoDetalhe.setText(embarque.getFuncao());
            tipoNavegacaoDetalhe.setText(embarque.getTipoNavegacao());
        } else {
            // Caso não encontre o embarque, exibir uma mensagem de erro
            Toast.makeText(this, "Embarque não encontrado", Toast.LENGTH_SHORT).show();
            finish(); // Finaliza a activity se não encontrar
        }
    }
}
