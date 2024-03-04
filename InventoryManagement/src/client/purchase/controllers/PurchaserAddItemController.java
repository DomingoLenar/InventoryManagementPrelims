package client.purchase.controllers;

import client.common.controllers.InventoryManagementController;
import client.purchase.views.PurchaserAddItemView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PurchaserAddItemController {
    InventoryManagementController inventoryManagementController;
    PurchaserAddItemView purchaserAddItemView;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public PurchaserAddItemController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs){
        this.inventoryManagementController = inventoryManagementController;
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
                String productName = purchaserAddItemView.getProductField().getText();
                int productPrice = Integer.parseInt(purchaserAddItemView.getPriceField().getText());
                int quantity = Integer.parseInt(purchaserAddItemView.getQuantityField().getText());

                // TODO: fix the process of newly added product
//                ItemManagementModel.addItems(productName, quantity, "null", 6, productPrice, objectOutputStream, objectInputStream);
//                ItemManagementModel.addItemOrders(6, inventoryManagementController.getFormattedDate(), productPrice, inventoryManagementController.getUserType(), 6,
//                        inventoryManagementController.getUsername(), 69, objectOutputStream, objectInputStream);

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
