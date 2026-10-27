package org.example.visao.cmd;

import org.example.controller.ClienteController;
import org.example.controller.ServicoController;
import org.example.controller.ServicoPrestadoController;
import org.example.model.Pet;
import org.example.model.ServicoPrestado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            System.out.print("Escolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

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

    private void servicosPorPeriodo(){
        System.out.println("\n--- Listar Servicos Por Periodo ---");

        System.out.printf("Data inicial: ");
        LocalDate dataInicial;
        try {
            dataInicial = LocalDate.parse(teclado.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Data inválida!");
            return;
        }

        System.out.printf("Data final: ");
        LocalDate dataFinal;
        try {
            dataFinal = LocalDate.parse(teclado.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Data inválida!");
            return;
        }

        for (ServicoPrestado servicoPrestado : controller.buscarPorPeriodo(dataInicial, dataFinal)) {
            System.out.println(servicoPrestado);
        }

    }

    private void adicionar(){
        System.out.println("\n--- Adicionar Servico Prestado ---");

        System.out.print("ID do Servico Prestado: ");
        int idPrestado = teclado.nextInt();

        System.out.print("ID do Servico: ");
        int idServico = teclado.nextInt();
        teclado.nextLine();

        System.out.printf("Data: ");
        LocalDate data;
        try {
            data = LocalDate.parse(teclado.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Data inválida!");
            return;
        }

        System.out.print("ID do Pet: ");
        int idPet = teclado.nextInt();

        System.out.println(controllerServico.buscar(idServico));

        System.out.println("Valor cobrado: ");
        double valor = teclado.nextDouble();
        teclado.nextLine();

        controller.adicionar(idPrestado, idPet, idServico, valor, data);

        System.out.println("Servico Prestado adicionado com sucesso!");
    }

    private void animaisDoDono(){
        System.out.println("\n--- Listar Animais do Dono ---");

        System.out.print("Nome do dono: ");
        String nome = teclado.nextLine();

        for (Pet pet : controller.buscarBichosPorNomeDono(nome)){
            System.out.println(pet);
        }
    }

    private void apagar(){
        System.out.println("\n--- Apagar Servico Prestado ---");

        System.out.print("Digite o ID do Servico Prestado a remover: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        if (controller.removerServicoPrestado(id)) {
            System.out.println("Servico Prestado removido com sucesso!");
        } else {
            System.out.println("Servico Prestado não encontrado.");
        }
    }

    private void maiorValor(){
        System.out.println("\n--- Listar Servico com Maior Valor do Cliente ---");

        System.out.print("Digite o ID do Cliente: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Maior Valor: R$" + controller.maiorValorPorCliente(controllerCliente.buscar(id)));
    }

    private void listar(){
        System.out.println("\n--- Listar Servicos Prestados ---");

        for (ServicoPrestado servicoPrestado : controller.listar()) {
            System.out.println(servicoPrestado);
        }
    }

    private void servicosDoCliente(){
        System.out.println("\n--- Listar Servicos do Cliente ---");

        System.out.print("Digite o ID do Cliente: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        for (ServicoPrestado servicoPrestado : controller.buscarPorCliente(controllerCliente.buscar(id))) {
            System.out.println(servicoPrestado);
        }
    }

    private void buscar(){
        System.out.println("\n--- Buscar Servico Prestado ---");

        System.out.print("Digite o ID do Servico Prestado: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        ServicoPrestado servicoPrestado = controller.buscar(id);

        if (servicoPrestado != null) {
            System.out.println(servicoPrestado);
        } else {
            System.out.println("Servico Prestado não encontrado.");
        }
    }
}
