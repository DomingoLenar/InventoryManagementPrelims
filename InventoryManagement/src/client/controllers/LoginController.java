package client.controllers;

import client.InventoryManagementInterface;
import client.views.LoginView;

public class LoginController {
    LoginView loginView;
    public LoginController(InventoryManagementInterface inventoryManagementInterface){
        loginView = new LoginView();
    }

    public LoginView getLoginView() {
        return loginView;
    }
}
