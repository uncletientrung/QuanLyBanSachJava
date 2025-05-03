/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.KhuyenMaiDialog;

import GUI.View.KhuyenMaiPanel;
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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

/**
 *
 * @author Hi
 */
public class KhuyenMaiDialogAdd extends JDialog{
    private JTextField txTenChuongTrinh;
    private JTextField txHoaDonToiThieu;
    private JTextField txPhanTramGiam;
    private JDateChooser dcNgayBatDau;
    private JDateChooser dcNgayKetThuc;
    private KhuyenMaiPanel KMPanel;
    private JButton btnXacNhan, btnHuy;

    public KhuyenMaiDialogAdd(JFrame parent, KhuyenMaiPanel KMPanel) {
        super(parent, "Thêm Chương trình khuyến mãi", true);
        this.KMPanel = KMPanel;
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // ======= Tiêu đề =======
        JLabel titleLabel = new JLabel("THÊM CHƯƠNG TRÌNH KHUYẾN MÃI", SwingConstants.CENTER);
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

        JLabel lb_ten = new JLabel("Tên chương trình:");
        JLabel lb_ngaybatdau = new JLabel("Ngày bắt đầu:");
        JLabel lb_ngayketthuc = new JLabel("Ngày kết thúc:");
        JLabel lb_hoadontoithieu = new JLabel("Hóa đơn tối thiểu:");
        JLabel lb_giamgia = new JLabel("% giảm giá:");

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        lb_ten.setFont(labelFont);
        lb_ngaybatdau.setFont(labelFont);
        lb_ngayketthuc.setFont(labelFont);
        lb_hoadontoithieu.setFont(labelFont);
        lb_giamgia.setFont(labelFont);

        txTenChuongTrinh = new JTextField(20);
        dcNgayBatDau = new JDateChooser();
        dcNgayKetThuc = new JDateChooser();
//  

        txHoaDonToiThieu = new JTextField(20);
        txPhanTramGiam = new JTextField(20);


        Dimension fieldSize = new Dimension(200, 30);
        txTenChuongTrinh.setPreferredSize(fieldSize);
        dcNgayBatDau.setPreferredSize(fieldSize);
        dcNgayKetThuc.setPreferredSize(fieldSize);
        txHoaDonToiThieu.setPreferredSize(fieldSize);
        txPhanTramGiam.setPreferredSize(fieldSize);

        gbc.gridx = 0; gbc.gridy = 0;
        pn_input.add(lb_ten, gbc);
        gbc.gridx = 1;
        pn_input.add(txTenChuongTrinh, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        pn_input.add(lb_ngaybatdau, gbc);
        gbc.gridx = 1;
        pn_input.add(dcNgayBatDau, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        pn_input.add(lb_ngayketthuc, gbc);
        gbc.gridx = 1;
        pn_input.add(dcNgayKetThuc, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        pn_input.add(lb_hoadontoithieu, gbc);
        gbc.gridx = 1;
        pn_input.add(txHoaDonToiThieu, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        pn_input.add(lb_giamgia, gbc);
        gbc.gridx = 1;
        pn_input.add(txPhanTramGiam, gbc);

        // ======= Panel nút bấm =======
        JPanel pn_button = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnXacNhan = createButton("Thêm dữ liệu", new Color(46, 204, 113));
        btnHuy = createButton("Hủy", new Color(231, 76, 60));

        pn_button.add(btnXacNhan);
        pn_button.add(btnHuy);
        dcNgayBatDau.setDateFormatString("dd-MM-yyyy");
        dcNgayKetThuc.setDateFormatString("dd-MM-yyyy");
        dcNgayBatDau.setDate(new Date());
        dcNgayKetThuc.setDate(new Date());

        // ======= Thêm vào dialog =======
        add(titlePanel, BorderLayout.NORTH);
        add(pn_input, BorderLayout.CENTER);
        add(pn_button, BorderLayout.SOUTH);

        // ======= Sự kiện =======
        btnHuy.addActionListener(e -> dispose());
    }

    // ======= Getter dữ liệu =======
    public String getTenChuongTrinh() {
        return txTenChuongTrinh.getText().trim();
    }

    public Date getNgayBatDau() {
        return dcNgayBatDau.getDate();
    }

    public Date getNgayKetThuc() {
        return dcNgayKetThuc.getDate();
    }

    public String getHoaDonToiThieu() {
        return txHoaDonToiThieu.getText().trim();
    }

    public String getPhanTramGiam() {
        return txPhanTramGiam.getText().trim();
    }

    public void setController(KhuyenMaiDialogAdd_Controller controller) {
        btnXacNhan.addActionListener(controller);
    }

    public KhuyenMaiPanel getKMPanel() {
        return KMPanel;
    }

    // ======= Tạo button đồng bộ UI =======
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
