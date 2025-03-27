/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhanQuyenDialog;

import DTO.NhomQuyenDTO;
import GUI.View.PhanQuyenPanel;
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

/**
 *
 * @author Hi
 */
public class PhanQuyenDialogUpdate extends JDialog{
    private JTextField txTenNhomQuyen,txMaNhomQuyen;
    private PhanQuyenPanel pqPanel;
    private JButton btnXacNhan, btnHuy;
    private NhomQuyenDTO nhomQuyenHienTai; // tao bien de lay doi tuong hien tai dang chon
    
    public PhanQuyenDialogUpdate(JFrame parent, PhanQuyenPanel pqPanel,NhomQuyenDTO nhomQuyen){
        super(parent, "Sửa nhóm quyền", true);
        this.pqPanel = pqPanel;
        this.nhomQuyenHienTai = nhomQuyen; // Lưu lại đối tượng nhóm quyền hiện tại
        setSize(500, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // ======= Tiêu đề =======
        JLabel titleLabel = new JLabel("SỬA NHÓM QUYỀN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(41, 128, 185)); // Xanh dương
        titlePanel.add(titleLabel);

        // ======= Panel nhập liệu =======
        JPanel pn_input = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Label Mã nhóm quyền
        JLabel lb_ma = new JLabel("Mã nhóm quyền:");
        lb_ma.setFont(new Font("Arial", Font.BOLD, 14));
        txMaNhomQuyen = new JTextField(20);
        txMaNhomQuyen.setPreferredSize(new Dimension(200, 30));
        txMaNhomQuyen.setEditable(false); // Không cho sửa
        txMaNhomQuyen.setBackground(Color.LIGHT_GRAY);

        // Label Tên nhóm quyền
        JLabel lb_ten = new JLabel("Tên nhóm quyền:");
        lb_ten.setFont(new Font("Arial", Font.BOLD, 14));
        txTenNhomQuyen = new JTextField(20);
        txTenNhomQuyen.setPreferredSize(new Dimension(200, 30));
        
        // Hiển thị dữ liệu từ nhomQuyen
        txMaNhomQuyen.setText(String.valueOf(nhomQuyen.getManhomquyen()));
        txTenNhomQuyen.setText(nhomQuyen.getTennhomquyen());

        // Thêm vào panel
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        pn_input.add(lb_ma, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        pn_input.add(txMaNhomQuyen, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        pn_input.add(lb_ten, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        pn_input.add(txTenNhomQuyen, gbc);

        // ======= Panel nút bấm =======
        JPanel pn_button = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnXacNhan = createButton("Cập nhật dữ liệu", new Color(46, 204, 113)); // Xanh lá
        btnHuy = createButton("Hủy", new Color(231, 76, 60)); // Đỏ

        pn_button.add(btnXacNhan);
        pn_button.add(btnHuy);

        // ======= Thêm vào dialog =======
        add(titlePanel, BorderLayout.NORTH);
        add(pn_input, BorderLayout.CENTER);
        add(pn_button, BorderLayout.SOUTH);

        // Xử lý sự kiện nút "Hủy"
        btnHuy.addActionListener(e -> dispose());
        
    }
    
    public String getTenNhomQuyen() {
        return txTenNhomQuyen.getText().trim();
    }

    public void setController(PhanQuyenDialogUpdate_Controller controller) {
        btnXacNhan.addActionListener(controller);
    }
    public PhanQuyenPanel getPqPanel() {
        return pqPanel;
    }
    
    public void setNhomQuyen(NhomQuyenDTO nq) {
        this.nhomQuyenHienTai = nq;
        txTenNhomQuyen.setText(nq.getTennhomquyen()); // Đổ tên nhóm quyền vào ô nhập
}
    public NhomQuyenDTO getNhomQuyen() {
        return nhomQuyenHienTai; // Trả về đối tượng nhóm quyền hiện tại
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
