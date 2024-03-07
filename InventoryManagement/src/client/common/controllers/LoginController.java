package client.common.controllers;

import client.common.models.LoginModel;
import client.common.views.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoginController {
    LoginModel loginModel;
    InventoryManagementController inventoryManagementController;
    LoginView loginView;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    public LoginController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) throws IOException {
        this.inventoryManagementController = inventoryManagementController;
        loginModel = new LoginModel();
        loginView = new LoginView();
        objectInputStream = oIs;
        objectOutputStream = oOs;

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
                    loginModel.handleLogin(username, password, objectOutputStream, objectInputStream);
                    String userType = loginModel.getUser().getRole(); // TODO: find a way where userType & username variable is not in inventory controller
                    if (userType != null) {
                        inventoryManagementController.getInventoryManagementInterface().setUsername(username);
                        inventoryManagementController.getInventoryManagementInterface().setUserType(userType);
                        inventoryManagementController.getUserSettingsController().getUserSettingsView().getUsernameLabel().setText(username);
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
