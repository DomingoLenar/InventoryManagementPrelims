package client.admin.controllers;

import client.admin.views.AdminStockControlView;
import client.common.controllers.InventoryManagementController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class AdminStockControlController {
    AdminStockControlView adminStockControlView;
    InventoryManagementController inventoryManagementController;

    public AdminStockControlController(InventoryManagementController inventoryManagementController, Socket clientSocket) {
        this.inventoryManagementController = inventoryManagementController;
        adminStockControlView = new AdminStockControlView();

        initComponents();
    }

    private void initComponents() {
        initButtons();
    }

    private void initButtons() {
        adminStockControlView.getAddItemButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getAdminAddItemController().adminAddItemView.getMainPanel());
            }
        });

        adminStockControlView.getSalesInvoiceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getAdminCreateSalesInvoiceController().adminCreateSalesInvoiceView.getMainPanel());
            }
        });
    }

    public AdminStockControlView getAdminStockControlView() {
        return adminStockControlView;
    }
}
