
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.TaiKhoanDialog;

import BUS.PhanQuyenBUS;
import DTO.NhomQuyenDTO;
import DTO.TaiKhoanDTO;
import GUI.View.TaiKhoanPanel;
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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TaiKhoanDialogUpdate extends JDialog {
    private JTextField txTenTaiKhoan, txMatKhau, txMaNhanVien;
    private JComboBox<String> comboBoxTrangThai;
    private JComboBox<String> comboBoxNhomQuyen;
    private TaiKhoanPanel tkPanel;
    private JButton btnXacNhan, btnHuy;
    private TaiKhoanDTO taiKhoanHienTai;

    public TaiKhoanDialogUpdate(JFrame parent, TaiKhoanPanel tkPanel, TaiKhoanDTO taiKhoan) {
        super(parent, "Sửa tài khoản", true);
        this.tkPanel = tkPanel;
        this.taiKhoanHienTai = taiKhoan;
        setSize(500, 450);
        setResizable(false);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("SỬA TÀI KHOẢN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(41, 128, 185));
        titlePanel.add(titleLabel);

        JPanel pn_input = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lb_ma = new JLabel("Mã nhân viên:");
        lb_ma.setFont(new Font("Arial", Font.BOLD, 14));
        txMaNhanVien = new JTextField(20);
        txMaNhanVien.setPreferredSize(new Dimension(200, 30));
        txMaNhanVien.setEditable(false);
        txMaNhanVien.setBackground(Color.LIGHT_GRAY);

        JLabel lb_ten = new JLabel("Tên tài khoản:");
        lb_ten.setFont(new Font("Arial", Font.BOLD, 14));
        txTenTaiKhoan = new JTextField(20);
        txTenTaiKhoan.setPreferredSize(new Dimension(200, 30));

        JLabel lb_mk = new JLabel("Mật khẩu:");
        lb_mk.setFont(new Font("Arial", Font.BOLD, 14));
        txMatKhau = new JTextField(20);
        txMatKhau.setPreferredSize(new Dimension(200, 30));

        JLabel lb_nq = new JLabel("Nhóm quyền:");
        lb_nq.setFont(new Font("Arial", Font.BOLD, 14));
        comboBoxNhomQuyen = new JComboBox<>();
        comboBoxNhomQuyen.setPreferredSize(new Dimension(200, 30));

        txMaNhanVien.setText(String.valueOf(taiKhoan.getManv()));
        txTenTaiKhoan.setText(taiKhoan.getUsername());
        txMatKhau.setText(taiKhoan.getMatkhau());
        PhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
        ArrayList<NhomQuyenDTO> danhSachTenNhomQuyen = phanQuyenBUS.getNhomQuyenAll();
        for (NhomQuyenDTO q : danhSachTenNhomQuyen) {
            comboBoxNhomQuyen.addItem(q.getTennhomquyen());
        }
        String tenHienTai = phanQuyenBUS.getTenquyenbyid(taiKhoan.getManhomquyen());
        comboBoxNhomQuyen.setSelectedItem(tenHienTai);

        JLabel lb_trangthai = new JLabel("Trạng thái:");
        lb_trangthai.setFont(new Font("Arial", Font.BOLD, 14));
        String[] trangThaiOptions = {"Đang hoạt động", "Khóa"};
        comboBoxTrangThai = new JComboBox<>(trangThaiOptions);
        comboBoxTrangThai.setPreferredSize(new Dimension(200, 30));
        if (taiKhoan.getTrangthai() == 1) {
            comboBoxTrangThai.setSelectedItem("Đang hoạt động");
        } else {
            comboBoxTrangThai.setSelectedItem("Khóa");
        }

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        pn_input.add(lb_ma, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        pn_input.add(txMaNhanVien, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        pn_input.add(lb_ten, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        pn_input.add(txTenTaiKhoan, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.3;
        pn_input.add(lb_mk, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        pn_input.add(txMatKhau, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.3;
        pn_input.add(lb_nq, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        pn_input.add(comboBoxNhomQuyen, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.3;
        pn_input.add(lb_trangthai, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        pn_input.add(comboBoxTrangThai, gbc);

        JPanel pn_button = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnXacNhan = createButton("Lưu thông tin", new Color(46, 204, 113));
        btnHuy = createButton("Hủy", new Color(231, 76, 60));

        pn_button.add(btnXacNhan);
        pn_button.add(btnHuy);

        add(titlePanel, BorderLayout.NORTH);
        add(pn_input, BorderLayout.CENTER);
        add(pn_button, BorderLayout.SOUTH);

        btnHuy.addActionListener(e -> dispose());
    }

    public String getMaNhanVien() {
        return txMaNhanVien.getText().trim();
    }

    public String getTenTaiKhoan() {
        return txTenTaiKhoan.getText().trim();
    }

    public String getMk() {
        return txMatKhau.getText().trim();
    }

    public String getNhomquyen() {
        String ten = (String) comboBoxNhomQuyen.getSelectedItem();
        PhanQuyenBUS pqBus = new PhanQuyenBUS();
        int ma = pqBus.getIdquyenbyTen(ten);
        return String.valueOf(ma);
    }

    public String getTrangThai() {
        return (String) comboBoxTrangThai.getSelectedItem();
    }

    public void setController(TaiKhoanDialogUpdate_Controller controller) {
        btnXacNhan.addActionListener(controller);
    }

    public TaiKhoanPanel getTkPanel() {
        return tkPanel;
    }

    public TaiKhoanDTO getTaiKhoanCanSua() {
        return taiKhoanHienTai;
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
