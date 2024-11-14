package com.example.ecir;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class NovoCertificadoActivity extends AppCompatActivity {

    private static final int PICK_PDF_REQUEST = 1; // Código para identificar o pedido de escolha de PDF
    private static final int PERMISSION_REQUEST_CODE = 2; // Código para a solicitação de permissão
    private EditText editNome, editData, editOrganizacao;
    private Button btnSalvar, btnSelecionarPdf;
    private Uri pdfUri; // Uri para armazenar o caminho do arquivo PDF selecionado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_certificado);

        // Inicializa os componentes da tela
        editNome = findViewById(R.id.editCertificadoNome);
        editData = findViewById(R.id.editCertificadoData);
        editOrganizacao = findViewById(R.id.editCertificadoOrganizacao);
        btnSalvar = findViewById(R.id.btnSalvarCertificado);
        btnSelecionarPdf = findViewById(R.id.btnSelecionarPdf);

        // Verifica e solicita a permissão para acessar o armazenamento
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Se a permissão não foi concedida, solicite-a
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }

        // Ação do botão para selecionar o PDF
        btnSelecionarPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirSeletorPdf();
            }
        });

        // Ação do botão para salvar o certificado
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarCertificado();
            }
        });
    }

    // Abre o seletor de PDF
    private void abrirSeletorPdf() {
        // Intent para abrir o seletor de arquivos de PDF
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, PICK_PDF_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null) {
            // Obtém o URI do arquivo PDF selecionado
            pdfUri = data.getData();
            Toast.makeText(this, "PDF selecionado!", Toast.LENGTH_SHORT).show();
        }
    }

    // Salva o certificado com as informações e o PDF
    private void salvarCertificado() {
        String nome = editNome.getText().toString();
        String data = editData.getText().toString();
        String organizacao = editOrganizacao.getText().toString();

        // Verifica se todos os campos foram preenchidos e se o PDF foi selecionado
        if (nome.isEmpty() || data.isEmpty() || organizacao.isEmpty() || pdfUri == null) {
            Toast.makeText(this, "Preencha todos os campos e selecione um PDF", Toast.LENGTH_SHORT).show();
        } else {
            // Lógica para salvar o certificado, incluindo o PDF (pdfUri)
            // Aqui você pode, por exemplo, enviar para um servidor ou salvar localmente
            Toast.makeText(this, "Certificado salvo com sucesso!", Toast.LENGTH_SHORT).show();
            finish(); // Fecha a atividade e retorna para a anterior
        }
    }

    // Resposta do sistema sobre a solicitação de permissão
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissão concedida
                Toast.makeText(this, "Permissão de leitura de armazenamento concedida!", Toast.LENGTH_SHORT).show();
            } else {
                // Permissão negada
                Toast.makeText(this, "Permissão de leitura de armazenamento negada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
