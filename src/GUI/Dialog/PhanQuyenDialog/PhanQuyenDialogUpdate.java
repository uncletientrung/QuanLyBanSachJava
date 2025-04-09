
package GUI.Dialog.PhanQuyenDialog;

import BUS.DanhMucChucNangBUS;
import DTO.DanhMucChucNangDTO;
import DTO.NhomQuyenDTO;
import GUI.View.PhanQuyenPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PhanQuyenDialogUpdate extends JDialog {
    private JTextField txTenNhomQuyen, txMaNhomQuyen;
    private PhanQuyenPanel pqPanel;
    private JButton btnXacNhan, btnHuy;
    private NhomQuyenDTO nhomQuyenHienTai;
    private JPanel checkboxPanel;
    private List<JCheckBox> danhSachCheckboxChucNang = new ArrayList<>();


    private ArrayList<DanhMucChucNangDTO> listChucNang = new DanhMucChucNangBUS().getAllchucnang();

    public PhanQuyenDialogUpdate(JFrame parent, PhanQuyenPanel pqPanel, NhomQuyenDTO nhomQuyen) {
        super(parent, "Sửa nhóm quyền", true);
        this.pqPanel = pqPanel;
        this.nhomQuyenHienTai = nhomQuyen;
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // ======= Tiêu đề =======
        JLabel titleLabel = new JLabel("SỬA NHÓM QUYỀN", SwingConstants.CENTER);
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

        // Mã nhóm quyền
        JLabel lb_ma = new JLabel("Mã nhóm quyền:");
        lb_ma.setFont(new Font("Arial", Font.BOLD, 14));
        txMaNhomQuyen = new JTextField(20);
        txMaNhomQuyen.setEditable(false);
        txMaNhomQuyen.setBackground(Color.LIGHT_GRAY);
        txMaNhomQuyen.setText(String.valueOf(nhomQuyen.getManhomquyen()));

        // Tên nhóm quyền
        JLabel lb_ten = new JLabel("Tên nhóm quyền:");
        lb_ten.setFont(new Font("Arial", Font.BOLD, 14));
        txTenNhomQuyen = new JTextField(20);
        txTenNhomQuyen.setText(nhomQuyen.getTennhomquyen());

        // Chức năng
        JLabel lb_cn = new JLabel("Chức năng:");
        lb_cn.setFont(new Font("Arial", Font.BOLD, 14));

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        pn_input.add(lb_ma, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        pn_input.add(txMaNhomQuyen, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        pn_input.add(lb_ten, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        pn_input.add(txTenNhomQuyen, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.3;
        pn_input.add(lb_cn, gbc);

        // Panel chức năng (checkbox)
        checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(5, 2, 10, 10));

        for (DanhMucChucNangDTO cn : listChucNang) {
            JCheckBox cb = new JCheckBox(cn.getTenchucnang());
            cb.putClientProperty("maCN", cn.getMachucnang()); // Gắn mã chức năng vào checkbox
            if (nhomQuyen.getDsMaChucNang() != null && nhomQuyen.getDsMaChucNang().contains(cn.getMachucnang())) {
                cb.setSelected(true);
            }
            danhSachCheckboxChucNang.add(cb); // <-- thêm vào danh sách
            checkboxPanel.add(cb);
        }


        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pn_input.add(checkboxPanel, gbc);

        // ======= Nút xác nhận và huỷ =======
        JPanel pn_button = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnXacNhan = createButton("Lưu thông tin", new Color(46, 204, 113));
        btnHuy = createButton("Hủy", new Color(231, 76, 60));
        pn_button.add(btnXacNhan);
        pn_button.add(btnHuy);

        // ======= Thêm vào dialog =======
        add(titlePanel, BorderLayout.NORTH);
        add(pn_input, BorderLayout.CENTER);
        add(pn_button, BorderLayout.SOUTH);

        btnHuy.addActionListener(e -> dispose());
    }

    public String getTenNhomQuyen() {
        return txTenNhomQuyen.getText().trim();
    }

  public List<Integer> getDanhSachChucNangDaChon() {
    List<Integer> danhSach = new ArrayList<>();
    for (JCheckBox cb : danhSachCheckboxChucNang) {
        if (cb.isSelected()) {
            danhSach.add((Integer) cb.getClientProperty("maCN"));
        }
    }
    return danhSach;
}



    public void setController(PhanQuyenDialogUpdate_Controller controller) {
        btnXacNhan.addActionListener(controller);
    }

    public PhanQuyenPanel getPqPanel() {
        return pqPanel;
    }

    public NhomQuyenDTO getNhomQuyen() {
        return nhomQuyenHienTai;
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color actualBgColor = bgColor;
                if (getModel().isPressed()) {
                    actualBgColor = bgColor.darker();
                } else if (getModel().isRollover()) {
                    actualBgColor = bgColor.brighter();
                }
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
