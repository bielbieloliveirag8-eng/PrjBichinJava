package org.example.repository;

import org.example.model.Cliente;
import org.example.model.Pet;
import org.example.model.Servico;
import org.example.model.ServicoPrestado;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServicoPrestadoRepository {
    private List<ServicoPrestado> servicosPrestados;

    public ServicoPrestadoRepository() {
        this.servicosPrestados = new ArrayList<>();
    }

    public List<ServicoPrestado> listar() {
        return servicosPrestados;
    }

    public void carregarDados(String arquivo, PetRepository petRepository, ServicoRepository servicoRepository) {
        try (BufferedReader leitor = Files.newBufferedReader(
                Path.of(arquivo))) {

            String linha = leitor.readLine();
            //Verifica se a linha é o cabeçalho, se não pode ler as próximas linhas que contém os dados
            if (linha != null && linha.contains("id;idServico;data;valorCobrado;idPet")) {
                linha = leitor.readLine();
            }

            while (linha != null) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                int idServico = Integer.parseInt(dados[1]);
                LocalDate data = LocalDate.parse(dados[2], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                double valorCobrado = Double.parseDouble(dados[3]);
                int idPet = Integer.parseInt(dados[4]);

                Pet pet = petRepository.buscarPorId(idPet);
                Servico servico = servicoRepository.buscarPorId(idServico);

                ServicoPrestado servicoPrestado = new ServicoPrestado(id, pet, servico, valorCobrado, data);
                adicionar(servicoPrestado);

                linha = leitor.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao carregar os serviçosPrestados.", e
            );
        }
    }

    public List<ServicoPrestado> buscarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        List<ServicoPrestado> listagem = new ArrayList<>();

        for (int i = 0; i < servicosPrestados.size(); i++) {
            if (servicosPrestados.get(i) != null) {
                if (!servicosPrestados.get(i).getData().isBefore(dataInicial)
                        && !servicosPrestados.get(i).getData().isAfter(dataFinal)) {
                    listagem.add(servicosPrestados.get(i));
                }
            }
        }

        return listagem;
    }

    public ServicoPrestado buscarPorId(int id) {
        for (int i = 0; i < servicosPrestados.size(); i++) {
            if (servicosPrestados.get(i) != null) {
                if (servicosPrestados.get(i).getId() == id) {
                    return servicosPrestados.get(i);
                }
            }
        }

        return null;
    }

    public boolean remover(int id) {
        ServicoPrestado servicoPrestado = buscarPorId(id);

        if (servicoPrestado != null) {
            servicosPrestados.remove(servicoPrestado);
            return true;
        }

        return false;
    }

    public List<Pet> buscarBichosPorNomeDono(String nomeDono) {
        List<Pet> listagem = new ArrayList<>();

        for (int i = 0; i < servicosPrestados.size(); i++) {
            if (servicosPrestados.get(i) != null) {
                if (servicosPrestados.get(i).getPet().getCliente().getNome().equals(nomeDono)) {
                    listagem.add(servicosPrestados.get(i).getPet());
                }
            }
        }

        return listagem;
    }

    public List<ServicoPrestado> buscarPorCliente(Cliente cliente) {
        List<ServicoPrestado> listagem = new ArrayList<>();

        for (int i = 0; i < servicosPrestados.size(); i++) {
            if (servicosPrestados.get(i) != null) {
                if (servicosPrestados.get(i).getPet().getCliente().equals(cliente)) {
                    listagem.add(servicosPrestados.get(i));
                }
            }
        }

        return listagem;
    }

    public double maiorValorPorCliente(Cliente cliente) {
        double maiorValor = 0;

        List<ServicoPrestado> servicosCliente = buscarPorCliente(cliente);

        for (int i = 0; i < servicosCliente.size(); i++) {
            if (servicosPrestados.get(i).getValorCobrado() > maiorValor)
                maiorValor = servicosPrestados.get(i).getValorCobrado();
        }

        return maiorValor;
    }

    public void adicionar(ServicoPrestado servicoPrestado) {
        servicosPrestados.add(servicoPrestado);
    }
}
