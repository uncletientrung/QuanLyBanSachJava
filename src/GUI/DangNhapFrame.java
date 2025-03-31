//package GUI;
//
//import BUS.TaiKhoanBUS;
//import DTO.TaiKhoanDTO;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import javax.swing.*;
//
//public class DangNhapFrame extends JFrame {
//    private JTextField txtUsername;
//    private JPasswordField txtPassword;
//    private JButton btnDangNhap, btnReset;
//    private TaiKhoanBUS tkBUS;
//
//    public DangNhapFrame() {
//        tkBUS = new TaiKhoanBUS();
//        initComponents();
//    }
//
//    private void initComponents() {
//        setTitle("Đăng Nhập");
//        setSize(450, 350);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout(10, 10));
//
//        // Tiêu đề
//        JLabel lbTitle = new JLabel("ĐĂNG NHẬP HỆ THỐNG", JLabel.CENTER);
//        lbTitle.setFont(new Font("Arial", Font.BOLD, 22));
//        lbTitle.setForeground(Color.WHITE);
//        lbTitle.setOpaque(true);
//        lbTitle.setBackground(new Color(30, 144, 255));
//        lbTitle.setPreferredSize(new Dimension(400, 50));
//        add(lbTitle, BorderLayout.NORTH);
//
//        // Panel nhập liệu
//        JPanel inputPanel = new JPanel(new GridBagLayout());
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.insets = new Insets(10, 10, 10, 10);
//
//        JLabel lbUsername = new JLabel("Tài khoản:");
//        lbUsername.setFont(new Font("Arial", Font.BOLD, 18));
//        txtUsername = new JTextField();
//        txtUsername.setPreferredSize(new Dimension(250, 40));
//        txtUsername.setFont(new Font("Arial", Font.PLAIN, 18));
//
//        JLabel lbPassword = new JLabel("Mật khẩu:");
//        lbPassword.setFont(new Font("Arial", Font.BOLD, 18));
//        txtPassword = new JPasswordField();
//        txtPassword.setPreferredSize(new Dimension(250, 40));
//        txtPassword.setFont(new Font("Arial", Font.PLAIN, 18));
//
//        gbc.gridx = 0; gbc.gridy = 0; inputPanel.add(lbUsername, gbc);
//        gbc.gridx = 1; gbc.gridy = 0; inputPanel.add(txtUsername, gbc);
//        gbc.gridx = 0; gbc.gridy = 1; inputPanel.add(lbPassword, gbc);
//        gbc.gridx = 1; gbc.gridy = 1; inputPanel.add(txtPassword, gbc);
//        add(inputPanel, BorderLayout.CENTER);
//
//        // Panel nút
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
//        btnDangNhap = new JButton("Đăng Nhập");
//        btnDangNhap.setFont(new Font("Arial", Font.BOLD, 16));
//        btnDangNhap.setPreferredSize(new Dimension(140, 45));
//        btnDangNhap.setBackground(new Color(50, 205, 50));
//        btnDangNhap.setForeground(Color.WHITE);
//        btnDangNhap.addActionListener((ActionEvent e) -> xuLyDangNhap());
//
//        btnReset = new JButton("Reset");
//        btnReset.setFont(new Font("Arial", Font.BOLD, 16));
//        btnReset.setPreferredSize(new Dimension(140, 45));
//        btnReset.setBackground(new Color(220, 20, 60));
//        btnReset.setForeground(Color.WHITE);
//        btnReset.addActionListener((ActionEvent e) -> xuLyReset());
//
//        buttonPanel.add(btnDangNhap);
//        buttonPanel.add(btnReset);
//        add(buttonPanel, BorderLayout.SOUTH);
//    }
//
//    private void xuLyDangNhap() {
//        String username = txtUsername.getText().trim();
//        String matkhau = new String(txtPassword.getPassword()).trim();
//
//        if (username.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tài khoản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        } else if (matkhau.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        TaiKhoanDTO taiKhoan = tkBUS.dangNhap(username, matkhau);
//        if (taiKhoan != null) {
//            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
//            WorkFrame workFrame = new WorkFrame(taiKhoan);
//            workFrame.setVisible(true);
//            dispose();
//        } else {
//            JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu sai, hoặc tài khoản bị khóa!");
//        }
//    }
//
//    private void xuLyReset() {
//        txtUsername.setText("");
//        txtPassword.setText("");
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new DangNhapFrame().setVisible(true));
//    }
//}