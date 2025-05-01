/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.View;

import BUS.KhuyenMaiBUS;
import BUS.PhanQuyenBUS;
import DTO.KhuyenMaiDTO;
import DTO.NhomQuyenDTO;
import GUI.Controller.KhuyenMaiController;
import GUI.WorkFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public class KhuyenMaiPanel extends JPanel{
    private JTable table;
    private JTextField txfind;
    private Boolean checkTimkiem=false;
    private WorkFrame workFrame;    
    private DefaultTableModel tableModelKhuyenMai;
    //goi ham getnhomquyenall ben BUS de lay ra array list
    public ArrayList<KhuyenMaiDTO> listkhuyenmai;
    
    public KhuyenMaiPanel(){
        JPanel toolBar= new JPanel(new GridLayout(1,2));
        
        JPanel toolBar_Left=new JPanel(new FlowLayout(FlowLayout.LEFT,10,20));
        JPanel toolBar_Right=new JPanel(new FlowLayout(FlowLayout.RIGHT,10,30));
        Font font=new Font("Arial", Font.BOLD, 16);

        // Tạo các nút CRUD cho JPanel toolBar_Left
        JButton btnAdd= createToolBarButton("Thêm", "insert1.png");
        JButton btnUpdate= createToolBarButton("Sửa", "update1.png");
        JButton btndelete= createToolBarButton("Xóa", "trash.png");
        
        btnAdd.setFont(font);
        //goi ham de thuc thi viec them
        KhuyenMaiController controller = new KhuyenMaiController(this, workFrame);
        btnAdd.addActionListener(controller);
        btnUpdate.addActionListener(controller);
        btndelete.addActionListener(controller);
        



        btnUpdate.setFont(font);
        btndelete.setFont(font);
        
         // Tạo phần tìm kiếm cho JPanel toolBar_Right
       

        txfind=new JTextField("Tìm kiếm.....");
        txfind.setPreferredSize(new Dimension(200,35));
        txfind.setForeground(Color.GRAY);
        // Khi click vào JTextField, xóa nội dung nếu là mặc định
        txfind.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txfind.getText().equals("Tìm kiếm.....")) {
                    txfind.setText("");
                    txfind.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txfind.getText().trim().isEmpty()) {
                    txfind.setText("Tìm kiếm.....");
                    txfind.setForeground(Color.GRAY);
                }
            }
        });

        JButton btnfind=createToolBarButton("", "find.png");
        btnfind.setPreferredSize(new Dimension(50,50));
        

        //tìm kiem khi dang go hoac an nut
        txfind.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
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
        btnfind.addActionListener(e -> {
            checkTimkiem=true;
            timKiemKhiDangGo();
            
        });
        
  
        //tạo table ở giữa
        String[] columnKhuyenMai = {
            "Mã khuyến mãi", 
            "Tên chương trình", 
            "Ngày bắt đầu", 
            "Ngày kết thúc", 
            "Hóa đơn tối thiểu", 
            "% giảm giá"
        };

        tableModelKhuyenMai = new DefaultTableModel(columnKhuyenMai, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // chặn chỉnh sửa các ô
            }
        };

        table = new JTable(tableModelKhuyenMai);
        table.getTableHeader().setReorderingAllowed(false); // Tắt tính năng thay đổi thứ tự cột
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setForeground(Color.BLACK); // Màu chữ đen

        // Thêm dữ liệu vào bảng GUI
        listkhuyenmai=new KhuyenMaiBUS().getAllKhuyenMai();
        for (KhuyenMaiDTO km : listkhuyenmai) {
            tableModelKhuyenMai.addRow(new Object[] {
                km.getMaKM(),
                km.getTenChuongTrinh(),
                km.getNgayBatDau(),
                km.getNgayKetThuc(),
                km.getDieuKienToiThieu(),
                km.getPhanTramGiam()
            });
        }

        // Căn giữa các cột cần thiết
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        int[] columnsToCenter = {0, 1, 4, 5}; // Mã, Tên CT, Hóa đơn tối thiểu, % giảm

        for (int col : columnsToCenter) {
            table.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }

        // Điều chỉnh kích thước hàng
        table.setRowHeight(30);

        // Điều chỉnh kích thước từng cột cho hợp lý
        table.getColumnModel().getColumn(0).setPreferredWidth(100);  // Mã khuyến mãi
        table.getColumnModel().getColumn(1).setPreferredWidth(250);  // Tên chương trình
        table.getColumnModel().getColumn(2).setPreferredWidth(130);  // Ngày bắt đầu
        table.getColumnModel().getColumn(3).setPreferredWidth(130);  // Ngày kết thúc
        table.getColumnModel().getColumn(4).setPreferredWidth(150);  // Hóa đơn tối thiểu
        table.getColumnModel().getColumn(5).setPreferredWidth(120);  // % giảm giá

        JScrollPane scrollPane = new JScrollPane(table); 
        
        setLayout(new BorderLayout());
        toolBar_Left.add(btnAdd);
        toolBar_Left.add(btnUpdate);
        toolBar_Left.add(btndelete);
        
        toolBar_Right.add(txfind);
        toolBar_Right.add(btnfind);

        toolBar.add(toolBar_Left);
        toolBar.add(toolBar_Right);

        setLayout(new BorderLayout());
        add(toolBar,BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        
        
    }
    private void timKiemKhiDangGo() {
        String keyword = txfind.getText().trim().toLowerCase();

        ArrayList<KhuyenMaiDTO> danhsachmoi;
        if (keyword.isEmpty() || keyword.equals("tìm kiếm.....")) {
                    danhsachmoi = new KhuyenMaiBUS().getAllKhuyenMai();
        } else {
                    danhsachmoi = new KhuyenMaiBUS().timkiem(keyword);
        }

        capNhatBang(danhsachmoi);
        //khi ấn tìm mới kiểm tra tìm thấy, tìm không thấy mới thông báo
        if (checkTimkiem && danhsachmoi.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);}
        checkTimkiem = false;
 }
    
    

    private JButton createToolBarButton(String text,String imageLink) {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/GUI/Image/" + imageLink));
        JButton button = new JButton(text, imageIcon); // Đặt ảnh và chữ
        button.setHorizontalTextPosition(SwingConstants.CENTER); // Căn chữ vào giữa
        button.setVerticalTextPosition(SwingConstants.BOTTOM); // Đặt chữ dưới ảnh
        button.setFocusPainted(false); // Bỏ viền khi click
        button.setBorderPainted(false); // Ẩn viền nút
        // button.setContentAreaFilled(false); // Ẩn nền nút
        button.setBackground(new Color(240, 240, 240)); // Màu nền nhẹ
        return button;
    }
    
   public void capNhatBang(ArrayList<KhuyenMaiDTO> danhSach) {
    tableModelKhuyenMai.setRowCount(0); // Xóa bảng cũ
    
    for (KhuyenMaiDTO km : danhSach) {
        tableModelKhuyenMai.addRow(new Object[]{km.getMaKM(),km.getTenChuongTrinh(),km.getNgayBatDau(),km.getNgayKetThuc(),km.getDieuKienToiThieu(),km.getPhanTramGiam()});
    }
}

//load lai du lieu khi moi them vo
    public void loadData() {
    listkhuyenmai = new KhuyenMaiBUS().getAllKhuyenMai(); // Lấy danh sách mới
    capNhatBang(listkhuyenmai); // Cập nhật lại bảng
}
    
    //hàm kiểm tra coi dòng trong bảng có được click chọn hay không để sửa
    public int getSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhóm quyền để sửa!");
        }
        return selectedRow;
    }
    
    
    //lấy đối tượng nhóm quyền đang được click để hàm update biết
  public KhuyenMaiDTO getSelectedKhuyenmai() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) return null;

        int maKhuyenmai = (int) table.getValueAt(selectedRow, 0);
        String tenChuongtrinh = (String) table.getValueAt(selectedRow, 1);

        Object valNgayBatDau = table.getValueAt(selectedRow, 2);
        Object valNgayKetThuc = table.getValueAt(selectedRow, 3);

        Date ngayBatDau = null;
        Date ngayKetThuc = null;

        // Nếu table đang lưu String, bạn cần parse:
        if (valNgayBatDau instanceof String) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // hoặc "yyyy-MM-dd HH:mm:ss" tùy bạn
                ngayBatDau = sdf.parse((String) valNgayBatDau);
                ngayKetThuc = sdf.parse((String) valNgayKetThuc);
            } catch (ParseException e) {
                e.printStackTrace();
                return null; // hoặc xử lý lỗi khác
            }
        } else if (valNgayBatDau instanceof Date) {
            ngayBatDau = (Date) valNgayBatDau;
            ngayKetThuc = (Date) valNgayKetThuc;
        }

        double hoaDontoithieu = (double) table.getValueAt(selectedRow, 4);
        double phantramgiam = (double) table.getValueAt(selectedRow, 5);

        return new KhuyenMaiDTO(maKhuyenmai, tenChuongtrinh, ngayBatDau, ngayKetThuc, hoaDontoithieu, phantramgiam);
    }
  
    public void refreshTable(){
        KhuyenMaiBUS kmBUS=new KhuyenMaiBUS();
        listkhuyenmai=kmBUS.getAllKhuyenMai();
        tableModelKhuyenMai.setRowCount(0);
        for(KhuyenMaiDTO km: listkhuyenmai){
            tableModelKhuyenMai.addRow(new Object[] {
                km.getMaKM(),
                km.getTenChuongTrinh(),
                km.getNgayBatDau(),
                km.getNgayKetThuc(),
                km.getDieuKienToiThieu(),
                km.getPhanTramGiam()
            });
        }
    }

    
}
