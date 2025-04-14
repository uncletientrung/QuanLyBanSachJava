package GUI.Dialog.BookDialog;

import DTO.SachDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author DELL
 */
public class BookDialogDelete extends JDialog {
    private SachDTO sDTO;

    public BookDialogDelete(JFrame parent, SachDTO sDTO) {
        super(parent, "Danh mục xóa sách", true);
        this.sDTO=sDTO;
        // Thiết lập layout chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        // ========== PHẦN TIÊU ĐỀ ==========
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(231, 76, 60)); // Màu đỏ giống PhieuXuatDialogDelete
        titlePanel.setBorder(new EmptyBorder(15, 0, 15, 0));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));

        JLabel titleLabel = new JLabel("XÓA SÁCH");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalGlue());

        // ========== PHẦN NỘI DUNG ==========
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Câu hỏi xác nhận
        JLabel questionLabel = new JLabel("Bạn có chắc chắn muốn xóa sách này?");
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Panel thông tin
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        infoPanel.setMaximumSize(new Dimension(400, 60));

        // Mã sách
        JPanel maSachPanel = createInfoPanel("Mã sách", String.valueOf(sDTO.getMasach()));

        // Tên sách
        JPanel tenSachPanel = createInfoPanel("Tên sách", sDTO.getTensach());

        infoPanel.add(maSachPanel);
        infoPanel.add(tenSachPanel);

        contentPanel.add(questionLabel);
        contentPanel.add(infoPanel);

        // ========== PHẦN NÚT BẤM ==========
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        JButton btnDelete = createButton("Xóa", new Color(244, 67, 54)); // Nút đỏ
        JButton btnCancel = createButton("Đóng", new Color(150, 150, 150)); // Nút xám

        buttonPanel.add(btnDelete);
        buttonPanel.add(btnCancel);

        // Thêm các phần vào main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Thêm ActionListener
        ActionListener action = new BookDialogDelete_Controller(this);
        btnDelete.addActionListener(action);
        btnCancel.addActionListener(action);

        add(mainPanel);
        setSize(500, 280); // Kích thước giống PhieuXuatDialogDelete
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
    }

    private JPanel createInfoPanel(String title, String value) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 14));
        valueLabel.setForeground(new Color(70, 70, 70));
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5))); // Khoảng cách giữa title và value
        panel.add(valueLabel);
        return panel;
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

            @Override
            protected void paintBorder(Graphics g) {
                // Không vẽ border mặc định
            }
        };

        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(100, 35)); // Kích thước nút giống PhieuXuatDialogDelete

        return button;
    }

    public SachDTO getsDTO() {
        return sDTO;
    }

    
}