package client.admin.controllers;

import client.admin.views.AdminUserManagementView;
import client.common.controllers.InventoryManagementController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUserManagementController {
    AdminUserManagementView adminUserManagementView;
    InventoryManagementController inventoryManagementController;
    public AdminUserManagementController(InventoryManagementController inventoryManagementController){
        this.inventoryManagementController = inventoryManagementController;
        adminUserManagementView = new AdminUserManagementView();
        initButtons();
    }

    private void initButtons() {
        adminUserManagementView.getCreateUserButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: send to server
            }
        });
    }

    public AdminUserManagementView getAdminUserManagementView() {
        return adminUserManagementView;
    }
}
