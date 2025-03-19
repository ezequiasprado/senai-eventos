package com.evento.dtos;

import com.evento.enums.Classificacao;
import com.evento.models.Endereco;
import com.evento.models.Produtora;
import jakarta.persistence.*;

import java.util.Date;

public class EventoDTO {
    private Long id;
    private Date data;
    private String descricao;
    private Classificacao classificacao;
    private String horaInicio;
    private String horaFim;
    private String horaAbertura;
    private EnderecoDTO endereco;
    private ProdutoraDTO produtora;

    public EventoDTO() {}

    public EventoDTO(Long id, Date data, String descricao, Classificacao classificacao, String horaInicio, String horaFim, String horaAbertura, EnderecoDTO endereco, ProdutoraDTO produtora) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.classificacao = classificacao;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.horaAbertura = horaAbertura;
        this.endereco = endereco;
        this.produtora = produtora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(String horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public ProdutoraDTO getProdutora() {
        return produtora;
    }

    public void setProdutora(ProdutoraDTO produtora) {
        this.produtora = produtora;
    }
}
