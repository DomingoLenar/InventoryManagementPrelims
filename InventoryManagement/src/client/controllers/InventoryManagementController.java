package client.controllers;

import client.InventoryManagementInterface;

import javax.swing.*;

/**
 * Notes: big controller <-> panel controllers
 *
 * Helper function:
 * - changeScreen() method: a shortcut for display[...] () methods
 */

public class InventoryManagementController { // big controller
    InventoryManagementInterface inventoryManagementInterface;
    IndexController indexController;
    SignUpController signUpController;
    LoginController loginController;
    public InventoryManagementController() {
        inventoryManagementInterface = new InventoryManagementInterface();
        initControllers();

        displayIndexPanel(); // main panel of the application

    }
    private void initControllers() {
        indexController = new IndexController(this);
        signUpController = new SignUpController(this);
        loginController = new LoginController(this);
    }

    /** EDT - background thread to process abstract window toolkit (AWT) events or GUI
     * - Each AWT events/functions should be process by one EDT background thread
     */
    private void displayIndexPanel() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                inventoryManagementInterface.add(indexController.getIndexView().getIVpanel());
                inventoryManagementInterface.revalidate();
                inventoryManagementInterface.repaint();
            }
        });
    }

    public void displayLoginView(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                inventoryManagementInterface.getContentPane().removeAll();
                inventoryManagementInterface.add(getLoginController().getLoginView().getLVpanel());
                inventoryManagementInterface.revalidate();
                inventoryManagementInterface.repaint();
            }
        });

    }

    public void displaySignUpView() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                inventoryManagementInterface.getContentPane().removeAll();
                inventoryManagementInterface.add(getSignUpController().getSignUpView().getSUpanel());
                inventoryManagementInterface.revalidate();
                inventoryManagementInterface.repaint();
            }
        });
    }

    public IndexController getIndexController() {
        return indexController;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public SignUpController getSignUpController() {
        return signUpController;
    }

    /**
     * Note: This method functionality is switching of panels which is an abstraction of display[...] () method.
     * @param currentPanel
     */
    public void changeScreen(JPanel currentPanel) {

    }
}
