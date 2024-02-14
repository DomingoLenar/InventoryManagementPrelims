package client.controllers;

import client.InventoryManagementInterface;
import client.views.IndexView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexController {
//    InventoryManagementInterface inventoryManagementInterface;
    InventoryManagementController inventoryManagementController;
    IndexView indexView;

    public IndexController(InventoryManagementController inventoryManagementController) {
//        this.inventoryManagementInterface = inventoryManagementInterface;
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
//                inventoryManagementController.changeScreen(inventoryManagementController.loginController.loginView.getLVpanel());
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
