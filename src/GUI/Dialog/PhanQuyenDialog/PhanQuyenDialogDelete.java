package GUI.Dialog.PhanQuyenDialog;

import DTO.NhomQuyenDTO;
import java.awt.*;
import javax.swing.*;

public class PhanQuyenDialogDelete extends JDialog {
    private JButton btnXoa, btnHuy;
    private boolean xacNhan = false;
    private String maNhomQuyen;
    private NhomQuyenDTO nhomQuyenHienTai; // Thêm biến này để lưu nhóm quyền

    public PhanQuyenDialogDelete(Frame parent, NhomQuyenDTO nhomQuyen) {
//        super(parent, "Xác nhận xóa", true);
//        this.nhomQuyenHienTai = nhomQuyen; // Lưu nhóm quyền cần xóa
//        setLayout(new BorderLayout());
//        setSize(400, 200);
//        setLocationRelativeTo(parent);
//        
//        // ======= Panel chứa nội dung =======
//        JPanel panelContent = new JPanel();
//        panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.Y_AXIS));
//        panelContent.setBackground(Color.WHITE);
//
//       
//
//        // ======= Tiêu đề =======
//        JLabel lblMessage = new JLabel("Bạn có chắc chắn muốn xóa nhóm quyền?");
//        lblMessage.setFont(new Font("Arial", Font.BOLD, 16));
//        lblMessage.setForeground(new Color(192, 57, 43)); // Màu đỏ đậm
//        lblMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        // ======= Tên nhóm quyền =======
//        JLabel lblTenNhom = new JLabel("==> " + nhomQuyen.getTennhomquyen());
//        lblTenNhom.setFont(new Font("Arial", Font.BOLD, 20));
//        lblTenNhom.setForeground(Color.DARK_GRAY);
//        lblTenNhom.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        // Thêm padding
//        
//        panelContent.add(Box.createVerticalStrut(5));
//        panelContent.add(lblMessage);
//        panelContent.add(Box.createVerticalStrut(5));
//        panelContent.add(lblTenNhom);
//        panelContent.add(Box.createVerticalStrut(15));
//        
//        add(panelContent, BorderLayout.CENTER);
//
//        // ======= Panel chứa button =======
//        JPanel panelButton = new JPanel();
//        panelButton.setBackground(Color.WHITE);
//        btnXoa = createButton("Xóa", new Color(231, 76, 60)); // Đỏ
//        btnHuy = createButton("Hủy", new Color(52, 152, 219)); // Xanh dương
//
//        btnXoa.addActionListener(e -> {
//            xacNhan = true;
//            dispose();
//        });
//
//        btnHuy.addActionListener(e -> dispose());
//
//        panelButton.add(btnXoa);
//        panelButton.add(btnHuy);
//        add(panelButton, BorderLayout.SOUTH);
        super(parent, "Xóa nhóm quyền", true);
        this.nhomQuyenHienTai = nhomQuyen;
        setSize(500, 250);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // ======= Tiêu đề =======
        JLabel titleLabel = new JLabel("XÓA NHÓM QUYỀN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(192, 57, 43)); // Màu đỏ đậm
        titlePanel.add(titleLabel);

        // ======= Panel nội dung =======
        JPanel pn_content = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lblMessage = new JLabel("Bạn có chắc chắn muốn xóa nhóm quyền này?", SwingConstants.CENTER);
        lblMessage.setFont(new Font("Arial", Font.BOLD, 16));
        lblMessage.setForeground(Color.DARK_GRAY);

        JLabel lblTenNhom = new JLabel(nhomQuyen.getTennhomquyen(), SwingConstants.CENTER);
        lblTenNhom.setFont(new Font("Arial", Font.BOLD, 18));
        lblTenNhom.setForeground(Color.RED);

        gbc.gridx = 0; gbc.gridy = 0;
        pn_content.add(lblMessage, gbc);

        gbc.gridy = 1;
        pn_content.add(lblTenNhom, gbc);

        // ======= Panel nút bấm =======
        JPanel pn_button = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnXoa = createButton("Xóa", new Color(231, 76, 60)); // Đỏ
        btnHuy = createButton("Hủy", new Color(52, 152, 219)); // Xanh dương

        btnXoa.addActionListener(e -> {
            xacNhan = true;
            dispose();
        });

        btnHuy.addActionListener(e -> dispose());

        pn_button.add(btnXoa);
        pn_button.add(btnHuy);

        // ======= Thêm vào dialog =======
        add(titlePanel, BorderLayout.NORTH);
        add(pn_content, BorderLayout.CENTER);
        add(pn_button, BorderLayout.SOUTH);
    }

    public boolean isXacNhan() {
        return xacNhan;
    }
    
    public void setNhomQuyen(NhomQuyenDTO nq) {
        this.nhomQuyenHienTai = nq;
        maNhomQuyen=(nq.getTennhomquyen()); // Đổ tên nhóm quyền vào ô nhập
    }
    
    public NhomQuyenDTO getNhomQuyen() {
        return nhomQuyenHienTai; // Trả về đối tượng nhóm quyền hiện tại
    }

    public void setController(PhanQuyenDialogDelete_Controller controller) {
        btnXoa.addActionListener(controller);
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
