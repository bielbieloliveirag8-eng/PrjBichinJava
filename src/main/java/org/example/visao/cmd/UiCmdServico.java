package org.example.visao.cmd;

import org.example.controller.ServicoController;
import org.example.model.Servico;

import java.util.Scanner;

public class UiCmdServico {
    private Scanner teclado;
    private ServicoController controller;

    public UiCmdServico(ServicoController controller) {
        this.teclado = new Scanner(System.in);
        this.controller = controller;
    }

    public void iniciar(){
        int opcao;

        do {
            System.out.println("\n=======================================");
            System.out.println("         GESTÃO DE SERVICOS");
            System.out.println("=======================================");
            System.out.println("1 - Adicionar Servico");
            System.out.println("2 - Listar Servico");
            System.out.println("3 - Buscar Servico por ID");
            System.out.println("4 - Apagar Servico");
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

    private void listar(){
        System.out.println("\n--- Listar Servicos ---");

        for (Servico servico : controller.listar()) {
            System.out.println(servico);
        }
    }

    private void adicionar(){
        System.out.println("\n--- Adicionar Servico ---");

        System.out.print("ID do Servico: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Descricao: ");
        String descricao = teclado.nextLine();

        System.out.print("Valor: ");
        double valor = teclado.nextDouble();
        teclado.nextLine();

        System.out.print("Qual o tipo de serviço (1-interno ou 2-externo): ");
        int tipo = teclado.nextInt();
        teclado.nextLine();

        String crmv = null;
        double valorTerceirizado = 0;
        if (tipo == 1){
            System.out.println("CRMV do responsável pelo serviço: ");
            crmv = teclado.nextLine();
        }
        else{
            System.out.println("Valor do serviço terceirizado: ");
            valorTerceirizado = teclado.nextDouble();
        }

        controller.adicionar(id, descricao, valor, crmv, valorTerceirizado);

        System.out.println("Servico adicionado com sucesso!");
    }

    private void apagar(){
        System.out.println("\n--- Apagar Servico ---");

        System.out.print("Digite o ID do Servico a remover: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        if (controller.remover(id)) {
            System.out.println("Servico removido com sucesso!");
        } else {
            System.out.println("Servico não encontrado.");
        }
    }

    private void buscar() {
        System.out.println("\n--- Buscar Servico ---");

        System.out.print("Digite o ID do Servico: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        Servico servico = controller.buscar(id);

        if (servico != null) {
            System.out.println(servico);
        } else {
            System.out.println("Servico não encontrado.");
        }
    }
}
