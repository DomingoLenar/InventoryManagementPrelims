package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.views.SalesStockMonitorView;

import javax.swing.*;
import javax.swing.table.TableModel;
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
        initComponents();
    }

    private void initComponents() {
        TableModel model = salesStockMonitorView.getStockMonitorTable().getModel();
        int col = model.getColumnCount();
        int row = model.getRowCount();
        salesStockMonitorView.getCreateCustomerOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = salesStockMonitorView.getStockMonitorTable().getSelectedRow();
                if (selectedRow != -1) {
                    inventoryManagementController.changeScreen(inventoryManagementController.getSalesCreateCustomerOrderController().getSalesCreateCustomerOrderView().getMainPanel());
                    String[] prodDetails = new String[2];
                    prodDetails[0] = model.getValueAt(selectedRow, 0).toString();
                    prodDetails[1] = model.getValueAt(selectedRow, 1).toString();
                    setIntentData(prodDetails);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please click a product for creation of customer order", "Error Creation of Customer Order", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void setIntentData(String[] prodDetails) {
        inventoryManagementController.getSalesCreateCustomerOrderController().getSalesCreateCustomerOrderView().getProdName().setText(prodDetails[0]);
        inventoryManagementController.getSalesCreateCustomerOrderController().getSalesCreateCustomerOrderView().getPrc().setText(prodDetails[1]);
    }

    public SalesStockMonitorView getSalesStockMonitorView() {
        return salesStockMonitorView;
    }
}
