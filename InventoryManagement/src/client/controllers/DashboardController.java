package client.controllers;

import client.views.DashboardView;

public class DashboardController {
    DashboardView dashboardView;

    public DashboardController(InventoryManagementController inventoryManagementController){
        dashboardView = new DashboardView();

        initComponents();
    }

    private void initComponents() {
        initButtons();
    }

    private void initButtons() {
    }
}
