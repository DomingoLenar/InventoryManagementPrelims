package client.common.controllers;

import client.common.views.SignUpView;
import client.models.ProfileManagementModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpController {
    InventoryManagementController inventoryManagementController;
    ProfileManagementModel profileManagementModel;
    SignUpView signUpView;

    public SignUpController(InventoryManagementController inventoryManagementController) {
        this.inventoryManagementController = inventoryManagementController;
        this.profileManagementModel = new ProfileManagementModel();

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
                String username=signUpView.getSUusernameField().getText();
                String password=signUpView.getSUpasswordField().getText();

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
                    String userType = profileManagementModel.handleLogin(username, password);
                    switch (userType){
                        case "sales":
                            inventoryManagementController.displaySalesMainMenu();
                            break;
                        case "purchase":
                            inventoryManagementController.displayPurchaserMainMenu();
                            break;
                    }
                }
            }
        });

    }

    public SignUpView getSignUpView() {
        return signUpView;
    }
}
