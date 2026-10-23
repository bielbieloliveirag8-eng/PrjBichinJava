package org.example.repository;

import org.example.model.Servico;

import java.util.ArrayList;
import java.util.List;

public class ServicoRepository {
    private List<Servico> servicos;

    public ServicoRepository() {
        this.servicos = new ArrayList<>();
    }

    public Servico buscarPorId(int id){
        for (int i = 0; i < servicos.size(); i++) {
            if (servicos.get(i) != null) {
                if (servicos.get(i).getId() == id) {
                    return servicos.get(i);
                }
            }
        }

        return null;
    }

    public List<Servico> listar(){
        return servicos;
    }

    public void adicionar(Servico servico){
        servicos.add(servico);
    }

    private boolean remover(int id){
        Servico servico = buscarPorId(id);

        if (servico != null) {
            servicos.remove(servico);
            return true;
        }

        return false;
    }

    public void carregarDados(String arquivo){

    }
}