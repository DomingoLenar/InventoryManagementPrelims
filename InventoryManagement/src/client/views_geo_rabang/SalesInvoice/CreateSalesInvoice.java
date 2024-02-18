package client.views_geo_rabang.SalesInvoice;

import javax.swing.*;

public class CreateSalesInvoice {
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
    private JButton createButton;

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getCreateButton() {
        return createButton;
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
