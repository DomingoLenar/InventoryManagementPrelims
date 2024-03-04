package client.purchase.controllers;

import client.common.controllers.InventoryManagementController;
import client.purchase.views.PurchaserStockControlView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PurchaserStockControlController {
    InventoryManagementController inventoryManagementController;
    PurchaserStockControlView purchaserStockControlView;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public PurchaserStockControlController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs)  {
        this.inventoryManagementController = inventoryManagementController;
        purchaserStockControlView = new PurchaserStockControlView();
        this.objectInputStream = oIs;
        this.objectOutputStream = oOs;
        
        initComponents();
    }

    private void initComponents() {
        initButtons();
    }

    private void initButtons() {
        purchaserStockControlView.getAddItemButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getPurchaserAddItemController().getPurchaserAddItemView().getMainPanel());
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
        purchaserStockControlView.getPurchaserTable();
    }
}
