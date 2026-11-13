package org.example;

import org.example.controller.*;
import org.example.repository.*;
import org.example.model.*;
import org.example.visao.cmd.*;
import org.example.service.*;
public class Main {

    public static void main(String[] args) {

        ClienteRepository clienteRepository = new ClienteRepository();
        PetRepository petRepository = new PetRepository();
        ServicoRepository servicoRepository = new ServicoRepository();
        ServicoPrestadoRepository servicoPrestadoRepository = new ServicoPrestadoRepository();

        clienteRepository.carregarDados("src/main/resources/dados/clientes.txt");

        petRepository.carregarDados("src/main/resources/dados/pets.txt", clienteRepository);

        servicoRepository.carregarDados("src/main/resources/dados/servicos.txt");

        servicoPrestadoRepository.carregarDados("src/main/resources/dados/servicos_prestados.txt", petRepository, servicoRepository);

        ClienteController clienteController = new ClienteController(clienteRepository);
        PetController petController = new PetController(petRepository, clienteRepository);
        ServicoController servicoController = new ServicoController(servicoRepository);
        ServicoPrestadoService servicoPrestadoService = new ServicoPrestadoService(servicoPrestadoRepository);
        ServicoPrestadoController servicoPrestadoController = new ServicoPrestadoController(petRepository, servicoRepository, servicoPrestadoService, servicoPrestadoRepository);

        UiCmdPet uiCmdPet = new UiCmdPet(petController, clienteController);
        UiCmdServico uiCmdServico = new UiCmdServico(servicoController);
        UiCmdServicoPrestado uiCmdServicoPrestado = new UiCmdServicoPrestado(servicoPrestadoController, clienteController, servicoController);
        UiCmdCliente uiCmdCliente = new UiCmdCliente(clienteController);

        UI_Cmd menu = new UI_Cmd(
                uiCmdCliente,
                uiCmdPet,
                uiCmdServico,
                uiCmdServicoPrestado
        );

        menu.iniciar();
    }
}