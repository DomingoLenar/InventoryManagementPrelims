package client.purchaser.views;

import client.common.views.GradientPanel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PurchaserStockControlView {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel searchPanel;
    private JTextField searchField;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton purchaseOrderButton;
    private JPanel centerPanel;
    private JTable stockTable;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getPurchaseOrderButton() {
        return purchaseOrderButton;
    }

    public JTable getStockTable() {
        return stockTable;
    }


    private void createUIComponents() {
        centerPanel = new GradientPanel();

        DefaultTableModel model = new DefaultTableModel();
        stockTable = new JTable(model);

        model.addColumn("Product");
        model.addColumn("Purchase Price");
        model.addColumn("Quantity");
//        model.addColumn("Reorder Level"); // minimum quantity at which the system should trigger a reorder

        for (int i=0; i<=20; i++){
            model.addRow(new Object[] {
                    "product" + i , 10 + i +"Peso", i
            });
        }

    }
}
