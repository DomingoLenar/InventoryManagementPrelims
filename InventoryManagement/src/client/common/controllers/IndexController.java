package client.common.controllers;

import client.common.views.IndexView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexController {
    InventoryManagementController inventoryManagementController;
    IndexView indexView;

    public IndexController(InventoryManagementController inventoryManagementController) {
        this.inventoryManagementController = inventoryManagementController;
        indexView = new IndexView();

        initComponents();

    }

    private void initComponents() {
        initButtons();  // init buttons of IndexView Panel
    }

    private void initButtons() {
        indexView.getIVloginBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.displayLoginView();
            }
        });

        indexView.getSUsignUpBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.displaySignUpView();
            }
        });
    }

    public IndexView getIndexView() {
        return indexView;
    }
}
