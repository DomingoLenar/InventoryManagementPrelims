package client.controllers;

import client.InventoryManagementInterface;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

/**
 * Notes: big controller <-> panel controllers
 * Helper function:
 * - changeScreen() method: a shortcut for display[...] () methods
 */

public class InventoryManagementController { // big controller
    JPanel mainContainer;
    InventoryManagementInterface inventoryManagementInterface;
    IndexController indexController;
    SignUpController signUpController;
    LoginController loginController;
    NavigationBarController navigationBarController;
    DashboardController dashboardController;
    FinancesController financesController;
    private Socket clientSocket;
    public InventoryManagementController() throws IOException {
        inventoryManagementInterface = new InventoryManagementInterface();

        try {
            clientSocket = new Socket("localhost", 2018);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        InventoryManagementController.startClient();

        initControllers();

        displayIndexPanel(); // main panel of the application
        
        initComponents();

    }

//    public static void startClient(){
//        try {
//            Socket sSocket = new Socket("localhost", 2018);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(sSocket.getOutputStream());
//            ObjectInputStream objectInputStream = new ObjectInputStream(sSocket.getInputStream());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private void initComponents() {
        mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());

    }

    private void initControllers() throws IOException {
        indexController = new IndexController(this);
        signUpController = new SignUpController(this);
        loginController = new LoginController(this, clientSocket);
//        loginController = new LoginController(this);
        navigationBarController = new NavigationBarController(this);
        dashboardController = new DashboardController(this);
        financesController = new FinancesController(this);
    }

    /**
     * Note: This method functionality is switching of panels which is an abstraction of display[...] () method.
     * @param currentPanel
     */
    public void changeScreen(JPanel currentPanel) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainContainer.remove(1);
                mainContainer.add(currentPanel);
                mainContainer.revalidate();
                mainContainer.repaint();
            }
        });
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

    public NavigationBarController getNavigationBarController() {
        return navigationBarController;
    }

    public DashboardController getDashboardController() {
        return dashboardController;
    }

    /**
     * Main menu consist two panels; the dashboard and navigation
     */
    public void displayMainMenu() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                inventoryManagementInterface.getContentPane().removeAll();
                inventoryManagementInterface.add(mainContainer);
                mainContainer.add(getNavigationBarController().navigationBarView.getLeftPanel(), BorderLayout.WEST);
                mainContainer.add(getDashboardController().dashboardView.getMainPanel(), BorderLayout.CENTER);
                inventoryManagementInterface.revalidate();
                inventoryManagementInterface.repaint();
            }
        });

    }
}
