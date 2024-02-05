package client.views;

import javax.swing.*;
import java.awt.*;

public class DashboardView {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel searchPanel;
    private JTextField searchField;
    private JList<String> activityList1;
    private JList<String> activityList2;

    public DashboardView() {

        DefaultListModel<String> listModel1 = new DefaultListModel<>();
        listModel1.addElement("Null");
        listModel1.addElement("Null");
        listModel1.addElement("Null");
        activityList1.setModel(listModel1);

        DefaultListModel<String> listModel2 = new DefaultListModel<>();
        listModel2.addElement("Null");
        listModel2.addElement("Null");
        listModel2.addElement("Null");
        activityList2.setModel(listModel2);

        //searchField.setBorder(null);
        //profileButton.setBorder(null);

    }

    public static void main(String[] args) {
        DashboardView dashboardView = new DashboardView();

        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(dashboardView.mainPanel);

        frame.setResizable(true);

        frame.setMinimumSize(new Dimension(700, 500));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}