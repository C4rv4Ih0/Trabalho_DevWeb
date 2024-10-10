package dw.futebol.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagamento")
public class Pagamento{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cod_pagamento;

    @Column(nullable = false)
    private int ano;

    @Column(nullable=false)
    private int mes;

    @Column(nullable=false)
    private double valor;

    @ManyToOne
    @JoinColumn(name = "cod_jogador", nullable = false)
    private Jogador jogador;

    public Pagamento(){
    }

    public Pagamento(int ano, int mes, double valor, Jogador jogador) {
        this.ano = ano;
        this.mes = mes;
        this.valor = valor;
        this.jogador = jogador;
    }

    public long getCod_pagamento() {
        return cod_pagamento;
    }

    public void setCod_pagamento(long cod_pagamento) {
        this.cod_pagamento = cod_pagamento;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }


}