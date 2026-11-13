package org.example.util;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Leitura {

    private static final DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Pra impedir a criação de objetos
    private Leitura() {
    }

    // Lê uma String e não deixa ela ficar vazia
    public static String lerString(Scanner teclado, String mensagem) {

        while (true) {
            System.out.print(mensagem);

            String entrada = teclado.nextLine().trim();

            if (!entrada.isBlank()) {
                return entrada;
            }

            System.out.println("O campo não pode ficar vazio!");
        }
    }

    // Lê um número inteiro (devendo ser positivo ou não) e não deixa que seja algo difente de número
    public static int lerInteiro(Scanner teclado, String mensagem, boolean deveSerPositivo){
        while (true) {
            System.out.print(mensagem);

            String entrada = teclado.nextLine().trim();

            try {
                int valor = Integer.parseInt(entrada);

                if (!deveSerPositivo || valor >= 0) {
                    return valor;
                }

                System.out.println("O valor não pode ser negativo!");

            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido!");
            }
        }
    }

    // Lê um número do tipo double (devendo ser positivo ou não) e não deixa que seja algo difente de número
    public static double lerDouble(Scanner teclado, String mensagem, boolean deveSerPositivo) {

        while (true) {
            System.out.print(mensagem);

            String entrada = teclado.nextLine().trim();

            try {
                double valor = Double.parseDouble(
                        entrada.replace(",", ".")
                );

                if (Double.isFinite(valor) && (!deveSerPositivo || valor >= 0)) {
                    return valor;
                }

                System.out.println("Digite um valor válido!");

            } catch (NumberFormatException e) {
                System.out.println("Digite um número decimal válido!");
            }
        }
    }

    // Lê uma data válida no formato (dd/MM/yyyy)
    public static LocalDate lerData(Scanner teclado, String mensagem) {
        while (true) {
            System.out.print(mensagem);

            String entrada = teclado.nextLine().trim();

            try {
                return LocalDate.parse(entrada, formatoData);

            } catch (DateTimeParseException e) {
                System.out.println(
                        "Data inválida! Utilize o formato dd/MM/yyyy."
                );
            }
        }
    }

    // Lê um caractere que deve estar entre os valores permitidos
    public static char lerCharValido(Scanner teclado, String mensagem, char... validos) {
        while (true) {
            System.out.print(mensagem);

            String entrada = teclado.nextLine().trim();

            if (entrada.length() == 1) {

                char caractere = Character.toUpperCase(
                        entrada.charAt(0)
                );

                for (char valido : validos) {
                    if (caractere == Character.toUpperCase(valido)) {
                        return caractere;
                    }
                }
            }

            System.out.print("Opção inválida! Digite: ");

            for (int i = 0; i < validos.length; i++) {
                System.out.print(validos[i]);

                if (i < validos.length - 1) {
                    System.out.print(" ou ");
                }
            }

            System.out.println(".");
        }
    }

    // Lê uma opção de um ENUM, apresentando as opções válidas
    public static <T extends Enum<T>> T lerEnum(
            Scanner teclado,
            String mensagem,
            Class<T> tipoEnum
    ) {
        T[] valores = tipoEnum.getEnumConstants();

        // Monta a lista de opções do ENUM
        StringBuilder opcoes = new StringBuilder();

        for (int i = 0; i < valores.length; i++) {
            opcoes.append(valores[i]);

            if (i < valores.length - 1) {
                opcoes.append(", ");
            }
        }

        while (true) {
            System.out.print(mensagem + " (" + opcoes + "): ");

            String entrada = teclado.nextLine().trim();

            try {
                return Enum.valueOf(
                        tipoEnum,
                        entrada.toUpperCase()
                );

            } catch (IllegalArgumentException e) {
                System.out.println("Opção inválida! Escolha uma das opções apresentadas.");
            }
        }
    }
}
