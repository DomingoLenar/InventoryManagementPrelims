package client;

import client.controllers.InventoryManagementController;
import java.net.Socket;

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
        new InventoryManagementController();
    }
}
