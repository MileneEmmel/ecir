package com.example.ecir;

import java.util.ArrayList;
import java.util.List;

public class Embarque {
    private String numInscricao;
    private String nomeEmbarcacao;
    private String numeroInscricao;
    private String arqueacao;
    private String localEmbarque;
    private String dataEmbarque;
    private String categoria;
    private String funcao;
    private String tipoNavegacao;

    // Lista estática de embarques
    private static List<Embarque> embarques = new ArrayList<>();

    // Construtor
    public Embarque(String numInscricao, String nomeEmbarcacao, String numeroInscricao,
                    String arqueacao, String localEmbarque, String dataEmbarque,
                    String categoria, String funcao, String tipoNavegacao) {
        this.numInscricao = numInscricao;
        this.nomeEmbarcacao = nomeEmbarcacao;
        this.numeroInscricao = numeroInscricao;
        this.arqueacao = arqueacao;
        this.localEmbarque = localEmbarque;
        this.dataEmbarque = dataEmbarque;
        this.categoria = categoria;
        this.funcao = funcao;
        this.tipoNavegacao = tipoNavegacao;
    }

    // Getters para cada atributo
    public String getNumInscricao() { return numInscricao; }
    public String getNomeEmbarcacao() { return nomeEmbarcacao; }
    public String getNumeroInscricao() { return numeroInscricao; }
    public String getArqueacao() { return arqueacao; }
    public String getLocalEmbarque() { return localEmbarque; }
    public String getDataEmbarque() { return dataEmbarque; }
    public String getCategoria() { return categoria; }
    public String getFuncao() { return funcao; }
    public String getTipoNavegacao() { return tipoNavegacao; }

    // Método estático para obter a lista de embarques
    public static List<Embarque> getEmbarques() {
        return embarques;
    }

    // Método estático para adicionar um novo embarque
    public static void addEmbarque(Embarque embarque) {
        embarques.add(embarque);
    }

    // Método estático para inicializar a lista com embarques fictícios, se necessário
    public static void initializeEmbarques() {
        if (embarques.isEmpty()) {
            embarques.add(new Embarque("001", "Embarcação A", "123", "300", "Porto A", "01/01/2024", "Categoria A", "Função A", "Tipo 1"));
            embarques.add(new Embarque("002", "Embarcação B", "124", "400", "Porto B", "02/02/2024", "Categoria B", "Função B", "Tipo 2"));
        }
    }
}