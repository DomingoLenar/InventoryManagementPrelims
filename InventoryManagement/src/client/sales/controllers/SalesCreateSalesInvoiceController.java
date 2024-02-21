package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.views.SalesCreateSalesInvoiceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SalesCreateSalesInvoiceController {
    SalesCreateSalesInvoiceView salesCreateSalesInvoiceView;
    InventoryManagementController inventoryManagementController;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public SalesCreateSalesInvoiceController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;
        salesCreateSalesInvoiceView = new SalesCreateSalesInvoiceView();
        objectInputStream = oIs;
        objectOutputStream = oOs;

        initButtons();

    }

    private void initButtons() {
        salesCreateSalesInvoiceView.getCreateInvoiceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName=salesCreateSalesInvoiceView.getProductField().getText();
                int price= Integer.parseInt(salesCreateSalesInvoiceView.getPriceField().getText());
                int qty = Integer.parseInt(salesCreateSalesInvoiceView.getQuantityField().getText());

                // TODO: fix the process of newly added product
//                salesCreateSalesInvoiceView.getEstimatedTotalField().getText();
//                ItemManagementModel.addItems(productName, qty, "null", 5, price, objectOutputStream, objectInputStream);
//                ItemManagementModel.addItemOrders(5, inventoryManagementController.getFormattedDate(), price, inventoryManagementController.getUserType(), 5,
//                        inventoryManagementController.getUsername(), 69, objectOutputStream, objectInputStream);
            }
        });

        salesCreateSalesInvoiceView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getSalesStockControlController().getSalesStockControlView().getMainPanel());
            }
        });
    }

    public SalesCreateSalesInvoiceView getSalesCreateSalesInvoiceView() {
        return salesCreateSalesInvoiceView;
    }
}
