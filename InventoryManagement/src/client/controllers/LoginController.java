package client.controllers;

import client.models.ProfileManagementModel;
import client.views.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class LoginController {
    InventoryManagementController inventoryManagementController;
    ProfileManagementModel profileManagementModel;
    LoginView loginView;
    public LoginController(InventoryManagementController inventoryManagementController, Socket clientSocket) throws IOException {
        this.inventoryManagementController = inventoryManagementController;
        this.profileManagementModel = new ProfileManagementModel();

        loginView = new LoginView();

        initComponents();
    }

//    public LoginController(InventoryManagementController inventoryManagementController) throws IOException {
//        this.inventoryManagementController = inventoryManagementController;
//        this.profileManagementModel = new ProfileManagementModel();
//
//        loginView = new LoginView();
//
//        initComponents();
//    }

    private void initComponents() {
        initButtons();
        initTextFields();
    }

    private void initTextFields() {

    }

    private void initButtons() {
        loginView.getLVloginBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginView.getLVusernameField().getText();
                String password = loginView.getLVpasswordField().getText();

                if (username.isEmpty() || password.isEmpty()) {
                    // do smth
                } else {
                    boolean valid = profileManagementModel.handleLogin(username, password);
                    if (valid) {
                        inventoryManagementController.displayMainMenu();
                    } else {
                        // do smth
                    }
                }
            }
        });
    }

    public LoginView getLoginView() {
        return loginView;
    }
}
