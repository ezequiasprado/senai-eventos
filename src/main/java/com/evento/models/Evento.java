package com.evento.models;

import com.evento.enums.Classificacao;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date data;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Classificacao classificacao;

    @Column(name = "hora_inicio")
    private String horaInicio;

    @Column(name = "hora_fim")
    private String horaFim;

    @Column(name = "hora_abertura")
    private String horaAbertura;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "produtora_id")
    private Produtora produtora;

    public Evento(){}

    public Evento(Long id, Date data, String descricao, Classificacao classificacao, String horaInicio, String horaFim,
                  String horaAbertura, Endereco endereco, Produtora produtora) {
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Produtora getProdutora() {
        return produtora;
    }

    public void setProdutora(Produtora produtora) {
        this.produtora = produtora;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(id, evento.id) && Objects.equals(data, evento.data) && Objects.equals(descricao, evento.descricao) && classificacao == evento.classificacao && Objects.equals(horaInicio, evento.horaInicio) && Objects.equals(horaFim, evento.horaFim) && Objects.equals(horaAbertura, evento.horaAbertura) && Objects.equals(endereco, evento.endereco) && Objects.equals(produtora, evento.produtora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, descricao, classificacao, horaInicio, horaFim, horaAbertura, endereco, produtora);
    }
}
