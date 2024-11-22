package com.example.ecir.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ecir.Embarque;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "eCIR.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_EMBARQUES = "embarques";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NUM_INSCRICAO = "numInscricao";
    private static final String COLUMN_NOME_EMBARCACAO = "nomeEmbarcacao";
    private static final String COLUMN_NUMERO_INSCRICAO = "numeroInscricao";
    private static final String COLUMN_ARQUEACAO = "arqueacao";
    private static final String COLUMN_LOCAL_EMBARQUE = "localEmbarque";
    private static final String COLUMN_DATA_EMBARQUE = "dataEmbarque";
    private static final String COLUMN_CATEGORIA = "categoria";
    private static final String COLUMN_FUNCAO = "funcao";
    private static final String COLUMN_TIPO_NAVEGACAO = "tipoNavegacao";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_EMBARQUES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NUM_INSCRICAO + " TEXT, " +
                COLUMN_NOME_EMBARCACAO + " TEXT, " +
                COLUMN_NUMERO_INSCRICAO + " TEXT, " +
                COLUMN_ARQUEACAO + " TEXT, " +
                COLUMN_LOCAL_EMBARQUE + " TEXT, " +
                COLUMN_DATA_EMBARQUE + " TEXT, " +
                COLUMN_CATEGORIA + " TEXT, " +
                COLUMN_FUNCAO + " TEXT, " +
                COLUMN_TIPO_NAVEGACAO + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMBARQUES);
        onCreate(db);
    }

    // Adicionar um novo embarque
    public boolean addEmbarque(Embarque embarque) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NUM_INSCRICAO, embarque.getNumInscricao());
        values.put(COLUMN_NOME_EMBARCACAO, embarque.getNomeEmbarcacao());
        values.put(COLUMN_NUMERO_INSCRICAO, embarque.getNumeroInscricao());
        values.put(COLUMN_ARQUEACAO, embarque.getArqueacao());
        values.put(COLUMN_LOCAL_EMBARQUE, embarque.getLocalEmbarque());
        values.put(COLUMN_DATA_EMBARQUE, embarque.getDataEmbarque());
        values.put(COLUMN_CATEGORIA, embarque.getCategoria());
        values.put(COLUMN_FUNCAO, embarque.getFuncao());
        values.put(COLUMN_TIPO_NAVEGACAO, embarque.getTipoNavegacao());

        long result = db.insert(TABLE_EMBARQUES, null, values);
        db.close();
        return result != -1; // Retorna true se a inserção foi bem-sucedida
    }

    // Obter todos os embarques
    public List<Embarque> getAllEmbarques() {
        List<Embarque> embarques = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EMBARQUES, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Embarque embarque = new Embarque(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NUM_INSCRICAO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME_EMBARCACAO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NUMERO_INSCRICAO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ARQUEACAO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCAL_EMBARQUE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA_EMBARQUE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORIA)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FUNCAO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO_NAVEGACAO))
                );
                embarques.add(embarque);
            } while (cursor.moveToNext());
        }

        if (cursor != null) cursor.close();
        db.close();
        return embarques;
    }

    // Deletar um embarque por ID
    public boolean deleteEmbarqueById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_EMBARQUES, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected > 0; // Retorna true se algum registro foi deletado
    }

    // Buscar um embarque pelo ID
    public Embarque getEmbarqueById(int embarqueId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_EMBARQUES,
                null,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(embarqueId)},
                null,
                null,
                null
        );

        Embarque embarque = null;
        if (cursor != null && cursor.moveToFirst()) {
            embarque = new Embarque(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NUM_INSCRICAO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME_EMBARCACAO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NUMERO_INSCRICAO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ARQUEACAO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCAL_EMBARQUE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA_EMBARQUE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORIA)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FUNCAO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO_NAVEGACAO))
            );
            cursor.close();
        }
        db.close();
        return embarque;
    }

}
