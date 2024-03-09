package client.purchaser.views;

import client.common.views.GradientPanel;

import javax.swing.*;

public class PurchaserLowStockView extends JPanel {
    private JTable lowStockTable;
    private JScrollPane lowStockScrollPane;
    private JPanel secondPanel;
    private JLabel lowStocks;
    private JPanel mainPanel; // just copied this from previous views

    public JPanel getMainPanel() { // just copied this from previous views
        return mainPanel;
    }

    public JTable getLowStockTable(){
        return lowStockTable;
    }
    public JScrollPane getLowStockScrollPane(){
        return lowStockScrollPane;
    }
    public JPanel getSecondPanel(){
        return secondPanel;
    }
    public JLabel getLowStocks(){
        return lowStocks;
    }
    private void createUIComponents() {
        mainPanel = new GradientPanel(); // just copied this from previous views
    }
}

