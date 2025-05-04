package GUI.Dialog.KhachHangDialog;

import DTO.KhachHangDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class KhachHangDialogDetail extends JDialog{
    private JTextField txfMa;
    private JTextField txfHo;
    private JTextField txfTen;
    private JTextField txfEmail;
    private JTextField txfNgaySinh;
    private JTextField txfSdt;
    
    public KhachHangDialogDetail(JFrame parent,KhachHangDTO a) {
        super(parent, "Danh mục xem chi tiết", true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        // Tiêu đề
        JLabel titleLabel = new JLabel("THÔNG TIN CHI TIẾT", SwingConstants.CENTER);
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
        JLabel lbMa=new JLabel("Mã: ");                      lbMa.setFont(labelFont);
        JLabel lbHo = new JLabel("ho:");                     lbHo.setFont(labelFont);
        JLabel lbTen= new JLabel("ten:");                    lbTen.setFont(labelFont);
        JLabel lbEmail = new JLabel("email:");               lbEmail.setFont(labelFont);
        JLabel lbNgaySinh = new JLabel("ngày sinh:");        lbNgaySinh.setFont(labelFont);
        JLabel lbSdt = new JLabel("số điện thoại:");         lbSdt.setFont(labelFont);

        txfMa= GuiSupport.createTextField(fieldFont);
        txfMa.setEditable(false); 
        txfHo = GuiSupport.createTextField(fieldFont);
        txfHo.setEditable(false);
        txfTen = GuiSupport.createTextField(fieldFont);
        txfTen.setEditable(false);
        txfEmail = GuiSupport.createTextField(fieldFont);
        txfEmail.setEditable(false);
        txfNgaySinh = GuiSupport.createTextField(fieldFont);
        txfNgaySinh.setEditable(false);
        txfSdt = GuiSupport.createTextField(fieldFont);
        txfSdt.setEditable(false);
     
        // Hàng 1 chứa 4 trường đầu
        JPanel row1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        row1Panel.add(GuiSupport.createFieldPanel(lbMa, txfMa));
        row1Panel.add(GuiSupport.createFieldPanel(lbHo, txfHo));
        row1Panel.add(GuiSupport.createFieldPanel(lbTen, txfTen));
        row1Panel.add(GuiSupport.createFieldPanel(lbEmail, txfEmail));
        
        // Hàng 2 chứa 3 trường còn lại
        JPanel row2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        row2Panel.add(GuiSupport.createFieldPanel(lbNgaySinh, txfNgaySinh));
        row2Panel.add(GuiSupport.createFieldPanel(lbSdt, txfSdt));      
        
        formPanel.add(row1Panel);
        formPanel.add(row2Panel);
        
        contentPanel.add(formPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
        
        // Panel chứa các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton deleteButton = GuiSupport.createButton("Đóng", new Color(72, 118, 255));
        buttonPanel.add(deleteButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        // SU KIEN       
        deleteButton.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
        
        // set thong tin
        txfMa.setText(String.valueOf(a.getMakh()));
        txfHo.setText(a.getHokh());
        txfTen.setText(a.getTenkh());
        txfEmail.setText(a.getemail());
        txfSdt.setText(a.getSdt());
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txfNgaySinh.setText(sdf.format(a.getNgaysinh()));
        
        pack(); 
        setLocationRelativeTo(parent); 
        this.setVisible(true);
    }
    
}
