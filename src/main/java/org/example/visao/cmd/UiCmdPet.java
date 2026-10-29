package org.example.visao.cmd;

import org.example.controller.ClienteController;
import org.example.controller.PetController;
import org.example.model.Cliente;
import org.example.model.Pet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UiCmdPet {

    private Scanner teclado;
    private PetController controller;
    private ClienteController controllerCliente;

    public UiCmdPet(PetController controller, ClienteController controllerCliente) {
        this.teclado = new Scanner(System.in);
        this.controller = controller;
        this.controllerCliente = controllerCliente;
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("========================");
            System.out.println("      GESTÃO DE PETS");
            System.out.println("========================");
            System.out.println("1 - Adicionar Pet");
            System.out.println("2 - Listar Pets");
            System.out.println("3 - Buscar Pet por ID");
            System.out.println("4 - Apagar Pet");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.println("========================");
            System.out.print("Escolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    adicionar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    apagar();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void adicionar() {
        System.out.println("\n--- Adicionar Pet ---");

        System.out.print("Digite o ID do dono do pet: ");
        int idCliente = teclado.nextInt();
        teclado.nextLine();

        Cliente cliente = controllerCliente.buscar(idCliente);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");

            System.out.print("Nome do cliente: ");
            String nomeCliente = teclado.nextLine();

            String telefoneCliente = lerTelefone("Telefone do cliente (formato (99) 99999-9999): ");

            controllerCliente.adicionar(idCliente, nomeCliente, telefoneCliente);

            cliente = controllerCliente.buscar(idCliente);
            System.out.println("Cliente cadastrado e associado com sucesso!");
        }

        System.out.print("ID do Pet: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Nome do Pet: ");
        String nome = teclado.nextLine();

        System.out.print("Espécie do Pet: ");
        String especie = teclado.nextLine();

        System.out.print("Raça do Pet: ");
        String raca = teclado.nextLine();

        LocalDate nascimento = lerData("Data de nascimento (dd/MM/yyyy): ");

        controller.adicionar(
                id, nome, especie, raca, nascimento, cliente.getId()
        );

        System.out.println("Pet adicionado com sucesso!");
    }

    private void listar() {
        System.out.println("\n--- Lista de Pets ---");

        for (Pet pet : controller.listar()) {
            System.out.println(pet);
        }
    }

    private void buscar() {
        System.out.println("\n--- Buscar Pets ---");
        System.out.print("Digite o ID do Pet: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        Pet pet = controller.buscar(id);

        if (pet != null) {
            System.out.println(pet);
        } else {
            System.out.println("Pet não encontrado.");
        }
    }

    private void apagar() {
        System.out.println("\n--- Apagar Pets ---");
        System.out.print("Digite o ID do Pet a remover: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        if (controller.remover(id)) {
            System.out.println("Pet removido com sucesso!");
        } else {
            System.out.println("Pet não encontrado.");
        }
    }

    private String lerTelefone(String mensagem) {
        String regex = "\\(\\d{2}\\) \\d{5}-\\d{4}";
        while (true) {
            System.out.print(mensagem);
            String telefone = teclado.nextLine();
            if (telefone.matches(regex)) {
                return telefone;
            }
            System.out.println("Formato inválido! Use o padrão (99) 99999-9999.");
        }
    }

    private LocalDate lerData(String mensagem) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print(mensagem);
            String entrada = teclado.nextLine();
            try {
                return LocalDate.parse(entrada, formato);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida ou fora do padrão dd/MM/yyyy. Tente novamente!");
            }
        }
    }
}