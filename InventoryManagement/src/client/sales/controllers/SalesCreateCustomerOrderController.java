package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.views.SalesCreateCustomerOrderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SalesCreateCustomerOrderController {
    InventoryManagementController inventoryManagementController;
    SalesCreateCustomerOrderView salesCreateCustomerOrderView;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public SalesCreateCustomerOrderController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;
        salesCreateCustomerOrderView = new SalesCreateCustomerOrderView();
        objectOutputStream = oOs;
        objectInputStream = oIs;
        
        initComponents();
    }

    private void initComponents() {
        salesCreateCustomerOrderView.getCreateOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int qnty = Integer.parseInt(salesCreateCustomerOrderView.getQnty().getText()); // TODO: subtract: current stock of prod - qnty of prod in creation of cx order

                // TODO: send to the server


            }
        });
    }

    public SalesCreateCustomerOrderView getSalesCreateCustomerOrderView() {
        return salesCreateCustomerOrderView;
    }
}
