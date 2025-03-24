/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.View;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author DELL
 */
public class HomePanel extends JPanel{
    public HomePanel(){
        // Tạo panel cho phần Center với background là hình ảnh
        JPanel centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load hình ảnh từ đường dẫn
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("/GUI/Image/Home_Background.png"));
                                    // Thay thế bằng đường dẫn hình ảnh thực tế
                                                                                                                 
                Image image = imageIcon.getImage();
                // Vẽ hình ảnh lên panel
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        setLayout(new BorderLayout());
        add(centerPanel,BorderLayout.CENTER);
    }
}
