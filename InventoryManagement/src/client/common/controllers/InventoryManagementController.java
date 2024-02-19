package client.common.controllers;

import client.admin.controllers.*;
import client.common.views.InventoryManagementInterface;
import client.deprecated.controllers.DashboardController;
import client.deprecated.controllers.FinancesController;
import client.deprecated.controllers.NavigationBarController;
import client.purchaser.controllers.PurchaserAddItemController;
import client.purchaser.controllers.PurchaserDashboardController;
import client.purchaser.controllers.PurchaserNavigationBarController;
import client.purchaser.controllers.PurchaserStockControlController;
import client.sales.controllers.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    AdminStockControlController adminStockControlController;
    AdminUserManagementController adminUserManagementController;
    AdminAddItemController adminAddItemController;
    AdminCreateSalesInvoiceController adminCreateSalesInvoiceController;
    SalesDashboardController salesDashboardController;
    SalesNavigationBarController salesNavigationBarController;
    SalesFinancesController salesFinancesController;
    SalesStockControlController salesStockControlController;
    SalesCreateSalesInvoiceController salesCreateSalesInvoiceController;
    SalesSalesInvoicesController salesSalesInvoicesController;
    PurchaserDashboardController purchaserDashboardController;
    PurchaserNavigationBarController purchaserNavigationBarController;
    PurchaserStockControlController purchaserStockControlController;
    PurchaserAddItemController purchaserAddItemController;
    UserSettingsController userSettingsController;
    ChangePasswordController changePasswordController;
    private Socket clientSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    String formattedDate;
    String userType;
    String username;
    public InventoryManagementController() throws IOException {
        inventoryManagementInterface = new InventoryManagementInterface();


        try {
            clientSocket = new Socket("localhost", 2018);
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        initControllers();

        displayIndexPanel(); // main panel of the application

        initComponents();
    }

    private void initComponents() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Format the date using a specific pattern (optional)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formattedDate = currentDate.format(formatter);

        // Display the current date
        System.out.println("Current Date: " + formattedDate);

        mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private void initControllers() throws IOException {
        adminControllers();
        salesControllers();
        purchaserControllers();
        commonControllers();
    }

    private void commonControllers() throws IOException {
        indexController = new IndexController(this);
        signUpController = new SignUpController(this, objectInputStream, objectOutputStream);
        loginController = new LoginController(this, objectInputStream, objectOutputStream);
        userSettingsController = new UserSettingsController(this, objectInputStream, objectOutputStream);
        navigationBarController = new NavigationBarController(this);
        dashboardController = new DashboardController(this);
        financesController = new FinancesController(this);
        changePasswordController = new ChangePasswordController(this, objectInputStream, objectOutputStream);
    }

    private void purchaserControllers() {
        purchaserDashboardController = new PurchaserDashboardController(this, objectInputStream, objectOutputStream);
        purchaserNavigationBarController = new PurchaserNavigationBarController(this);
        purchaserStockControlController = new PurchaserStockControlController(this, objectInputStream, objectOutputStream);
        purchaserAddItemController = new PurchaserAddItemController(this, objectInputStream, objectOutputStream);
    }

    private void salesControllers() {
        salesDashboardController = new SalesDashboardController(this, objectInputStream, objectOutputStream);
        salesNavigationBarController = new SalesNavigationBarController(this);
        salesFinancesController = new SalesFinancesController(this, objectInputStream, objectOutputStream);
        salesStockControlController = new SalesStockControlController(this, objectInputStream, objectOutputStream);
        salesCreateSalesInvoiceController = new SalesCreateSalesInvoiceController(this, objectInputStream, objectOutputStream);
        salesSalesInvoicesController = new SalesSalesInvoicesController(this, objectInputStream, objectOutputStream);
    }

    private void adminControllers() {
        adminDashboardController = new AdminDashboardController(this, objectInputStream, objectOutputStream);
        adminNavigationBarController = new AdminNavigationBarController(this);
        adminFinancesController = new AdminFinancesController(this, objectInputStream, objectOutputStream);
        adminStockControlController = new AdminStockControlController(this, objectInputStream, objectOutputStream);
        adminAddItemController = new AdminAddItemController(this, objectInputStream, objectOutputStream);
        adminCreateSalesInvoiceController = new AdminCreateSalesInvoiceController(this, objectInputStream, objectOutputStream);
        adminUserManagementController = new AdminUserManagementController(this, objectInputStream, objectOutputStream);
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
    public void displayIndexPanel() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                inventoryManagementInterface.getContentPane().removeAll();
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

    public AdminStockControlController getAdminStockControlController() {
        return adminStockControlController;
    }

    public AdminUserManagementController getAdminUserManagementController() {
        return adminUserManagementController;
    }

    public AdminAddItemController getAdminAddItemController() {
        return adminAddItemController;
    }

    public AdminCreateSalesInvoiceController getAdminCreateSalesInvoiceController() {
        return adminCreateSalesInvoiceController;
    }

    public SalesStockControlController getSalesStockControlController() {
        return salesStockControlController;
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

    public SalesCreateSalesInvoiceController getSalesCreateSalesInvoiceController() {
        return salesCreateSalesInvoiceController;
    }

    public UserSettingsController getUserSettingsController() {
        return userSettingsController;
    }

    public SalesSalesInvoicesController getSalesSalesInvoicesController() {
        return salesSalesInvoicesController;
    }

    public ChangePasswordController getChangePasswordController() {
        return changePasswordController;
    }

    public PurchaserStockControlController getPurchaserStockControlController() {
        return purchaserStockControlController;
    }

    public PurchaserAddItemController getPurchaserAddItemController() {
        return purchaserAddItemController;
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
            getSalesDashboardController().initComponents();
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
