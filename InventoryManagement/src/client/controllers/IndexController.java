package client.controllers;

import client.InventoryManagementInterface;
import client.views.IndexView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexController {

    InventoryManagementInterface inventoryManagementInterface;
    InventoryManagementController inventoryManagementController;
    IndexView indexView;

    public IndexController(InventoryManagementInterface inventoryManagementInterface, InventoryManagementController inventoryManagementController) {
        this.inventoryManagementInterface = inventoryManagementInterface;
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
                // EDT - background thread to process abstract window toolkit (AWT) events or GUI
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        inventoryManagementInterface.getContentPane().removeAll();
                        inventoryManagementInterface.changePanel(inventoryManagementController.getLoginController().getLoginView().getLVpanel());
                        inventoryManagementInterface.revalidate();
                        inventoryManagementInterface.repaint();
                    }
                });

            }
        });

        indexView.getSUsignUpBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        inventoryManagementInterface.getContentPane().removeAll();
                        inventoryManagementInterface.changePanel(inventoryManagementController.getSignUpController().getSignUpView().getSUpanel());
                        inventoryManagementInterface.revalidate();
                        inventoryManagementInterface.repaint();
                    }
                });
            }
        });
    }

    public IndexView getIndexView() {
        return indexView;
    }
}
