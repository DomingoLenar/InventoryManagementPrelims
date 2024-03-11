package client.sales.views;

import org.knowm.xchart.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class SalesDashboardView {
    private JPanel mainPanel,bottomPanel;
    private JTextField searchField;
    private JList<String> recentlyAddedItemsIDList, recentlyAddedItemsNameList;
    private JPanel stockControlPanel, revenueVsCostPanel, recentlyAddedItemsPanel;
    private JLabel totalQtySoldLabel, soldQtyTodayLabel, soldQtyAnnualLabel, recentlyAddedItemsLabel;
    CategoryChart chart;
    DefaultListModel<String> recentlyAddedItemsIDListModel, recentlyAddedItemsNameListModel;
    PieChart pieChart;
    String soldQtyAnnual, soldQtyToday, totalQtySold;

    public SalesDashboardView() {
        // Recently Added Items

        recentlyAddedItemsIDListModel = new DefaultListModel<>();

        recentlyAddedItemsIDList.setModel(recentlyAddedItemsIDListModel);
        recentlyAddedItemsIDList.setEnabled(false);
        recentlyAddedItemsIDList.setFont(new Font("Fira Code", Font.PLAIN, 14));

        recentlyAddedItemsNameListModel = new DefaultListModel<>();

        recentlyAddedItemsNameList.setModel(recentlyAddedItemsNameListModel);
        recentlyAddedItemsNameList.setEnabled(false);
        recentlyAddedItemsNameList.setFont(new Font("Fira Code", Font.PLAIN, 14));

        // Revenue Vs Costs

        chart = new CategoryChartBuilder().width(400).height(300).build();
        chart.getStyler().setStacked(true);
        chart.getStyler().setOverlapped(true);
        chart.getStyler().setSeriesColors(new Color[]{new Color(130, 0, 255), new Color(100, 180, 180)});
        chart.getStyler().setChartBackgroundColor(Color.WHITE);

        JPanel chartPanel = new XChartPanel<>(chart);

        revenueVsCostPanel.setLayout(new BorderLayout());
        revenueVsCostPanel.add(chartPanel, BorderLayout.CENTER);

        // Unit sold pie chart
        pieChart = new PieChartBuilder().width(400).height(300).build();

        pieChart.getStyler().setLegendVisible(true);
        pieChart.setTitle("Revenue & Cogs");
        pieChart.getStyler().setChartBackgroundColor(Color.WHITE);

        Color todayColor = new Color(130, 0, 255);
        Color maxColor = new Color(100, 180, 180);
        pieChart.getStyler().setSeriesColors(new Color[]{todayColor, maxColor});

        // Change to Raw Data
//        soldQtyToday = 274;
//        soldQtyAnnual = 2300; // TODO: this could be improve depending on the company requirement
//        totalQtySold = soldQtyToday + soldQtyAnnual;
//
////        pieChart.addSeries("Today", soldQtyToday);
////        pieChart.addSeries("Max", soldQtyAnnual);
//
//        soldQtyTodayPercent = ((double) soldQtyToday / totalQtySold) * 100;
//        soldQtyAnnualPercent = ((double) soldQtyAnnual / totalQtySold) * 100;
//
//        soldQtyTodayLabel = String.format("Today: %.2f%%", 333.2);
//        soldQtyAnnualLabel = String.format("Max: %.2f%%", 444.3);
//        totalQtySoldLabel = String.format("Total: %d", 555);

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
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public CategoryChart getChart() {
        return chart;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public DefaultListModel<String> getRecentlyAddedItemsIDListModel() {
        return recentlyAddedItemsIDListModel;
    }

    public DefaultListModel<String> getRecentlyAddedItemsNameListModel() {
        return recentlyAddedItemsNameListModel;
    }

    public PieChart getPieChart() {
        return pieChart;
    }

    public JLabel getTotalQtySoldLabel() {
        return totalQtySoldLabel;
    }

    public JLabel getSoldQtyTodayLabel() {
        return soldQtyTodayLabel;
    }

    public JLabel getSoldQtyAnnualLabel() {
        return soldQtyAnnualLabel;
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