package org.example.repository;

import org.example.model.Cliente;
import org.example.model.Servico;

import java.util.ArrayList;

public class ClienteRepository {
    private  ArrayList<Cliente> clientes;

    public ClienteRepository() {
        this.clientes = new ArrayList<>();
    }

    public void adicionar(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public ArrayList<Cliente> listar() {
        return this.clientes;
    }

    public  Cliente buscarPorId(int id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                return clientes.get(i);
            }

        }
        return null;
    }

    public boolean remover(int id) {

        Cliente cliente = buscarPorId(id);

        if (cliente != null) {
            clientes.remove(cliente);
            return true;
        }

        return false;
    }

    public void carregarDados(String Arquivo) {
        try (java.io.BufferedReader leitor = java.nio.file.Files.newBufferedReader(
                java.nio.file.Path.of(Arquivo))) {

            String linha;
            boolean primeiraLinha = true;

            while ((linha = leitor.readLine()) != null) {

                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] dados = linha.split(";");

                if (dados.length == 3) {
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    String telefone = dados[2];

                    Cliente cliente = new Cliente(id, nome, telefone);
                    this.adicionar(cliente);
                }
            }

        } catch (java.io.IOException e) {
            throw new RuntimeException(
                    "Erro ao ler o ficheiro de clientes: " + Arquivo, e
            );
        } catch (NumberFormatException e) {
            throw new RuntimeException(
                    "Erro no formato do ID no ficheiro de clientes.", e
            );
        }
    }
}
