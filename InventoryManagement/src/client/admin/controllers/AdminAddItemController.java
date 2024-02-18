package client.admin.controllers;

import client.admin.views.AdminAddItemView;
import client.common.controllers.InventoryManagementController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class AdminAddItemController {
    AdminAddItemView adminAddItemView;

    InventoryManagementController inventoryManagementController;
    public AdminAddItemController(InventoryManagementController inventoryManagementController, Socket clientSocket) {
        this.inventoryManagementController = inventoryManagementController;
        adminAddItemView = new AdminAddItemView();
        initButtons();
    }

    private void initButtons() {
        adminAddItemView.getAddItemButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: send to server
            }
        });

        adminAddItemView.getCancelbtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getAdminStockControlController().adminStockControlView.getMainPanel());
            }
        });
    }

    public AdminAddItemView getAdminAddItemView() {
        return adminAddItemView;
    }
}
