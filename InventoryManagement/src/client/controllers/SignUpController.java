package client.controllers;

import client.InventoryManagementInterface;
import client.views.SignUpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpController {
    InventoryManagementController inventoryManagementController;
    SignUpView signUpView;

    public SignUpController(InventoryManagementController inventoryManagementController) {
        this.inventoryManagementController = inventoryManagementController;
        signUpView = new SignUpView();

        initComponents();
    }

    private void initComponents() {
        initButtons();
    }

    private void initButtons() {
        signUpView.getSUsignUpBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.displayMainMenu();
//                String username=signUpView.getSUusernameField().getText();
//                char[] password=signUpView.getSUpasswordField().getPassword();
//
//                if (username.isEmpty()) {
//                    // show rLabel below username textfield
//                }
//                if (password.length == 0) {
//                    // show rLabel below password textfield
//                }
//                if (username.isEmpty() && password.length == 0);
//                // show rLabel below password textfield
//                // show rLabel below username textfield
//                if (password.length < 7); // show rLabel password must be => 8 characters
            }
        });

    }

    public SignUpView getSignUpView() {
        return signUpView;
    }
}
