package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.views.SalesNavigationBarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalesNavigationBarController {
    InventoryManagementController inventoryManagementController;
    SalesNavigationBarView salesNavigationBarView;
    public SalesNavigationBarController(InventoryManagementController inventoryManagementController) {
        this.inventoryManagementController = inventoryManagementController;

        salesNavigationBarView = new SalesNavigationBarView();

        initButtons();
    }

    private void initButtons() {
        salesNavigationBarView.getDashboardButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getSalesDashboardController().getSalesDashboardView().getMainPanel());
            }
        });
        salesNavigationBarView.getFinancesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getSalesFinancesController().getSalesFinancesView().getMainPanel());
            }
        });
        salesNavigationBarView.getStockControlButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: implement code
            }
        });
        salesNavigationBarView.getProfileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                inventoryManagementController.changeScreen(inventoryManagementController.get);
            }
        });
    }

    public SalesNavigationBarView getSalesNavigationBarView() {
        return salesNavigationBarView;
    }
}
