package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.models.SalesStockMonitorModel;
import client.sales.views.SalesStockMonitorView;
import utility.revision.Item;
import utility.revision.Stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Stack;

public class SalesStockMonitorController {
    SalesStockMonitorModel salesStockMonitorModel;
    SalesStockMonitorView salesStockMonitorView;
    InventoryManagementController inventoryManagementController;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public SalesStockMonitorController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;
        salesStockMonitorModel = new SalesStockMonitorModel();
        salesStockMonitorView = new SalesStockMonitorView();
        objectInputStream = oIs;
        objectOutputStream=  oOs;

    }

    public void initComponents() {
        Stack<Item> itemStack = salesStockMonitorModel.fetchItems(objectInputStream, objectOutputStream);

        // setup table model object of Jtable
        DefaultTableModel model = (DefaultTableModel) salesStockMonitorView.getStockMonitorTable().getModel();
        model.setRowCount(0);
        for (int i=0; i<itemStack.size(); i++) {
            Item item = itemStack.get(i);
            LinkedList<Stock> stockLinkedList = item.getAllStocks();
            if (stockLinkedList.isEmpty()) {
                continue;
            }
            Stock stock = stockLinkedList.getFirst();
            int itemId = item.getId();
            String prod = item.getName();
            String type = item.getType();
            String batchNo = stock.getBatchNo();
            String supplierName = stock.getSupplier();
            int qty = stock.getQty();
            float prc = stock.getPrice();

            model.addRow(new Object[] {
                    itemId,prod,prc,type,qty,batchNo,supplierName
            });
        }
        salesStockMonitorView.getStockMonitorTable().setModel(model);

        salesStockMonitorView.getCreateCustomerOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = salesStockMonitorView.getStockMonitorTable().getSelectedRow();
                if (selectedRow != -1) {
                    inventoryManagementController.changeScreen(inventoryManagementController.getSalesCreateCustomerOrderController().getSalesCreateCustomerOrderView().getMainPanel());
                    String[] prodDetails = new String[7];
                    prodDetails[0] = model.getValueAt(selectedRow, 0).toString();
                    prodDetails[1] = model.getValueAt(selectedRow, 1).toString();
                    prodDetails[2] = model.getValueAt(selectedRow, 2).toString();
                    prodDetails[3] = model.getValueAt(selectedRow, 3).toString();
                    prodDetails[4] = model.getValueAt(selectedRow, 5).toString();
                    prodDetails[5] = model.getValueAt(selectedRow, 6).toString();
                    prodDetails[6] = model.getValueAt(selectedRow, 4).toString();
                    setIntentData(prodDetails);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please click a product for creation of customer order", "Error Creation of Customer Order", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void setIntentData(String[] prodDetails) {
        inventoryManagementController.getSalesCreateCustomerOrderController().setProductDetails(prodDetails);
        inventoryManagementController.getSalesCreateCustomerOrderController().getSalesCreateCustomerOrderView().getProdName().setText(prodDetails[1]);
        inventoryManagementController.getSalesCreateCustomerOrderController().getSalesCreateCustomerOrderView().getPrc().setText(prodDetails[2]);
        inventoryManagementController.getSalesCreateCustomerOrderController().getSalesCreateCustomerOrderView().getProdType().setText(prodDetails[3]);
        inventoryManagementController.getSalesCreateCustomerOrderController().getSalesCreateCustomerOrderView().getBatchNo().setText(prodDetails[4]);
        inventoryManagementController.getSalesCreateCustomerOrderController().getSalesCreateCustomerOrderView().getSupplier().setText(prodDetails[5]);
    }

    public SalesStockMonitorView getSalesStockMonitorView() {
        return salesStockMonitorView;
    }
}
