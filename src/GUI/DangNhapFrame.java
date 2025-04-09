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
//        setSize(800, 500);
//        
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
//        txtUsername = new JTextField("trung123");
//        txtUsername.setPreferredSize(new Dimension(250, 40));
//        txtUsername.setFont(new Font("Arial", Font.PLAIN, 18));
//
//        JLabel lbPassword = new JLabel("Mật khẩu:");
//        lbPassword.setFont(new Font("Arial", Font.BOLD, 18));
//        txtPassword = new JPasswordField("12345");
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
package GUI;
import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DangNhapFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnDangNhap, btnReset;
    private TaiKhoanBUS tkBUS;

    public DangNhapFrame() {
        tkBUS = new TaiKhoanBUS();
        initComponents();
        setSystemLookAndFeel();
    }
        // Phương thức thiết lập System Look and Feel
    private void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        setTitle("Đăng Nhập");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Sử dụng null layout

        // Panel chính để chứa background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Vẽ hình ảnh background
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/GUI/Image/backgroundlogin.jpg"));
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, getWidth(), getHeight());
        add(mainPanel);

        // Tiêu đề - Giữ nguyên vị trí làm điểm tham chiếu
        JLabel lbTitle = new JLabel("ĐĂNG NHẬP HỆ THỐNG", JLabel.CENTER);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lbTitle.setOpaque(true);
        lbTitle.setBackground(new Color(128,128,128));
        lbTitle.setBounds(550, 160, 400, 50);
        mainPanel.add(lbTitle);

        // Panel nhập liệu - Điều chỉnh vị trí để căn chỉnh với tiêu đề
        JPanel inputPanel = new JPanel(null);
        inputPanel.setOpaque(false);
        inputPanel.setBounds(550, 230, 400, 160);
        mainPanel.add(inputPanel);

        // Tạo các JLabel và JTextField - Điều chỉnh vị trí trong inputPanel
        JLabel lbUsername = new JLabel("Tài khoản:");
        lbUsername.setFont(new Font("Arial", Font.BOLD, 18));
        lbUsername.setForeground(Color.WHITE);
        lbUsername.setBounds(0, 60, 120, 30);
        inputPanel.add(lbUsername);

        txtUsername = new JTextField("admin");
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 18));
        txtUsername.setBounds(160, 60, 250, 40);
        inputPanel.add(txtUsername);

        JLabel lbPassword = new JLabel("Mật khẩu:");
        lbPassword.setFont(new Font("Arial", Font.BOLD, 18));
        lbPassword.setForeground(Color.WHITE);
        lbPassword.setBounds(0, 120, 120, 30);
        inputPanel.add(lbPassword);

        txtPassword = new JPasswordField("12345");
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        txtPassword.setBounds(160, 120, 250, 40);
        inputPanel.add(txtPassword);

        // Panel nút - Điều chỉnh để căn giữa với panel nhập liệu và tiêu đề
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(550, 400, 400, 100);
        mainPanel.add(buttonPanel);

        // Điều chỉnh vị trí các nút để căn đều trong buttonPanel
        btnDangNhap = new JButton("Đăng Nhập");
        btnDangNhap.setFont(new Font("Arial", Font.BOLD, 16));
        btnDangNhap.setBackground(new Color(0,128,0));
        btnDangNhap.setForeground(Color.WHITE);
        btnDangNhap.setBounds(50, 40, 140, 45);
        btnDangNhap.addActionListener((ActionEvent e) -> xuLyDangNhap());
        buttonPanel.add(btnDangNhap);

        btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Arial", Font.BOLD, 16));
        btnReset.setBackground(new Color(220, 20, 60));
        btnReset.setForeground(Color.WHITE);
        btnReset.setBounds(210, 40, 140, 45);
        btnReset.addActionListener((ActionEvent e) -> xuLyReset());
        buttonPanel.add(btnReset);
    }

    private void xuLyDangNhap() {
        String username = txtUsername.getText().trim();
        String matkhau = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tài khoản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (matkhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TaiKhoanDTO taiKhoan = tkBUS.dangNhap(username, matkhau);
        if (taiKhoan != null) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
            WorkFrame workFrame = new WorkFrame(taiKhoan);
            workFrame.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu sai, hoặc tài khoản bị khóa!");
        }
    }

    private void xuLyReset() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DangNhapFrame().setVisible(true));
    }
}

