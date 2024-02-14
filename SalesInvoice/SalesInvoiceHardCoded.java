package SalesInvoice;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SalesInvoiceHardCoded {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sales Invoice");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(600, 400));

            JPanel mainPanel = new JPanel(new BorderLayout());

            // Sample data for the table
            Object[][] data = { // provided data for testing
                    {"1", "2024-02-14", "Product A", 10, 1000},
                    {"2", "2024-02-14", "Product B", 20, 2000},
                    {"3", "2024-02-14", "Product C", 15, 15000},
            };

            // Column names for the table
            String[] columnNames = {"ID", "Date", "Product", "Quantity", "Total"};

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);

            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        JTable target = (JTable)e.getSource();
                        int row = target.getSelectedRow();
                        String id = (String) target.getValueAt(row, 0);
                        String date = (String) target.getValueAt(row, 1);
                        String product = (String) target.getValueAt(row, 2);
                        int quantity = (int) target.getValueAt(row, 3);
                        float total = (int) target.getValueAt(row,4);
                        // Sample Data
                        System.out.println("Clicked Row Data: ID=" + id + ", Date=" + date +
                                ", Product=" + product + ", Quantity=" + quantity);

                        // For demonstration purposes (swing panel for sales invoice will show up here)
                        JOptionPane.showMessageDialog(frame,
                                "You clicked on: ID=" + id + ", Date=" + date +
                                        ", Product=" + product + ", Quantity=" + quantity + ", Total=" +total);
                    }
                }
            });

            mainPanel.add(scrollPane, BorderLayout.CENTER);
            frame.add(mainPanel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
