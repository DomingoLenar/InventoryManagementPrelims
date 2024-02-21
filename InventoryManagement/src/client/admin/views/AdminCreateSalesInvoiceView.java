package client.admin.views;

import client.common.views.GradientPanel;

import javax.swing.*;

public class AdminCreateSalesInvoiceView {
    private JTextField productPrice;
    private JTextField quantity;
    private JTextField estimatedTotal;
    private JButton cancelButton;
    private JButton createSalesInvoiceButton;
    private JPanel mainPanel;
    private JTextField productName;

    public JButton getCreateSalesInvoiceButton() {
        return createSalesInvoiceButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }


    public JTextField getEstimatedTotal() {
        return estimatedTotal;
    }

    public JTextField getProductPrice() {
        return productPrice;
    }

    public JTextField getQuantity() {
        return quantity;
    }

    public JTextField getProductName() {
        return productName;
    }

    private void createUIComponents() {
        mainPanel = new GradientPanel();
    }
}
