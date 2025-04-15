/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.View;
import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


/**
 *
 * @author Hi
 */

public class CustomButton extends JButton {
    private JPanel textPanel;
    private Color originalTextBgColor = new Color(230, 230, 230);
    private Color iconBgColor;
    private String text;
    private ImageIcon icon;

    public CustomButton(String text, String iconPath, Color iconBgColor) {
        this.text = text;
        this.iconBgColor = iconBgColor;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(180, 60));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false); // Để custom vẽ bo góc

        // Panel chứa icon (bo góc)
        JPanel iconPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(iconBgColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Bo góc 20px
                g2.dispose();
                super.paintComponent(g);
            }
        };
        iconPanel.setOpaque(false);
        iconPanel.setPreferredSize(new Dimension(140, 60)); // Phần icon rộng 60px

        // Label chứa icon
        JLabel iconLabel = new JLabel();
        
        icon = new ImageIcon(getClass().getResource("/GUI/Image/" + iconPath));
        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(img));
        
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconPanel.add(iconLabel);

        //Panel chứa chữ (bo góc)
        textPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(originalTextBgColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Bo góc 20px
                g2.dispose();
                super.paintComponent(g);
            }
        };
        textPanel.setOpaque(false);
        JLabel textLabel = new JLabel(text, JLabel.CENTER);
        textLabel.setFont(new Font("Arial", Font.BOLD, 25)); 
        textPanel.add(textLabel, BorderLayout.CENTER);

        // Hiệu ứng hover (đổi màu nền textPanel)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                originalTextBgColor = new Color(248, 248, 255); // Sáng hơn hơn khi hover
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                originalTextBgColor = new Color(230, 230, 230); // Quay lại màu gốc
                repaint();
            }
        });

        // Thêm vào JButton
        add(iconPanel, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);
    }
  
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Bo góc 20px
        g2.dispose();
        super.paintComponent(g);
    }
}
