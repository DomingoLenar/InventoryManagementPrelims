package client;

import client.common.controllers.InventoryManagementController;
import shared.StockControlServer;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main implements Runnable{
    public static void main(String[] args) {
        try {
            Main main = new Main();
            main.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            StockControlServer stub = (StockControlServer) registry.lookup("servant");
            new InventoryManagementController(stub);
        } catch (IOException | NotBoundException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
