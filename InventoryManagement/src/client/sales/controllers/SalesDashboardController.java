package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.views.SalesDashboardView;

public class SalesDashboardController {
    SalesDashboardView salesDashboardView;

    public SalesDashboardController(InventoryManagementController inventoryManagementController) {
        salesDashboardView = new SalesDashboardView();
    }

    public SalesDashboardView getSalesDashboardView() {
        return salesDashboardView;
    }
}
