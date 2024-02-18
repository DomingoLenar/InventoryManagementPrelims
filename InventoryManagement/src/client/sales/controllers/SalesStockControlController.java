package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.views.SalesStockControlView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalesStockControlController {
    SalesStockControlView salesStockControlView;
    InventoryManagementController inventoryManagementController;
    public SalesStockControlController(InventoryManagementController inventoryManagementController) {
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
