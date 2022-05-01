package br.dev.felipeborges.batch.produtor.produtorbatch.model;

import java.math.BigDecimal;

public class Transacao {

    private final Integer id;
    private final Integer numero;
    private final BigDecimal valor;

    public Transacao(Integer id, Integer numero, BigDecimal valor) {
        this.id = id;
        this.numero = numero;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
    }

    public BigDecimal getValor() {
        return valor;
    }

}
