/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.KhachHangDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.event.AncestorListener;

/**
 *
 * @author DELL
 */
public class KhachHangDialogDetail extends JDialog{
    private JTextField txfMa,txfHo,txfTen,txfEmail,txfNgaySinh,txfSdt;
    public KhachHangDialogDetail(JFrame parent) {
        super(parent, "Danh mục  xem chi tiết", true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        // Tiêu đề
        JLabel titleLabel = new JLabel("THÔNG TIN CHI TIẾT", SwingConstants.CENTER);
        Font titleFont = new Font("Arial", Font.BOLD, 25);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);
        
        JPanel titlePanel = new JPanel(new BorderLayout()); 
        titlePanel.setBackground(new Color(72, 118, 255));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(titlePanel, BorderLayout.NORTH);
        
        // Panel chứa nội dung chính
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        // Panel chứa các label và text field - bố cục ngang
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        
        // Các Label và TextField
        JLabel lbMa=new JLabel("Mã : ");            lbMa.setFont(labelFont);
        JLabel lbHo = new JLabel("ho:");        lbHo.setFont(labelFont);
        JLabel lbTen= new JLabel("ten:");            lbTen.setFont(labelFont);
        JLabel lbEmail = new JLabel("email:");     lbEmail.setFont(labelFont);
        JLabel lbNgaySinh = new JLabel("ngay sinh:");  lbNgaySinh.setFont(labelFont);
        JLabel lbSdt = new JLabel("sdt:");        lbSdt.setFont(labelFont);
        

        txfMa= createTextField(fieldFont);
        txfMa.setEditable(false); // Không cho chỉnh sửa mã sách
        txfHo = createTextField(fieldFont);
        txfHo.setEditable(false);
        txfTen = createTextField(fieldFont);
        txfTen.setEditable(false);
        txfEmail = createTextField(fieldFont);
        txfEmail.setEditable(false);
        txfNgaySinh = createTextField(fieldFont);
        txfNgaySinh.setEditable(false);
        txfSdt = createTextField(fieldFont);
        txfSdt.setEditable(false);
        
        
        // Hàng 1 chứa 4 trường đầu
        JPanel row1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        row1Panel.add(createFieldPanel(lbMa, txfMa));
        row1Panel.add(createFieldPanel(lbHo, txfHo));
        row1Panel.add(createFieldPanel(lbTen, txfTen));
        row1Panel.add(createFieldPanel(lbEmail, txfEmail));
        
        // Hàng 2 chứa 3 trường còn lại
        JPanel row2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        row2Panel.add(createFieldPanel(lbNgaySinh, txfNgaySinh));
        row2Panel.add(createFieldPanel(lbSdt, txfSdt));
        
        
        formPanel.add(row1Panel);
        formPanel.add(row2Panel);
        
        contentPanel.add(formPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
        
        // Panel chứa các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton deleteButton = createButton("Đóng", new Color(72, 118, 255));
        buttonPanel.add(deleteButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        // Thêm ActionListener vào nút
        ActionListener action=new KhachHangDialogDetail_Controller(this);
        deleteButton.addActionListener(action);
        
        pack(); // Điều chỉnh kích thước tự động dựa trên nội dung
        setLocationRelativeTo(parent); // Hiển thị giữa màn hình
        
    }
    
    private JPanel createFieldPanel(JLabel label, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }
    
    private JTextField createTextField(Font font) {
        JTextField textField = new JTextField(20);
        textField.setFont(font);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return textField;
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
        button.setPreferredSize(new Dimension(140, 40));
    
        return button;
    }
    public void ShowInfo(String ma,String ho,String ten,String email,String ns,String sdt){
        System.err.println(ma);
        txfMa.setText(ma);
        txfHo.setText(ho);
        txfTen.setText(ten);
        txfNgaySinh.setText(ns);
        txfSdt.setText(sdt);
        txfEmail.setText(email);
        
    }

     public void ShowInfo2(String ma,String ho,String ten,String email,String ns,String sdt){
        System.err.println(ma);
        txfMa.setText(ma);
        txfHo.setText(ho);
        txfTen.setText(ten);
        txfNgaySinh.setText(ns);
        txfSdt.setText(sdt);
        txfEmail.setText(email);
        this.setVisible(true);
    }
}
