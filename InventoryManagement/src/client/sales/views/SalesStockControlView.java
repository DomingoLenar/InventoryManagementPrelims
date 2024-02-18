package client.sales.views;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class SalesStockControlView extends JFrame{
    private JPanel topPanel;
    private JPanel searchPanel;
    private JTextField searchField;
    private JPanel centerPanel;
    private JScrollPane salesScrollPane;
    private JTable salesTable;
    private JPanel bottomPanel;
    private JButton salesInvoiceButton;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private SalesStockControlView.SalesInvoiceListener salesInvoiceListener;

    public interface SalesInvoiceListener {
        void onSalesInvoiceRequested();
    }

    public interface AddItemListener {
        void onAddItemRequested();
    }

    public JTable getSalesTable() {
        return salesTable;
    }

    public JButton getSalesInvoiceButton() {
        return salesInvoiceButton;
    }

//    public SalesStockControlView() {
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
//        salesScrollPane.setBorder(BorderFactory.createCompoundBorder(
//                new SalesStockControlView.RoundedCornerBorder(30),
//                new LineBorder(Color.lightGray, 2)
//
//        ));
//
//        searchField.setBorder(BorderFactory.createCompoundBorder(
//                new SalesStockControlView.RoundedCornerBorder(20),
//                new EmptyBorder(5, 5, 5, 5)
//        ));
//        salesInvoiceButton.addActionListener(e -> {
//            if (salesInvoiceListener != null) {
//                salesInvoiceListener.onSalesInvoiceRequested();
//            }
//        });
//    }

    public static void main(String[] args) {
//        SwingUtilities.invokeLater(client.deprecated.views.admin.StockControlView::new);
    }

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
