package client.admin.controllers;

import client.admin.views.AdminUserManagementView;
import client.common.controllers.InventoryManagementController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminUserManagementController {
    AdminUserManagementView adminUserManagementView;
    InventoryManagementController inventoryManagementController;
    String userRole = "purchase";
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public AdminUserManagementController(InventoryManagementController inventoryManagementController,ObjectInputStream oIs,ObjectOutputStream oOs){
        this.inventoryManagementController = inventoryManagementController;
        adminUserManagementView = new AdminUserManagementView();
        objectOutputStream = oOs;
        objectInputStream = oIs;

        initButtons();
    }

    private void initButtons() {

        // TODO: ability to create a sales and purchase users
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
//                    User user = ProfileManagementModel.handleSignup(username, password, userRole, objectOutputStream, objectInputStream);
//                    String userType = user.getRole();
//                    if (userType != null) {
//                        switch (userType) {
//                            case "sales":
//                                inventoryManagementController.displaySalesMainMenu();
//                                break;
//                            case "purchase":
//                                inventoryManagementController.displayPurchaserMainMenu();
//                                break;
//                        }
//                    }
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
