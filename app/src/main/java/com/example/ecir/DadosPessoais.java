package com.example.ecir;

public class DadosPessoais {

    private String numInscricao;
    private String nome;
    private String naturalidade;
    private String nacionalidade;
    private String dataNascimento;
    private String omEmissao;

    public DadosPessoais(String numInscricao, String nome, String naturalidade, String nacionalidade, String dataNascimento, String omEmissao) {
        this.numInscricao = numInscricao;
        this.nome = nome;
        this.naturalidade = naturalidade;
        this.nacionalidade = nacionalidade;
        this.dataNascimento = dataNascimento;
        this.omEmissao = omEmissao;
    }

    // Getters e setters
    public String getNumInscricao() {
        return numInscricao;
    }

    public void setNumInscricao(String numInscricao) {
        this.numInscricao = numInscricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getOmEmissao() {
        return omEmissao;
    }

    public void setOmEmissao(String omEmissao) {
        this.omEmissao = omEmissao;
    }
}
