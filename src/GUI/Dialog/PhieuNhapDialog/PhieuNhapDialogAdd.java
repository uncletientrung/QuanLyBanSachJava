/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhieuNhapDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 *
 * @author Minnie
 */
public class PhieuNhapDialogAdd extends JDialog{
    private JTextField txfMaPhieu, txfMaNv, txfMaNcc, txfThoigian, txfTongTien, txfTrangthai;
    public PhieuNhapDialogAdd(JFrame parent){
        super(parent, "Danh mục thêm phiếu nhập", true);
        
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1200, 700));
        panel.setLayout(new BorderLayout(10,10));
        
        //Tittle
        JLabel titleLabel = new JLabel("THÊM PHIẾU NHẬP HÀNG", SwingConstants.CENTER);
        Font tittleFont = new Font("Arial", Font.BOLD, 25); 
        titleLabel.setFont(tittleFont);
        titleLabel.setForeground(Color.WHITE);
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(72, 118, 225));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(titlePanel, BorderLayout.NORTH);
        
        //Panel chứa nội dung chính
        JPanel contentPanel = new JPanel(new BorderLayout(10,10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
        
        //Panel chứa các label và textField
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor = GridBagConstraints.WEST;
        
        //Set font
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        
        //Lable với textfield
        JLabel lbMa = new JLabel("Mã phiếu: ");
        JLabel lbManv = new JLabel("Mã nhân viên: ");
        JLabel lbMancc = new JLabel("Mã nhà cung cấp: ");
        JLabel lbThoigiantao = new JLabel("Thời gian tạo: ");
        JLabel lbTongtien = new JLabel("Tổng tiền: ");
        JLabel lbTrangthai = new JLabel("Trạng thái: ");
        lbMa.setFont(labelFont);
        lbManv.setFont(labelFont);
        lbMancc.setFont(labelFont);
        lbThoigiantao.setFont(labelFont);
        lbTongtien.setFont(labelFont);
        lbTrangthai.setFont(labelFont);
        
        txfMaPhieu = createTextField(fieldFont);
        txfMaNv = createTextField(fieldFont);
        txfMaNcc = createTextField(fieldFont);
        txfThoigian = createTextField(fieldFont);
        txfTongTien = createTextField(fieldFont);
        txfTrangthai = createTextField(fieldFont);
        
        //Cột 1 - Labels
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        formPanel.add(lbMa, gbc);
        gbc.gridy++; formPanel.add(lbManv, gbc);
        gbc.gridy++; formPanel.add(lbMancc, gbc);
//        gbc.gridy++; formPanel.add(lbThoigiantao, gbc);
        gbc.gridy++; formPanel.add(lbTongtien, gbc);
        gbc.gridy++; formPanel.add(lbTrangthai, gbc);
        
        //Cột 2 - TexfField
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txfMaPhieu, gbc);
        gbc.gridy++; formPanel.add(txfMaNv, gbc);
        gbc.gridy++; formPanel.add(txfMaNcc, gbc);
//        gbc.gridy++; formPanel.add(txfThoigian, gbc);
        gbc.gridy++; formPanel.add(txfTongTien, gbc);
        gbc.gridy++; formPanel.add(txfTrangthai, gbc);
        
        contentPanel.add(formPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
        
        //Panel chứa các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton addButton = createButton("Thêm dữ liệu", new Color(76, 175, 80));
        JButton deleteButton = createButton("Xóa", new Color(244, 67, 54));

        buttonPanel.add(addButton);
        buttonPanel.add(Box.createHorizontalStrut(20)); // Khoảng cách giữa 2 nút
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
        // Thêm sự kiện cho 2 nút
        ActionListener action= new PhieuNhapDialogAdd_Controller(this);
        addButton.addActionListener(action);
        deleteButton.addActionListener(action);

        
        pack(); // Điều chỉnh kích thước tự động dựa trên nội dung
        setLocationRelativeTo(parent); // Hiển thị giữa màn hình
        setVisible(true);
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

    public JTextField getTxfMaPhieu() {
        return txfMaPhieu;
    }

    public void setTxfMaPhieu(JTextField txfMaPhieu) {
        this.txfMaPhieu = txfMaPhieu;
    }

    public JTextField getTxfMaNv() {
        return txfMaNv;
    }

    public void setTxfMaNv(JTextField txfMaNv) {
        this.txfMaNv = txfMaNv;
    }

    public JTextField getTxfMaNcc() {
        return txfMaNcc;
    }

    public void setTxfMaNcc(JTextField txfMaNcc) {
        this.txfMaNcc = txfMaNcc;
    }

    public JTextField getTxfThoigian() {
        return txfThoigian;
    }

    public void setTxfThoigian(JTextField txfThoigian) {
        this.txfThoigian = txfThoigian;
    }

    public JTextField getTxfTongTien() {
        return txfTongTien;
    }

    public void setTxfTongTien(JTextField txfTongTien) {
        this.txfTongTien = txfTongTien;
    }

    public JTextField getTxfTrangthai() {
        return txfTrangthai;
    }

    public void setTxfTrangthai(JTextField txfTrangthai) {
        this.txfTrangthai = txfTrangthai;
    }

    @Override
    public String toString() {
        return "PhieuNhapDialogAdd{" + "txfMaPhieu=" + txfMaPhieu + ", txfMaNv=" + txfMaNv + ", txfMaNcc=" + txfMaNcc + ", txfThoigian=" + txfThoigian + ", txfTongTien=" + txfTongTien + ", txfTrangthai=" + txfTrangthai + '}';
    }
    
    public void ClearTextField(){
        txfMaPhieu.setText("");
        txfMaNv.setText("");
        txfMaNcc.setText("");
        txfThoigian.setText("");
        txfTongTien.setText("");
        txfTrangthai.setText("");
    }
}
