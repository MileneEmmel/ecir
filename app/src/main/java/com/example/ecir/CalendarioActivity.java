package com.example.ecir;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecir.database.DatabaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarioActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView embarqueInfoTextView;
    private DatabaseHelper databaseHelper;
    private List<Embarque> embarques;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        calendarView = findViewById(R.id.calendarView);
        embarqueInfoTextView = findViewById(R.id.embarqueInfoTextView);

        databaseHelper = new DatabaseHelper(this);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Obter todos os embarques do banco
        embarques = databaseHelper.getAllEmbarques();

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = formatDate(year, month, dayOfMonth);

            // Filtrar embarques pela data selecionada
            StringBuilder embarqueInfo = new StringBuilder();
            for (Embarque embarque : embarques) {
                if (selectedDate.equals(embarque.getDataEmbarque())) {
                    embarqueInfo.append("Nome: ").append(embarque.getNomeEmbarcacao())
                            .append("\nLocal: ").append(embarque.getLocalEmbarque())
                            .append("\nCategoria: ").append(embarque.getCategoria())
                            .append("\n\n");
                }
            }

            if (embarqueInfo.length() > 0) {
                embarqueInfoTextView.setText(embarqueInfo.toString());
            } else {
                embarqueInfoTextView.setText("Nenhum embarque encontrado para: " + selectedDate);
            }
        });
    }

    private String formatDate(int year, int month, int dayOfMonth) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date;
        try {
            date = sdf.parse(dayOfMonth + "/" + (month + 1) + "/" + year);
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
}