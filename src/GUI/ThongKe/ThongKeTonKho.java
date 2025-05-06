
package GUI.ThongKe;

import BUS.ThongKeBUS;
import DTO.ThongKe.ThongKeTonKhoDTO;
import GUI.Dialog.BookDialog.BookDialogDetail_Controller;
import GUI.ThongKe.Support.ButtonCustom;
import GUI.ThongKe.Support.InputDate;
import GUI.ThongKe.Support.InputForm;
import GUI.ThongKe.Support.JTableExporter;
import GUI.ThongKe.Support.PanelBorderRadius;
import GUI.ThongKe.Support.TableSorter;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public final class ThongKeTonKho extends JPanel implements ActionListener, KeyListener, PropertyChangeListener {

    PanelBorderRadius nhapxuat_left, nhapxuat_center;
    JTable tblTonKho;
    JScrollPane scrollTblTonKho;
    DefaultTableModel tblModel;
    InputForm tensanpham;
    JButton export, reset;
    ArrayList<ThongKeTonKhoDTO> listSp;
    private Font font=new Font("Arial", Font.BOLD, 14);
   

    public ThongKeTonKho() {
        listSp = new ThongKeBUS().getTonKho();
        initComponent();
        loadDataTalbe(listSp);
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        nhapxuat_left = new PanelBorderRadius();
        nhapxuat_left.setPreferredSize(new Dimension(300, 100));
        nhapxuat_left.setLayout(new BorderLayout());
        nhapxuat_left.setBorder(new EmptyBorder(0, 0, 0, 5));
        nhapxuat_left.setOpaque(false); // Thêm cái này

        JPanel left_content = new JPanel(new GridLayout(4, 1));
        left_content.setPreferredSize(new Dimension(300, 360));
        left_content.setOpaque(false); // Quan trọng
        nhapxuat_left.add(left_content, BorderLayout.NORTH);

        tensanpham = new InputForm("Tìm kiếm sản phẩm");
        tensanpham.getTxtForm().putClientProperty("JTextField.showClearButton", true);
        tensanpham.getTxtForm().addKeyListener(this);
        tensanpham.getLblTitle().setFont(font);

        JPanel btn_layout = new JPanel(new BorderLayout());
        btn_layout.setPreferredSize(new Dimension(30, 36));
        btn_layout.setBorder(new EmptyBorder(20, 10, 0, 10));
        btn_layout.setOpaque(false); // Đừng set white

        JPanel btninner = new JPanel(new GridLayout(1, 2,10,10));
        btninner.setOpaque(false);

        export = createButton("Xuất Excel", new Color(76, 175, 80)); // Sửa lại Jbutton
        reset = createButton("Làm mới", new Color(72, 118, 255));

        export.addActionListener(this);
        reset.addActionListener(this);

        btninner.add(export);
        btninner.add(reset);
        btn_layout.add(btninner, BorderLayout.NORTH);

        left_content.add(tensanpham);
        left_content.add(btn_layout);

        nhapxuat_center = new PanelBorderRadius();
        nhapxuat_center.setOpaque(false);
        BoxLayout boxly = new BoxLayout(nhapxuat_center, BoxLayout.Y_AXIS);
        nhapxuat_center.setLayout(boxly);

        tblTonKho = new JTable();
        scrollTblTonKho = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên sản phẩm", "SL Tồn"};
        tblModel.setColumnIdentifiers(header);
        tblTonKho.setModel(tblModel);
        tblTonKho.setAutoCreateRowSorter(true);
        tblTonKho.setDefaultEditor(Object.class, null);
        scrollTblTonKho.setViewportView(tblTonKho);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblTonKho.setDefaultRenderer(Object.class, centerRenderer);
        tblTonKho.setRowHeight(30);
        tblTonKho.setFocusable(false);
        tblTonKho.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblTonKho.getColumnModel().getColumn(1).setPreferredWidth(10);
        tblTonKho.getColumnModel().getColumn(2).setPreferredWidth(200);

        TableSorter.configureTableColumnSorter(tblTonKho, 0, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tblTonKho, 1, TableSorter.STRING_COMPARATOR);
        TableSorter.configureTableColumnSorter(tblTonKho, 2, TableSorter.STRING_COMPARATOR);
        TableSorter.configureTableColumnSorter(tblTonKho, 3, TableSorter.INTEGER_COMPARATOR);
       

        nhapxuat_center.add(scrollTblTonKho);

        tblTonKho.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTonKhoClicked(evt);
            }
        });

        this.add(nhapxuat_left, BorderLayout.WEST);
        this.add(nhapxuat_center, BorderLayout.CENTER);
    }

    private void tblTonKhoClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            if (tblTonKho.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm");
            } else {
                int i =  (int) tblTonKho.getValueAt(tblTonKho.getSelectedRow(), 0);
                new BookDialogDetail(new JFrame(), listSp.get(i-1));
            }
        }
    }

    public void resetForm() throws ParseException {
        tensanpham.setText("");
    }
    
    private void loadDataTalbe(ArrayList<ThongKeTonKhoDTO> list) {
        tblModel.setRowCount(0);   
        for (ThongKeTonKhoDTO i : list) {    
            tblModel.addRow(new Object[]{
                i.getStt(), i.getMasach(),i.getTensach(), i.getSoluongton()
            });
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == export) {
            try {
                JTableExporter.exportJTableToExcel(tblTonKho);
            } catch (IOException ex) {
                Logger.getLogger(ThongKeTonKho.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == reset) {
            try {
                resetForm();
                loadDataTalbe(new ThongKeBUS().getTonKho());
            } catch (ParseException ex) {
                Logger.getLogger(ThongKeTonKho.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(tensanpham.getDocument().isEmpty()){
            loadDataTalbe(listSp);
        }
        else{
            ArrayList<ThongKeTonKhoDTO> temp=new ArrayList<>();
            for(ThongKeTonKhoDTO i : listSp){
                if(i.getMasach().toUpperCase().contains(tensanpham.getDocument().toUpperCase()) ||
                   i.getTensach().toUpperCase().contains(tensanpham.getDocument().toUpperCase())){
                    temp.add(i);
                }
            }
            loadDataTalbe(temp);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
       
    }
    
    
    
public class BookDialogDetail extends JDialog{
    private JTextField txfMasach,txfTensach,txfManxb,txfMatacgia,txfMatheloai,txfSoluong,txfNamxuatban,txfDongia,stt;
    
    public BookDialogDetail(JFrame parent,ThongKeTonKhoDTO a) {
        super(parent, "Danh mục  xem chi tiết", true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        // Tiêu đề
        JLabel titleLabel = new JLabel("THÔNG TIN CHI TIẾT SÁCH", SwingConstants.CENTER);
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
        JLabel lbMasach=new JLabel("Mã sách: ");            lbMasach.setFont(labelFont);
        JLabel lbTensach = new JLabel("Tên sách:");        lbTensach.setFont(labelFont);
        JLabel lbManxb = new JLabel("Tên NXB:");            lbManxb.setFont(labelFont);
        JLabel lbMatacgia = new JLabel("Tên tác giả:");     lbMatacgia.setFont(labelFont);
        JLabel lbMatheloai = new JLabel("Tên thể loại:");  lbMatheloai.setFont(labelFont);
        JLabel lbSoluong = new JLabel("Số lượng:");        lbSoluong.setFont(labelFont);
        JLabel lbNamxuatban = new JLabel("Năm xuất bản:"); lbNamxuatban.setFont(labelFont);
        JLabel lbDongia = new JLabel("Đơn giá:");          lbDongia.setFont(labelFont);
       

        txfMasach= createTextField(fieldFont);
        txfMasach.setEditable(false); // Không cho chỉnh sửa mã sách
        txfTensach = createTextField(fieldFont);
        txfTensach.setEditable(false);
        txfManxb = createTextField(fieldFont);
        txfManxb.setEditable(false);
        txfMatacgia = createTextField(fieldFont);
        txfMatacgia.setEditable(false);
        txfMatheloai = createTextField(fieldFont);
        txfMatheloai.setEditable(false);
        txfSoluong = createTextField(fieldFont);
        txfSoluong.setEditable(false);
        txfNamxuatban = createTextField(fieldFont);
        txfNamxuatban.setEditable(false);
        txfDongia = createTextField(fieldFont);
        txfDongia.setEditable(false);
        stt = createTextField(fieldFont);
       stt.setEditable(false);
        
        // Hàng 1 chứa 4 trường đầu
        JPanel row1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        row1Panel.add(createFieldPanel(lbMasach, txfMasach));
        row1Panel.add(createFieldPanel(lbTensach, txfTensach));
        row1Panel.add(createFieldPanel(lbManxb, txfManxb));
        row1Panel.add(createFieldPanel(lbMatacgia, txfMatacgia));
        
        // Hàng 2 chứa 3 trường còn lại
        JPanel row2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        row2Panel.add(createFieldPanel(lbMatheloai, txfMatheloai));
        row2Panel.add(createFieldPanel(lbSoluong, txfSoluong));
        row2Panel.add(createFieldPanel(lbNamxuatban, txfNamxuatban));
        row2Panel.add(createFieldPanel(lbDongia, txfDongia));
        
        
        formPanel.add(row1Panel);
        formPanel.add(row2Panel);
        
        contentPanel.add(formPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
        
        txfMasach.setText(a.getMasach());
        txfTensach.setText(a.getTensach());
        txfManxb.setText(a.getManxb());
        txfMatacgia.setText(a.getMatacgia());
        txfMatheloai.setText(a.getMatheloai());
        txfSoluong.setText(String.valueOf(a.getSoluongton()));
        txfNamxuatban.setText(a.getNamxuatban());
        txfDongia.setText(String.valueOf(a.getDongia()));
         
     
        // Thêm ActionListener vào nút
       
  
        
        pack(); // Điều chỉnh kích thước tự động dựa trên nội dung
        setLocationRelativeTo(parent); // Hiển thị giữa màn hình
        this.setVisible(true);       
    }
    
    private JPanel createFieldPanel(JLabel label, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }
    
    private JTextField createTextField(Font font) {
        JTextField textField = new JTextField(20);
        textField.setFont(font);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return textField;
    }
    
 
    }

        public JButton createButton(String text, Color bgColor) {
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
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            return button;
    }
        public void refreshTable(){
            tblModel.setRowCount(0);
            ThongKeBUS tkBUS=new ThongKeBUS();
            listSp=tkBUS.getTonKho();
            for(ThongKeTonKhoDTO s: listSp){
                tblModel.addRow(new Object[]{s.getStt(),s.getMasach(),s.getTensach(),s.getSoluongton()});
            }
        }
        
}
