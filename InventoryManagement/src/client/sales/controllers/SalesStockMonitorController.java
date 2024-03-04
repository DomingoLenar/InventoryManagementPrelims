package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.views.SalesStockMonitorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SalesStockMonitorController {
    SalesStockMonitorView salesStockMonitorView;
    InventoryManagementController inventoryManagementController;
    public SalesStockMonitorController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;
        salesStockMonitorView = new SalesStockMonitorView();
        initButtons();
    }

    private void initButtons() {
        salesStockMonitorView.getCreateCustomerOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getSalesCreateCustomerOrderController().getSalesCreateCustomerOrderView().getMainPanel());
            }
        });

    }

    public SalesStockMonitorView getSalesStockMonitorView() {
        return salesStockMonitorView;
    }
}
