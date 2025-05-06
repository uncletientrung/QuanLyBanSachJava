
package GUI.ThongKe;


import BUS.ThongKeBUS;
import DTO.ThongKe.ThongKeKhachHangDTO;
import DTO.ThongKe.ThongKeNhaCungCapDTO;
import GUI.ThongKe.Support.ButtonCustom;
import GUI.ThongKe.Support.Formater;
import GUI.ThongKe.Support.InputDate;
import GUI.ThongKe.Support.InputForm;
import GUI.ThongKe.Support.JTableExporter;
import GUI.ThongKe.Support.PanelBorderRadius;
import GUI.ThongKe.Support.TableSorter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author andin
 */
public class ThongKeNhaCungCap extends JPanel implements ActionListener, KeyListener, PropertyChangeListener {

    PanelBorderRadius nhapxuat_left, nhapxuat_center;
    JTable tblKH;
    JScrollPane scrollTblTonKho;
    DefaultTableModel tblModel;
    InputForm tenkhachhang;
    InputDate start_date, end_date;
    JButton export, reset;
    ArrayList<ThongKeNhaCungCapDTO> list;
    private Font font=new Font("Arial", Font.BOLD, 14);

    public ThongKeNhaCungCap() {
        
        list = new ThongKeBUS().getThongKeNhaCungCap(LocalDate.of(2000,1,1),  LocalDate.now());
        initComponent();
        loadDataTable(list);
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        nhapxuat_left = new PanelBorderRadius();
        nhapxuat_left.setPreferredSize(new Dimension(300, 100));
        nhapxuat_left.setLayout(new BorderLayout());
        nhapxuat_left.setBorder(new EmptyBorder(0, 0, 0, 5));
        JPanel left_content = new JPanel(new GridLayout(4, 1));
        left_content.setPreferredSize(new Dimension(300, 360));
        nhapxuat_left.add(left_content, BorderLayout.NORTH);

        tenkhachhang = new InputForm("Tìm kiếm nhà cung cấp");
        tenkhachhang.getTxtForm().putClientProperty("JTextField.showClearButton", true);
        tenkhachhang.getTxtForm().addKeyListener(this);
        tenkhachhang.getLblTitle().setFont(font); // Thêm font
        
        
        start_date = new InputDate("Từ ngày");
        end_date = new InputDate("Đến ngày");

        start_date.getDateChooser().addPropertyChangeListener(this);
        end_date.getDateChooser().addPropertyChangeListener(this);
            
        start_date.getLbltitle().setFont(font); // Thêm font
        end_date.getLbltitle().setFont(font); // Thêm font
        
        JPanel btn_layout = new JPanel(new BorderLayout());
        JPanel btninner = new JPanel(new GridLayout(1, 2,10,10)); // thêm Margin cho Panel chứa button
        btninner.setOpaque(false);
        btn_layout.setPreferredSize(new Dimension(30, 36));
        btn_layout.setBorder(new EmptyBorder(20, 10, 0, 10));
        btn_layout.setBackground(Color.white);
        
        export = createButton("Xuất Excel", new Color(76, 175, 80)); // Sửa lại thành createButton 
        reset = createButton("Làm mới", new Color(72, 118, 255)); // Sửa lại thành createButton 

        export.addActionListener(this);
        reset.addActionListener(this);

        btninner.add(export);
        btninner.add(reset);
        btn_layout.add(btninner, BorderLayout.NORTH);

        left_content.add(tenkhachhang);
        left_content.add(start_date);
        left_content.add(end_date);
        left_content.add(btn_layout);

        nhapxuat_center = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(nhapxuat_center, BoxLayout.Y_AXIS);
        nhapxuat_center.setLayout(boxly);

        tblKH = new JTable();
        scrollTblTonKho = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã nhà cung cấp", "Tên nhà cung cấp", "Số lượng nhập", "Tổng số tiền"};
        tblModel.setColumnIdentifiers(header);
        tblKH.setModel(tblModel);
        tblKH.setAutoCreateRowSorter(true);
        tblKH.setDefaultEditor(Object.class, null);
        scrollTblTonKho.setViewportView(tblKH);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblKH.setDefaultRenderer(Object.class, centerRenderer);
        tblKH.setFocusable(false);
        tblKH.setRowHeight(30);
        tblKH.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblKH.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblKH.getColumnModel().getColumn(2).setPreferredWidth(200);
        
        TableSorter.configureTableColumnSorter(tblKH, 0, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tblKH, 1, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tblKH, 3, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tblKH, 4, TableSorter.VND_CURRENCY_COMPARATOR);

        nhapxuat_center.add(scrollTblTonKho);

        this.add(nhapxuat_left, BorderLayout.WEST);
        this.add(nhapxuat_center, BorderLayout.CENTER);
        
        // Cài đặt ngày mặc định của dateS và dateE
        Calendar cal=Calendar.getInstance();
        cal.set(2025, Calendar.JANUARY,1);
        start_date.getDateChooser().setDate(cal.getTime());
        end_date.getDateChooser().setDate(new Date());
    }

    public boolean validateSelectDate() throws ParseException {
        java.util.Date time_start = start_date.getDate();
        java.util.Date time_end = end_date.getDate();

        java.util.Date current_date = new java.util.Date();
        if (time_start != null && time_start.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            Calendar cal=Calendar.getInstance();
            cal.set(2025, Calendar.JANUARY,1);
            start_date.getDateChooser().setDate(cal.getTime());
            return false;
        }
        if (time_end != null && time_end.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            end_date.getDateChooser().setDate(new Date());
            return false;
        }
        if (time_start != null && time_end != null && time_start.after(time_end)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            end_date.getDateChooser().setDate(new Date());
            return false;
        }
        return true;
    }

    public void Fillter() throws ParseException {
        if (validateSelectDate()) {
            String input = tenkhachhang.getText() != null ? tenkhachhang.getText() : "";
            java.util.Date time_start = start_date.getDate() != null ? start_date.getDate() : new java.util.Date(0);
            java.util.Date time_end = end_date.getDate() != null ? end_date.getDate() : new java.util.Date(System.currentTimeMillis());
            LocalDate localStart = time_start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate localEnd = time_end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            this.list = new ThongKeBUS().getThongKeNhaCungCap(localStart,localEnd);
            loadDataTable(list);
        }
    }

    public void loadDataTable(ArrayList<ThongKeNhaCungCapDTO> result) {
        tblModel.setRowCount(0);
        int k = 1;
        for (ThongKeNhaCungCapDTO i : result) {
            tblModel.addRow(new Object[]{
                k, i.getMancc(), i.getTenncc(), i.getSoluong(), Formater.FormatVND(i.getTongtien())
            });
            k++;
        }
    }

    public void resetForm() throws ParseException {
        tenkhachhang.setText("");
        Calendar cal = Calendar.getInstance(); // Cài lại ngày bắt đầu  khi ấn reset
        cal.set(2025, Calendar.JANUARY, 1);
        start_date.getDateChooser().setDate(cal.getTime());
        end_date.getDateChooser().setDate(new Date());
        list = new ThongKeBUS().getThongKeNhaCungCap(LocalDate.of(2025,1,1), LocalDate.now()); // Reset lại table khi reset
        loadDataTable(list);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == export) {
            try {
                JTableExporter.exportJTableToExcel(tblKH);
            } catch (IOException ex) {
                Logger.getLogger(ThongKeNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == reset) {
            try {
                resetForm();
            } catch (ParseException ex) {
                Logger.getLogger(ThongKeNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
       if(tenkhachhang.getDocument().isEmpty()){
            loadDataTable(list);
        }
        else{
            ArrayList<ThongKeNhaCungCapDTO> temp=new ArrayList<>();
            for(ThongKeNhaCungCapDTO i : list){
                if(String.valueOf(i.getMancc()).contains(tenkhachhang.getDocument()) ||
                   i.getTenncc().toUpperCase().contains(tenkhachhang.getDocument().toUpperCase())){
                    temp.add(i);
                }
            }
            loadDataTable(temp);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            Fillter();
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(ThongKeNhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    public void refreshTable(){
        list = new ThongKeBUS().getThongKeNhaCungCap(LocalDate.of(2000,1,1),  LocalDate.now());
        loadDataTable(list);
    }
}
