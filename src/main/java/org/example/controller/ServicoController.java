package org.example.controller;

import org.example.model.Servico;
import org.example.repository.ServicoRepository;

import java.util.List;

public class ServicoController {
    private ServicoRepository repository;

    public ServicoController(ServicoRepository repository) {
        this.repository = repository;
    }

    public boolean remover(int id){
        return repository.remover(id);
    }

    public void adicionar(int id, String descricao, double valor){
        Servico servico = new Servico(id, descricao, valor);
        repository.adicionar(servico);
    }

    public List<Servico> listar(){
        return repository.listar();
    }

    public Servico buscar(int id){
        return repository.buscarPorId(id);
    }
}
