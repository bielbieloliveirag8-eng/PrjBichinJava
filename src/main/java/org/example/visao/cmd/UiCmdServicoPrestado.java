package org.example.visao.cmd;

import org.example.controller.ServicoPrestadoController;

import java.util.Scanner;

public class UiCmdServicoPrestado {
    private Scanner teclado;
    private ServicoPrestadoController controller;

    public UiCmdServicoPrestado(ServicoPrestadoController controller) {
        this.teclado = new Scanner(System.in);
        this.controller = controller;
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n=======================================");
            System.out.println("      GESTÃO DE SERVICOS PRESTADOS");
            System.out.println("=======================================");
            System.out.println("1 - Adicionar Servico Prestado");
            System.out.println("2 - Listar Servicos Prestados");
            System.out.println("3 - Buscar Servico Prestado");
            System.out.println("3 - Apagar Servico");
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
                    //adicionar();
                    break;

                case 2:
                    //listar();
                    break;

                case 3:
                    //buscar();
                    break;

                case 4:
                    //alterar();
                    break;

                case 5:
                    //apagar();
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

    }

}
