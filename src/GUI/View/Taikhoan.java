/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.View;

import BUS.TaiKhoanBUS;
import GUI.Controller.TaiKhoanLoginController;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 *
 * @author DELL
 */
public class Taikhoan extends JFrame{
    public JTextField usernameField;
    public JPasswordField passwordField;
    public Taikhoan(){
        this.init();
        this.setVisible(true);
    }
    public void init(){
        setTitle("Login");
        setSize(500, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo tiêu đề
        JLabel lbtitle = new JLabel("LOGIN", JLabel.CENTER);
        lbtitle.setFont(new Font("Arial", Font.BOLD, 20));
        lbtitle.setForeground(new Color(0, 102, 204));

        // Tạo các label và textfield
        JLabel lbdangnhap = new JLabel("Username:",JLabel.CENTER);
        JLabel lbmatkhau = new JLabel("Password:",JLabel.CENTER);
        usernameField = new JTextField("admin",15);
        passwordField = new JPasswordField("12345",15);

        // Tạo các nút
        JButton btsubmit = new JButton("Submit");
        JButton btreset = new JButton("Reset");

        // Căn giữa các thành phần
        JPanel panelInput = new JPanel(new GridLayout(2, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelInput.add(lbdangnhap);
        panelInput.add(usernameField);
        panelInput.add(lbmatkhau);
        panelInput.add(passwordField);

        // Chỉnh layout cho nút
        JPanel panelButton = new JPanel();
        panelButton.add(btsubmit);
        panelButton.add(btreset);

        // Thêm các thành phần vào frame
        setLayout(new BorderLayout(10, 10));
        add(lbtitle, BorderLayout.NORTH);
        add(panelInput, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);
        ActionListener action =new TaiKhoanLoginController(this);
        btsubmit.addActionListener(action);
        btreset.addActionListener(action);
        
    }
   
        
}

