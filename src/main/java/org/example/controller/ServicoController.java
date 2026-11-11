package org.example.controller;

import org.example.model.Servico;
import org.example.model.ServicoExterno;
import org.example.model.ServicoInterno;
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

    public void adicionar(int id, String descricao, double valor, String crmv, double valorTerceirizado){
        Servico servico;

        if (crmv == null || crmv.isBlank())
            servico = new ServicoExterno(id, descricao, valor, valorTerceirizado);
        else
            servico = new ServicoInterno(id, descricao, valor, crmv);

        repository.adicionar(servico);
    }

    public List<Servico> listar(){
        return repository.listar();
    }

    public Servico buscar(int id){
        return repository.buscarPorId(id);
    }
}
