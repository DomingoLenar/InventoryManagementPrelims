package client.admin.views;

import org.knowm.xchart.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class AdminDashboardView {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel searchPanel;
    private JTextField searchField;
    private JList<String> activeUsersList;
    private JList<String> activeUsersTypeList;
    private JButton addUserButton;
    private JPanel addUserPanel;
    private JPanel stockControlPanel;
    private JPanel revenueVsCostPanel;
    private JPanel usersActivePanel;
    private JLabel usersActiveLabel;
    CategoryChart chart;
    JPanel chartPanel;
    private JLabel totalQtySoldLabel, soldQtyTodayLabel, soldQtyAnnualLabel, recentlyAddedItemsLabel;
    String soldQtyAnnual, soldQtyToday, totalQtySold;
    DefaultListModel<String> activeUsersListModel, activeUsersTypeListModel;
    PieChart pieChart;

    public PieChart getPieChart() {
        return pieChart;
    }

    public DefaultListModel<String> getActiveUsersListModel() {
        return activeUsersListModel;
    }

    public DefaultListModel<String> getActiveUsersTypeListModel() {
        return activeUsersTypeListModel;
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

    public AdminDashboardView() {

        // Users Active
        usersActiveLabel.setFont(new Font("Fira Code", Font.BOLD, 20));
        activeUsersListModel = new DefaultListModel<>();

        // Change to Raw Data
//        activeUsersListModel.addElement("User 1");
//        activeUsersListModel.addElement("User 2");
//        activeUsersListModel.addElement("User 3");
//        activeUsersListModel.addElement("User 4");
//        activeUsersListModel.addElement("User 5");

        activeUsersList.setModel(activeUsersListModel);

        activeUsersList.setEnabled(false);
        activeUsersList.setFont(new Font("Fira Code", Font.BOLD, 14));

        activeUsersTypeListModel = new DefaultListModel<>();

        // Change to Raw Data
//        activeUsersTypeListModel.addElement("Sales Person");
//        activeUsersTypeListModel.addElement("Purchaser");
//        activeUsersTypeListModel.addElement("Sales Person");
//        activeUsersTypeListModel.addElement("Sales Person");
//        activeUsersTypeListModel.addElement("Purchaser");

        activeUsersTypeList.setModel(activeUsersTypeListModel);

        activeUsersTypeList.setEnabled(false);
        activeUsersTypeList.setFont(new Font("Fira Code", Font.BOLD, 14));

        addUserButton.setBorderPainted(false);

        // Unit sold
        pieChart = new PieChartBuilder().width(400).height(300).build();

        pieChart.getStyler().setLegendVisible(true);
        pieChart.setTitle("Units Sold");
        pieChart.getStyler().setChartBackgroundColor(Color.WHITE);

        Color todayColor = new Color(130, 0, 255);
        Color maxColor = new Color(100, 180, 180);
        pieChart.getStyler().setSeriesColors(new Color[]{todayColor, maxColor});

        // Change to Raw Data
//        todayValue = 274;
//        maxValue = 2300;
//        totalValue = todayValue + maxValue;
//
//        pieChart.addSeries("Today", todayValue);
//        pieChart.addSeries("Max", maxValue);
//
//        todayPercentage = ((double) todayValue / totalValue) * 100;
//        maxPercentage = ((double) maxValue / totalValue) * 100;
//
//        todayLabel = String.format("Today: %.2f%%", todayPercentage);
//        maxLabel = String.format("Max: %.2f%%", maxPercentage);
//        totalLabel = String.format("Total: %d", totalValue);

        JPanel pieChartPanel = new XChartPanel<>(pieChart);

        JLabel todayColorLabel = new JLabel("    ");
        todayColorLabel.setFont(new Font("Fira Code", Font.BOLD, 14));
        todayColorLabel.setBackground(todayColor);
        todayColorLabel.setOpaque(true);

        JLabel maxColorLabel = new JLabel("    ");
        maxColorLabel.setFont(new Font("Fira Code", Font.BOLD, 14));
        maxColorLabel.setBackground(maxColor);
        maxColorLabel.setOpaque(true);

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



        // Call for Controller
        searchField.setBorder(BorderFactory.createCompoundBorder(
                new RoundedCornerBorder(20),
                new EmptyBorder(5, 5, 5, 5)
        ));

        // Revenue Vs Costs
        chart = new CategoryChartBuilder().width(400).height(300).build();
        chart.getStyler().setStacked(true);
        chart.getStyler().setOverlapped(true);
        chart.getStyler().setSeriesColors(new Color[]{new Color(130, 0, 255), new Color(100, 180, 180)});
        chart.getStyler().setChartBackgroundColor(Color.WHITE);

        JPanel chartPanel = new XChartPanel<>(chart);

        revenueVsCostPanel.setLayout(new BorderLayout());
        revenueVsCostPanel.add(chartPanel, BorderLayout.CENTER);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            AdminDashboardView dashboardView = new AdminDashboardView();
//
//            JFrame frame = new JFrame("Dashboard");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setContentPane(dashboardView.mainPanel);
//
//            frame.setResizable(true);
//            frame.setMinimumSize(new Dimension(700, 500));
//
//            frame.pack();
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//        });
//    }

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

    public CategoryChart getChart() {
        return chart;
    }

    public JPanel getChartPanel() {
        return chartPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}