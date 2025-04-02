/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.TacGiaDialog;
import DTO.TacGiaDTO;
import GUI.Dialog.ThongTinChungDialog.TacGiaDialog;
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
public class TacGiaDialogUpdate extends JDialog{
    private JTextField txTenTacGia,txMaTacGia;
    private TacGiaDialog tgPanel;
    private JButton btnXacNhan, btnHuy;
    private TacGiaDTO tacGiaHienTai; // tao bien de lay doi tuong hien tai dang chon
    
    public TacGiaDialogUpdate(JFrame parent, TacGiaDialog tgPanel,TacGiaDTO tacGia){
        super(parent, "Sửa tác giả", true);
        this.tgPanel = tgPanel;
        this.tacGiaHienTai = tacGia; // Lưu lại đối tượng tác giả hiện tại
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // ======= Tiêu đề =======
        JLabel titleLabel = new JLabel("SỬA TÁC GIẢ", SwingConstants.CENTER);
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
        
        // Label Mã tác giả
        JLabel lb_ma = new JLabel("Mã tác giả:");
        lb_ma.setFont(new Font("Arial", Font.BOLD, 14));
        txMaTacGia = new JTextField(20);
        txMaTacGia.setPreferredSize(new Dimension(200, 30));
        txMaTacGia.setEditable(false); // Không cho sửa
        txMaTacGia.setBackground(Color.LIGHT_GRAY);

        // Label Tên tác giả
        JLabel lb_ten = new JLabel("Tên tác giả:");
        lb_ten.setFont(new Font("Arial", Font.BOLD, 14));
        txTenTacGia = new JTextField(20);
        txTenTacGia.setPreferredSize(new Dimension(200, 30));
        
        // Hiển thị dữ liệu từ tacGia
        txMaTacGia.setText(String.valueOf(tacGia.getMatacgia()));
        txTenTacGia.setText(tacGia.getHotentacgia());

        // Thêm vào panel
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        pn_input.add(lb_ma, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        pn_input.add(txMaTacGia, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        pn_input.add(lb_ten, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        pn_input.add(txTenTacGia, gbc);

        // ======= Panel nút bấm =======
        JPanel pn_button = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnXacNhan = createButton("Lưu thông tin", new Color(46, 204, 113)); // Xanh lá
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
    
    public String getTenTacGia() {
        return txTenTacGia.getText().trim();
    }

    public void setController(TacGiaDialogUpdate_Controller controller) {
        btnXacNhan.addActionListener(controller);
    }
    public TacGiaDialog getTgPanel() {
        return tgPanel;
    }
    
    public void setTacGia(TacGiaDTO tg) {
        this.tacGiaHienTai = tg;
        txTenTacGia.setText(tg.getHotentacgia()); // Đổ tên tác giả vào ô nhập
    }
    public TacGiaDTO getTacGia() {
        return tacGiaHienTai; // Trả về đối tượng tác giả hiện tại
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
