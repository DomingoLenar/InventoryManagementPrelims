package client.admin.controllers;

import client.admin.views.AdminCreateSalesInvoiceView;
import client.common.controllers.InventoryManagementController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminCreateSalesInvoiceController {
    AdminCreateSalesInvoiceView adminCreateSalesInvoiceView;
    InventoryManagementController inventoryManagementController;
    public AdminCreateSalesInvoiceController(InventoryManagementController inventoryManagementController) {
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
