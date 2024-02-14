package client;

import client.controllers.InventoryManagementController;
import client.models.LoginSignupModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main implements Runnable{
    public static void main(String[] args) {
        try {
            Main main = new Main();
            main.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            Socket sSocket = new Socket("localhost", 2018);
//            ObjectOutputStream oOs = new ObjectOutputStream(sSocket.getOutputStream());
//            BufferedReader bReader = new BufferedReader(new InputStreamReader(sSocket.getInputStream()));
//            System.out.println(bReader.readLine());
//            LoginSignupModel loginSignupModel = new LoginSignupModel(sSocket, oOs);
//            boolean valid = loginSignupModel.handleLogin("testuser", "usertest");
//
//            if (valid) {
//                System.out.println("login success");
//            } else {
//                System.out.println("faield");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void run() {
        new InventoryManagementController();
    }
}
