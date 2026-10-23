package org.example.controller;

import org.example.model.Cliente;
import org.example.model.Pet;
import org.example.model.Servico;
import org.example.model.ServicoPrestado;
import org.example.repository.PetRepository;
import org.example.repository.ServicoPrestadoRepository;
import org.example.repository.ServicoRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicoPrestadoController {
    private PetRepository repositoryPet;
    private ServicoRepository repositoryServico;
    private ServicoPrestadoRepository repository;

    public ServicoPrestadoController(PetRepository repositoryPet, ServicoRepository repositoryServico,
                                     ServicoPrestadoRepository repository) {
        this.repositoryPet = repositoryPet;
        this.repositoryServico = repositoryServico;
        this.repository = repository;
    }

    public double maiorValorPorCliente(Cliente cliente){
        return repository.maiorValorPorCliente(cliente);
    }

    public boolean removerServicoPrestado(int id){
        return repository.remover(id);
    }

    public ServicoPrestado buscar(int id){
        return repository.buscarPorId(id);
    }

    public List<Pet> buscarBichosPorNomeDono(String nomeDono){
        return repository.buscarBichosPorNomeDono(nomeDono);
    }

    public List<ServicoPrestado> buscarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal){
        return repository.buscarPorPeriodo(dataInicial, dataFinal);
    }

    public void adicionar(int id, int idPet, int idServico, double valorCobrado, LocalDate data){
        Pet pet = repositoryPet.buscarPorId(idPet);
        Servico servico = repositoryServico.buscarPorId(idServico);

        ServicoPrestado servicoPrestado = new ServicoPrestado(id, pet, servico, valorCobrado, data);

        repository.adicionar(servicoPrestado);
    }

    public List<ServicoPrestado> buscarPorCliente(Cliente cliente){
        return repository.buscarPorCliente(cliente);
    }

    public List<ServicoPrestado> listar(){
        return repository.listar();
    }
}
