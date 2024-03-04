package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.views.SalesCustomerOrderManagementView;

import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SalesCustomerOrderManagementController {
    InventoryManagementController inventoryManagementController;
    SalesCustomerOrderManagementView salesCustomerOrderManagementView;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public SalesCustomerOrderManagementController(InventoryManagementController inventoryManagementController, ObjectOutputStream oOs, ObjectInputStream oIs){
        this.inventoryManagementController = inventoryManagementController;
        salesCustomerOrderManagementView = new SalesCustomerOrderManagementView();
        objectInputStream = oIs;
        objectOutputStream = oOs;
        
        initComponents();
    }

    private void initComponents() {
        initButtons();
        initTables();
    }

    private void initTables() {
        TableModel tableModel = salesCustomerOrderManagementView.getTable1().getModel();

        // TODO: add an event where the selected row is extracted from jtable and do smth about it
//        salesCustomerOrderManagementView.getTable1().
    }

    private void initButtons() {
        salesCustomerOrderManagementView.getGenerateSalesInvoiceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public SalesCustomerOrderManagementView getSalesCustomerOrderManagementView() {
        return salesCustomerOrderManagementView;
    }
}
