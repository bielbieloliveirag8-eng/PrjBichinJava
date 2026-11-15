package org.example.visao.cmd;

import org.example.controller.ClienteController;
import org.example.controller.PetController;
import org.example.model.Cliente;
import org.example.model.Especie;
import org.example.model.Pet;
import org.example.util.Leitura;

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

            opcao = Leitura.lerInteiro(teclado,"Escolha uma opção: ",true);

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

        int idCliente = Leitura.lerInteiro(teclado,"Digite o ID do dono do pet: ",true);

        Cliente cliente = controllerCliente.buscar(idCliente);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");

            String nomeCliente = Leitura.lerString(teclado,"Digite o nome do cliente:");

            String telefoneCliente = Leitura.lerTelefone(teclado,"Telefone do cliente (formato (99) 99999-9999): ");

            controllerCliente.adicionar(idCliente, nomeCliente, telefoneCliente);

            cliente = controllerCliente.buscar(idCliente);
            System.out.println("Cliente cadastrado e associado com sucesso!");
        }

        int id = Leitura.lerInteiro(teclado,"Digite o ID do pet: ",true);

        String nome = Leitura.lerString(teclado,"Digite o nome do pet:");

        Especie especie = Leitura.lerEnum(teclado, "Digite a espécie do pet:", Especie.class);

        String raca =Leitura.lerString(teclado,"Digite a raça do pet:");

        LocalDate nascimento = Leitura.lerData(teclado,"Digite a data de nascimento do pet (dd/MM/yyyy):");

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
        int id = Leitura.lerInteiro(teclado,"Digite o ID do pet:",true);

        Pet pet = controller.buscar(id);

        if (pet != null) {
            System.out.println(pet);
        } else {
            System.out.println("Pet não encontrado!");
        }
    }

    private void apagar() {
        System.out.println("\n--- Apagar Pets ---");
        int id = Leitura.lerInteiro(teclado,"Digite o ID do pet a remover:",true);

        if (controller.remover(id)) {
            System.out.println("Pet removido com sucesso!");
        } else {
            System.out.println("Pet não encontrado!");
        }
    }

}