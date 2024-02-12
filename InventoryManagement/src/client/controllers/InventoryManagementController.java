package client.controllers;

import client.InventoryManagementInterface;

import javax.swing.*;

public class InventoryManagementController {
    /**
     * Multiple controllers
     */
    InventoryManagementInterface inventoryManagementInterface;
    IndexController indexController;
    SignUpController signUpController;
    LoginController loginController;
    public InventoryManagementController() {
        inventoryManagementInterface = new InventoryManagementInterface();
        initControllers();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                inventoryManagementInterface.add(indexController.getIndexView().getIVpanel());
                inventoryManagementInterface.revalidate();
                inventoryManagementInterface.repaint();
            }
        });
    }

    private void initControllers() {
        indexController = new IndexController(inventoryManagementInterface, this);
        signUpController = new SignUpController(inventoryManagementInterface, this);
        loginController = new LoginController(inventoryManagementInterface, this);
    }

    public IndexController getIndexController() {
        return indexController;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public SignUpController getSignUpController() {
        return signUpController;
    }
}
