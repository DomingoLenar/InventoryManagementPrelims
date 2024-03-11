package client.views_geo_rabang.sales.SalesInvoice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CreateSalesInvoice extends JFrame {
    // Hardcoded database of products and their prices
    private static final Map<String, Double> productDatabase = new HashMap<>();

    static {
        productDatabase.put("milk", 10.0);
        productDatabase.put("bread", 20.0);
        productDatabase.put("banana", 30.0);
        productDatabase.put("condom", 300.0);
        productDatabase.put("coconut", 50.0);
        // Add more products as needed
    }

    private JComboBox<String> productComboBox;
    private JTextField quantityField;
    private JTextArea invoiceTextArea;
    private double totalAmount;

    public CreateSalesInvoice() {
        super("Stock Pilot");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        productComboBox = new JComboBox<>(productDatabase.keySet().toArray(new String[0]));
        productComboBox.setEditable(true); // Allow user to type
        mainPanel.add(new JLabel("Product Name:"), gbc);
        gbc.gridx++;
        mainPanel.add(productComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        quantityField = new JTextField(5);
        mainPanel.add(new JLabel("Quantity:"), gbc);
        gbc.gridx++;
        mainPanel.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton addButton = new JButton("Add Product");
        mainPanel.add(addButton, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        invoiceTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(invoiceTextArea);
        mainPanel.add(scrollPane, gbc);

        JButton createInvoiceButton = new JButton("Create Invoice");
        createInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder invoiceText = new StringBuilder();
                invoiceText.append("Invoice:\n");
                for (String line : invoiceTextArea.getText().split("\n")) {
                    invoiceText.append(line).append("\n");
                }
                invoiceText.append("Total Amount: $").append(totalAmount).append("\n");

                JOptionPane.showMessageDialog(CreateSalesInvoice.this, invoiceText.toString(), "Invoice", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        gbc.gridy++;
        mainPanel.add(createInvoiceButton, gbc);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = (String) productComboBox.getSelectedItem();
                int quantity = Integer.parseInt(quantityField.getText());

                if (!productDatabase.containsKey(productName)) {
                    JOptionPane.showMessageDialog(CreateSalesInvoice.this, "Product not found in the database.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double price = productDatabase.get(productName);
                double totalPrice = price * quantity;

                invoiceTextArea.append(quantity + " " + productName + " - $" + price + " each\n");
                totalAmount += totalPrice;

                JOptionPane.showMessageDialog(CreateSalesInvoice.this, "Product added to invoice.");
            }
        });

        getContentPane().add(mainPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CreateSalesInvoice();
            }
        });
    }
}

