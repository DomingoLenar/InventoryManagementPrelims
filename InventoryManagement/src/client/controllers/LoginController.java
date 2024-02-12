package client.controllers;

import client.InventoryManagementInterface;
import client.views.LoginView;

public class LoginController {
    LoginView loginView;
    public LoginController(InventoryManagementInterface inventoryManagementInterface, InventoryManagementController inventoryManagementController){
        loginView = new LoginView();
    }

    public LoginView getLoginView() {
        return loginView;
    }
}
