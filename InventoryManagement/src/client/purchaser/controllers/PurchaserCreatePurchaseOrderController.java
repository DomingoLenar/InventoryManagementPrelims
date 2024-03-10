package client.purchaser.controllers;

import client.common.controllers.InventoryManagementController;
import client.purchaser.models.PurchaserCreatePurchaseOrderModel;
import client.purchaser.views.PurchaserAddItemView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PurchaserCreatePurchaseOrderController {
    PurchaserCreatePurchaseOrderModel purchaserCreatePurchaseOrderModel;
    InventoryManagementController inventoryManagementController;
    PurchaserAddItemView purchaserAddItemView;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    String[] productDetails;

    public void setProductDetails(String[] productDetails) {
        this.productDetails = productDetails;
    }

    public String[] getProductDetails() {
        return productDetails;
    }

    public PurchaserCreatePurchaseOrderController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs){
        this.inventoryManagementController = inventoryManagementController;
        purchaserCreatePurchaseOrderModel = new PurchaserCreatePurchaseOrderModel();
        purchaserAddItemView = new PurchaserAddItemView();
        objectInputStream = oIs;
        objectOutputStream = oOs;

        initComponents();
    }

    private void initComponents() {
        initButtons();
    }

    private void initButtons() {
        purchaserAddItemView.getAddItemButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int itemId = Integer.parseInt(productDetails[0]);
                String productName = purchaserAddItemView.getProductField().getText();
                float purchasePrice = Float.parseFloat(purchaserAddItemView.getPurchasePriceField().getText());
                int qtyItem = Integer.parseInt(purchaserAddItemView.getQuantityField().getText());
                String supplier = purchaserAddItemView.getSupplierField().getText();
                String type = purchaserAddItemView.getTypeField().getText();
                String createdBy = inventoryManagementController.getInventoryManagementInterface().getUsername();
                String dateCreated = inventoryManagementController.getInventoryManagementInterface().getFormattedDate();
                String orderType = inventoryManagementController.getInventoryManagementInterface().getUserType();
                String batchNo = purchaserAddItemView.getBatchNoField().getText(); // todo: uncertain about the logic
                boolean creationSuccess = purchaserCreatePurchaseOrderModel.OnCreate(productName, purchasePrice, qtyItem, supplier, type, createdBy, dateCreated, itemId, orderType,
                        batchNo, objectOutputStream, objectInputStream);
                if (!creationSuccess) {
                    JOptionPane.showMessageDialog(null, "Failure to create purchase order", "Creation of Purchase Order Failed", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Purchase order creation success ", "Creation of Purchase Order Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        purchaserAddItemView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getPurchaserStockControlController().purchaserStockControlView.getMainPanel());
            }
        });
    }

    public PurchaserAddItemView getPurchaserAddItemView() {
        return purchaserAddItemView;
    }
}
