package org.example.controller;

import org.example.model.Cliente;
import org.example.model.Pet;
import org.example.repository.ClienteRepository;
import org.example.repository.PetRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class PetController {

    private PetRepository repository;
    private ClienteRepository repositoryCliente;


    public PetController(PetRepository repository, ClienteRepository repositoryCliente) {
        this.repository = repository;
        this.repositoryCliente = repositoryCliente;
    }

    public void adicionar(int id, String nome, String especie, String raca, LocalDate nascimento, int idCliente) {

        Cliente cliente = repositoryCliente.buscarPorId(idCliente);

        if (cliente != null) {
            Pet pet = new Pet(id, nome, especie, raca, nascimento, cliente);
            repository.adicionar(pet);
        } else {
            System.out.println("Erro: Cliente não encontrado. Registe o cliente primeiro!");
        }
    }

    public Pet buscar(int id) {
        return repository.buscarPorId(id);
    }

    public ArrayList<Pet> listar() {
        return repository.listar();
    }

    public boolean remover(int id) {
        return repository.remover(id);
    }
}