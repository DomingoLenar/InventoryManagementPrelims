package client.common.controllers;

import client.StockControlCallbackClass;
import client.admin.controllers.*;
import client.common.views.InventoryManagementInterface;
import client.purchaser.controllers.*;
import client.sales.controllers.*;
import shared.StockControlCallback;
import shared.StockControlServer;
import utility.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Notes: big controller <-> panel controllers
 * Helper function:
 * - changeScreen() method: a shortcut for display[...] () methods
 */

public class InventoryManagementController extends UnicastRemoteObject implements StockControlCallback, Serializable { // big controller
    InventoryManagementInterface inventoryManagementInterface;
    IndexController indexController;
    SignUpController signUpController;
    LoginController loginController;
    AdminDashboardController adminDashboardController;
    AdminNavigationBarController adminNavigationBarController;
    AdminFinancesController adminFinancesController;
    AdminStockControlController adminStockControlController;
    AdminUserManagementController adminUserManagementController;
    AdminAddItemController adminAddItemController;
    AdminCreateSalesInvoiceController adminCreateSalesInvoiceController;
    SalesDashboardController salesDashboardController;
    SalesNavigationBarController salesNavigationBarController;
    SalesCreateCustomerOrderController salesCreateCustomerOrderController;
    SalesCustomerOrderManagementController salesCustomerOrderManagementController;
    @Deprecated
    SalesFinancesController salesFinancesController;
    SalesStockMonitorController salesStockMonitorController;
    SalesCreateSalesInvoiceController salesCreateSalesInvoiceController;
    SalesSalesInvoicesController salesSalesInvoicesController;
    PurchaserDashboardController purchaserDashboardController;
    PurchaserNavigationBarController purchaserNavigationBarController;
    PurchaserStockMonitorController purchaserStockMonitorController;
    PurchaserCreatePurchaseOrderController purchaserCreatePurchaseOrderController;
    PurchaserItemListingController purchaserItemListingController;
    UserSettingsController userSettingsController;
    ChangePasswordController changePasswordController;
    private Socket clientSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private StockControlCallbackClass stockControlCallbackClass;
    private StockControlServer stub;
    public InventoryManagementController(StockControlServer stub) throws IOException, InstantiationException, IllegalAccessException {
        inventoryManagementInterface = new InventoryManagementInterface();
        stockControlCallbackClass = new StockControlCallbackClass();
        this.stub = stub;
//
//        try {
////            clientSocket = new Socket("lestatheh", 2018); // tailscale vpn
////            clientSocket = new Socket("localhost", 2020); // local terminal
////            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
////            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        initControllers();
        stockControlCallbackClass.index(displayIndexPanel()); // caller
    }
    @Override
    public User getUser() throws RemoteException {
        return null;
    }

    @Override
    public void loginCall(User user) throws RemoteException {

    }

    @Override
    public void broadcastCall(User user, String msg) throws RemoteException {

    }

    @Override
    public void logoutCall(User user) throws RemoteException {
    }

    @Override
    public void index(Void o) throws RemoteException{
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
        loginController = new LoginController(this, stockControlCallbackClass,stub );
        userSettingsController = new UserSettingsController(this, objectInputStream, objectOutputStream);
        changePasswordController = new ChangePasswordController(this, objectInputStream, objectOutputStream);
    }

    private void purchaserControllers(){
        purchaserDashboardController = new PurchaserDashboardController(this, objectInputStream, objectOutputStream);
        purchaserNavigationBarController = new PurchaserNavigationBarController(this);
        purchaserStockMonitorController = new PurchaserStockMonitorController(this, objectInputStream, objectOutputStream);
        purchaserCreatePurchaseOrderController = new PurchaserCreatePurchaseOrderController(this, objectInputStream, objectOutputStream);
        purchaserItemListingController = new PurchaserItemListingController(this, objectInputStream, objectOutputStream);
    }

    private void salesControllers() {
        salesDashboardController = new SalesDashboardController(this, objectInputStream, objectOutputStream);
        salesNavigationBarController = new SalesNavigationBarController(this);
        salesFinancesController = new SalesFinancesController(this, objectInputStream, objectOutputStream);
        salesStockMonitorController = new SalesStockMonitorController(this, objectInputStream, objectOutputStream);
        salesCreateSalesInvoiceController = new SalesCreateSalesInvoiceController(this, objectInputStream, objectOutputStream);
        salesSalesInvoicesController = new SalesSalesInvoicesController(this, objectInputStream, objectOutputStream);
        salesCreateCustomerOrderController = new SalesCreateCustomerOrderController(this, objectInputStream, objectOutputStream);
        salesCustomerOrderManagementController = new SalesCustomerOrderManagementController(this, objectOutputStream, objectInputStream);
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
                inventoryManagementInterface.getMainContainer().remove(1);
                inventoryManagementInterface.getMainContainer().add(currentPanel);
                inventoryManagementInterface.getMainContainer().revalidate();
                inventoryManagementInterface.getMainContainer().repaint();
            }
        });
    }

    /** EDT - background thread to process abstract window toolkit (AWT) events or GUI
     * - Each AWT events/functions should be process by one EDT background thread
     */
    public Void displayIndexPanel() throws InstantiationException, IllegalAccessException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                inventoryManagementInterface.getMainContainer().removeAll();
                inventoryManagementInterface.getMainContainer().add(indexController.getIndexView().getIVpanel());
                inventoryManagementInterface.getMainContainer().revalidate();
                inventoryManagementInterface.getMainContainer().repaint();
            }
        });
        return null;
    }

    public void displayLoginView(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                inventoryManagementInterface.getMainContainer().removeAll();
                inventoryManagementInterface.getMainContainer().add(getLoginController().getLoginView().getLVpanel());
                inventoryManagementInterface.getMainContainer().revalidate();
                inventoryManagementInterface.getMainContainer().repaint();
            }
        });

    }

    @Deprecated
    public void displaySignUpView() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                inventoryManagementInterface.getMainContainer().removeAll();
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

    public SalesStockMonitorController getSalesStockMonitorController() {
        return salesStockMonitorController;
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

    @Deprecated
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

    public PurchaserStockMonitorController getPurchaserStockControlController() {
        return purchaserStockMonitorController;
    }

    public PurchaserCreatePurchaseOrderController getPurchaserCreatePurchaseOrderController() {
        return purchaserCreatePurchaseOrderController;
    }

    public SalesCreateCustomerOrderController getSalesCreateCustomerOrderController() {
        return salesCreateCustomerOrderController;
    }

    public SalesCustomerOrderManagementController getSalesCustomerOrderManagementController() {
        return salesCustomerOrderManagementController;
    }

    public PurchaserItemListingController getPurchaserItemListingController() {
        return purchaserItemListingController;
    }

    public void displayAdminMainMenu() {
        SwingUtilities.invokeLater(() -> {
            inventoryManagementInterface.getMainContainer().removeAll();
            inventoryManagementInterface.getMainContainer().add(getAdminNavigationBarController().getAdminNavigationBarView().getLeftPanel(), BorderLayout.WEST);
            inventoryManagementInterface.getMainContainer().add(getAdminDashboardController().getAdminDashboardView().getMainPanel(), BorderLayout.CENTER);
//            getAdminDashboardController().initComponents();
            inventoryManagementInterface.getMainContainer().revalidate();
            inventoryManagementInterface.getMainContainer().repaint();
        });
    }

    public void displaySalesMainMenu() {
        SwingUtilities.invokeLater(() -> {
             // a band aid TODO: bug: displaying the user settings view instead of main menu
            inventoryManagementInterface.getMainContainer().removeAll();
            inventoryManagementInterface.getMainContainer().add(getSalesNavigationBarController().getSalesNavigationBarView().getLeftPanel(), BorderLayout.WEST);
            inventoryManagementInterface.getMainContainer().add(getSalesDashboardController().getSalesDashboardView().getMainPanel(), BorderLayout.CENTER);
            getSalesDashboardController().initComponents();
            inventoryManagementInterface.getMainContainer().revalidate();
            inventoryManagementInterface.getMainContainer().repaint();
        });
    }

    public void displayPurchaserMainMenu() {
        SwingUtilities.invokeLater(() -> {
            inventoryManagementInterface.getMainContainer().removeAll();
            inventoryManagementInterface.getMainContainer().add(getPurchaserNavigationBarController().getPurchaserNavigationBarView().getLeftPanel(), BorderLayout.WEST);
            inventoryManagementInterface.getMainContainer().add(getPurchaserDashboardController().getPurchaserDashboardView().getMainPanel(), BorderLayout.CENTER);
            getPurchaserDashboardController().initComponents();
            // TODO: initComponents of purchaser user in EDT thread
            inventoryManagementInterface.getMainContainer().revalidate();
            inventoryManagementInterface.getMainContainer().repaint();
        });
    }

    public InventoryManagementInterface getInventoryManagementInterface() {
        return inventoryManagementInterface;
    }

}
