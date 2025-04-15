package GUI.Dialog.BookDialog;

import BUS.NhaXuatBanBUS;
import BUS.TacGiaBUS;
import BUS.TheLoaiBUS;
import DTO.NhaXuatBanDTO;
import DTO.TacGiaDTO;
import DTO.TheLoaiDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import GUI.Format.NumberFormatter;

public class BookDialogUpdate extends JDialog {
    private JTextField txfMasach, txfTensach, txfSoluong, txfNamxuatban, txfDongia;
    private NhaXuatBanBUS nxbBUS = new NhaXuatBanBUS();
    private TacGiaBUS tgBUS = new TacGiaBUS();
    private TheLoaiBUS tlBUS = new TheLoaiBUS();
    private JComboBox<String> cbb_NXB, cbb_TG, cbb_TL;

    public BookDialogUpdate(JFrame parent) {
        super(parent, "Danh mục sửa sách", true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        // Tiêu đề
        JLabel titleLabel = new JLabel("CHỈNH SỬA THÔNG TIN SÁCH", SwingConstants.CENTER);
        Font titleFont = new Font("Arial", Font.BOLD, 25);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(72, 118, 255));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(titlePanel, BorderLayout.NORTH);

        // Panel chứa nội dung chính
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Panel chứa các label và text field - bố cục ngang
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        // Các Label
        JLabel lbMasach = new JLabel("Mã sách: ");
        lbMasach.setFont(labelFont);
        JLabel lbTensach = new JLabel("Tên sách:");
        lbTensach.setFont(labelFont);
        JLabel lbManxb = new JLabel("Nhà xuất bản:");
        lbManxb.setFont(labelFont);
        JLabel lbMatacgia = new JLabel("Tác giả:");
        lbMatacgia.setFont(labelFont);
        JLabel lbMatheloai = new JLabel("Thể loại:");
        lbMatheloai.setFont(labelFont);
        JLabel lbSoluong = new JLabel("Số lượng:");
        lbSoluong.setFont(labelFont);
        JLabel lbNamxuatban = new JLabel("Năm xuất bản:");
        lbNamxuatban.setFont(labelFont);
        JLabel lbDongia = new JLabel("Đơn giá:");
        lbDongia.setFont(labelFont);

        // Các TextField và ComboBox
        txfMasach = createTextField(fieldFont);
        txfMasach.setEditable(false); // Không cho chỉnh sửa mã sách
        txfTensach = createTextField(fieldFont);
        txfSoluong = createTextField(fieldFont);
        txfSoluong.setEditable(false);// Không cho chỉnh sửa số l sách 
        txfNamxuatban = createTextField(fieldFont);
        txfDongia = createTextField(fieldFont);

        cbb_NXB = createComboBox(fieldFont, nxbBUS.getAllNameNXB().toArray(new String[0]));
        cbb_TG = createComboBox(fieldFont, tgBUS.getAllNameTG().toArray(new String[0]));
        cbb_TL = createComboBox(fieldFont, tlBUS.getAllNameTL().toArray(new String[0]));

        // Hàng 1: Mã sách, Tên sách, Mã NXB, Mã tác giả
        JPanel row1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        row1Panel.add(createFieldPanel(lbMasach, txfMasach));
        row1Panel.add(createFieldPanel(lbTensach, txfTensach));
        row1Panel.add(createCBBPanel(lbManxb, cbb_NXB));
        row1Panel.add(createCBBPanel(lbMatacgia, cbb_TG));

        // Hàng 2: Mã thể loại, Số lượng, Năm xuất bản, Đơn giá
        JPanel row2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        row2Panel.add(createCBBPanel(lbMatheloai, cbb_TL));
        row2Panel.add(createFieldPanel(lbSoluong, txfSoluong));
        row2Panel.add(createFieldPanel(lbNamxuatban, txfNamxuatban));
        row2Panel.add(createFieldPanel(lbDongia, txfDongia));

        formPanel.add(row1Panel);
        formPanel.add(Box.createVerticalStrut(10)); // Khoảng cách giữa hai hàng
        formPanel.add(row2Panel);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = createButton("Lưu thông tin", new Color(76, 175, 80));
        JButton deleteButton = createButton("Đóng", new Color(67, 110, 238));

        buttonPanel.add(addButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(deleteButton);

        ActionListener action = new BookDialogUpdate_Controller(this);
        addButton.addActionListener(action);
        deleteButton.addActionListener(action);

        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(parent);
    }

    private JComboBox<String> createComboBox(Font font, String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(font);
        comboBox.setPreferredSize(new Dimension(200, 30)); // Giảm chiều rộng để ngắn hơn
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        comboBox.setBackground(Color.WHITE);
        return comboBox;
    }

    private JTextField createTextField(Font font) {
        JTextField textField = new JTextField();
        textField.setFont(font);
        textField.setPreferredSize(new Dimension(200, 30)); // Đồng bộ với ComboBox
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return textField;
    }

    private JPanel createCBBPanel(JLabel label, JComboBox<String> cbb) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        label.setPreferredSize(new Dimension(100, 20)); // Căn chỉnh label
        panel.add(label, BorderLayout.NORTH);
        panel.add(cbb, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createFieldPanel(JLabel label, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        label.setPreferredSize(new Dimension(100, 20)); // Căn chỉnh label
        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
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

    public void ShowInfo(String maSach, String TenSach, String NXB, String TG, String TL, String soluong, String NamXB, String dongia) {
        txfMasach.setText(maSach);
        txfTensach.setText(TenSach);
        txfSoluong.setText(soluong);
        txfNamxuatban.setText(NamXB);
        txfDongia.setText(NumberFormatter.formatReverse(dongia));
        cbb_NXB.setSelectedItem(NXB);
        cbb_TG.setSelectedItem(TG);
        cbb_TL.setSelectedItem(TL);
    }

    public JTextField getTxfMasach() {
        return txfMasach;
    }

    public JTextField getTxfTensach() {
        return txfTensach;
    }

    public JTextField getTxfSoluong() {
        return txfSoluong;
    }

    public JTextField getTxfNamxuatban() {
        return txfNamxuatban;
    }

    public JTextField getTxfDongia() {
        return txfDongia;
    }

    public JComboBox<String> getCbb_NXB() {
        return cbb_NXB;
    }

    public JComboBox<String> getCbb_TG() {
        return cbb_TG;
    }

    public JComboBox<String> getCbb_TL() {
        return cbb_TL;
    }
}