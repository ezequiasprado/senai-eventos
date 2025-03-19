package com.evento.enums;

public enum Classificacao {
    LIVRE("Livre"),
    ACIMA_12_ANOS("Acima de 12 anos"),
    ACIMA_18_ANOS("Acima de 18 anos");

    private final String descricao;

    Classificacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
