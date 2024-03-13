package client.purchaser.controllers;

import client.common.controllers.InventoryManagementController;
import client.purchaser.models.PurchaserStockMonitorModel;
import client.purchaser.views.PurchaserStockMonitorView;
import utility.revision.Item;
import utility.revision.Stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

public class PurchaserStockMonitorController {
    PurchaserStockMonitorModel purchaserStockMonitorModel;
    InventoryManagementController inventoryManagementController;
    PurchaserStockMonitorView purchaserStockMonitorView;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public PurchaserStockMonitorController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs)  {
        this.inventoryManagementController = inventoryManagementController;
        purchaserStockMonitorModel = new PurchaserStockMonitorModel();
        purchaserStockMonitorView = new PurchaserStockMonitorView();
        this.objectInputStream = oIs;
        this.objectOutputStream = oOs;
        
    }

    public void initComponents() {

        Stack<Item> itemStack = purchaserStockMonitorModel.fetchItems(objectInputStream, objectOutputStream);
        // todo: fix table structure
        // setup table model object of Jtable
        DefaultTableModel model = (DefaultTableModel) purchaserStockMonitorView.getStockMonitorTable().getModel();
        model.setRowCount(0);
        for (int i = 0; i < itemStack.size(); i++) {
            Item item = itemStack.get(i);
            int itemId = item.getId();
            String prod = item.getName();
            String type = item.getType();
            if (item.getAllStocks().isEmpty()) {
                float purchasePrice = 0;
                String supplierName = "none";
                int qtyItem = 0;
                model.addRow(new Object[]{
                        itemId, prod, purchasePrice, type, qtyItem, supplierName
                });
            } else {
                Stock stock = item.getStock(0);
                String batchNo = stock.getBatchNo();
                float purchasePrice = stock.getCost();
                String supplierName = stock.getSupplier();
                int qtyItem = stock.getQty();
                float prc = stock.getPrice();
                model.addRow(new Object[]{
                        itemId, prod, purchasePrice, type, qtyItem, supplierName
                });
            }
        }


//            LinkedList<Stock> stockLinkedList = item.getAllStocks();
//
//            if (!stockLinkedList.isEmpty()) {
//                Stock stock = stockLinkedList.get(i);
//                stock.getBatchNo();
//                stock.getQty();
//                stock.getSupplier();
//                stock.getSupplier();
//                stock.getPrice();
//            }
        purchaserStockMonitorView.getStockMonitorTable().setModel(model);
        purchaserStockMonitorView.getPurchaseOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = purchaserStockMonitorView.getStockMonitorTable().getSelectedRow();
                if (selectedRow != -1) {
                    String[] prodDetails = new String[7];
                    prodDetails[0] = model.getValueAt(selectedRow, 0).toString();
                    prodDetails[1] = model.getValueAt(selectedRow, 1).toString();
                    prodDetails[2] = model.getValueAt(selectedRow, 2).toString();
                    prodDetails[3] = model.getValueAt(selectedRow, 3).toString();
                    prodDetails[4] = model.getValueAt(selectedRow, 4).toString();
                    prodDetails[5] = model.getValueAt(selectedRow, 5).toString();
//                    prodDetails[6] = model.getValueAt(selectedRow, 6).toString();
                    setIntentData(prodDetails);
                    inventoryManagementController.getPurchaserCreatePurchaseOrderController().initComponents();
                    inventoryManagementController.changeScreen(inventoryManagementController.getPurchaserCreatePurchaseOrderController().getPurchaserCreatePurchaseOrderView().getMainPanel());
                } else {
                    JOptionPane.showMessageDialog(null, "Please click an item before creation of purchase order", null, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        purchaserStockMonitorView.getAddProductButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.getPurchaserItemListingController().initComponents();
                inventoryManagementController.changeScreen(inventoryManagementController.getPurchaserItemListingController().getPurchaserItemListingView().getMainPanel());
            }
        });
        purchaserStockMonitorView.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        purchaserStockMonitorView.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // TODO: implement search logic and populate purchaser jtable
        purchaserStockMonitorView.getSearchField();

    }

    private void setIntentData(String[] prodDetails) {
        inventoryManagementController.getPurchaserCreatePurchaseOrderController().setProductDetails(prodDetails);
        inventoryManagementController.getPurchaserCreatePurchaseOrderController().getPurchaserCreatePurchaseOrderView().getProductField().setText(prodDetails[1]);
        inventoryManagementController.getPurchaserCreatePurchaseOrderController().getPurchaserCreatePurchaseOrderView().getPurchasePriceField().setText(prodDetails[2]);
        inventoryManagementController.getPurchaserCreatePurchaseOrderController().getPurchaserCreatePurchaseOrderView().getTypeField().setText(prodDetails[3]);
        inventoryManagementController.getPurchaserCreatePurchaseOrderController().setSupplier(prodDetails[5]);
//        inventoryManagementController.getPurchaserAddItemController().getPurchaserCreatePurchaseOrderView().getSupplierField().setText(prodDetails[5]);
//        inventoryManagementController.getPurchaserCreatePurchaseOrderController().getPurchaserCreatePurchaseOrderView().getBatchNoField().setText(prodDetails[6]);

        int currentQtyItem = Integer.parseInt(prodDetails[4]);
        if (currentQtyItem == 0) {
            inventoryManagementController.getPurchaserCreatePurchaseOrderController().getPurchaserCreatePurchaseOrderView().getStockLevelField().setText("Out of Stock");
        }
        if (currentQtyItem > 0) {
            inventoryManagementController.getPurchaserCreatePurchaseOrderController().getPurchaserCreatePurchaseOrderView().getStockLevelField().setText("Critical");
        }
        if (currentQtyItem >= 20) {
            inventoryManagementController.getPurchaserCreatePurchaseOrderController().getPurchaserCreatePurchaseOrderView().getStockLevelField().setText("Low");
        }
        if (currentQtyItem >= 50) {
            inventoryManagementController.getPurchaserCreatePurchaseOrderController().getPurchaserCreatePurchaseOrderView().getStockLevelField().setText("Normal");
        }
        if (currentQtyItem >= 100) {
            inventoryManagementController.getPurchaserCreatePurchaseOrderController().getPurchaserCreatePurchaseOrderView().getStockLevelField().setText("High");
        }

    }

    public PurchaserStockMonitorView getPurchaserStockMonitorView() {
        return purchaserStockMonitorView;
    }
}
