package client.controllers;

import client.views.NavigationBarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationBarController {
    InventoryManagementController inventoryManagementController;
    NavigationBarView navigationBarView;

    public NavigationBarController(InventoryManagementController inventoryManagementController) {
        this.inventoryManagementController = inventoryManagementController;
        navigationBarView = new NavigationBarView();

        initComponents();
    }

    private void initComponents() {
        initButtons();
    }

    private void initButtons() {
        navigationBarView.getLogobtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                inventoryManagementController.changeScreen();
            }
        });

        navigationBarView.getDashboardButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.dashboardController.dashboardView.getMainPanel());
            }
        });

        navigationBarView.getStockControlButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        navigationBarView.getFinancesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.financesController.financesView.getMainPanel());
            }
        });

        navigationBarView.getProfileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
