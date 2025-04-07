/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.View;

import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import java.awt.*;
import javax.swing.*;
import BUS.PhieuXuatBUS;
import DTO.PhieuXuatDTO;
import GUI.Controller.PhieuXuatController;
import GUI.WorkFrame;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class PhieuXuatPanel extends  JPanel{
    public ArrayList<PhieuXuatDTO> listPx;
    private PhieuXuatBUS pxBUS;
    private JTextField txfFind;
    private  JComboBox<String> cbbox;
    private DefaultTableModel dataPhieuXuat;
    private JTable tablePhieuXuat;
    private WorkFrame Wf;
    private NhanVienBUS nvBUS;
    private KhachHangBUS khBUS;
    private JPanel PanelCenter;

    public PhieuXuatPanel(){
        // Tạo Panel toolBar cho thanh công cụ trên cùng
        JPanel toolBar= new JPanel(new GridLayout(1,2));
        JPanel toolBar_Left=new JPanel(new FlowLayout(FlowLayout.LEFT,10,20));
        JPanel toolBar_Right=new JPanel(new FlowLayout(FlowLayout.RIGHT,10,30));
        Font font=new Font("Arial", Font.BOLD, 16);

        
        // Tạo các nút CRUD cho JPanel toolBar_Left
        JButton btnHome= createToolBarButton("Trang chủ", "home.png");
        JButton btnAdd= createToolBarButton("Thêm", "insert1.png");
        JButton btndelete= createToolBarButton("Hủy bỏ", "delete.png");
        JButton btndetail= createToolBarButton("Chi tiết", "detail1.png");
        JButton btnexport= createToolBarButton("Xuất Excel", "export_excel.png");
        btnHome.setFont(font);
        btnAdd.setFont(font);btndelete.setFont(font);btndetail.setFont(font);btnexport.setFont(font);
        
        // Tạo phần tìm kiếm cho JPanel toolBar_Right
        String[] List_Combobox={"Tất cả","Giá thấp đến cao ⬆","Giá cao đến thấp ⬇","NXB thấp đến cao ⬆","NXB cao đến thấp ⬇"};
        cbbox=new JComboBox<String>(List_Combobox);
        cbbox.setPreferredSize(new Dimension(150,35));
        
        txfFind=new JTextField("");
        txfFind.setPreferredSize(new Dimension(200,35));
        txfFind.setForeground(Color.GRAY);
        
        JButton btnfind=createToolBarButton("", "find.png");
        btnfind.setPreferredSize(new Dimension(50,50));
        
        // Tạo JTable cho BookPanel
        listPx=new PhieuXuatBUS().getAll();
        String[] columnPhieuXuat ={"Mã phiếu xuất","Nhân viên","Khách hàng","Thời gian","Tổng tiền","Trạng thái"};
        dataPhieuXuat=new DefaultTableModel(columnPhieuXuat,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Chặn chỉnh sửa tất cả các ô
            }
        };
        tablePhieuXuat= new JTable(dataPhieuXuat);
        tablePhieuXuat.getTableHeader().setBackground(Color.LIGHT_GRAY);
        tablePhieuXuat.getTableHeader().setForeground(Color.BLACK); // Màu chữ đen
        tablePhieuXuat.getTableHeader().setReorderingAllowed(false); // Ngăn kéo các cột
        tablePhieuXuat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// Ngăn chọn nhiều row cùng 1 lúc

        nvBUS=new NhanVienBUS();
        khBUS=new KhachHangBUS();
        for(PhieuXuatDTO px: listPx){  
            String hoTenNv=nvBUS.getHoTenNVById(px.getManv());
            String hoTenKh=khBUS.getFullNameKHById(px.getMakh());
            String trangThai = (px.getTrangthai() == 1) ? "Đã xử lý" : "Chưa được xử lý";
            dataPhieuXuat.addRow(new Object[]{px.getMaphieu(),hoTenNv,hoTenKh,px.getThoigiantao(),px.getTongTien(),
                                                    trangThai});
        }
        // Tạo renderer để căn giữa dữ liệu trong TableBook
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        int[] columnsToCenter = {0,1,2,3,4,5}; // Căn giữa tất cả trừ tên sách và tên nbx
        for (int col : columnsToCenter) {
            tablePhieuXuat.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }
        
         // Điều chỉnh kích thước width và hieght của các cột tableBook 
        tablePhieuXuat.setRowHeight(30);
        tablePhieuXuat.getColumnModel().getColumn(0).setPreferredWidth(30);
        tablePhieuXuat.getColumnModel().getColumn(1).setPreferredWidth(150);
        tablePhieuXuat.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablePhieuXuat.getColumnModel().getColumn(3).setPreferredWidth(165);
        tablePhieuXuat.getColumnModel().getColumn(4).setPreferredWidth(65);
        tablePhieuXuat.getColumnModel().getColumn(5).setPreferredWidth(30);
         // Tạo ScrollPane cho Table để tên cột column hiện
        JScrollPane SPPhieuXuat= new JScrollPane(tablePhieuXuat);
        
        toolBar_Left.add(btnHome);
        toolBar_Left.add(btnAdd);
        toolBar_Left.add(btndetail);
        toolBar_Left.add(btndelete);
        toolBar_Left.add(btnexport);

        toolBar_Right.add(cbbox);
        toolBar_Right.add(txfFind);
        toolBar_Right.add(btnfind);

        toolBar.add(toolBar_Left);
        toolBar.add(toolBar_Right);
        PanelCenter= new JPanel();
        PanelCenter.setLayout(new GridLayout(1,1));
        PanelCenter.add(SPPhieuXuat);
        setLayout(new BorderLayout());
        add(toolBar,BorderLayout.NORTH);
        add(PanelCenter,BorderLayout.CENTER);
        
        ActionListener action=new PhieuXuatController(this, Wf);
        btnAdd.addActionListener(action);
        btndetail.addActionListener(action);
        btndelete.addActionListener(action);
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
    public JPanel getPanelCenter(){
        return PanelCenter;
    }
    public void refreshTablePx(){
        listPx=new PhieuXuatBUS().getAll();
        dataPhieuXuat.setRowCount(0);
        nvBUS=new NhanVienBUS();
        khBUS=new KhachHangBUS();
        for(PhieuXuatDTO px: listPx){  
            String hoTenNv=nvBUS.getHoTenNVById(px.getManv());
            String hoTenKh=khBUS.getFullNameKHById(px.getMakh());
            String trangThai = (px.getTrangthai() == 1) ? "Đã xử lý" : "Chưa được xử lý";
            dataPhieuXuat.addRow(new Object[]{px.getMaphieu(),hoTenNv,hoTenKh,px.getThoigiantao(),px.getTongTien(),
                                                    trangThai});
        }
    }
    public JTable getTablePhieuXuat(){
        return tablePhieuXuat;
    }

}
