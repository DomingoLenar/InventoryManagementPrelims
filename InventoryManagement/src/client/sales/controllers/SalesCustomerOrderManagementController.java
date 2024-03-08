package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.models.SalesCustomerOrderManagementModel;
import client.sales.views.SalesCustomerOrderManagementView;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SalesCustomerOrderManagementController {
    SalesCustomerOrderManagementModel salesCustomerOrderManagementModel;
    InventoryManagementController inventoryManagementController;
    SalesCustomerOrderManagementView salesCustomerOrderManagementView;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public SalesCustomerOrderManagementController(InventoryManagementController inventoryManagementController, ObjectOutputStream oOs, ObjectInputStream oIs){
        salesCustomerOrderManagementModel = new SalesCustomerOrderManagementModel(oOs, oIs);
        this.inventoryManagementController = inventoryManagementController;
        salesCustomerOrderManagementView = new SalesCustomerOrderManagementView();
        objectInputStream = oIs;
        objectOutputStream = oOs;
        
        initComponents();
    }

    private void initComponents() {
        initButtons();
        initTables();
    }

    private void initTables() {
        TableModel tableModel = salesCustomerOrderManagementView.getTable1().getModel();
        int row = tableModel.getRowCount();
        int col = tableModel.getColumnCount();

//        salesCustomerOrderManagementView.getTable1().addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//            }
//        });

        salesCustomerOrderManagementView.getGenerateSalesInvoiceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = salesCustomerOrderManagementView.getTable1().getSelectedRow();
                generateSalesInvoice(row, col, tableModel);

            }
        });

        salesCustomerOrderManagementView.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = salesCustomerOrderManagementView.getTable1().getSelectedRow();
                updateCxOrderDetails(row, col, tableModel);

            }
        });

        salesCustomerOrderManagementView.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = salesCustomerOrderManagementView.getTable1().getSelectedRow();
                deleteCxOrderDetails(row, col, tableModel);

            }
        });


        // TODO: add an event where the selected row is extracted from jtable and do smth about it
//        salesCustomerOrderManagementView.getTable1().
    }

    private void generateSalesInvoice(int row, int col, TableModel tableModel) {
        if (row != -1) {
            String[] cxOrderDetails = new String[col];
            for (int i = 0; i < col; i++) {
                String result = tableModel.getValueAt(row, i).toString();
                cxOrderDetails[i] = result;
            }
            salesCustomerOrderManagementModel.OnGenerate(cxOrderDetails);
        }
        else {
            JOptionPane.showMessageDialog(null, "Please click a customer order before generating sales invoice", "Error Generating Sales Invoice", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void updateCxOrderDetails(int row, int col, TableModel tableModel) {
        if (row != -1) {
            String[] cxOrderDetails = new String[col];
            for (int i = 0; i < col; i++) {
                String result = tableModel.getValueAt(row, i).toString();
                cxOrderDetails[i] = result;
            }
            salesCustomerOrderManagementModel.OnUpdate(cxOrderDetails);
        }
        else {
            JOptionPane.showMessageDialog(null, "Please click a customer order before updation", "Error Updating Customer Order", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCxOrderDetails(int row, int col, TableModel tableModel) {
        if (row != -1) {
            String[] cxOrderDetails = new String[col];
            for (int i = 0; i < col; i++) {
                String result = tableModel.getValueAt(row, i).toString();
                cxOrderDetails[i] = result;
            }
            salesCustomerOrderManagementModel.OnDelete(cxOrderDetails);
        }
        else {
            JOptionPane.showMessageDialog(null, "Please click a customer order before deletion", "Error Deleting Customer Order", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void initButtons() {

    }

    public SalesCustomerOrderManagementView getSalesCustomerOrderManagementView() {
        return salesCustomerOrderManagementView;
    }
}
