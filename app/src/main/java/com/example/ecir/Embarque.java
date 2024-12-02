package com.example.ecir;

public class Embarque {

    private final int id; // ID do embarque
    private final String nomeEmbarcacao;
    private final String numeroInscricao;
    private final String arqueacao;
    private final String localEmbarque;
    private final String dataEmbarque;
    private final String categoria;
    private final String funcao;
    private final String tipoNavegacao;

    // Construtor
    public Embarque(int id, String nomeEmbarcacao, String numeroInscricao, String arqueacao, String localEmbarque, String dataEmbarque, String categoria, String funcao, String tipoNavegacao) {
        this.id = id;
        this.nomeEmbarcacao = nomeEmbarcacao;
        this.numeroInscricao = numeroInscricao;
        this.arqueacao = arqueacao;
        this.localEmbarque = localEmbarque;
        this.dataEmbarque = dataEmbarque;
        this.categoria = categoria;
        this.funcao = funcao;
        this.tipoNavegacao = tipoNavegacao;
    }

    // Getters
    public int getId() { return id; }
    public String getNomeEmbarcacao() { return nomeEmbarcacao; }
    public String getNumeroInscricao() { return numeroInscricao; }
    public String getArqueacao() { return arqueacao; }
    public String getLocalEmbarque() { return localEmbarque; }
    public String getDataEmbarque() { return dataEmbarque; }
    public String getCategoria() { return categoria; }
    public String getFuncao() { return funcao; }
    public String getTipoNavegacao() { return tipoNavegacao; }
}
