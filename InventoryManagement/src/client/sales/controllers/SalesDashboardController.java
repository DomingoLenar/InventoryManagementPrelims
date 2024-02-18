package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.views.SalesDashboardView;

import java.net.Socket;

public class SalesDashboardController {
    SalesDashboardView salesDashboardView;

    public SalesDashboardController(InventoryManagementController inventoryManagementController, Socket clientSocket) {
        salesDashboardView = new SalesDashboardView();
    }

    public SalesDashboardView getSalesDashboardView() {
        return salesDashboardView;
    }
}
