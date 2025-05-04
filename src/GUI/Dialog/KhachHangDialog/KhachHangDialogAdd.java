
package GUI.Dialog.KhachHangDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class KhachHangDialogAdd extends JDialog {
    private JTextField txfMa,txfHo,txfTen,txfEmail,txfNgaySinh,txfSdt;
    public KhachHangDialogAdd(JFrame parent) {
        super(parent, "Danh mục thêm khách hàng", true);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1200, 700));
        panel.setLayout(new BorderLayout(10, 10));

        // Tiêu đề
        JLabel titleLabel = new JLabel("THÊM KHÁCH HÀNG", SwingConstants.CENTER);
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
        
        JLabel lbHo = new JLabel("Họ :");            lbHo.setFont(labelFont);
        JLabel lbTen = new JLabel("Tên :");     lbTen.setFont(labelFont);
        JLabel lbEmail = new JLabel("Email :");  lbEmail.setFont(labelFont);
        JLabel lbNgaySinh = new JLabel("Ngày sinh :");        lbNgaySinh.setFont(labelFont);
        JLabel lbSdt = new JLabel("Số điện thoại:"); lbSdt.setFont(labelFont);
     

       
        txfHo = createTextField(fieldFont);
        txfTen = createTextField(fieldFont);
        txfEmail = createTextField(fieldFont);
        txfNgaySinh = createTextField(fieldFont);
        txfSdt = createTextField(fieldFont);
        

        // Cột 1 - Labels
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        formPanel.add(lbHo, gbc);
        
        gbc.gridy++; formPanel.add(lbTen, gbc);
        gbc.gridy++; formPanel.add(lbEmail, gbc);
        gbc.gridy++; formPanel.add(lbNgaySinh, gbc);
        gbc.gridy++; formPanel.add(lbSdt, gbc);
     
        // Cột 2 - TextFields
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txfHo, gbc);
     
        gbc.gridy++; formPanel.add(txfTen, gbc);
        gbc.gridy++; formPanel.add(txfEmail, gbc);
        gbc.gridy++; formPanel.add(txfNgaySinh, gbc);
        gbc.gridy++; formPanel.add(txfSdt, gbc);
  

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
        ActionListener action= new KhachHangDialogAdd_Controller(this);
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

    public JTextField getTxfMa() {
        return txfMa;
    }

    public JTextField getTxfHo() {
        return txfHo;
    }

    public JTextField getTxfTen() {
        return txfTen;
    }

    public JTextField getTxfEmail() {
        return txfEmail;
    }

    public JTextField getTxfNgaySinh() {
        return txfNgaySinh;
    }

    public JTextField getTxfSdt() {
        return txfSdt;
    }

    public void setTxfMa(JTextField txfMa) {
        this.txfMa = txfMa;
    }

    public void setTxfHo(JTextField txfHo) {
        this.txfHo = txfHo;
    }

    public void setTxfTen(JTextField txfTen) {
        this.txfTen = txfTen;
    }

    public void setTxfEmail(JTextField txfEmail) {
        this.txfEmail = txfEmail;
    }

    public void setTxfNgaySinh(JTextField txfNgaySinh) {
        this.txfNgaySinh = txfNgaySinh;
    }

    public void setTxfSdt(JTextField txfSdt) {
        this.txfSdt = txfSdt;
    }
    
     public void ClearTextField(){
        txfMa.setText("");
        txfHo.setText("");
        txfTen.setText("");
        txfEmail.setText("");
        txfNgaySinh.setText("");
        txfSdt.setText("");
    }

   
   


}
