package GUI.Dialog.KhachHangDialog;

import BUS.KhachHangBUS;
import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class KhachHangDialogAdd extends JDialog{
    private JTextField txfMa;
    private JTextField txfHo;
    private JTextField txfTen;
    private JTextField txfEmail;
    private JTextField txfSdt;
    private JDateChooser ngaysinh;
    
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
        
        JLabel lbHo = new JLabel("Họ :");                     lbHo.setFont(labelFont);
        JLabel lbTen = new JLabel("Tên :");                   lbTen.setFont(labelFont);
        JLabel lbEmail = new JLabel("Email :");               lbEmail.setFont(labelFont);
        JLabel lbNgaySinh = new JLabel("Ngày sinh :");        lbNgaySinh.setFont(labelFont);
        JLabel lbSdt = new JLabel("Số điện thoại:");          lbSdt.setFont(labelFont);
 
        txfHo = GuiSupport.createTextField(fieldFont);
        txfTen = GuiSupport.createTextField(fieldFont);
        txfEmail = GuiSupport.createTextField(fieldFont);
        txfSdt = GuiSupport.createTextField(fieldFont);
        
        ngaysinh = new JDateChooser(new Date());
        ngaysinh.setPreferredSize(new Dimension(100, 35));       

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
        gbc.gridy++; formPanel.add(ngaysinh, gbc);
        gbc.gridy++; formPanel.add(txfSdt, gbc);
  
        contentPanel.add(formPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = GuiSupport.createButton("Thêm dữ liệu", new Color(76, 175, 80));
        JButton deleteButton = GuiSupport.createButton("Xóa", new Color(244, 67, 54));

        buttonPanel.add(addButton);
        buttonPanel.add(Box.createHorizontalStrut(20)); // Khoảng cách giữa 2 nút
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
        
        // SU KIEN ....................................................
        
        addButton.addActionListener((ActionEvent e) -> {
            add();
        });
        
        deleteButton.addActionListener((ActionEvent e) -> {
            ClearTextField();
        });
        
        
        
        //.................................................................
        
        pack(); // Điều chỉnh kích thước tự động dựa trên nội dung
        setLocationRelativeTo(parent); // Hiển thị giữa màn hình
        setVisible(true);
    }
    
    public void ClearTextField(){
        txfMa.setText("");
        txfHo.setText("");
        txfTen.setText("");
        txfEmail.setText("");
        ngaysinh.setDate(new Date());
        txfSdt.setText("");
    }
    
    public void add(){
        if ( txfHo.getText().isEmpty()||txfTen.getText().isEmpty()||txfEmail.getText().isEmpty()||txfSdt.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng đầy đủ thông tin ", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        else if (!txfEmail.getText().endsWith("@gmail.com")){
            JOptionPane.showMessageDialog(null, "Lỗi định dạng email\nĐịnh dạng : Phải bao gồm '@gmail.com' ", "Error", JOptionPane.ERROR_MESSAGE);
        }         
        else if (!txfSdt.getText().startsWith("0")){
            JOptionPane.showMessageDialog(null, "Lỗi định dạng số điện thoại\nĐịnh dạng : bắt đầu bằng số 0", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        else if(ngaysinh.getDate().getTime()>new Date().getTime()){
            JOptionPane.showMessageDialog(null, "Bro ???", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if("ma".isEmpty()){
            
        }
        else if("gmail".isEmpty()){
            
        }
        else if("sdt".isEmpty()){
            
        }
        else if(new KhachHangBUS().add(new KhachHangDTO(KhachHangBUS.getAutoIncrement(),txfHo.getText(),txfTen.getText(),txfEmail.getText(),ngaysinh.getDate(),txfSdt.getText()))){
            JOptionPane.showMessageDialog(null, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Thêm thất bại", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}


// JOptionPane.showMessageDialog(null, "Vui lòng đầy đủ thông tin ", "Error", JOptionPane.ERROR_MESSAGE);