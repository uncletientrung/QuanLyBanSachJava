/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.KhuyenMaiDialog;
import DTO.KhuyenMaiDTO;
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
public class KhuyenMaiDialogDelete extends JDialog {
    private JButton btnXoa, btnHuy;
    private boolean xacNhan = false;
    private KhuyenMaiDTO khuyenMaiHienTai;

    public KhuyenMaiDialogDelete(Frame parent, KhuyenMaiDTO km) {
        super(parent, "Xóa khuyến mãi", true);
        this.khuyenMaiHienTai = km;
        setSize(500, 250);
        setResizable(false);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // ======= Tiêu đề =======
        JLabel titleLabel = new JLabel("XÓA KHUYẾN MÃI", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(192, 57, 43));
        titlePanel.add(titleLabel);

        // ======= Nội dung =======
        JPanel pn_content = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lblMessage = new JLabel("Bạn có chắc chắn muốn xóa khuyến mãi này?", SwingConstants.CENTER);
        lblMessage.setFont(new Font("Arial", Font.BOLD, 16));
        lblMessage.setForeground(Color.DARK_GRAY);

        JLabel lblTenKM = new JLabel(km.getTenChuongTrinh(), SwingConstants.CENTER);
        lblTenKM.setFont(new Font("Arial", Font.BOLD, 18));
        lblTenKM.setForeground(Color.RED);

        gbc.gridx = 0; gbc.gridy = 0;
        pn_content.add(lblMessage, gbc);

        gbc.gridy = 1;
        pn_content.add(lblTenKM, gbc);

        // ======= Nút bấm =======
        JPanel pn_button = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnXoa = createButton("Xóa", new Color(231, 76, 60));
        btnHuy = createButton("Hủy", new Color(52, 152, 219));

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

    public KhuyenMaiDTO getKhuyenMai() {
        return khuyenMaiHienTai;
    }

    public void setController(KhuyenMaiDialogDelete_Controller controller) {
        btnXoa.addActionListener(controller);
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
