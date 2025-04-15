package GUI;

import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DangNhapFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnDangNhap;
    private TaiKhoanBUS tkBUS;

    public DangNhapFrame() {
        tkBUS = new TaiKhoanBUS();
        setSystemLookAndFeel();
        initComponents();
    }

    private void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        setTitle("Đăng Nhập - Quản Lý Bán Sách");
        setSize(800, 452);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel chính với background
        JPanel mainPanel = new BackgroundPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        // Panel trung tâm chứa form đăng nhập
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;



        // Panel form đăng nhập
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(true);
        formPanel.setBackground(new Color(255, 255, 255, 130));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setPreferredSize(new Dimension(400, 300));

        // Username
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Reset insets cho các thành phần khác
        JLabel lbUsername = new JLabel("Tên đăng nhập:");
        lbUsername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        formPanel.add(lbUsername, gbc);

        gbc.gridy = 1;
        txtUsername = new JTextField(20);
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtUsername.setText("admin");
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        formPanel.add(txtUsername, gbc);

        // Password
        gbc.gridy = 2;
        JLabel lbPassword = new JLabel("Mật khẩu:");
        lbPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        formPanel.add(lbPassword, gbc);

        gbc.gridy = 3;
        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtPassword.setText("12345");
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        formPanel.add(txtPassword, gbc);

        // Nút đăng nhập
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        btnDangNhap = new JButton("Đăng Nhập");
        btnDangNhap.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnDangNhap.setBackground(new Color(44, 62, 80));
        btnDangNhap.setForeground(Color.WHITE);
        btnDangNhap.setFocusPainted(false);
        btnDangNhap.setBorderPainted(false);
        btnDangNhap.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hiệu ứng hover cho nút
        btnDangNhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnDangNhap.setBackground(new Color(33, 150, 243));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDangNhap.setBackground(new Color(44, 62, 80));
            }
        });

        btnDangNhap.addActionListener(e -> xuLyDangNhap());
        formPanel.add(btnDangNhap, gbc);

        // Thêm formPanel vào centerPanel với dịch chuyển sang phải
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 350, 10, 10);
        centerPanel.add(formPanel, gbc);
        
        // Thêm các panel vào mainPanel
        mainPanel.add(centerPanel, BorderLayout.CENTER);

    }

    private void xuLyDangNhap() {
        String username = txtUsername.getText().trim();
        String matkhau = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty()) {
            showErrorMessage("Vui lòng nhập tên tài khoản!");
            return;
        }
        if (matkhau.isEmpty()) {
            showErrorMessage("Vui lòng nhập mật khẩu!");
            return;
        }

        TaiKhoanDTO taiKhoan = tkBUS.dangNhap(username, matkhau);
        if (taiKhoan != null) {
            new WorkFrame(taiKhoan).setVisible(true);
            dispose();
        } else {
            showErrorMessage("Tài khoản hoặc mật khẩu không đúng!");
        }
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
    }

    // Lớp panel tùy chỉnh cho background
    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = new ImageIcon(getClass().getResource("/GUI/Image/backgroundlogin.jpg")).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DangNhapFrame().setVisible(true));
    }
}
