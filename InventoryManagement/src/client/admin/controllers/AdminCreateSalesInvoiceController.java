package client.admin.controllers;

import client.admin.views.AdminCreateSalesInvoiceView;
import client.common.controllers.InventoryManagementController;
import client.common.models.ItemManagementModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminCreateSalesInvoiceController {
    AdminCreateSalesInvoiceView adminCreateSalesInvoiceView;
    InventoryManagementController inventoryManagementController;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public AdminCreateSalesInvoiceController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;
        adminCreateSalesInvoiceView = new AdminCreateSalesInvoiceView();
        objectInputStream = oIs;
        objectOutputStream = oOs;

        initButtons();
    }

    private void initButtons() {
        adminCreateSalesInvoiceView.getCreateSalesInvoiceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO: construct in such a way it is normalize
                String pName = adminCreateSalesInvoiceView.getProductName().getText();
                float pPrice = Float.parseFloat(adminCreateSalesInvoiceView.getProductPrice().getText());
                int qty = Integer.parseInt(adminCreateSalesInvoiceView.getQuantity().getText());

                adminCreateSalesInvoiceView.getEstimatedTotal().getText();

                ItemManagementModel.addItems(pName, qty, "warehouse", 13, pPrice, objectOutputStream, objectInputStream);
                ItemManagementModel.addItemOrders(13, inventoryManagementController.getFormattedDate(), pPrice, "sales",
                        13, inventoryManagementController.getUsername(), qty, objectOutputStream, objectInputStream);

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
