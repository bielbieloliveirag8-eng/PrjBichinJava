package org.example.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Pet {
    private int id;
    private String nome;
    private String especie;
    private String raca;
    private Cliente cliente;
    private LocalDate nascimento;

    public Pet() {

    }

    public Pet(int id, String nome, String especie, String raca, LocalDate nascimento, Cliente cliente) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.nascimento = nascimento;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public int getIdade() {
        if (nascimento == null) {
            return 0;
        }
        return Period.between(nascimento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", especie='" + especie + '\'' +
                ", raca='" + raca + '\'' +
                ", cliente=" + cliente +
                ", nascimento=" + nascimento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id && Objects.equals(nome, pet.nome) && Objects.equals(especie, pet.especie) && Objects.equals(raca, pet.raca) && Objects.equals(cliente, pet.cliente) && Objects.equals(nascimento, pet.nascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, especie, raca, cliente, nascimento);
    }
}
