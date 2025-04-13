
package GUI.Dialog.TaiKhoanDialog;

import BUS.PhanQuyenBUS;
import DTO.NhomQuyenDTO;
import GUI.View.TaiKhoanPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TaiKhoanDialogAdd extends JDialog {
    private JTextField txTenDangNhap, txmatKhau, txMaNhanVien;
    private JComboBox<String> cbbTenQuyen;
    private TaiKhoanPanel tkPanel;
    private JButton btnXacNhan, btnHuy;

    private PhanQuyenBUS pqBUS = new PhanQuyenBUS();

    public TaiKhoanDialogAdd(JFrame parent, TaiKhoanPanel tkPanel) {
        super(parent, "Thêm tài khoản", true);
        this.tkPanel = tkPanel;
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // ======= Tiêu đề =======
        JLabel titleLabel = new JLabel("THÊM TÀI KHOẢN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(41, 128, 185));
        titlePanel.add(titleLabel);

        // ======= Panel nhập liệu =======
        JPanel pn_input = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lb_ten = new JLabel("Tên đăng nhập:");
        JLabel lb_mk = new JLabel("Mật khẩu:");
        JLabel lb_maquyen = new JLabel("Tên nhóm quyền:");

        lb_ten.setFont(new Font("Arial", Font.BOLD, 14));
        lb_mk.setFont(new Font("Arial", Font.BOLD, 14));
        lb_maquyen.setFont(new Font("Arial", Font.BOLD, 14));

        txTenDangNhap = new JTextField(20);
        txmatKhau = new JTextField(20);
        cbbTenQuyen = new JComboBox<>();

        txTenDangNhap.setPreferredSize(new Dimension(200, 30));
        txmatKhau.setPreferredSize(new Dimension(200, 30));
        cbbTenQuyen.setPreferredSize(new Dimension(200, 30));

        // Đổ dữ liệu nhóm quyền vào combobox
        List<NhomQuyenDTO> listQuyen = pqBUS.getNhomQuyenAll();
        for (NhomQuyenDTO pq : listQuyen) {
            cbbTenQuyen.addItem(pq.getTennhomquyen());
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        pn_input.add(lb_ten, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        pn_input.add(txTenDangNhap, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        pn_input.add(lb_mk, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        pn_input.add(txmatKhau, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        pn_input.add(lb_maquyen, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        pn_input.add(cbbTenQuyen, gbc);

        // ======= Panel nút bấm =======
        JPanel pn_button = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnXacNhan = createButton("Thêm dữ liệu", new Color(46, 204, 113));
        btnHuy = createButton("Hủy", new Color(231, 76, 60));
        pn_button.add(btnXacNhan);
        pn_button.add(btnHuy);

        // ======= Thêm vào dialog =======
        add(titlePanel, BorderLayout.NORTH);
        add(pn_input, BorderLayout.CENTER);
        add(pn_button, BorderLayout.SOUTH);

        // ======= Sự kiện nút "Hủy" =======
        btnHuy.addActionListener(e -> dispose());
    }

    public String getTenDangNhap() {
        return txTenDangNhap.getText().trim();
    }

    public String getMatKhau() {
        return txmatKhau.getText().trim();
    }

    public String getTenQuyen() {
        return (String) cbbTenQuyen.getSelectedItem();
    }

    public TaiKhoanPanel getTkPanel() {
        return tkPanel;
    }

    public void setController(TaiKhoanDialogAdd_Controller controller) {
        btnXacNhan.addActionListener(controller);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color actualBgColor = bgColor;
                if (getModel().isPressed()) actualBgColor = bgColor.darker();
                else if (getModel().isRollover()) actualBgColor = bgColor.brighter();

                g2.setColor(actualBgColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
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
}
