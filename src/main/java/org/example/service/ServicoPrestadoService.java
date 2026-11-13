package org.example.service;

import org.example.model.ServicoPrestado;
import org.example.repository.ServicoPrestadoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicoPrestadoService {
    private ServicoPrestadoRepository repository;

    public ServicoPrestadoService(ServicoPrestadoRepository repository) {
        this.repository = repository;
    }

    public void adicionar(ServicoPrestado servicoPrestado) {
        List<String> erros = new ArrayList<>();

        if (servicoPrestado.getData().isBefore(LocalDate.now())) {
            erros.add("Não é permitido datas passadas!");
        }

        if (servicoPrestado.getValorCobrado() <= 0) {
            erros.add("Não é permitido valor negativo!");
        }

        if (!erros.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", erros));
        }

        repository.adicionar(servicoPrestado);
    }

    public List<ServicoPrestado> listar()
    {
        return repository.listar();
    }
}
