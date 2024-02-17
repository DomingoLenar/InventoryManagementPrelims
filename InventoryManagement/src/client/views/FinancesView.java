package client.views;

import javax.swing.*;
import java.awt.*;

public class FinancesView {
    private JPanel topPanel;
    private JPanel searchPanel;
    private JTextField searchField;
    private JPanel mainPanel;
    private JPanel bottomPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        bottomPanel = new GradientPanel();
    }
    //    public FinancesView() {
//        searchField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
//    }

//    public static void main(String[] args) {
//        FinancesView financesView = new FinancesView();
//
//        JFrame frame = new JFrame("Finances");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(financesView.mainPanel);
//
//        frame.setResizable(true);
//
//        frame.setMinimumSize(new Dimension(700, 500));
//
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }

}

