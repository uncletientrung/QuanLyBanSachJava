/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.TheLoaiDialog;

/**
 *
 * @author Hi
 */
import javax.swing.*;
import java.awt.*;
//class nay dùng để làm nút giống tk trung định dạng
public class RoundButton extends JButton {
    private Color bgColor;
    private int radius;

    public RoundButton(String text, Color bgColor, int radius) {
        super(text);
        this.bgColor = bgColor;
        this.radius = radius;
        setFont(new Font("Arial", Font.BOLD, 14));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setPreferredSize(new Dimension(140, 40));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Màu thay đổi theo trạng thái của nút
        Color actualBgColor = bgColor;
        if (getModel().isPressed()) {
            actualBgColor = bgColor.darker();
        } else if (getModel().isRollover()) {
            actualBgColor = bgColor.brighter();
        }

        // Vẽ nền bo góc
        g2.setColor(actualBgColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        super.paintComponent(g);
        g2.dispose();
    }
}
