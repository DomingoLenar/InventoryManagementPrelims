package client.admin.views;

import org.knowm.xchart.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AdminFinancesView {
    private JPanel topPanel;
    private JPanel searchPanel;
    private JTextField searchField;
    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JPanel revenueVsCostPanel;
    private JPanel stockControlPanel;
    private JPanel grossSalesPanel;
    private JPanel averageSalesPanel;
    private JPanel grossProfitsPanel;
    private JLabel grossSalesLabel;
    private JLabel averageOrderValueLabel;
    private JLabel grossProfitsLabel;
    private JLabel gPAmount;
    private JLabel nSAmount;
    private JLabel aSAmount;
    private JLabel gSAmount;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public AdminFinancesView() {

        // Labels
        grossProfitsLabel.setFont(new Font("Fira Code", Font.BOLD, 14));
        averageOrderValueLabel.setFont(new Font("Fira Code", Font.BOLD, 14));
        grossSalesLabel.setFont(new Font("Fira Code", Font.BOLD, 14));
        gPAmount.setFont(new Font("Fira Code", Font.BOLD, 14));
        nSAmount.setFont(new Font("Fira Code", Font.BOLD, 14));
        aSAmount.setFont(new Font("Fira Code", Font.BOLD, 14));
        gSAmount.setFont(new Font("Fira Code", Font.BOLD, 14));

        // Search Area
        searchField.setBorder(BorderFactory.createLineBorder(Color.WHITE));

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

        revenueVsCostPanel.setLayout(new BorderLayout());
        revenueVsCostPanel.add(chartPanel, BorderLayout.CENTER);

        // Stock Control
        PieChart pieChart = new PieChartBuilder().width(400).height(300).build();

        pieChart.getStyler().setLegendVisible(true);
        pieChart.setTitle("Units Sold");
        pieChart.getStyler().setChartBackgroundColor(Color.WHITE);

        Color todayColor = new Color(130, 0, 255);
        Color maxColor = new Color(100, 180, 180);
        pieChart.getStyler().setSeriesColors(new Color[]{todayColor, maxColor});

        // Change to Raw Data
        int todayValue = 274;
        int maxValue = 2300;
        int totalValue = todayValue + maxValue;

        pieChart.addSeries("Today", todayValue);
        pieChart.addSeries("Max", maxValue);

        double todayPercentage = ((double) todayValue / totalValue) * 100;
        double maxPercentage = ((double) maxValue / totalValue) * 100;

        String todayLabel = String.format("Today: %.2f%%", todayPercentage);
        String maxLabel = String.format("Max: %.2f%%", maxPercentage);
        String totalLabel = String.format("Total: %d", totalValue);

        JPanel pieChartPanel = new XChartPanel<>(pieChart);

        JLabel todayColorLabel = new JLabel("    ");
        todayColorLabel.setBackground(todayColor);
        todayColorLabel.setOpaque(true);
        todayColorLabel.setFont(new Font("Fira Code", Font.BOLD, 20));

        JLabel maxColorLabel = new JLabel("    ");
        maxColorLabel.setBackground(maxColor);
        maxColorLabel.setOpaque(true);
        maxColorLabel.setFont(new Font("Fira Code", Font.BOLD, 20));

        JLabel totalLabelComponent = new JLabel(totalLabel);
        totalLabelComponent.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel labelPanel = new JPanel(new GridLayout(1, 3));
        labelPanel.add(new JLabel(todayLabel, SwingConstants.CENTER));
        labelPanel.add(new JLabel(maxLabel, SwingConstants.CENTER));
        labelPanel.add(totalLabelComponent);

        JPanel stockControlContentPanel = new JPanel(new BorderLayout());
        stockControlContentPanel.add(pieChartPanel, BorderLayout.CENTER);
        stockControlContentPanel.add(labelPanel, BorderLayout.SOUTH);

        stockControlPanel.setLayout(new BorderLayout());
        stockControlPanel.add(stockControlContentPanel, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        AdminFinancesView financesView = new AdminFinancesView();

        JFrame frame = new JFrame("Finances");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(financesView.mainPanel);

        frame.setResizable(true);

        frame.setMinimumSize(new Dimension(700, 500));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

