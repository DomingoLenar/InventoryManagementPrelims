package client.admin.views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class AdminStockControlView extends JFrame{
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
    private JButton lowStockButton;
    private SalesInvoiceListener salesInvoiceListener;
    private AddItemListener addItemListener;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public interface SalesInvoiceListener {
        void onSalesInvoiceRequested();
    }

    public interface AddItemListener {
        void onAddItemRequested();
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JTable getSalesTable() {
        return salesTable;
    }

    public JButton getAddItemButton() {
        return addItemButton;
    }

    public JButton getSalesInvoiceButton() {
        return salesInvoiceButton;
    }

    public SalesInvoiceListener getSalesInvoiceListener() {
        return salesInvoiceListener;
    }

    public AddItemListener getAddItemListener() {
        return addItemListener;
    }

//    public AdminStockControlView() {
//        setContentPane(mainPanel);
//        setTitle("Stock Control");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(700, 500);
//        setLocationRelativeTo(null);
//        setVisible(true);
////For testing
////        Object rowData [][] = {{"Row1-Column1", "Row1-Column2", "Row1-Column3"}};
////        Object columnNames[] = { "Column One", "Column Two", "Column Three" };
////        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
////        salesTable.setModel(tableModel);
//
//
////        salesScrollPane.setBorder(BorderFactory.createCompoundBorder(
////                new StockControlView.RoundedCornerBorder(30),
////                new LineBorder(Color.lightGray, 2)
////
////        ));
////
////        searchField.setBorder(BorderFactory.createCompoundBorder(
////                new StockControlView.RoundedCornerBorder(20),
////                new EmptyBorder(5, 5, 5, 5)
////        ));
//
//        salesInvoiceButton.addActionListener(e -> {
//            if (salesInvoiceListener != null) {
//                salesInvoiceListener.onSalesInvoiceRequested();
//            }
//        });
//        addItemButton.addActionListener(e -> {
//            if (addItemListener != null) {
//                addItemListener.onAddItemRequested();
//            }
//        });
//    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(StockControlView::new);
//    }

    private static class RoundedCornerBorder implements Border {
        private final int arc;

        public RoundedCornerBorder(int arc) {
            this.arc = arc;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Stroke originalStroke = g2d.getStroke();
            g2d.setStroke(new BasicStroke(3.0f));

            g2d.setColor(Color.WHITE);
            g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, arc, arc));

            g2d.setStroke(originalStroke);

            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(5, 5, 5, 5);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }
}
