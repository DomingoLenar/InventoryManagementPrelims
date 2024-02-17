package client.common.controllers;

import client.admin.controllers.AdminDashboardController;
import client.admin.controllers.AdminFinancesController;
import client.admin.controllers.AdminNavigationBarController;
import client.common.views.InventoryManagementInterface;
import client.controllers.DashboardController;
import client.controllers.FinancesController;
import client.controllers.NavigationBarController;
import client.purchaser.controllers.PurchaserDashboardController;
import client.purchaser.controllers.PurchaserNavigationBarController;
import client.sales.controllers.SalesDashboardController;
import client.sales.controllers.SalesFinancesController;
import client.sales.controllers.SalesNavigationBarController;

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
    AdminDashboardController adminDashboardController;
    AdminNavigationBarController adminNavigationBarController;
    AdminFinancesController adminFinancesController;
    SalesDashboardController salesDashboardController;
    SalesNavigationBarController salesNavigationBarController;
    SalesFinancesController salesFinancesController;
    PurchaserDashboardController purchaserDashboardController;
    PurchaserNavigationBarController purchaserNavigationBarController;
    private Socket clientSocket;
    public InventoryManagementController() throws IOException {
        inventoryManagementInterface = new InventoryManagementInterface();

        try {
            clientSocket = new Socket("localhost", 2018);
        } catch (IOException e) {
            e.printStackTrace();
        }

        initControllers();

        displayIndexPanel(); // main panel of the application
        
        initComponents();
    }

    private void initComponents() {
        mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
    }

    private void initControllers() throws IOException {
        adminControllers();
        salesControllers();
        purchaserControllers();
        commonControllers();
    }

    private void commonControllers() throws IOException {
        indexController = new IndexController(this);
        signUpController = new SignUpController(this, clientSocket);
        loginController = new LoginController(this, clientSocket);
        navigationBarController = new NavigationBarController(this);
        dashboardController = new DashboardController(this);
        financesController = new FinancesController(this);
    }

    private void purchaserControllers() {
        purchaserDashboardController = new PurchaserDashboardController(this);
        purchaserNavigationBarController = new PurchaserNavigationBarController(this);
    }

    private void salesControllers() {
        salesDashboardController = new SalesDashboardController(this);
        salesNavigationBarController = new SalesNavigationBarController(this);
        salesFinancesController = new SalesFinancesController(this);
    }

    private void adminControllers() {
        adminDashboardController = new AdminDashboardController(this);
        adminNavigationBarController = new AdminNavigationBarController(this);
        adminFinancesController = new AdminFinancesController(this);
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

    public FinancesController getFinancesController() {
        return financesController;
    }

    public SalesDashboardController getSalesDashboardController() {
        return salesDashboardController;
    }

    public AdminDashboardController getAdminDashboardController() {
        return adminDashboardController;
    }

    public PurchaserDashboardController getPurchaserDashboardController() {
        return purchaserDashboardController;
    }

    public AdminNavigationBarController getAdminNavigationBarController() {
        return adminNavigationBarController;
    }

    public PurchaserNavigationBarController getPurchaserNavigationBarController() {
        return purchaserNavigationBarController;
    }

    public SalesNavigationBarController getSalesNavigationBarController() {
        return salesNavigationBarController;
    }

    public AdminFinancesController getAdminFinancesController() {
        return adminFinancesController;
    }

    public SalesFinancesController getSalesFinancesController() {
        return salesFinancesController;
    }

    public void displayAdminMainMenu() {
        SwingUtilities.invokeLater(() -> {
            inventoryManagementInterface.getContentPane().removeAll();
            inventoryManagementInterface.add(mainContainer);
            mainContainer.add(getAdminNavigationBarController().getAdminNavigationBarView().getLeftPanel(), BorderLayout.WEST);
            mainContainer.add(getAdminDashboardController().getAdminDashboardView().getMainPanel(), BorderLayout.CENTER);
            inventoryManagementInterface.revalidate();
            inventoryManagementInterface.repaint();
        });
    }

    public void displaySalesMainMenu() {
        SwingUtilities.invokeLater(() -> {
            inventoryManagementInterface.getContentPane().removeAll();
            inventoryManagementInterface.add(mainContainer);
            mainContainer.add(getSalesNavigationBarController().getSalesNavigationBarView().getLeftPanel(), BorderLayout.WEST);
            mainContainer.add(getSalesDashboardController().getSalesDashboardView().getMainPanel(), BorderLayout.CENTER);
            inventoryManagementInterface.revalidate();
            inventoryManagementInterface.repaint();
        });
    }

    public void displayPurchaserMainMenu() {
        SwingUtilities.invokeLater(() -> {
            inventoryManagementInterface.getContentPane().removeAll();
            inventoryManagementInterface.add(mainContainer);
            mainContainer.add(getPurchaserNavigationBarController().getPurchaserNavigationBarView().getLeftPanel(), BorderLayout.WEST);
            mainContainer.add(getPurchaserDashboardController().getPurchaserDashboardView().getMainPanel(), BorderLayout.CENTER);
            inventoryManagementInterface.revalidate();
            inventoryManagementInterface.repaint();
        });
    }
}
