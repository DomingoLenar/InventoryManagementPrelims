package client.admin.controllers;

import client.admin.models.AdminUserManagementModel;
import client.admin.views.AdminUserManagementView;
import client.common.controllers.InventoryManagementController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class AdminUserManagementController {
    AdminUserManagementModel adminUserManagementModel;
    AdminUserManagementView adminUserManagementView;
    InventoryManagementController inventoryManagementController;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public AdminUserManagementController(InventoryManagementController inventoryManagementController,ObjectInputStream oIs,ObjectOutputStream oOs){
        this.inventoryManagementController = inventoryManagementController;
        adminUserManagementModel = new AdminUserManagementModel();
        adminUserManagementView = new AdminUserManagementView();
        objectOutputStream = oOs;
        objectInputStream = oIs;

        initComponents();
    }

    private void initComponents() {

        // TODO: ability to create a sales and purchase users
        adminUserManagementView.getCreateUserButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = adminUserManagementView.getUsernameTextField().getText();
                String password = adminUserManagementView.getPasswordTextField().getText();

                if (username.isEmpty()) {
                    // show rLabel below username textfield
                }
                if (password.length() == 0) {
                    // show rLabel below password textfield
                }
                if (username.isEmpty() && password.length() == 0);
                // show rLabel below password textfield
                // show rLabel below username textfield
                if (password.length() < 7); // show rLabel password must be => 8 characters

                if (!username.isEmpty() && password.length() > 7) {
                    String userRole = Objects.requireNonNull(adminUserManagementView.getRoleComboBox().getSelectedItem()).toString();
                    boolean verifier = adminUserManagementModel.process(username, password, userRole, objectOutputStream, objectInputStream);

                    if (verifier) {
                        JOptionPane.showMessageDialog(null, "Account Created");
                    } else {
                        JOptionPane.showMessageDialog(null, "Account already exist!", null, JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

//        adminUserManagementView.getRoleComboBox().addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    userRole = e.getItem().toString();
//                }
//            }
//        });
    }

    public AdminUserManagementView getAdminUserManagementView() {
        return adminUserManagementView;
    }
}
