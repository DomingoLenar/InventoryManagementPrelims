package hardcode_navbar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class HoverCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        label.setBorder(new EmptyBorder(15, 15, 5, 5));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setBackground(new Color(204, 186, 186)); // Set the background color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setBackground(isSelected ? new Color(204, 186, 186) : null); // Set the background color on exit
            }
        });

        return label;
    }
}

public class HoverLeftNavBar {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Stock Pilot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] menuItems = {"Dashboard", "Stock Control", "Finances", "Profile"};

        JList<String> navList = new JList<>(menuItems);
        navList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        navList.setCellRenderer(new HoverCellRenderer());

        JScrollPane scrollPane = new JScrollPane(navList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        frame.getContentPane().add(scrollPane);
        frame.setSize(200, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
