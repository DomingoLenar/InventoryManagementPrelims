package client.admin.controllers;

import client.admin.views.AdminCreateSalesInvoiceView;
import client.common.controllers.InventoryManagementController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminCreateSalesInvoiceController {
    AdminCreateSalesInvoiceView adminCreateSalesInvoiceView;
    InventoryManagementController inventoryManagementController;
    public AdminCreateSalesInvoiceController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;
        adminCreateSalesInvoiceView = new AdminCreateSalesInvoiceView();
        initButtons();
    }

    private void initButtons() {
        adminCreateSalesInvoiceView.getCreateSalesInvoiceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: send data to server
            }
        });

        adminCreateSalesInvoiceView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getAdminStockControlController().getAdminStockControlView().getMainPanel());
            }
        });
    }

    public AdminCreateSalesInvoiceView getAdminCreateSalesInvoiceView() {
        return adminCreateSalesInvoiceView;
    }
}
