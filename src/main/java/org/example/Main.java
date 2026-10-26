package org.example;

import org.example.controller.ClienteController;
import org.example.controller.PetController;
import org.example.repository.ClienteRepository;
import org.example.repository.PetRepository;
import org.example.visao.cmd.UI_Cmd;
import org.example.visao.cmd.UiCmdPet;

public class Main {

    public static void main(String[] args) {


        ClienteRepository clienteRepository = new ClienteRepository();
        PetRepository petRepository = new PetRepository();

        ClienteController clienteController = new ClienteController(clienteRepository);
        PetController petController = new PetController(petRepository, clienteRepository);

        UiCmdPet uiCmdPet = new UiCmdPet(petController, clienteController);

        UI_Cmd menu = new UI_Cmd(
                uiCmdPet

        );


        menu.iniciar();
    }
}