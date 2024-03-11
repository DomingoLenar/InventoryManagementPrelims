package client.purchase.views;

import client.common.views.GradientPanel;

import javax.swing.*;

public class PurchaserStockControlView {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel searchPanel;
    private JTextField searchField;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton addItemButton;
    private JPanel centerPanel;
    private JTable purchaserTable;
    private JScrollPane puchaserScrollPane;
    private JButton lowStockButton;

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

    public JButton getAddItemButton() {
        return addItemButton;
    }

    public JTable getPurchaserTable() {
        return purchaserTable;
    }

    public JScrollPane getPuchaserScrollPane() {
        return puchaserScrollPane;
    }

    private void createUIComponents() {
        mainPanel = new JPanel();
        centerPanel = new GradientPanel();
    }
}
