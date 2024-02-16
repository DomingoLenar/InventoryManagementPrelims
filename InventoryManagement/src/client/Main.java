package client;

import client.controllers.InventoryManagementController;

import java.io.IOException;

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
            new InventoryManagementController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
