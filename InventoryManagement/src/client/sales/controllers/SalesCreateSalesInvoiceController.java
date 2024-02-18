package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.views.SalesCreateSalesInvoiceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class SalesCreateSalesInvoiceController {
    SalesCreateSalesInvoiceView salesCreateSalesInvoiceView;
    InventoryManagementController inventoryManagementController;

    public SalesCreateSalesInvoiceController(InventoryManagementController inventoryManagementController, Socket clientSocket) {
        this.inventoryManagementController = inventoryManagementController;
        salesCreateSalesInvoiceView = new SalesCreateSalesInvoiceView();
        initButtons();
    }

    private void initButtons() {
        salesCreateSalesInvoiceView.getCreateInvoiceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: send to the server
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
