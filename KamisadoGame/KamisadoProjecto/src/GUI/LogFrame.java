package GUI;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.*;

import javax.swing.JPanel;

import static java.awt.Label.LEFT;

public class LogFrame extends JFrame {

    private final GradientPanel gradientPanel;
    private final JLabel label = new JLabel();
    private ImageIcon logo;
    public LogFrame() {
        JLabel label = new JLabel("<html>Welcome To Kamisado Game!<br>Log File:<br></html>");

        logo = new ImageIcon("KamisadoProjecto/Images/Icons/Logo.png");
        this.setIconImage(logo.getImage());

        add(label);

        setTitle("Log File");
        setSize(600, 1200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gradientPanel = new GradientPanel();
        setContentPane(gradientPanel);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        label.setForeground(Color.BLUE);

        getContentPane().add(label, BorderLayout.CENTER);

        setVisible(true);
    }

    private class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            int w = getWidth();
            int h = getHeight();

            // Create a gradient paint from red to yellow
            GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 100, 100), w, h, new Color(255, 255, 100));

            // Set the paint to the graphics context
            g2d.setPaint(gradient);

            // Draw a rectangle the size of the panel
            g2d.fillRect(0, 0, w, h);
        }
    }

    public void addText(String text) {
        // Create a new JLabel with the specified text
        JLabel newLabel = new JLabel(text);
        add(newLabel);
        // Set the font and color of the label
        newLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        newLabel.setForeground(Color.black);
    }
}
