package client.views;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

class GradientPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cast the Graphics object to Graphics2D for more advanced rendering
        Graphics2D g2d = (Graphics2D) g;

        // Define start and end points for the gradient
        Point2D start = new Point2D.Float(0, 0);
        Point2D end = new Point2D.Float(0, getHeight());

        // Define gradient colors
        Color color1 = Color.PINK;
        Color color2 = Color.WHITE;

        // Create a LinearGradientPaint object
        LinearGradientPaint gradient = new LinearGradientPaint(start, end, new float[]{0.0f, 1.0f}, new Color[]{color1, color2});

        // Set the paint to use the gradient
        g2d.setPaint(gradient);

        // Fill the entire panel with the gradient
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
