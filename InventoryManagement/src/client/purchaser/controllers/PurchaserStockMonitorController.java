package client.purchaser.controllers;

import client.common.controllers.InventoryManagementController;
import client.purchaser.models.PurchaserStockMonitorModel;
import client.purchaser.views.PurchaserStockControlView;
import utility.revision.Item;
import utility.revision.Stock;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

public class PurchaserStockMonitorController {
    PurchaserStockMonitorModel purchaserStockMonitorModel;
    InventoryManagementController inventoryManagementController;
    PurchaserStockControlView purchaserStockControlView;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public PurchaserStockMonitorController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs)  {
        this.inventoryManagementController = inventoryManagementController;
        purchaserStockMonitorModel = new PurchaserStockMonitorModel();
        purchaserStockControlView = new PurchaserStockControlView();
        this.objectInputStream = oIs;
        this.objectOutputStream = oOs;
        
    }

    public void initComponents() {

        Stack<Item> itemStack = purchaserStockMonitorModel.fetchItems(objectInputStream, objectOutputStream);

        // setup table model object of Jtable
        DefaultTableModel model = (DefaultTableModel) purchaserStockControlView.getStockMonitorTable().getModel();
        model.setRowCount(0);
        for (int i=0; i<itemStack.size(); i++) {
            Item item = itemStack.get(i);
            Stock stock = item.getStock(0);
            int itemId = item.getId();
            String prod = item.getName();
            String type = item.getType();
            String batchNo = stock.getBatchNo();
            float purchasePrice = stock.getCost();
            String supplierName = stock.getSupplier();
            int qtyItem = stock.getQty();
            float prc = stock.getPrice();

            model.addRow(new Object[] {
                    itemId,prod,purchasePrice,type,qtyItem,supplierName,batchNo
            });

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
        }
        purchaserStockControlView.getStockMonitorTable().setModel(model);
        purchaserStockControlView.getPurchaseOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = purchaserStockControlView.getStockMonitorTable().getSelectedRow();
                if (selectedRow != -1) {
                    String[] prodDetails = new String[7];
                    prodDetails[0] = model.getValueAt(selectedRow, 0).toString();
                    prodDetails[1] = model.getValueAt(selectedRow, 1).toString();
                    prodDetails[2] = model.getValueAt(selectedRow, 2).toString();
                    prodDetails[3] = model.getValueAt(selectedRow, 3).toString();
                    prodDetails[4] = model.getValueAt(selectedRow, 4).toString();
                    prodDetails[5] = model.getValueAt(selectedRow, 5).toString();
                    prodDetails[6] = model.getValueAt(selectedRow, 6).toString();
                    setIntentData(prodDetails);

                    inventoryManagementController.changeScreen(inventoryManagementController.getPurchaserAddItemController().getPurchaserAddItemView().getMainPanel());
                }
            }
        });
        purchaserStockControlView.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        purchaserStockControlView.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // TODO: implement search logic and populate purchaser jtable
        purchaserStockControlView.getSearchField();

    }

    private void setIntentData(String[] prodDetails) {
        inventoryManagementController.getPurchaserAddItemController().setProductDetails(prodDetails);
        inventoryManagementController.getPurchaserAddItemController().getPurchaserAddItemView().getProductField().setText(prodDetails[1]);
        inventoryManagementController.getPurchaserAddItemController().getPurchaserAddItemView().getPurchasePriceField().setText(prodDetails[2]);
        inventoryManagementController.getPurchaserAddItemController().getPurchaserAddItemView().getTypeField().setText(prodDetails[3]);
        inventoryManagementController.getPurchaserAddItemController().getPurchaserAddItemView().getSupplierField().setText(prodDetails[5]);
        inventoryManagementController.getPurchaserAddItemController().getPurchaserAddItemView().getBatchNoField().setText(prodDetails[6]);

        int currentQtyItem = Integer.parseInt(prodDetails[4]);
        if (currentQtyItem == 0) {
            inventoryManagementController.getPurchaserAddItemController().getPurchaserAddItemView().getStockLevelField().setText("Out of Stock");
        }
        if (currentQtyItem > 0) {
            inventoryManagementController.getPurchaserAddItemController().getPurchaserAddItemView().getStockLevelField().setText("Critical");
        }
        if (currentQtyItem >= 20) {
            inventoryManagementController.getPurchaserAddItemController().getPurchaserAddItemView().getStockLevelField().setText("Low");
        }
        if (currentQtyItem >= 50) {
            inventoryManagementController.getPurchaserAddItemController().getPurchaserAddItemView().getStockLevelField().setText("Normal");
        }
        if (currentQtyItem >= 100) {
            inventoryManagementController.getPurchaserAddItemController().getPurchaserAddItemView().getStockLevelField().setText("High");
        }

    }
}
