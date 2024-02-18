package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.views.SalesStockControlView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class SalesStockControlController {
    SalesStockControlView salesStockControlView;
    InventoryManagementController inventoryManagementController;
    public SalesStockControlController(InventoryManagementController inventoryManagementController, Socket clientSocket) {
        this.inventoryManagementController = inventoryManagementController;
        salesStockControlView = new SalesStockControlView();
        initButtons();
    }

    private void initButtons() {
        salesStockControlView.getSalesInvoiceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getSalesCreateSalesInvoiceController().getSalesCreateSalesInvoiceView().getMainPanel());
            }
        });

    }

    public SalesStockControlView getSalesStockControlView() {
        return salesStockControlView;
    }
}
