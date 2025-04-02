/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.TheLoaiDialog;  // Changed package name
import DTO.TheLoaiDTO;  // Changed import
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Hi
 */
public class TheLoaiDialogDelete extends JDialog {  // Changed class name
    private JButton btnXoa, btnHuy;
    private boolean xacNhan = false;
    private String maTheLoai;  // Changed variable name
    private TheLoaiDTO theLoaiHienTai; // Changed variable name and type

    public TheLoaiDialogDelete(Frame parent, TheLoaiDTO theLoai) {  // Changed parameter type
        super(parent, "Xóa thể loại", true);  // Changed dialog title
        this.theLoaiHienTai = theLoai;  // Updated variable name
        setSize(500, 250);
        setResizable(false);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // ======= Tiêu đề =======
        JLabel titleLabel = new JLabel("XÓA THỂ LOẠI", SwingConstants.CENTER);  // Changed title text
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

        JLabel lblMessage = new JLabel("Bạn có chắc chắn muốn xóa thể loại này?", SwingConstants.CENTER);  // Changed message text
        lblMessage.setFont(new Font("Arial", Font.BOLD, 16));
        lblMessage.setForeground(Color.DARK_GRAY);

        JLabel lblTenTheLoai = new JLabel(theLoai.getTentheloai(), SwingConstants.CENTER);  // Changed variable name and method call
        lblTenTheLoai.setFont(new Font("Arial", Font.BOLD, 18));
        lblTenTheLoai.setForeground(Color.RED);

        gbc.gridx = 0; gbc.gridy = 0;
        pn_content.add(lblMessage, gbc);

        gbc.gridy = 1;
        pn_content.add(lblTenTheLoai, gbc);

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
    
    public void setTheLoai(TheLoaiDTO tl) {  // Changed method name and parameter type
        this.theLoaiHienTai = tl;  // Updated variable name
        maTheLoai = (tl.getTentheloai()); // Changed variable name and method call
    }
    
    public TheLoaiDTO getTheLoai() {  // Changed method name and return type
        return theLoaiHienTai; // Updated variable name
    }

    public void setController(TheLoaiDialogDelete_Controller controller) {  // Changed parameter type
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
