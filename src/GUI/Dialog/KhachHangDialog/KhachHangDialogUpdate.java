package GUI.Dialog.KhachHangDialog;

import BUS.KhachHangBUS;
import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class KhachHangDialogUpdate extends JDialog{
    private JTextField txfMa;
    private JTextField txfHo;
    private JTextField txfTen;
    private JTextField txfEmail;
    private JTextField txfSdt;    
    private JDateChooser ngaysinh;
    
    public KhachHangDialogUpdate(JFrame parent,KhachHangDTO a) {
        super(parent, "Danh mục sửa khách hàng", true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        // Tiêu đề
        JLabel titleLabel = new JLabel("CHỈNH SỬA THÔNG TIN KHÁCH HÀNG", SwingConstants.CENTER);
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
        
        // Các Label và TextField
        JLabel lbMa=new JLabel("Mã:");                  lbMa.setFont(labelFont);
        JLabel lbHo = new JLabel("Họ:");                lbHo.setFont(labelFont);
        JLabel lbTen = new JLabel("Tên:");              lbTen.setFont(labelFont);
        JLabel lbEmail = new JLabel("Email:");          lbEmail.setFont(labelFont);
        JLabel lbNgaySinh = new JLabel("Ngày sinh:");   lbNgaySinh.setFont(labelFont);
        JLabel lbSdt = new JLabel("Số điện thoại:");    lbSdt.setFont(labelFont);
  
        txfMa= GuiSupport.createTextField(fieldFont);
        txfMa.setEditable(false); // Không cho chỉnh sửa mã sách
        txfHo = GuiSupport.createTextField(fieldFont);
        txfTen = GuiSupport.createTextField(fieldFont);
        txfEmail = GuiSupport.createTextField(fieldFont);
        txfSdt = GuiSupport.createTextField(fieldFont);
        
        ngaysinh = new JDateChooser();
        ngaysinh.setPreferredSize(new Dimension(250, 35));
        
        // Hàng 1 chứa 4 trường đầu
        JPanel row1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        row1Panel.add(GuiSupport.createFieldPanel(lbMa, txfMa));
        row1Panel.add(GuiSupport.createFieldPanel(lbHo, txfHo));
        row1Panel.add(GuiSupport.createFieldPanel(lbTen, txfTen));
        row1Panel.add(GuiSupport.createFieldPanel(lbEmail, txfEmail));
        
        // Hàng 2 chứa 3 trường còn lại
        JPanel row2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        row2Panel.add(GuiSupport.createFieldPanel2(lbNgaySinh, ngaysinh));
        row2Panel.add(GuiSupport.createFieldPanel(lbSdt, txfSdt));
        
        
        formPanel.add(row1Panel);
        formPanel.add(row2Panel);
        
        contentPanel.add(formPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
        
        // Panel chứa các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton addButton = GuiSupport.createButton("Lưu thông tin", new Color(76, 175, 80));
        JButton deleteButton = GuiSupport.createButton("Đóng", new Color(67 ,110 ,238));
        
        buttonPanel.add(addButton);
        buttonPanel.add(Box.createHorizontalStrut(20)); // Khoảng cách giữa 2 nút
        buttonPanel.add(deleteButton);
        
        // Thêm ActionListener vào 2 Button
        
        addButton.addActionListener((ActionEvent e) -> {
            add();
        });
        deleteButton.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
        
        // set thong tin
        txfMa.setText(String.valueOf(a.getMakh()));
        txfHo.setText(a.getHokh());
        txfTen.setText(a.getTenkh());
        txfEmail.setText(a.getemail());
        txfSdt.setText(a.getSdt());
        ngaysinh.setDate(a.getNgaysinh());
    
        add(buttonPanel, BorderLayout.SOUTH);
        pack(); 
        setLocationRelativeTo(parent); 
        this.setVisible(true);
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
        else if(new KhachHangBUS().updateKhachHang_DB(new KhachHangDTO(Integer.parseInt(txfMa.getText()),txfHo.getText(),txfTen.getText(),txfEmail.getText(),ngaysinh.getDate(),txfSdt.getText()))){
            JOptionPane.showMessageDialog(null, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Sửa thất bại", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
