package client.controllers;

import client.views.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    InventoryManagementController inventoryManagementController;
    LoginView loginView;
    public LoginController(InventoryManagementController inventoryManagementController){
        this.inventoryManagementController = inventoryManagementController;
        loginView = new LoginView();

        initComponents();
    }

    private void initComponents() {
        initButtons();
        initTextFields();
    }

    private void initTextFields() {
        loginView.getLVusernameField().getText();
        loginView.getLVpasswordField().getPassword();
    }

    private void initButtons() {
        loginView.getLVloginBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.displayMainMenu();
            }
        });


    }

    public LoginView getLoginView() {
        return loginView;
    }
}
