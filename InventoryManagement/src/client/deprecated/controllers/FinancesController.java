package client.deprecated.controllers;

import client.common.controllers.InventoryManagementController;
import client.views.FinancesView;

public class FinancesController {
    InventoryManagementController inventoryManagementController;
    FinancesView financesView;

    public FinancesController(InventoryManagementController inventoryManagementController) {
        this.inventoryManagementController = inventoryManagementController;
        financesView = new FinancesView();

        initComponents();
    }

    private void initComponents() {
    }
}
