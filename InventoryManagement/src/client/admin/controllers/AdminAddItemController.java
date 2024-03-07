package client.admin.controllers;

import client.admin.views.AdminAddItemView;
import client.common.controllers.InventoryManagementController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminAddItemController {
    AdminAddItemView adminAddItemView;
    InventoryManagementController inventoryManagementController;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public AdminAddItemController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;
        adminAddItemView = new AdminAddItemView();
        objectInputStream = oIs;
        objectOutputStream = oOs;

        initButtons();
    }

    private void initButtons() {
        adminAddItemView.getAddItemButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pName = adminAddItemView.getProductName().getText();
                float pPrice = Float.parseFloat(adminAddItemView.getProductPrice().getText());
                int qty = Integer.parseInt(adminAddItemView.getQuantity().getText());

//                ItemManagementModel.addItems(pName, qty, "warehouse", 13, pPrice, objectOutputStream, objectInputStream);
//                ItemManagementModel.addItemOrders(13, inventoryManagementController.getFormattedDate(), pPrice, "purchase", 13, inventoryManagementController.getUsername(),
//                        qty, objectOutputStream, objectInputStream);
            }
        });

        adminAddItemView.getCancelbtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getAdminStockControlController().adminStockControlView.getMainPanel());
            }
        });
    }

    public AdminAddItemView getAdminAddItemView() {
        return adminAddItemView;
    }
}
