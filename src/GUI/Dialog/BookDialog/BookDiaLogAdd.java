/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.BookDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 *
 * @author DELL
 * 

 */
public class BookDiaLogAdd extends JDialog {
    private TextField txfTensach,txfManxb,txfMatacgia,txfMatheloai,txfSoluong,txfNamxuatban,txfDongia;
    public BookDiaLogAdd(JFrame parent) {
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

        // Các Label và TextField (giữ nguyên tên biến)
        JLabel lbTensach = new JLabel("Tên sách:");        lbTensach.setFont(labelFont);
        JLabel lbManxb = new JLabel("Mã NXB:");            lbManxb.setFont(labelFont);
        JLabel lbMatacgia = new JLabel("Mã tác giả:");     lbMatacgia.setFont(labelFont);
        JLabel lbMatheloai = new JLabel("Mã thể loại:");  lbMatheloai.setFont(labelFont);
        JLabel lbSoluong = new JLabel("Số lượng:");        lbSoluong.setFont(labelFont);
        JLabel lbNamxuatban = new JLabel("Năm xuất bản:"); lbNamxuatban.setFont(labelFont);
        JLabel lbDongia = new JLabel("Đơn giá:");          lbDongia.setFont(labelFont);

        JTextField txfTensach = createTextField(fieldFont);
        JTextField txfManxb = createTextField(fieldFont);
        JTextField txfMatacgia = createTextField(fieldFont);
        JTextField txfMatheloai = createTextField(fieldFont);
        JTextField txfSoluong = createTextField(fieldFont);
        JTextField txfNamxuatban = createTextField(fieldFont);
        JTextField txfDongia = createTextField(fieldFont);

        // Cột 1 - Labels
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        formPanel.add(lbTensach, gbc);
        gbc.gridy++; formPanel.add(lbManxb, gbc);
        gbc.gridy++; formPanel.add(lbMatacgia, gbc);
        gbc.gridy++; formPanel.add(lbMatheloai, gbc);
        gbc.gridy++; formPanel.add(lbSoluong, gbc);
        gbc.gridy++; formPanel.add(lbNamxuatban, gbc);
        gbc.gridy++; formPanel.add(lbDongia, gbc);

        // Cột 2 - TextFields
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txfTensach, gbc);
        gbc.gridy++; formPanel.add(txfManxb, gbc);
        gbc.gridy++; formPanel.add(txfMatacgia, gbc);
        gbc.gridy++; formPanel.add(txfMatheloai, gbc);
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
        buttonPanel.add(Box.createHorizontalStrut(20)); // Khoảng cách giữa 2 nút
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
        // Thêm sự kiện cho 2 nút
        ActionListener action= new BookDiaLogAdd_Controller(this);
        addButton.addActionListener(action);
        deleteButton.addActionListener(action);

        
        pack(); // Điều chỉnh kích thước tự động dựa trên nội dung
        setLocationRelativeTo(parent); // Hiển thị giữa màn hình
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
                // Vẽ hình tròn làm nền
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
    public TextField getTxfTensach() {
        return txfTensach;
    }

    public void setTxfTensach(TextField txfTensach) {
        this.txfTensach = txfTensach;
    }

    public TextField getTxfManxb() {
        return txfManxb;
    }

    public void setTxfManxb(TextField txfManxb) {
        this.txfManxb = txfManxb;
    }

    public TextField getTxfMatacgia() {
        return txfMatacgia;
    }

    public void setTxfMatacgia(TextField txfMatacgia) {
        this.txfMatacgia = txfMatacgia;
    }

    public TextField getTxfMatheloai() {
        return txfMatheloai;
    }

    public void setTxfMatheloai(TextField txfMatheloai) {
        this.txfMatheloai = txfMatheloai;
    }

    public TextField getTxfSoluong() {
        return txfSoluong;
    }

    public void setTxfSoluong(TextField txfSoluong) {
        this.txfSoluong = txfSoluong;
    }

    public TextField getTxfNamxuatban() {
        return txfNamxuatban;
    }

    public void setTxfNamxuatban(TextField txfNamxuatban) {
        this.txfNamxuatban = txfNamxuatban;
    }

    public TextField getTxfDongia() {
        return txfDongia;
    }

    public void setTxfDongia(TextField txfDongia) {
        this.txfDongia = txfDongia;
    }
    
    public void ClearTextField(){
        txfTensach.setText("");
        txfManxb.setText("");
        txfMatacgia.setText("");
        txfMatheloai.setText("");
        txfSoluong.setText("");
        txfNamxuatban.setText("");
        txfDongia.setText(""); 
    }


}
