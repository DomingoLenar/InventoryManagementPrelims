package client.sales.views;

import javax.swing.*;

public class SalesCreateSalesInvoiceView {
    private JTextField productField;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextField estimatedTotalField;
    private JButton cancelButton;
    private JButton createInvoiceButton;
    private JPanel mainPanel;

    public JTextField getEstimatedTotalField() {
        return estimatedTotalField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getProductField() {
        return productField;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getCreateInvoiceButton() {
        return createInvoiceButton;
    }
}
