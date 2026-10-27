package org.example.visao.cmd;

import org.example.controller.ClienteController;
import org.example.model.Cliente;

import java.util.ArrayList;
import java.util.Scanner;

public class UiCmdCliente {

    private Scanner teclado;
    private ClienteController controller;

    public UiCmdCliente(ClienteController controller) {
        this.teclado = new Scanner(System.in);
        this.controller = controller;
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n=======================================");
            System.out.println("         GESTÃO DE CLIENTES");
            System.out.println("=========================================");
            System.out.println("1 - Adicionar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Buscar Cliente por ID");
            System.out.println("4 - Apagar Cliente");
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
        System.out.println("\n--- Adicionar Cliente ---");
        System.out.print("Digite o ID do cliente: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Digite o nome do cliente: ");
        String nome = teclado.nextLine();

        String telefone = "";
        boolean telefoneValido = false;
        while (!telefoneValido) {
            System.out.print("Digite o telefone (formato (dd) ddddd-dddd): ");
            telefone = teclado.nextLine();

            if (telefone.matches("\\(\\d{2}\\)\\s\\d{5}-\\d{4}")) {
                telefoneValido = true;
            } else {
                System.out.println("Formato inválido!");
            }
        }

        controller.adicionar(id, nome, telefone);
        System.out.println("Cliente adicionado com sucesso!");
    }

    private void listar() {
        System.out.println("\n--- Lista de clientes ---");
        ArrayList<Cliente> clientes = controller.listar();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente registado.");
        } else {
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }
    }

    private void buscar() {
        System.out.println("\n--- Buscar cliente ---");
        System.out.print("Digite o ID do cliente: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        Cliente c = controller.buscar(id);
        if (c != null) {
            System.out.println("Cliente encontrado: " + c);
        } else {
            System.out.println("Cliente com ID " + id + " não encontrado.");
        }
    }

    private void apagar() {
        System.out.println("\n--- Apagar cliente ---");
        System.out.print("Digite o ID do cliente que deseja apagar: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        boolean sucesso = controller.remover(id);
        if (sucesso) {
            System.out.println("Cliente apagado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado ou erro ao apagar.");
        }
    }
}