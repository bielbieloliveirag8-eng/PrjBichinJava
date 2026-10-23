package org.example.repository;

import org.example.model.Cliente;
import org.example.model.Pet;
import org.example.model.Servico;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

public class PetRepository {

    private ArrayList<Pet> pets;

    public PetRepository() {
        this.pets = new ArrayList<>();
    }

    public ArrayList<Pet> listar() {
        return this.pets;
    }

    public void adicionar(Pet pet) {
        this.pets.add(pet);
    }

    public Pet buscarPorId(int id) {
        for (int i = 0; i < pets.size(); i++) {
            if (this.pets.get(i).getId() == id) {
                return pets.get(i);
            }
        }

        return null;
    }

    public boolean remover(int id) {

        Pet pet = buscarPorId(id);

        if (pet != null) {
            pets.remove(pet);
            return true;
        }

        return false;
    }

    public void carregarDados(String arquivo, ClienteRepository clienteRepository) {
        try (BufferedReader leitor = Files.newBufferedReader(
                Path.of(arquivo))) {

            String linha = leitor.readLine();


            if (linha != null && linha.contains("id;nome")) {
                linha = leitor.readLine();
            }

            java.time.format.DateTimeFormatter formatoData = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");

            while (linha != null) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String especie = dados[2];
                String raca = dados[3];
                int clienteId = Integer.parseInt(dados[4]);

                LocalDate nascimento = LocalDate.parse(dados[5], formatoData);

                Cliente cliente = clienteRepository.buscarPorId(clienteId);

                if (cliente != null) {
                    Pet pet = new Pet(id, nome, especie, raca, nascimento, cliente);
                    adicionar(pet);
                }

                linha = leitor.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao carregar os pets.", e
            );
        } catch (java.time.format.DateTimeParseException e) {
            throw new RuntimeException("Formato de data inválido no arquivo. Use dd/MM/yyyy.", e);
        }
    }
}
