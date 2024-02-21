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
                // a band aid TODO: construct a better implementation
                inventoryManagementController.getSalesDashboardController().salesDashboardView.getChart().removeSeries("Revenue");
                inventoryManagementController.getSalesDashboardController().salesDashboardView.getChart().removeSeries("Cost");
                inventoryManagementController.getSalesDashboardController().salesDashboardView.getPieChart().removeSeries("Today");
                inventoryManagementController.getSalesDashboardController().salesDashboardView.getPieChart().removeSeries("Annual");
                inventoryManagementController.getSalesDashboardController().initComponents();
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
                inventoryManagementController.changeScreen(inventoryManagementController.getSalesStockControlController().getSalesStockControlView().getMainPanel());
            }
        });
        salesNavigationBarView.getProfileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getUserSettingsController().getUserSettingsView().getMainPanel());
            }
        });
        salesNavigationBarView.getSalesInvoicesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.getSalesSalesInvoicesController().initComponents();
                inventoryManagementController.changeScreen(inventoryManagementController.getSalesSalesInvoicesController().getSalesInvoiceHardCoded().getMainPanel());
            }
        });
    }

    public SalesNavigationBarView getSalesNavigationBarView() {
        return salesNavigationBarView;
    }
}
