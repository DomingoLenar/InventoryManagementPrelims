package client.sales.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SalesInvoiceViewHardCoded {
    JPanel mainPanel;
    Object[][] listOfSalesInvoice;
    JTable table;
    DefaultTableModel model;

    public DefaultTableModel getModel() {
        return model;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTable getTable() {
        return table;
    }

    public Object[][] getListOfSalesInvoice() {
        return listOfSalesInvoice;
    }

    public SalesInvoiceViewHardCoded() {
            mainPanel = new JPanel(new BorderLayout());

            // Sample data for the table
            listOfSalesInvoice = new Object[][]{ // provided data for testing
//                    {"1", "2024-02-14", "Product A", 10, 1000},
//                    {"2", "2024-02-14", "Product B", 20, 2000},
//                    {"3", "2024-02-14", "Product C", 15, 15000},
            };

            model = new DefaultTableModel();
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);

            mainPanel.add(scrollPane, BorderLayout.CENTER);
            mainPanel.setSize(800, 800);
    }
}
