package org.example.model;

import java.util.Objects;

public class Servico {
    private String descricao;
    private int id;
    private double valor;

    public Servico() {}

    public Servico(int id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "descricao='" + descricao + '\'' +
                ", id=" + id +
                ", valor=" + valor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return id == servico.id && Double.compare(valor, servico.valor) == 0 && Objects.equals(descricao, servico.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, id, valor);
    }
}
