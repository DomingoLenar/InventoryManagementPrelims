package client.purchaser.controllers;

import client.common.controllers.InventoryManagementController;
import client.purchaser.views.PurchaserStockControlView;

import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PurchaserStockMonitorController {
    InventoryManagementController inventoryManagementController;
    PurchaserStockControlView purchaserStockControlView;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public PurchaserStockMonitorController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs)  {
        this.inventoryManagementController = inventoryManagementController;
        purchaserStockControlView = new PurchaserStockControlView();
        this.objectInputStream = oIs;
        this.objectOutputStream = oOs;
        
        initComponents();
    }

    private void initComponents() {
        TableModel model = purchaserStockControlView.getStockTable().getModel();
        int col = model.getColumnCount();
        purchaserStockControlView.getPurchaseOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                int selectedRow = purchaserStockControlView.getStockTable().getSelectedRow();
//                if (selectedRow != -1) {}
                inventoryManagementController.changeScreen(inventoryManagementController.getPurchaserAddItemController().getPurchaserAddItemView().getMainPanel());
            }
        });
        purchaserStockControlView.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        purchaserStockControlView.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // TODO: implement search logic and populate purchaser jtable
        purchaserStockControlView.getSearchField();

//        initTables();
//        initButtons();
    }

    private void initTables() {
    }

    private void initButtons() {
    }
}
