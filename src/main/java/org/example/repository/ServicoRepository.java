package org.example.repository;

import org.example.model.Servico;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ServicoRepository {
    private List<Servico> servicos;

    public ServicoRepository() {
        this.servicos = new ArrayList<>();
    }

    public Servico buscarPorId(int id) {
        for (int i = 0; i < servicos.size(); i++) {
            if (servicos.get(i) != null) {
                if (servicos.get(i).getId() == id) {
                    return servicos.get(i);
                }
            }
        }

        return null;
    }

    public List<Servico> listar() {
        return servicos;
    }

    public void adicionar(Servico servico) {
        servicos.add(servico);
    }

    public boolean remover(int id) {
        Servico servico = buscarPorId(id);

        if (servico != null) {
            servicos.remove(servico);
            return true;
        }

        return false;
    }

    public void carregarDados(String arquivo) {
        try (BufferedReader leitor = Files.newBufferedReader(
                Path.of(arquivo))) {

            String linha = leitor.readLine();
            //Verifica se a linha é o cabeçalho, se não pode ler as próximas linhas que contém os dados
            if (linha != null && linha.contains("id;descricao;valor")) {
                linha = leitor.readLine();
            }

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                String descricao = dados[1];
                double valor = Double.parseDouble(dados[2]);

                Servico servico = new Servico(id, descricao, valor);
                adicionar(servico);
            }

        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao carregar os serviços.", e
            );
        }
    }
}