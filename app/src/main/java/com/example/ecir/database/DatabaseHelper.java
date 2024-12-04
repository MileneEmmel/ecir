package com.example.ecir.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ecir.Embarque;
import com.example.ecir.DadosPessoais;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "eCIR.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DatabaseHelper";

    // Tabela Users
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";

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

    // Tabela de certificados
    private static final String TABLE_CERTIFICADOS = "certificados";
    private static final String COLUMN_ID_CERTIFICADO = "id";
    private static final String COLUMN_NOME_CERTIFICADO = "nome";
    private static final String COLUMN_DATA = "data";
    private static final String COLUMN_ORGANIZACAO = "organizacao";
    private static final String COLUMN_PDF_PATH = "pdf_path";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação da tabela de usuários
        String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_EMAIL + " TEXT)";
        db.execSQL(CREATE_TABLE_USERS);

        // Criação da tabela Dados Pessoais
        String CREATE_TABLE_DADOS_PESSOAIS = "CREATE TABLE " + TABLE_DADOS_PESSOAIS + " (" +
                "registro_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NUM_INSCRICAO + " TEXT, " +
                COLUMN_NOME + " TEXT, " +
                COLUMN_NATURALIDADE + " TEXT, " +
                COLUMN_NACIONALIDADE + " TEXT, " +
                COLUMN_DATA_NASCIMENTO + " TEXT, " +
                COLUMN_OM_EMISSAO + " TEXT)";
        db.execSQL(CREATE_TABLE_DADOS_PESSOAIS);

        // Criação da tabela de certificados
        String CREATE_TABLE_CERTIFICADOS = "CREATE TABLE " + TABLE_CERTIFICADOS + " (" +
                COLUMN_ID_CERTIFICADO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME_CERTIFICADO + " TEXT, " +
                COLUMN_DATA + " TEXT, " +
                COLUMN_ORGANIZACAO + " TEXT, " +
                COLUMN_PDF_PATH + " TEXT)";
        db.execSQL(CREATE_TABLE_CERTIFICADOS);

        // Criação da tabela Embarques
        String CREATE_TABLE_EMBARQUES = "CREATE TABLE " + TABLE_EMBARQUES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
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
        if (oldVersion < DATABASE_VERSION) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DADOS_PESSOAIS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMBARQUES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CERTIFICADOS);
            onCreate(db);
        }
    }

    // Método para inserir um usuário
    public boolean insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_EMAIL, user.getEmail());

        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    // Classe User para representar os dados dos usuários
    public static class User {
        private final String username;
        private final String password;
        private final String email;

        public User(String username, String email, String password) {
            this.username = username;
            this.password = password;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }
    }

    public boolean saveOrUpdateDadosPessoais(DadosPessoais dados) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NUM_INSCRICAO, dados.getNumInscricao());
        values.put(COLUMN_NOME, dados.getNome());
        values.put(COLUMN_NATURALIDADE, dados.getNaturalidade());
        values.put(COLUMN_NACIONALIDADE, dados.getNacionalidade());
        values.put(COLUMN_DATA_NASCIMENTO, dados.getDataNascimento());
        values.put(COLUMN_OM_EMISSAO, dados.getOmEmissao());

        Cursor cursor = db.query(TABLE_DADOS_PESSOAIS, null, COLUMN_NUM_INSCRICAO + " = ?", new String[]{dados.getNumInscricao()}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            long result = db.update(TABLE_DADOS_PESSOAIS, values, COLUMN_NUM_INSCRICAO + " = ?", new String[]{dados.getNumInscricao()});
            cursor.close();
            return result != -1;
        } else {
            if (cursor != null) cursor.close();
            long result = db.insert(TABLE_DADOS_PESSOAIS, null, values);
            return result != -1;
        }
    }

    public DadosPessoais getDadosPessoais() {
        SQLiteDatabase db = this.getReadableDatabase();
        DadosPessoais dados = null;

        // Consulta para pegar o último registro
        String query = "SELECT * FROM " + TABLE_DADOS_PESSOAIS +
                " ORDER BY " + COLUMN_NUM_INSCRICAO + " DESC LIMIT 1";

        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                dados = new DadosPessoais(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NUM_INSCRICAO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NATURALIDADE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NACIONALIDADE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA_NASCIMENTO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OM_EMISSAO))
                );
            }
        } catch (Exception e) {
            Log.e("DatabaseError", "Erro ao recuperar dados pessoais", e);
        }

        return dados; // Retorna os dados recuperados ou null se não existirem
    }


    // Métodos para Embarques
    public boolean addEmbarque(Embarque embarque) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME_EMBARCACAO, embarque.getNomeEmbarcacao());
        values.put(COLUMN_NUMERO_INSCRICAO, embarque.getNumeroInscricao());
        values.put(COLUMN_ARQUEACAO, embarque.getArqueacao());
        values.put(COLUMN_LOCAL_EMBARQUE, embarque.getLocalEmbarque());
        values.put(COLUMN_DATA_EMBARQUE, embarque.getDataEmbarque());
        values.put(COLUMN_CATEGORIA, embarque.getCategoria());
        values.put(COLUMN_FUNCAO, embarque.getFuncao());
        values.put(COLUMN_TIPO_NAVEGACAO, embarque.getTipoNavegacao());

        long result = db.insert(TABLE_EMBARQUES, null, values);
        return result != -1;
    }

    // Obter todos os embarques
    public List<Embarque> getAllEmbarques() {
        List<Embarque> embarques = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        try (Cursor cursor = db.query(TABLE_EMBARQUES, null, null, null, null, null, COLUMN_ID + " DESC")) {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Embarque embarque = new Embarque(
                            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
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
        } catch (Exception e) {
            Log.e(TAG, "Erro ao buscar embarques", e);
        }
        db.close(); // Fecha o banco de dados
        return embarques;
    }


    public boolean deleteEmbarqueById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_EMBARQUES, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected > 0; // Retorna verdadeiro se pelo menos uma linha foi afetada
    }

}

