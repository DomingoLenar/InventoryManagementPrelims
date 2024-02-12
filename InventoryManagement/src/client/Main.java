package client;

import client.controllers.InventoryManagementController;
public class Main implements Runnable{
    /**
     * Entry point of the application.
     * @param args
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    @Override
    public void run() {
        new InventoryManagementController();
    }
}
