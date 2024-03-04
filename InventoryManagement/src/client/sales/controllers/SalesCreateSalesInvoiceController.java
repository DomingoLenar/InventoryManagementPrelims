package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.common.models.ItemManagementModel;
import client.sales.views.SalesCreateSalesInvoiceView;
import utility.ItemOrder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
                salesCreateSalesInvoiceView.getEstimatedTotalField().getText();

                temporaryImplementation(productName, price, qty);

            }
        });

        salesCreateSalesInvoiceView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getSalesStockControlController().getSalesStockMonitorView().getMainPanel());
            }
        });
    }

    private void temporaryImplementation(String productName, int price, int qty) {
        ArrayList<ItemOrder> itemOrderArrayList = ItemManagementModel.fetchItemOrdersByUserType("sales", objectOutputStream, objectInputStream);

        ItemManagementModel.addItems(productName, qty, "null", itemOrderArrayList.size() + 1, price, objectOutputStream, objectInputStream);
        ItemManagementModel.addItemOrders(itemOrderArrayList.size() + 1, inventoryManagementController.getFormattedDate(), price, inventoryManagementController.getUserType(), itemOrderArrayList.size() + 1,
                inventoryManagementController.getUsername(), qty, objectOutputStream, objectInputStream);
    }

    public SalesCreateSalesInvoiceView getSalesCreateSalesInvoiceView() {
        return salesCreateSalesInvoiceView;
    }
}
