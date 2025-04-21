/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.BookDialog;
import BUS.NhaXuatBanBUS;
import BUS.TacGiaBUS;
import BUS.TheLoaiBUS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 *
 * @author DELL
 * 
 */
public class BookDialogAdd extends JDialog {
    private JTextField txfMasach, txfTensach, txfSoluong, txfNamxuatban, txfDongia;
    private JComboBox<String> cbb_NXB, cbb_TG, cbb_TL;
    private NhaXuatBanBUS nxbBUS = new NhaXuatBanBUS();
    private TacGiaBUS tgBUS = new TacGiaBUS();
    private TheLoaiBUS tlBUS = new TheLoaiBUS();
    
    public BookDialogAdd(JFrame parent) {
        super(parent, "Danh mục thêm sách", true);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1200, 700));
        panel.setLayout(new BorderLayout(10, 10));

        // Tiêu đề
        JLabel titleLabel = new JLabel("THÊM SÁCH", SwingConstants.CENTER);
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

        // Panel chứa các label và text field
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        // Các Label và TextField
        JLabel lbMasach = new JLabel("Mã sách:"); lbMasach.setFont(labelFont);
        JLabel lbTensach = new JLabel("Tên sách:"); lbTensach.setFont(labelFont);
        JLabel lbManxb = new JLabel("Nhà xuất bản:"); lbManxb.setFont(labelFont);
        JLabel lbMatacgia = new JLabel("Tác giả:"); lbMatacgia.setFont(labelFont);
        JLabel lbMatheloai = new JLabel("Thể loại:"); lbMatheloai.setFont(labelFont);
        JLabel lbSoluong = new JLabel("Số lượng:"); lbSoluong.setFont(labelFont);
        JLabel lbNamxuatban = new JLabel("Năm xuất bản:"); lbNamxuatban.setFont(labelFont);
        JLabel lbDongia = new JLabel("Đơn giá:"); lbDongia.setFont(labelFont);

        txfMasach = createTextField(fieldFont);
        txfTensach = createTextField(fieldFont);
        txfSoluong = createTextField(fieldFont);
        txfNamxuatban = createTextField(fieldFont);
        txfDongia = createTextField(fieldFont);
        
        // Tạo các Combox nxb
        cbb_NXB = createComboBox(fieldFont, nxbBUS.getAllNameNXB().toArray(new String[0]));
        cbb_TG = createComboBox(fieldFont, tgBUS.getAllNameTG().toArray(new String[0]));
        cbb_TL = createComboBox(fieldFont, tlBUS.getAllNameTL().toArray(new String[0]));
        

        // Cột 1 - Labels
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        formPanel.add(lbMasach, gbc);
        gbc.gridy++; formPanel.add(lbTensach, gbc);
        gbc.gridy++; formPanel.add(lbManxb, gbc);
        gbc.gridy++; formPanel.add(lbMatacgia, gbc);
        gbc.gridy++; formPanel.add(lbMatheloai, gbc);
        gbc.gridy++; formPanel.add(lbSoluong, gbc);
        gbc.gridy++; formPanel.add(lbNamxuatban, gbc);
        gbc.gridy++; formPanel.add(lbDongia, gbc);

        // Cột 2 - TextFields
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txfMasach, gbc);
        gbc.gridy++; formPanel.add(txfTensach, gbc);
        gbc.gridy++; formPanel.add(cbb_NXB, gbc);
        gbc.gridy++; formPanel.add(cbb_TG, gbc);
        gbc.gridy++; formPanel.add(cbb_TL, gbc);
        gbc.gridy++; formPanel.add(txfSoluong, gbc);    
        gbc.gridy++; formPanel.add(txfNamxuatban, gbc);
        gbc.gridy++; formPanel.add(txfDongia, gbc);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = createButton("Thêm dữ liệu", new Color(76, 175, 80));
        JButton deleteButton = createButton("Xóa", new Color(244, 67, 54));

        buttonPanel.add(addButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
        // Thêm sự kiện cho 2 nút
        ActionListener action = new BookDiaLogAdd_Controller(this);
        addButton.addActionListener(action);
        deleteButton.addActionListener(action);

        
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }
    
    private JTextField createTextField(Font font) {
        JTextField textField = new JTextField(20);
        textField.setFont(font);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return textField;
    }
    
    private JComboBox<String> createComboBox(Font font, String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(font);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        comboBox.setBackground(Color.WHITE);
        return comboBox;
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
    
    public JTextField getTxfMasach() { return txfMasach; }
    public JTextField getTxfTensach() { return txfTensach; }
    public JTextField getTxfSoluong() { return txfSoluong; }
    public JTextField getTxfNamxuatban() { return txfNamxuatban; }
    public JTextField getTxfDongia() { return txfDongia; }
    public JComboBox<String> getCbb_TG() { return cbb_TG; }
    public JComboBox<String> getCbb_TL() { return cbb_TL; }
    public JComboBox<String> getCbb_NXB() { return cbb_NXB; }

    public void ClearTextField() {
        txfMasach.setText("");
        txfTensach.setText("");
        cbb_NXB.setSelectedIndex(0);
        cbb_TG.setSelectedIndex(0);
        cbb_TL.setSelectedIndex(0);
        txfSoluong.setText("");
        txfNamxuatban.setText("");
        txfDongia.setText(""); 
    }
}