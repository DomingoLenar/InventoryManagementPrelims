package client.purchaser.controllers;

import client.common.controllers.InventoryManagementController;
import client.purchaser.views.PurchaserNavigationBarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchaserNavigationBarController {
    InventoryManagementController inventoryManagementController;
    PurchaserNavigationBarView purchaserNavigationBarView;
    public PurchaserNavigationBarController (InventoryManagementController inventoryManagementController) {
        this.inventoryManagementController = inventoryManagementController;

        purchaserNavigationBarView = new PurchaserNavigationBarView();

        initButtons();
    }

    private void initButtons() {
        purchaserNavigationBarView.getDashboardButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getPurchaserDashboardController().getPurchaserDashboardView().getMainPanel());
            }
        });

        purchaserNavigationBarView.getProfileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getUserSettingsController().getUserSettingsView().getMainPanel());
            }
        });

        purchaserNavigationBarView.getStockControlButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getPurchaserStockControlController().purchaserStockControlView.getMainPanel());
            }
        });
    }

    public PurchaserNavigationBarView getPurchaserNavigationBarView() {
        return purchaserNavigationBarView;
    }
}
