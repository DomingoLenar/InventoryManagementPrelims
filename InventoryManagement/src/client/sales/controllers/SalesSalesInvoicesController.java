package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.common.models.ItemManagementModel;
import client.sales.views.SalesInvoiceViewHardCoded;
import utility.Item;
import utility.ItemOrder;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SalesSalesInvoicesController {
    SalesInvoiceViewHardCoded salesInvoiceHardCoded;
    InventoryManagementController inventoryManagementController;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public SalesSalesInvoicesController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;
        salesInvoiceHardCoded = new SalesInvoiceViewHardCoded();
        objectInputStream = oIs;
        objectOutputStream = oOs;

    }

    public void initComponents() {
        ArrayList<ItemOrder> listOfItemOrders = ItemManagementModel.fetchItemOrders(objectOutputStream, objectInputStream);
        ArrayList<Item> listOfItems = ItemManagementModel.fetchListOfItems(objectOutputStream, objectInputStream);
        String[][] strings_array = new String[listOfItemOrders.size()][5];
        for (int i=0; i < listOfItemOrders.size(); i++) {
            ItemOrder itemOrder = listOfItemOrders.get(i);
            Item item = listOfItems.get(i);
            strings_array[i][0] = String.valueOf(itemOrder.getItemId());
            strings_array[i][1] = itemOrder.getDate();
            strings_array[i][2] = item.getName();
            strings_array[i][3] = String.valueOf(item.getQty());
            strings_array[i][4] = String.valueOf(item.getQty() * item.getPrice());
        }
        String[] columnNames = {"ID", "Date", "Product", "Quantity", "Total"};

        salesInvoiceHardCoded.getModel().setDataVector(strings_array, columnNames);
        salesInvoiceHardCoded.getTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    String id = (String) target.getValueAt(row, 0);
                    String date = (String) target.getValueAt(row, 1);
                    String product = (String) target.getValueAt(row, 2);
                    String quantity = (String) target.getValueAt(row, 3);
                    String total = (String) target.getValueAt(row,4);
                    // Sample Data
                    System.out.println("Clicked Row Data: ID=" + id + ", Date=" + date +
                            ", Product=" + product + ", Quantity=" + quantity);

                    // For demonstration purposes (swing panel for sales invoice will show up here)
                    JOptionPane.showMessageDialog(null,
                            "You clicked on: ID=" + id + ", Date=" + date +
                                    ", Product=" + product + ", Quantity=" + quantity + ", Total=" +total);
                }
            }
        });
    }

    public SalesInvoiceViewHardCoded getSalesInvoiceHardCoded() {
        return salesInvoiceHardCoded;
    }
}
