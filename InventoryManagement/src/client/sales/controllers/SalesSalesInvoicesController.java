package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.models.SalesSalesInvoicesModel;
import client.sales.views.SalesInvoiceViewHardCoded;
import utility.revision.ItemOrder;

import javax.swing.table.DefaultTableModel;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SalesSalesInvoicesController {
    SalesSalesInvoicesModel salesSalesInvoicesModel;
    SalesInvoiceViewHardCoded salesInvoiceHardCoded;
    InventoryManagementController inventoryManagementController;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public SalesSalesInvoicesController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        salesSalesInvoicesModel = new SalesSalesInvoicesModel();
        this.inventoryManagementController = inventoryManagementController;
        salesInvoiceHardCoded = new SalesInvoiceViewHardCoded();
        objectInputStream = oIs;
        objectOutputStream = oOs;
    }
    public void initComponents(){
        String userType = inventoryManagementController.getInventoryManagementInterface().getUserType();
        ArrayList<ItemOrder> itemOrderArrayList = salesSalesInvoicesModel.fetchListOfSalesInvoice(objectOutputStream, objectInputStream, userType);

        DefaultTableModel model = salesInvoiceHardCoded.getModel();
        model.setRowCount(0);
        for (ItemOrder itemOrder:itemOrderArrayList) {
            model.addRow(new Object[] {
                    itemOrder.getOrderId(),itemOrder.getDate(),itemOrder.getCreatedBy().getUsername()
            });
        }
    }
//    public void initComponents() {
//
//        // populate the table
//        ArrayList<ItemOrder> listOfItemOrders = null;
//        Stack<Item> listOfAllItems = null;
//        ArrayList<Item> filteredListOfItems = new ArrayList<>();
//        for (int i=0; i< listOfItemOrders.size(); i++) { // t(n) bad but it works.
//            ItemOrder itemOrder = listOfItemOrders.get(i);
//            for (int j=0; j<listOfAllItems.size(); j++){
//                Item item = listOfAllItems.get(j);
//
//                if (itemOrder.getItemId() == item.getItemId()) {
//                    filteredListOfItems.add(item);
//                }
//            }
//        }
//
//        String[][] strings_array = new String[listOfItemOrders.size()][5];
//        for (int i=0; i < listOfItemOrders.size(); i++) {
//            ItemOrder itemOrder = listOfItemOrders.get(i);
//            Item item = filteredListOfItems.get(i);
//            strings_array[i][0] = String.valueOf(itemOrder.getItemId());
//            strings_array[i][1] = itemOrder.getDate();
//            strings_array[i][2] = item.getName();
//            strings_array[i][3] = String.valueOf(item.getQty());
//            strings_array[i][4] = String.valueOf(item.getQty() * item.getPrice());
//        }
//        String[] columnNames = {"ID", "Date", "Product", "Quantity", "Total"};
//
//        salesInvoiceHardCoded.getModel().setDataVector(strings_array, columnNames);
//        salesInvoiceHardCoded.getTable().addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 1) {
//                    JTable target = (JTable)e.getSource();
//                    int row = target.getSelectedRow();
//                    String id = (String) target.getValueAt(row, 0);
//                    String date = (String) target.getValueAt(row, 1);
//                    String product = (String) target.getValueAt(row, 2);
//                    String quantity = (String) target.getValueAt(row, 3);
//                    String total = (String) target.getValueAt(row,4);
//                    // Sample Data
//                    System.out.println("Clicked Row Data: ID=" + id + ", Date=" + date +
//                            ", Product=" + product + ", Quantity=" + quantity);
//
//                    // For demonstration purposes (swing panel for sales invoice will show up here)
//                    JOptionPane.showMessageDialog(null,
//                            "You clicked on: ID=" + id + ", Date=" + date +
//                                    ", Product=" + product + ", Quantity=" + quantity + ", Total=" +total);
//                }
//            }
//        });
//
//    }

    public SalesInvoiceViewHardCoded getSalesInvoiceHardCoded() {
        return salesInvoiceHardCoded;
    }
}
