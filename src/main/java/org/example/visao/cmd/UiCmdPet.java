package org.example.visao.cmd;

import org.example.controller.ClienteController;
import org.example.controller.PetController;
import org.example.model.Cliente;
import org.example.model.Pet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            System.out.println("\n========================");
            System.out.println("      GESTÃO DE PETS");
            System.out.println("========================");
            System.out.println("1 - Adicionar Pet");
            System.out.println("2 - Listar Pets");
            System.out.println("3 - Buscar Pet por ID");
            System.out.println("4 - Alterar Pet");
            System.out.println("5 - Apagar Pet");
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
                    alterar();
                    break;

                case 5:
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

        System.out.print("ID do Pet: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Nome do Pet: ");
        String nome = teclado.nextLine();

        System.out.print("Espécie do Pet: ");
        String especie = teclado.nextLine();

        System.out.print("Raça do Pet: ");
        String raca = teclado.nextLine();

        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        String dataTexto = teclado.nextLine();

        LocalDate nascimento;

        try {
            nascimento = LocalDate.parse(
                    dataTexto,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy")
            );
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Data inválida!");
            return;
        }

        System.out.print("ID do Cliente responsável: ");
        int idCliente = teclado.nextInt();
        teclado.nextLine();

        Cliente cliente = controllerCliente.buscar(idCliente);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

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

    private void alterar() {
        System.out.println(
                "A função de alteração ainda não foi implementada."
        );
    }

    private void apagar() {
        System.out.print("Digite o ID do Pet a remover: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        if (controller.remover(id)) {
            System.out.println("Pet removido com sucesso!");
        } else {
            System.out.println("Pet não encontrado.");
        }
    }
}