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

    public void adicionar(ServicoPrestado servicoPrestado)
    {
        if (servicoPrestado.getData()!= null
        && servicoPrestado.getData().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Não é permitido cadastrar um serviço prestado com data no passado!");
        }

        if (servicoPrestado.getValorCobrado()<0)
        {
            throw new IllegalArgumentException("Não é permitido valor negativo!");
        }

        repository.adicionar(servicoPrestado);
    }

    public List<ServicoPrestado> listar()
    {
        return repository.listar();
    }
}
