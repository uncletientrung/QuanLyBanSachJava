/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.NhanVienDialog;
import GUI.Dialog.NhanVienDialog.NhanVienDialogAdd_Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 *
 * @author Minnie
 */
public class NhanVienDialogAdd extends JDialog{
    private JTextField txfHo, txfTen, txfGioitinh, txfSdt, txfNgaysinh, txfTrangthai;
    public NhanVienDialogAdd(JFrame parent){
        super(parent, "Danh mục thêm nhân viên", true);
        
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1200, 700));
        panel.setLayout(new BorderLayout(10,10));
        
        //Tittle
        JLabel titleLabel = new JLabel("THÊM NHÂN VIÊN", SwingConstants.CENTER);
        Font tittleFont = new Font("Arial", Font.BOLD, 25); 
        titleLabel.setFont(tittleFont);
        titleLabel.setForeground(Color.WHITE);
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(72, 118, 225));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(titlePanel, BorderLayout.NORTH);
        
        //Panel chứa nội dung chính
        JPanel contentPanel = new JPanel(new BorderLayout(10,10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
        
        //Panel chứa các label và textField
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor = GridBagConstraints.WEST;
        
        //Set font
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        
        // Lable với TextField
//        JLabel lbMa = new JLabel("Mã nhân viên:");  lbMa.setFont(labelFont);
        JLabel lbHo = new JLabel("Họ:");  lbHo.setFont(labelFont);
        JLabel lbTen = new JLabel("Tên:");  lbTen.setFont(labelFont);
        JLabel lbGioitinh = new JLabel("Giới tính:");  lbGioitinh.setFont(labelFont);
        JLabel lbSdt = new JLabel("Số điện thoại:");  lbSdt.setFont(labelFont);
        JLabel lbNgaysinh = new JLabel("Ngày sinh:");  lbNgaysinh.setFont(labelFont);
        JLabel lbTrangthai = new JLabel("Trạng thái:");  lbTrangthai.setFont(labelFont);
        
//        JTextField txfMa = createTextField(fieldFont);
        txfHo = createTextField(fieldFont);
        txfTen = createTextField(fieldFont);
        txfGioitinh = createTextField(fieldFont);
        txfSdt = createTextField(fieldFont);
        txfNgaysinh = createTextField(fieldFont);
        txfTrangthai = createTextField(fieldFont);
        
        //Cột 1 - Labels
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
//        formPanel.add(lbMa, gbc);
//        gbc.gridy++; 
        formPanel.add(lbHo, gbc);
        gbc.gridy++; formPanel.add(lbTen, gbc);
        gbc.gridy++; formPanel.add(lbGioitinh, gbc);
        gbc.gridy++; formPanel.add(lbSdt, gbc);
        gbc.gridy++; formPanel.add(lbNgaysinh, gbc);
        gbc.gridy++; formPanel.add(lbTrangthai, gbc);
        
        //Cột 2 - TextField
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7; gbc.fill = GridBagConstraints.HORIZONTAL;
//        formPanel.add(txfMa, gbc);
//        gbc.gridy++; 
        formPanel.add(txfHo, gbc);
        gbc.gridy++; formPanel.add(txfTen, gbc);
        gbc.gridy++; formPanel.add(txfGioitinh, gbc);
        gbc.gridy++; formPanel.add(txfSdt, gbc);
        gbc.gridy++; formPanel.add(txfNgaysinh, gbc);
        gbc.gridy++; formPanel.add(txfTrangthai, gbc);
        
        contentPanel.add(formPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
        
        //Panel chứa các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton addButton = createButton("Thêm dữ liệu", new Color(76, 175, 80));
        JButton deleteButton = createButton("Xóa", new Color(244, 67, 54));

        buttonPanel.add(addButton);
        buttonPanel.add(Box.createHorizontalStrut(20)); // Khoảng cách giữa 2 nút
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
        // Thêm sự kiện cho 2 nút
        ActionListener action= new NhanVienDialogAdd_Controller(this);
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
    
//    public TextField getTxfMa(){
//        return txfMa;
//    }
//    
//    public void setTxfMa(TextField txfMa){
//        this.txfMa = txfMa;
//    }
    
    public JTextField getTxfHo(){
        return txfHo;
    }
    
    public void setTxfHo(JTextField txfHo){
        this.txfHo = txfHo;
    }
    
    public JTextField getTxfTen(){
        return txfTen;
    }

    public JTextField getTxfGioitinh() {
        return txfGioitinh;
    }

    public JTextField getTxfSdt() {
        return txfSdt;
    }

    public JTextField getTxfNgaysinh() {
        return txfNgaysinh;
    }

    public JTextField getTxfTrangthai() {
        return txfTrangthai;
    }

    public void setTxfTen(JTextField txfTen) {
        this.txfTen = txfTen;
    }

    public void setTxfGioitinh(JTextField txfGioitinh) {
        this.txfGioitinh = txfGioitinh;
    }

    public void setTxfSdt(JTextField txfSdt) {
        this.txfSdt = txfSdt;
    }

    public void setTxfNgaysinh(JTextField txfNgaysinh) {
        this.txfNgaysinh = txfNgaysinh;
    }

    public void setTxfTrangthai(JTextField txfTrangthai) {
        this.txfTrangthai = txfTrangthai;
    }

    @Override
    public String toString() {
        return "NhanVienDialogAdd{txfHo=" + txfHo + ", txfTen=" + txfTen + ", txfGioitinh=" + txfGioitinh + ", txfSdt=" + txfSdt + ", txfNgaysinh=" + txfNgaysinh + ", txfTrangthai=" + txfTrangthai + '}';
    }
    
    public void ClearTextField(){
//        txfMa.setText("");
        txfHo.setText("");
        txfTen.setText("");
        txfNgaysinh.setText("");
        txfGioitinh.setText("");
        txfSdt.setText("");
        txfTrangthai.setText("");
    }
}
