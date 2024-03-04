package client.sales.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SalesCustomerOrderManagementView {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JTextField searchField;
    private JPanel bottomPanel;
    private JButton generateSalesInvoiceButton;
    private JTable table1;

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getGenerateSalesInvoiceButton() {
        return generateSalesInvoiceButton;
    }

    public JTable getTable1() {
        return table1;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        DefaultTableModel model = new DefaultTableModel();
        table1 = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Customer Name");
        model.addColumn("Date Ordered");
        model.addColumn("Product");
        model.addColumn("Quantity");
        model.addColumn("Price");

        for (int i=0; i<=20; i++) {
            model.addRow(new Object[]{
                    i,i,i,i,i
            });
        }

    }
}
