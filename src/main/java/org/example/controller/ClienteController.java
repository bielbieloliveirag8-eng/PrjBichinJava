package org.example.controller;

import org.example.model.Cliente;
import org.example.repository.ClienteRepository;
import java.util.ArrayList;

public class ClienteController {

    private ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    public void adicionar(int id, String nome, String telefone) {
        Cliente cliente = new Cliente(id, nome, telefone);
        repository.adicionar(cliente);
    }

    public Cliente buscar(int id) {
        return repository.buscarPorId(id);
    }

    public ArrayList<Cliente> listar() {
        return repository.listar();
    }

    public boolean remover(int id) {
        return repository.remover(id);
    }
}