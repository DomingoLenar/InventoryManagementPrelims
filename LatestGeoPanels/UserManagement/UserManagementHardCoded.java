package UserManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserManagementHardCoded {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("User Management");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(600, 400));

            JPanel mainPanel = new JPanel(new BorderLayout());

            // Sample data for the table
            Object[][] data = { // provided data for testing
                    {"123", "user", "Purchaser"},
                    {"123", "user", "Sales Person"},
                    {"123", "user", "Purchaser"},
            };

            // Column names for the table
            String[] columnNames = {"ID", "Name", "Role"};

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);

            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        JTable target = (JTable)e.getSource();
                        int row = target.getSelectedRow();
                        String id = (String) target.getValueAt(row, 0);
                        String name = (String) target.getValueAt(row, 1);
                        String role = (String) target.getValueAt(row, 2);

                        // Sample Data
                        System.out.println("Clicked Row Data: ID=" + id + ", Name=" + name +
                                ", Role=" + role );

                        // For demonstration purposes (swing panel for sales invoice will show up here)
                        JOptionPane.showMessageDialog(frame,
                                "You clicked on: ID=" + id + ", Name=" + name +
                                        ", Role=" + role);
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

