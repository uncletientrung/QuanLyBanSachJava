
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

