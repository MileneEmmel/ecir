package com.example.ecir.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ecir.Embarque;
import com.example.ecir.DadosPessoais;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "eCIR.db";
    private static final int DATABASE_VERSION = 1;

    // Tabela Dados Pessoais
    private static final String TABLE_DADOS_PESSOAIS = "dados_pessoais";
    private static final String COLUMN_NUM_INSCRICAO = "numInscricao";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_NATURALIDADE = "naturalidade";
    private static final String COLUMN_NACIONALIDADE = "nacionalidade";
    private static final String COLUMN_DATA_NASCIMENTO = "dataNascimento";
    private static final String COLUMN_OM_EMISSAO = "omEmissao";

    // Tabela Embarques
    private static final String TABLE_EMBARQUES = "embarques";
    private static final String COLUMN_ID = "id";
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
        // Criação da tabela Dados Pessoais
        String CREATE_TABLE_DADOS_PESSOAIS = "CREATE TABLE " + TABLE_DADOS_PESSOAIS + "(" +
                COLUMN_NUM_INSCRICAO + " TEXT PRIMARY KEY, " +
                COLUMN_NOME + " TEXT, " +
                COLUMN_NATURALIDADE + " TEXT, " +
                COLUMN_NACIONALIDADE + " TEXT, " +
                COLUMN_DATA_NASCIMENTO + " TEXT, " +
                COLUMN_OM_EMISSAO + " TEXT)";
        db.execSQL(CREATE_TABLE_DADOS_PESSOAIS);

        // Criação da tabela Embarques
        String CREATE_TABLE_EMBARQUES = "CREATE TABLE " + TABLE_EMBARQUES + "(" +
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
        db.execSQL(CREATE_TABLE_EMBARQUES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Exclui tabelas antigas e recria
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DADOS_PESSOAIS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMBARQUES);
        onCreate(db);
    }


    // Métodos para Dados Pessoais
    public boolean saveDadosPessoais(DadosPessoais dados) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NUM_INSCRICAO, dados.getNumInscricao());
        values.put(COLUMN_NOME, dados.getNome());
        values.put(COLUMN_NATURALIDADE, dados.getNaturalidade());
        values.put(COLUMN_NACIONALIDADE, dados.getNacionalidade());
        values.put(COLUMN_DATA_NASCIMENTO, dados.getDataNascimento());
        values.put(COLUMN_OM_EMISSAO, dados.getOmEmissao());

        long result = db.insertWithOnConflict(TABLE_DADOS_PESSOAIS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
        return result != -1;
    }

    /**
     * Retorna os dados pessoais salvos no banco ou null se não existirem.
     */
    public DadosPessoais getDadosPessoais() {
        SQLiteDatabase db = this.getReadableDatabase();
        DadosPessoais dados = null;

        Cursor cursor = db.query(
                TABLE_DADOS_PESSOAIS,
                null,
                null, // Nenhuma cláusula WHERE
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            dados = new DadosPessoais(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NUM_INSCRICAO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NATURALIDADE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NACIONALIDADE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA_NASCIMENTO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OM_EMISSAO))
            );
            cursor.close();
        }
        db.close();
        return dados;
    }


    // Métodos para Embarques
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
        return result != -1;
    }

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

    public boolean deleteEmbarqueById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_EMBARQUES, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();

        if (rowsAffected > 0) {
            // Registro excluído com sucesso
            System.out.println("Embarque com ID " + id + " foi excluído com sucesso.");
        } else {
            // Nenhum registro encontrado com o ID fornecido
            System.out.println("Nenhum embarque encontrado com o ID " + id + ".");
        }
        return false;
    }

    public Embarque getEmbarqueById(int embarqueId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Embarque embarque = null;

        // Consulta para buscar o embarque pelo ID
        Cursor cursor = db.query(
                TABLE_EMBARQUES, // Tabela
                null, // Todas as colunas
                COLUMN_ID + " = ?", // Condição WHERE
                new String[]{String.valueOf(embarqueId)}, // Argumento para o WHERE
                null, null, null); // Sem agrupamento, ordenação ou limite

        // Verifica se o cursor contém dados
        if (cursor != null && cursor.moveToFirst()) {
            // Criação do objeto Embarque com os dados do cursor
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
        }

        // Fecha o cursor e o banco de dados
        if (cursor != null) cursor.close();
        db.close();

        return embarque;
    }
}
