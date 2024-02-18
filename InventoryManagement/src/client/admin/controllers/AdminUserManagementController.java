package client.admin.controllers;

import client.admin.views.AdminUserManagementView;
import client.common.controllers.InventoryManagementController;
import client.common.models.ProfileManagementModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.Socket;

public class AdminUserManagementController {
    AdminUserManagementView adminUserManagementView;
    InventoryManagementController inventoryManagementController;
    Socket socket;
    String userRole = "purchase";
    public AdminUserManagementController(InventoryManagementController inventoryManagementController, Socket clientSocket){
        this.inventoryManagementController = inventoryManagementController;
        adminUserManagementView = new AdminUserManagementView();
        socket = clientSocket;
        initButtons();
    }

    private void initButtons() {
        adminUserManagementView.getCreateUserButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = adminUserManagementView.getUsernameTextField().getText();
                String password = adminUserManagementView.getPasswordTextField().getText();

                if (username.isEmpty()) {
                    // show rLabel below username textfield
                }
                if (password.length() == 0) {
                    // show rLabel below password textfield
                }
                if (username.isEmpty() && password.length() == 0);
                // show rLabel below password textfield
                // show rLabel below username textfield
                if (password.length() < 7); // show rLabel password must be => 8 characters

                if (!username.isEmpty() && password.length() > 7) {
                    String userType = ProfileManagementModel.handleSignup(username, password, userRole, socket);
                    if (userType != null) {
                        switch (userType) {
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

        adminUserManagementView.getRoleComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    userRole = e.getItem().toString();
                }
            }
        });
    }

    public AdminUserManagementView getAdminUserManagementView() {
        return adminUserManagementView;
    }
}
