/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.ThongKeDialog;

import BUS.KhachHangBUS;
import BUS.NhaCungCapBUS;
import BUS.ThongKeBUS;
import DAO.ChiTietPhieuXuatDAO;
import DAO.PhieuXuatDAO;
import DTO.ChiTietPhieuXuatDTO;
import DTO.KhachHangDTO;
import DTO.NhaCungCapDTO;
import DTO.PhieuXuatDTO;
import GUI.Controller.NhaCungCapController;
import GUI.WorkFrame;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public class KhachHangDialog extends JDialog {

    private JTable table;
    private JTable table2;
    private JTable table3;
    private DefaultTableModel tablekh;
    private DefaultTableModel tablekh2;
    private DefaultTableModel tablekh3;
    private WorkFrame workFrame;
    private JTextField txtTimKiem;
    private Boolean checkTimkiem = false;
    private ImageIcon icon;
    private JComboBox<String> cbbox;
    JDateChooser aaChooser;
    JDateChooser bbChooser;
    JMonthChooser m1Chooser;
    JMonthChooser m2Chooser;
    JYearChooser y1Chooser2;
    JYearChooser  y2Chooser2;
    
    JYearChooser y1Chooser;
    JYearChooser  y2Chooser;
    

    JYearChooser y1Chooser4;
    JYearChooser  y2Chooser4;
    
    JPanel panel,panel2,panel3,panel4;
    
    JComboBox<String> qui1;
    JComboBox<String> qui2;
    
    
    int t=1;
    public ArrayList<PhieuXuatDTO> listpx = new PhieuXuatDAO().selectAll();
    public ArrayList<ChiTietPhieuXuatDTO> listctpx = new ChiTietPhieuXuatDAO().selectAll2();
    public ArrayList<KhachHangDTO> listkh = new KhachHangBUS().getKhachHangAll();
    public ArrayList<Long> list;
    
    JScrollPane scrollPane;
    
    public KhachHangDialog(JFrame parent) {
        super(parent, "Thống kê khách hàng", true);
        setSize(1300, 700);
        setResizable(false);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10)); // Khoảng cách giữa các phần

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(100, 100));

        JLabel lbTitle = new JLabel("Thông kê khách hàng", JLabel.CENTER);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lbTitle.setForeground(Color.BLACK);
        lbTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0)); // Tạo khoảng cách dưới tiêu đề
        headerPanel.add(lbTitle, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel();
         // Tạo khoảng cách giữa tìm kiếm và tiêu đề
        
       
        
        JLabel a = new JLabel("Theo : ");

        String[] List_Combobox = {"Tất cả","Trước đến nay", "Ngày", "Tháng","Quý", "Năm"};
        cbbox = new JComboBox<String>(List_Combobox);
        cbbox.setPreferredSize(new Dimension(150, 35));

        aaChooser = new JDateChooser();
        bbChooser = new JDateChooser();
        m1Chooser = new JMonthChooser();
        m2Chooser = new JMonthChooser();
        y1Chooser=new JYearChooser();
        y2Chooser=new JYearChooser();
        
        y1Chooser2=new JYearChooser();
        y2Chooser2=new JYearChooser();
        
      
        y1Chooser4=new JYearChooser();
        y2Chooser4=new JYearChooser();
        
        JLabel b = new JLabel("Từ : ");
        JLabel c = new JLabel("Đến : ");
        
        JLabel b2 = new JLabel("Từ : ");
        JLabel c2 = new JLabel("Đến : ");
        JLabel b3 = new JLabel("Từ : ");
        JLabel c3 = new JLabel("Đến : ");
        JLabel b4 = new JLabel("Từ : ");
        JLabel c4 = new JLabel("Đến : ");
        
        panel=new JPanel();
        panel.add(b);
        panel.add(aaChooser);
        panel.add(c);
        panel.add(bbChooser);
        panel.setVisible(false);
        
        panel2=new JPanel();
        panel2.add(b2);
        panel2.add(m1Chooser);
        panel2.add(y1Chooser2);
        panel2.add(c2);
        panel2.add(m2Chooser);
        panel2.add(y2Chooser2);
        panel2.setVisible(false);
        
        panel3=new JPanel();
        panel3.add(b3);
        panel3.add(y1Chooser);
        panel3.add(c3);
        panel3.add(y2Chooser);
        panel3.setVisible(false);
        
        String[] List = {"Quý I","Quý II","Quý III","Quý IV  "};
        String[] List2 = {"Quý I","Quý II","Quý III","Quý IV  "};
        qui1 = new JComboBox<String>(List);
        qui2 = new JComboBox<String>(List2);
        
        panel4=new JPanel();
        panel4.add(b4);
        panel4.add(qui1);
        panel4.add(y1Chooser4);
        panel4.add(c2);
        panel4.add(qui2);
        panel4.add(y2Chooser4);
        panel4.setVisible(false);
        
        txtTimKiem = new JTextField("Tìm kiếm.....");
        txtTimKiem.setPreferredSize(new Dimension(200, 35));
        txtTimKiem.setForeground(Color.GRAY);

        // gắn icon cho nút
        icon = new ImageIcon(getClass().getResource("/GUI/Image/find.png"));
        Image img = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        JButton btnTimKiem = new JButton(new ImageIcon(img));

        searchPanel.add(a);
        searchPanel.add(cbbox);
        searchPanel.add(panel);
        searchPanel.add(panel2);
        searchPanel.add(panel3);
        searchPanel.add(panel4);
        searchPanel.add(txtTimKiem);
        searchPanel.add(btnTimKiem);
        headerPanel.add(searchPanel, BorderLayout.CENTER);

        // Thêm headerPanel vào phần NORTH 
        add(headerPanel, BorderLayout.NORTH);

        //tim kiem
        txtTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtTimKiem.getText().equals("Tìm kiếm.....")) {
                    txtTimKiem.setText("");
                    txtTimKiem.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtTimKiem.getText().trim().isEmpty()) {
                    txtTimKiem.setText("Tìm kiếm.....");
                    txtTimKiem.setForeground(Color.GRAY);
                }
            }
        });
        //tìm kiem khi dang go hoac an nut
        txtTimKiem.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                timKiemKhiDangGo();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                timKiemKhiDangGo();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                timKiemKhiDangGo();
            }

        });
        btnTimKiem.addActionListener(e -> {
            checkTimkiem = true;
            timKiemKhiDangGo();

        });

        //bảng dữ liệu
        String[] columnNCC = {"Mã", "Họ", "Tên","Số điện thoại"};
        tablekh = new DefaultTableModel(columnNCC, 0) {
            ;
        
        @Override
            public boolean isCellEditable(int row, int column) {
                return false;// chặn chỉnh sửa các ô      
            }
        };

        table = new JTable(tablekh);
        table.getTableHeader().setReorderingAllowed(false); // Tắt tính năng thay đổi thứ tự cột

        //lam tieu de no dam hơn 
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setForeground(Color.BLACK); // Màu chữ đen

        for (KhachHangDTO ncc : listkh) {
            tablekh.addRow(new Object[]{ncc.getMakh(), ncc.getHokh(), ncc.getTenkh(), ncc.getSdt()});

        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        // Căn giữa các cột: Mã NCC (0), SĐT (3)

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        int[] columnsToCenter = {0, 1, 2, 3};
        for (int col : columnsToCenter) {
            table.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }

       

        // Điều chỉnh kích thước width và hieght của các cột tableBook 
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
       

        
        // table1
        String[] columnNCC2 = {"Mã", "Họ", "Tên","Tổng hóa đơn","Tổng tiền","Tổng sách"};
        tablekh2 = new DefaultTableModel(columnNCC2, 0) {
            ;
        
        @Override
            public boolean isCellEditable(int row, int column) {
                return false;// chặn chỉnh sửa các ô      
            }
        };

        table2 = new JTable(tablekh2);
        table2.getTableHeader().setReorderingAllowed(false); // Tắt tính năng thay đổi thứ tự cột

        //lam tieu de no dam hơn 
        table2.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table2.getTableHeader().setForeground(Color.BLACK); // Màu chữ đen
 
       
         DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
        // Căn giữa các cột: Mã NCC (0), SĐT (3)

        centerRenderer2.setHorizontalAlignment(JLabel.CENTER);
        int[] columnsToCenter2 = {0, 1, 2, 3, 4, 5};
        for (int col : columnsToCenter) {
            table2.getColumnModel().getColumn(col).setCellRenderer(centerRenderer2);
        }

       

        // Điều chỉnh kích thước width và hieght của các cột tableBook 
        table2.setRowHeight(30);
        table2.getColumnModel().getColumn(0).setPreferredWidth(80);
        table2.getColumnModel().getColumn(1).setPreferredWidth(80);
        table2.getColumnModel().getColumn(2).setPreferredWidth(80);
        table2.getColumnModel().getColumn(3).setPreferredWidth(80);
        table2.getColumnModel().getColumn(4).setPreferredWidth(80);
        table2.getColumnModel().getColumn(5).setPreferredWidth(80);
        
        
        table3=table;
        
        scrollPane = new JScrollPane(table3);
        add(scrollPane, BorderLayout.CENTER);
        
        //panel chứa 3 
        JButton btnct = new JButton("chi tiết");
        JButton btntk = new JButton("thống kê");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnct = createButton("chi tiết", new Color(46, 204, 113));
        btntk = createButton("thống kê", new Color(60, 123, 231));
        
        btnct.setFont(new Font("Arial", Font.BOLD, 16));
        btntk.setFont(new Font("Arial", Font.BOLD, 16));

        KhachHangController controller = new KhachHangController(this, workFrame);
        btnct.addActionListener(controller);
        btntk.addActionListener(controller);
        cbbox.addActionListener(controller);
      
        
        
        buttonPanel.add(btnct);
        buttonPanel.add(btntk);

        add(buttonPanel, BorderLayout.SOUTH);
        //ham nay de cho khong focus con tro vao o tim kiem
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                table.requestFocusInWindow();
            }
        });

    }

    private void timKiemKhiDangGo() {
        String keyword = txtTimKiem.getText().trim().toLowerCase();

        ArrayList<KhachHangDTO> danhsachmoi;
        if (keyword.isEmpty() || keyword.equals("tìm kiếm.....")) {
            danhsachmoi = new KhachHangBUS().getKhachHangAll();
        } else {
            danhsachmoi = new KhachHangBUS().search(keyword);
        }

        capNhatBang(danhsachmoi);
        //khi ấn tìm mới kiểm tra tìm thấy, tìm không thấy mới thông báo
        if (checkTimkiem && danhsachmoi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        checkTimkiem = false;
    }

    public void capNhatBang(ArrayList<KhachHangDTO> danhSach) {
        tablekh.setRowCount(0); // Xóa bảng cũ

        for (KhachHangDTO ncc : danhSach) {
            tablekh.addRow(new Object[]{ncc.getMakh(), ncc.getHokh(), ncc.getTenkh(), ncc.getemail(), ncc.getNgaysinh(), ncc.getSdt()});
        }
    }

    //load lai du lieu khi moi them vo
    public void loadData() {
        listkh = new KhachHangBUS().getKhachHangAll(); // Lấy danh sách mới
        capNhatBang(listkh); // Cập nhật lại bảng
    }

    public int getSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhà cung cấp để sửa!");
        }
        return selectedRow;
    }

    //lấy đối tượng nhóm quyền đang được click để hàm update biết
    public NhaCungCapDTO getSelectedNhaCungCap() {
        int selectedRow = table.getSelectedRow(); // Lấy chỉ số hàng đang chọn
        if (selectedRow == -1) {
            return null; // Nếu không chọn gì, trả về null
        }
        int maNhaCungCap = (int) table.getValueAt(selectedRow, 0); // Lấy mã nhóm quyền
        String tenncc = (String) table.getValueAt(selectedRow, 1);
        String diachi = (String) table.getValueAt(selectedRow, 2);
        String sdt = (String) table.getValueAt(selectedRow, 3);
        String email = (String) table.getValueAt(selectedRow, 4);

        return new NhaCungCapDTO(maNhaCungCap, tenncc, diachi, sdt, email);
    }

    // ======= Tạo button đồng bộ với phong cách UI =======
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
                // Vẽ bo tròn góc cho nút
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

    public JTable getTable() {
        return table;
    }
    
    public JTable getTable2() {
        return table2;
    }

    public JComboBox<String> getCbbox() {
        return cbbox;
    }

    public JDateChooser getAaChooser() {
        return aaChooser;
    }

    public JDateChooser getBbChooser() {
        return bbChooser;
    }
    
       public void doitable(int index,JScrollPane a) {
        tablekh2.setRowCount(0);
        tablekh.setRowCount(0);
        // Thay đổi bảng tùy theo lựa chọn trong JComboBox
         
        if (index == 1) {
            suatable2(listkh);
            table3 = table;
        } else {
            suatable(listkh, list);
            table3 = table2;
        }
     
        // Cập nhật JScrollPane để hiển thị bảng mới
        a.setViewportView(table3);
        table3.revalidate();  // Cập nhật lại bảng
        table3.repaint();  // Vẽ lại bảng
    }
    public void suatable( ArrayList<KhachHangDTO> listkh , ArrayList<Long> list ){
        int n=0;
         for (KhachHangDTO ncc : listkh) {
             
            tablekh2.addRow(new Object[]{ncc.getMakh(), ncc.getHokh(), ncc.getTenkh(), list.get(n),list.get(n+1),list.get(n+2)});
                n+=3;
        }
    }
    
    public void suatable2( ArrayList<KhachHangDTO> listkh  ){
        
         for (KhachHangDTO ncc : listkh) {
             
            tablekh.addRow(new Object[]{ncc.getMakh(), ncc.getHokh(), ncc.getTenkh(),ncc.getSdt()});
                
        }
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setCbbox() {
        this.cbbox.setSelectedItem("Tất cả");
    }
    


}
