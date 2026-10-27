package org.example.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class ServicoPrestado {
    private int id;
    private Servico servico;
    private LocalDate data;
    private double valorCobrado;
    private Pet pet;

    public ServicoPrestado() {}

    public ServicoPrestado(int id, Pet pet, Servico servico, double valorCobrado, LocalDate data) {
        this.id = id;
        this.pet = pet;
        this.servico = servico;
        this.valorCobrado = valorCobrado;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public int getDiasDaExecucaoDoServico() {
        if (this.data == null) {
            return 0;
        }
        return Period.between(this.data, LocalDate.now()).getDays();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ServicoPrestado that = (ServicoPrestado) o;
        return id == that.id && Double.compare(valorCobrado, that.valorCobrado) == 0 && Objects.equals(servico, that.servico) && Objects.equals(data, that.data) && Objects.equals(pet, that.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, servico, data, valorCobrado, pet);
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Serviço: " + servico.getDescricao() + "\n" +
                "Data: " + data + "\n" +
                "Valor cobrado: R$ " + valorCobrado + "\n" +
                "Pet: " + pet ;
    }

}
