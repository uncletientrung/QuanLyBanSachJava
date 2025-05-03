package GUI.Dialog.KhuyenMaiDialog;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import DTO.KhuyenMaiDTO;
import GUI.View.KhuyenMaiPanel;
import com.toedter.calendar.JDateChooser;
import GUI.Format.DateFormat;

public class KhuyenMaiDialogUpdate extends JDialog {
    private JTextField txTenChuongTrinh, txHoaDonToiThieu, txPhanTramGiam;
    private JDateChooser dateNgayBatDau, dateNgayKetThuc;
    private JButton btnXacNhan, btnHuy;
    private KhuyenMaiDTO khuyenMaiHienTai; // Lưu lại đối tượng chương trình khuyến mãi hiện tại
    private KhuyenMaiPanel kmPanel;

    public KhuyenMaiDialogUpdate(JFrame parent, KhuyenMaiPanel kmPanel, KhuyenMaiDTO khuyenMai) {
        super(parent, "Sửa Chương Trình Khuyến Mãi", true);
        this.kmPanel = kmPanel;
        this.khuyenMaiHienTai = khuyenMai; // Lưu lại đối tượng chương trình khuyến mãi hiện tại
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // ======= Tiêu đề =======
        JLabel titleLabel = new JLabel("SỬA CHƯƠNG TRÌNH KHUYẾN MÃI", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(41, 128, 185)); // Màu xanh dương
        titlePanel.add(titleLabel);

        // ======= Panel nhập liệu =======
        JPanel pn_input = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Các label cho form
        JLabel lb_tenChuongTrinh = new JLabel("Tên chương trình:");
        JLabel lb_hoaDonToiThieu = new JLabel("Hóa đơn tối thiểu:");
        JLabel lb_phanTramGiam = new JLabel("% giảm giá:");
        JLabel lb_ngayBatDau = new JLabel("Ngày bắt đầu:");
        JLabel lb_ngayKetThuc = new JLabel("Ngày kết thúc:");
        
        lb_tenChuongTrinh.setFont(new Font("Arial", Font.BOLD, 14));
        lb_hoaDonToiThieu.setFont(new Font("Arial", Font.BOLD, 14));
        lb_phanTramGiam.setFont(new Font("Arial", Font.BOLD, 14));
        lb_ngayBatDau.setFont(new Font("Arial", Font.BOLD, 14));
        lb_ngayKetThuc.setFont(new Font("Arial", Font.BOLD, 14));

        // Các trường nhập liệu
        txTenChuongTrinh = new JTextField(20);
        txHoaDonToiThieu = new JTextField(20);
        txPhanTramGiam = new JTextField(20);
        dateNgayBatDau = new JDateChooser();
        dateNgayKetThuc = new JDateChooser();

        txTenChuongTrinh.setPreferredSize(new Dimension(200, 30));
        txHoaDonToiThieu.setPreferredSize(new Dimension(200, 30));
        txPhanTramGiam.setPreferredSize(new Dimension(200, 30));
        
     

        gbc.gridx = 0; gbc.gridy = 0;
        pn_input.add(lb_tenChuongTrinh, gbc);
        gbc.gridx = 1;
        pn_input.add(txTenChuongTrinh, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        pn_input.add(lb_hoaDonToiThieu, gbc);
        gbc.gridx = 1;
        pn_input.add(txHoaDonToiThieu, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        pn_input.add(lb_phanTramGiam, gbc);
        gbc.gridx = 1;
        pn_input.add(txPhanTramGiam, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        pn_input.add(lb_ngayBatDau, gbc);
        gbc.gridx = 1;
        pn_input.add(dateNgayBatDau, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        pn_input.add(lb_ngayKetThuc, gbc);
        gbc.gridx = 1;
        pn_input.add(dateNgayKetThuc, gbc);

        // ======= Panel nút bấm =======
        JPanel pn_button = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnXacNhan = createButton("Lưu thông tin", new Color(46, 204, 113)); // Xanh lá
        btnHuy = createButton("Hủy", new Color(231, 76, 60)); // Đỏ

        pn_button.add(btnXacNhan);
        pn_button.add(btnHuy);
        
        // Hiển thị dữ liệu từ đối tượng khuyến mãi lên form
        txTenChuongTrinh.setText(khuyenMai.getTenChuongTrinh());
        txHoaDonToiThieu.setText(String.valueOf(khuyenMai.getDieuKienToiThieu()));
        txPhanTramGiam.setText(String.valueOf(khuyenMai.getPhanTramGiam()));
        dateNgayBatDau.setDateFormatString("dd-MM-yyyy");
        dateNgayKetThuc.setDateFormatString("dd-MM-yyyy");
        dateNgayBatDau.setDate(khuyenMai.getNgayBatDau());
        dateNgayKetThuc.setDate(khuyenMai.getNgayKetThuc());

        // ======= Thêm vào dialog =======
        add(titlePanel, BorderLayout.NORTH);
        add(pn_input, BorderLayout.CENTER);
        add(pn_button, BorderLayout.SOUTH);

        // Xử lý sự kiện nút "Hủy"
        btnHuy.addActionListener(e -> dispose());
    }

    public String getTenChuongTrinh() {
        return txTenChuongTrinh.getText().trim();
    }

    public String getHoaDonToiThieu() {
        return txHoaDonToiThieu.getText().trim();
    }

    public String getPhanTramGiam() {
        return txPhanTramGiam.getText().trim();
    }

    public Date getNgayBatDau() {
        return dateNgayBatDau.getDate();
    }

    public Date getNgayKetThuc() {
        return dateNgayKetThuc.getDate();
    }

    public void setController(KhuyenMaiDialogUpdate_Controller controller) {
        btnXacNhan.addActionListener(controller);
    }

    public KhuyenMaiPanel getKmPanel() {
        return kmPanel;
    }

    public KhuyenMaiDTO getKhuyenMai() {
        return khuyenMaiHienTai; // Trả về đối tượng chương trình khuyến mãi hiện tại
    }

    // ======= Tạo button đồng bộ với phong cách UI =======
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
                // Vẽ bo tròn góc cho nút
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
}
