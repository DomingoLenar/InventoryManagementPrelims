package client.views.salesperson;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockControlView extends JFrame{
    private JPanel mainPanel;
    private JTextField searchField;
    private JPanel topPanel;
    private JTable salesTable;
    private JPanel centerPanel;
    private JButton addItemButton;
    private JButton salesInvoiceButton;
    private JPanel bottomPanel;
    private JScrollPane salesScrollPane;
    private JPanel searchPanel;
    private SalesInvoiceListener salesInvoiceListener;
    private AddItemListener addItemListener;

    public interface SalesInvoiceListener {
        void onSalesInvoiceRequested();
    }

    public interface AddItemListener {
        void onAddItemRequested();
    }

    public StockControlView() {
        setContentPane(mainPanel);
        setTitle("Stock Control");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);
//For testing
//        Object rowData [][] = {{"Row1-Column1", "Row1-Column2", "Row1-Column3"}};
//        Object columnNames[] = { "Column One", "Column Two", "Column Three" };
//        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
//        salesTable.setModel(tableModel);


        salesInvoiceButton.addActionListener(e -> {
            if (salesInvoiceListener != null) {
                salesInvoiceListener.onSalesInvoiceRequested();
            }
        });
        addItemButton.addActionListener(e -> {
            if (addItemListener != null) {
                addItemListener.onAddItemRequested();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StockControlView::new);
    }
}
