package client.common.controllers;

import client.common.views.LoginView;
import client.models.ProfileManagementModel;

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
                    String userType = profileManagementModel.handleLogin(username, password);
                    switch (userType) {
                        case "admin":
                            inventoryManagementController.displayAdminMainMenu();
                            break;
                        case "sales":
                            inventoryManagementController.displaySalesMainMenu();
                            break;
                        case "purchase":
                            inventoryManagementController.displayPurchaserMainMenu();
                            break;
                    }
                }
            }
        });
    }

    public LoginView getLoginView() {
        return loginView;
    }
}
