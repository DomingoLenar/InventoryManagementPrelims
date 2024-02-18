package client.admin.controllers;

import client.admin.views.AdminNavigationBarView;
import client.common.controllers.InventoryManagementController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminNavigationBarController {
    InventoryManagementController inventoryManagementController;
    AdminNavigationBarView adminNavigationBarView;

    public AdminNavigationBarController(InventoryManagementController inventoryManagementController) {
        this.inventoryManagementController = inventoryManagementController;
        adminNavigationBarView = new AdminNavigationBarView();

        initButtons();
    }

    private void initButtons() {
        adminNavigationBarView.getDashboardButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getAdminDashboardController().adminDashboardView.getMainPanel());
            }
        });
        adminNavigationBarView.getFinancesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getAdminFinancesController().adminFinancesView.getMainPanel());
            }
        });
        adminNavigationBarView.getStockControlButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getAdminStockControlController().getAdminStockControlView().getMainPanel());
            }
        });
        adminNavigationBarView.getProfileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getUserSettingsController().getUserSettingsView().getMainPanel());
            }
        });
        adminNavigationBarView.getSalesInvoicesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: create an appropriate panel
            }
        });
        adminNavigationBarView.getUserManagementButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getAdminUserManagementController().getAdminUserManagementView().getMainPanel());
            }
        });
    }

    public AdminNavigationBarView getAdminNavigationBarView() {
        return adminNavigationBarView;
    }
}
