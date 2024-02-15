package client.views.sales;

import org.knowm.xchart.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class FinancesView {
    private JPanel topPanel;
    private JPanel searchPanel;
    private JTextField searchField;
    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JPanel revenueVsCostPanel;
    private JPanel stockControlPanel;
    private JPanel grossSalesPanel;
    private JPanel averageSalesPanel;
    private JPanel newSalesPanel;
    private JPanel grossProfitsPanel;

    public FinancesView() {

        searchField.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        // Revenue Vs Costs
        CategoryChart chart = new CategoryChartBuilder().width(400).height(300).build();

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

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

        JLabel maxColorLabel = new JLabel("    ");
        maxColorLabel.setBackground(maxColor);
        maxColorLabel.setOpaque(true);

        JLabel totalLabelComponent = new JLabel(totalLabel);
        totalLabelComponent.setHorizontalAlignment(SwingConstants.CENTER);
        totalLabelComponent.setFont(new Font("Arial", Font.BOLD, 14));

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
        FinancesView financesView = new FinancesView();

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

