package client.purchaser.controllers;

import client.common.controllers.InventoryManagementController;
import client.purchaser.models.PurchaserItemListingModel;
import client.purchaser.views.PurchaserItemListingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PurchaserItemListingController {
    PurchaserItemListingModel purchaserItemListingModel;
    InventoryManagementController inventoryManagementController;
    PurchaserItemListingView purchaserItemListingView;
    ObjectInputStream oIs;
    ObjectOutputStream oOs;
    public PurchaserItemListingController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs){
        this.inventoryManagementController = inventoryManagementController;
        purchaserItemListingModel = new PurchaserItemListingModel();
        purchaserItemListingView = new PurchaserItemListingView();
        this.oOs = oOs;
        this.oIs = oIs;

    }
    public void initComponents() {
        purchaserItemListingView.getAddItemButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prodName = purchaserItemListingView.getProdField().getText();
                boolean success = purchaserItemListingModel.OnCreate(oIs, oOs, prodName);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Success creation of item ", null, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed creation of item", null, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public PurchaserItemListingView getPurchaserItemListingView() {
        return purchaserItemListingView;
    }
}
