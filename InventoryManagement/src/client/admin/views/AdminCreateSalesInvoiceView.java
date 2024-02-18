package client.admin.views;

import javax.swing.*;

public class AdminCreateSalesInvoiceView {
    private JLabel salesInvoice;
    private JTextField prdct;
    private JTextField qty;
    private JTextField prc;
    private JTextField estTotal;
    private JLabel product;
    private JLabel quantity;
    private JLabel price;
    private JLabel estimatedTotal;
    private JButton cancelButton;
    private JButton createSalesInvoiceButton;
    private JPanel mainPanel;

    public JButton getCreateSalesInvoiceButton() {
        return createSalesInvoiceButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }


    public JLabel getEstimatedTotal() {
        return estimatedTotal;
    }

    public JLabel getPrice() {
        return price;
    }

    public JLabel getProduct() {
        return product;
    }

    public JLabel getQuantity() {
        return quantity;
    }

    public JLabel getSalesInvoice() {
        return salesInvoice;
    }

    public JTextField getEstTotal() {
        return estTotal;
    }

    public JTextField getPrc() {
        return prc;
    }

    public JTextField getPrdct() {
        return prdct;
    }

    public JTextField getQty() {
        return qty;
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
