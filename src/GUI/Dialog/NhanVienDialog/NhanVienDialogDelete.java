/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.NhanVienDialog;

import GUI.Dialog.BookDialog.BookDialogDelete_Controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author Minnie
 */
public class NhanVienDialogDelete extends JDialog {
    private String maNV;
    public NhanVienDialogDelete(JFrame parent, String maNV){
        super(parent, "Danh mục xóa nhân viên", true);
        this.maNV = maNV;
        setSize(350,150);
        setLocationRelativeTo(null);
        
        //Panel phía trên chính giữa
        Font font=new Font("Arial", Font.BOLD, 18);
        JPanel panelMain=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lbIcon=new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
        JLabel lbNotice= new JLabel("Bạn có chắn chắn muốn xóa nhân viên này ?");
        lbNotice.setFont(font);
        panelMain.add(lbIcon);panelMain.add(lbNotice);
        
        //Panel phía dưới phải
        JPanel panelFoot=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btndelete=createButton("Xóa",new Color(244, 67, 54));
        JButton btnclose=createButton("Đóng",new Color(156, 156, 156));
        panelFoot.add(btndelete);panelFoot.add(btnclose);
 
        // Thêm 2 Panel trên vào Panel tổng
         setLayout(new BorderLayout());
         add(panelMain,BorderLayout.CENTER);
         add(panelFoot,BorderLayout.SOUTH);
         // Thêm sự kiên vào 2 nút
         ActionListener action = new NhanVienDialogDelete_Controller(this);
         btndelete.addActionListener(action);
         btnclose.addActionListener(action);
         
         pack(); // Dùng để căn chỉnh kích thước tự động với các phẩn tử bên trong
         setVisible(true);
    }
    
    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
                // Xác định màu nền dựa trên trạng thái của button
                Color actualBgColor = bgColor;
                if (getModel().isPressed()) {
                    actualBgColor = bgColor.darker(); // Màu tối hơn khi nhấn
                } else if (getModel().isRollover()) {
                    actualBgColor = bgColor.brighter(); // Màu sáng hơn khi hover
                }
                // Vẽ hình tròn làm nền
                g2.setColor(actualBgColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15); // Bo tròn góc 15px
    
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(80, 30));
    
        return button;
    }

    public String getMaNV() {
        return maNV;
    }
}
