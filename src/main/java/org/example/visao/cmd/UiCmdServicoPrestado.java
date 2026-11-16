package org.example.visao.cmd;

import org.example.controller.ClienteController;
import org.example.controller.ServicoController;
import org.example.controller.ServicoPrestadoController;
import org.example.model.Pet;
import org.example.model.ServicoPrestado;
import org.example.util.Leitura;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UiCmdServicoPrestado {
    private ClienteController controllerCliente;
    private ServicoPrestadoController controller;
    private ServicoController controllerServico;
    private Scanner teclado;

    public UiCmdServicoPrestado(ServicoPrestadoController controller, ClienteController controllerCliente, ServicoController controllerServico) {
        this.teclado = new Scanner(System.in);
        this.controllerCliente = controllerCliente;
        this.controller = controller;
        this.controllerServico = controllerServico;
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n=======================================");
            System.out.println("      GESTÃO DE SERVICOS PRESTADOS");
            System.out.println("=======================================");
            System.out.println("1 - Adicionar Servico Prestado");
            System.out.println("2 - Buscar Servico Prestado");
            System.out.println("3 - Apagar Servico");
            System.out.println("4 - Listar Servicos Prestados");
            System.out.println("5 - Listar Animais do Cliente");
            System.out.println("6 - Listar Servico com Maior Valor do Cliente");
            System.out.println("7 - Listar Servicos do Cliente");
            System.out.println("8 - Listar Servicos Prestado por Periodo");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.println("=======================================");

            opcao = Leitura.lerInteiro(teclado,"Escolha uma opção:",true);

            switch (opcao) {
                case 1:
                    adicionar();
                    break;

                case 2:
                    buscar();
                    break;

                case 3:
                    apagar();
                    break;

                case 4:
                    listar();
                    break;

                case 5:
                    animaisDoDono();
                    break;

                case 6:
                    maiorValor();
                    break;

                case 7:
                    servicosDoCliente();
                    break;

                case 8:
                    servicosPorPeriodo();
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void servicosPorPeriodo() {
        System.out.println("\n--- Listar Servicos Por Periodo ---");

        LocalDate dataInicial = Leitura.lerData(teclado, "Data inicial: ");

        LocalDate dataFinal = Leitura.lerData(teclado, "Data final: ");

        List<ServicoPrestado> servicosPrestados = controller.buscarPorPeriodo(dataInicial, dataFinal);

        if (servicosPrestados.isEmpty()){
            System.out.println("Não possui serviços prestados nesse periodo.");
            return;
        }

        for (ServicoPrestado servicoPrestado : servicosPrestados) {
            System.out.println(servicoPrestado);
        }

    }

    private void adicionar() {
        System.out.println("\n--- Adicionar Servico Prestado ---");

        int idPrestado = Leitura.lerInteiro(teclado,"Digite o ID do serviço prestado: ",true);

        int idServico = Leitura.lerInteiro(teclado,"Digite o ID do serviço: ",true);

        LocalDate data = Leitura.lerData(teclado, "Data: ");

        int idPet = Leitura.lerInteiro(teclado,"Digite o ID do pet:",true);

        System.out.println(controllerServico.buscar(idServico));

        double valor = Leitura.lerDouble(teclado, "Valor cobrado: ", true);
        try {
            controller.adicionar(idPrestado, idPet, idServico, valor, data);
            System.out.println("Servico Prestado adicionado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

        }
    }

    private void animaisDoDono() {
        System.out.println("\n--- Listar Animais do Dono ---");

        String nome = Leitura.lerString(teclado,"Digite o nome do dono:");

        List<Pet> pets = controller.buscarBichosPorNomeDono(nome);

        if (pets.isEmpty()){
            System.out.println("Dono não encontrado ou não possui animais.");
            return;
        }

        for (Pet pet : pets) {
            System.out.println(pet);
        }
    }

    private void apagar() {
        System.out.println("\n--- Apagar Servico Prestado ---");

        int id = Leitura.lerInteiro(teclado,"Digite o ID do Servico Prestado a remover:",true);

        if (controller.removerServicoPrestado(id)) {
            System.out.println("Servico Prestado removido com sucesso!");
        } else {
            System.out.println("Servico Prestado não encontrado.");
        }
    }

    private void maiorValor() {
        System.out.println("\n--- Listar Servico com Maior Valor do Cliente ---");

        int id = Leitura.lerInteiro(teclado,"Digite o ID do cliente:",true);

        double valor = controller.maiorValorPorCliente(controllerCliente.buscar(id));

        if (valor == 0){
            System.out.println("O cliente não possui serviços prestados.");
            return;
        }

        System.out.println("Maior Valor: R$" + valor);
    }

    private void listar() {
        System.out.println("\n--- Listar Servicos Prestados ---");

        List<ServicoPrestado> servicos = controller.listar();

        if (servicos.isEmpty()){
            System.out.println("Não possui serviços prestados.");
            return;
        }

        for (ServicoPrestado servicoPrestado : controller.listar()) {
            System.out.println(servicoPrestado);
        }
    }

    private void servicosDoCliente() {
        System.out.println("\n--- Listar Servicos do Cliente ---");

        int id = Leitura.lerInteiro(teclado,"Digite o ID do cliente:",true);

        List<ServicoPrestado> servicos = controller.buscarPorCliente(controllerCliente.buscar(id));

        if (servicos.isEmpty()) {
            System.out.println("Este cliente ainda não possui serviços prestados.");
            return;
        }

        for (ServicoPrestado servicoPrestado : servicos) {
            System.out.println(servicoPrestado);
        }
    }

    private void buscar() {
        System.out.println("\n--- Buscar Servico Prestado ---");

        int id = Leitura.lerInteiro(teclado,"Digite o ID do serviço prestado:",true);

        ServicoPrestado servicoPrestado = controller.buscar(id);

        if (servicoPrestado != null) {
            System.out.println(servicoPrestado);
        } else {
            System.out.println("Servico Prestado não encontrado.");
        }
    }
}
