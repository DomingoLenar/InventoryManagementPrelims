package client.purchaser.views;

import org.knowm.xchart.*;
import org.knowm.xchart.PieChart;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Arrays;

public class PurchaserDashboardView {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel searchPanel;
    private JTextField searchField;
    private JList<String> activityList1;
    private JList<String> activityList2;
    private JPanel stockControlPanel;
    private JPanel lowStockItemsPanel;
    private JLabel lowStockItemsLabel;
    private PieChart pieChart;
    private JLabel totalQtySoldLabel, soldQtyTodayLabel, soldQtyAnnualLabel, recentlyAddedItemsLabel;
    String soldQtyAnnual, soldQtyToday, totalQtySold;

    public PieChart getPieChart() {
        return pieChart;
    }

    DefaultListModel<String> quantityOfProductsModel;
    DefaultListModel<String> lowStockProductsModel;

    public DefaultListModel<String> getLowStockProductsModel() {
        return lowStockProductsModel;
    }

    public DefaultListModel<String> getQuantityOfProductsModel() {
        return quantityOfProductsModel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public PurchaserDashboardView() {

        // Low Stock Items
        lowStockItemsLabel.setFont(new Font("Fira Code", Font.BOLD, 20));
        quantityOfProductsModel = new DefaultListModel<>();

        activityList1.setModel(quantityOfProductsModel);
        activityList1.setEnabled(false);
        activityList1.setFont(new Font("Fira Code", Font.BOLD, 14));

        lowStockProductsModel = new DefaultListModel<>();

        activityList2.setModel(lowStockProductsModel);
        activityList2.setEnabled(false);
        activityList2.setFont(new Font("Fira Code", Font.BOLD, 14));

        // Call for Controller
        searchField.setBorder(BorderFactory.createCompoundBorder(
                new RoundedCornerBorder(20),
                new EmptyBorder(5, 5, 5, 5)
        ));

        // Revenue Vs Costs
        CategoryChart chart = new CategoryChartBuilder().width(400).height(300).build();

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        // Change to Raw Data
        chart.addSeries("Cost", Arrays.asList(months), Arrays.asList(1000, 1500, 1200, 800, 1200, 800, 800, 800, 800, 800, 800, 800));
        chart.addSeries("Revenue", Arrays.asList(months), Arrays.asList(1500, 1700, 1500, 1000, 1500, 1000, 1000, 1000, 1000, 1000, 1000, 1000));

        chart.getStyler().setStacked(true);
        chart.getStyler().setOverlapped(true);
        chart.getStyler().setSeriesColors(new Color[]{new Color(130, 0, 255), new Color(100, 180, 180)});
        chart.getStyler().setChartBackgroundColor(Color.WHITE);

        JPanel chartPanel = new XChartPanel<>(chart);

        // Units Sold
        pieChart = new PieChartBuilder().width(400).height(300).build();

        pieChart.getStyler().setLegendVisible(true);
        pieChart.setTitle("Units Sold");
        pieChart.getStyler().setChartBackgroundColor(Color.WHITE);

        Color todayColor = new Color(130, 0, 255);
        Color maxColor = new Color(100, 180, 180);
        pieChart.getStyler().setSeriesColors(new Color[]{todayColor, maxColor});

//        // Change to Raw Data
//        int todayValue = 274;
//        int maxValue = 2300;
//        int totalValue = todayValue + maxValue;
//
//        pieChart.addSeries("Today", todayValue);
//        pieChart.addSeries("Max", maxValue);
//
//        double todayPercentage = ((double) todayValue / totalValue) * 100;
//        double maxPercentage = ((double) maxValue / totalValue) * 100;
//
//        String todayLabel = String.format("Today: %.2f%%", todayPercentage);
//        String maxLabel = String.format("Max: %.2f%%", maxPercentage);
//        String totalLabel = String.format("Total: %d", totalValue);

        JPanel pieChartPanel = new XChartPanel<>(pieChart);

        JLabel todayColorLabel = new JLabel("    ");
        todayColorLabel.setBackground(todayColor);
        todayColorLabel.setOpaque(true);
        todayColorLabel.setFont(new Font("Fira Code", Font.PLAIN, 20));

        JLabel maxColorLabel = new JLabel("    ");
        maxColorLabel.setBackground(maxColor);
        maxColorLabel.setOpaque(true);
        maxColorLabel.setFont(new Font("Fira Code", Font.PLAIN, 20));

        totalQtySoldLabel = new JLabel(totalQtySold);
        totalQtySoldLabel.setHorizontalAlignment(SwingConstants.CENTER);

        soldQtyTodayLabel = new JLabel(soldQtyToday);
        soldQtyAnnualLabel = new JLabel(soldQtyAnnual);

        JPanel labelPanel = new JPanel(new GridLayout(1, 3));
        labelPanel.add(soldQtyTodayLabel, SwingConstants.CENTER);
        labelPanel.add(soldQtyAnnualLabel, SwingConstants.CENTER);
        labelPanel.add(totalQtySoldLabel);

        JPanel stockControlContentPanel = new JPanel(new BorderLayout());
        stockControlContentPanel.add(pieChartPanel, BorderLayout.CENTER);
        stockControlContentPanel.add(labelPanel, BorderLayout.SOUTH);

        stockControlPanel.setLayout(new BorderLayout());
        stockControlPanel.add(stockControlContentPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PurchaserDashboardView dashboardView = new PurchaserDashboardView();

            JFrame frame = new JFrame("Dashboard");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(dashboardView.mainPanel);

            frame.setResizable(true);
            frame.setMinimumSize(new Dimension(700, 500));

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    // Should be added in controller, used only for testing
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
