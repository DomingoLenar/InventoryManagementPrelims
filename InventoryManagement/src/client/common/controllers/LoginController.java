package client.common.controllers;

import client.common.models.ProfileManagementModel;
import client.common.views.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoginController {
    InventoryManagementController inventoryManagementController;
    ProfileManagementModel profileManagementModel;
    LoginView loginView;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    public LoginController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) throws IOException {
        this.inventoryManagementController = inventoryManagementController;
        this.profileManagementModel = new ProfileManagementModel();
        objectInputStream = oIs;
        objectOutputStream = oOs;

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
                    String userType = ProfileManagementModel.handleLogin(username, password, objectOutputStream, objectInputStream);
                    if (userType != null) {
                        inventoryManagementController.setUserType(userType);
                        inventoryManagementController.setUsername(username);
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
            }
        });
    }

    public LoginView getLoginView() {
        return loginView;
    }
}
