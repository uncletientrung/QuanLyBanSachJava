/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.View;

import java.awt.*;
import javax.swing.*;
import BUS.PhieuXuatBUS;
import DTO.PhieuXuatDTO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class PhieuXuatPanel extends  JPanel{
    public ArrayList<PhieuXuatDTO> listPx;
    private JTextField txfFind;
    private  JComboBox<String> cbbox;
    private DefaultTableModel dataPhieuXuat;
    private JTable tablePhieuXuat;

    public PhieuXuatPanel(){
        // Tạo Panel toolBar cho thanh công cụ trên cùng
        JPanel toolBar= new JPanel(new GridLayout(1,2));
        JPanel toolBar_Left=new JPanel(new FlowLayout(FlowLayout.LEFT,10,20));
        JPanel toolBar_Right=new JPanel(new FlowLayout(FlowLayout.RIGHT,10,30));
        Font font=new Font("Arial", Font.BOLD, 16);
        
        // Tạo các nút CRUD cho JPanel toolBar_Left
        JButton btnAdd= createToolBarButton("Thêm", "insert1.png");
        JButton btnUpdate= createToolBarButton("Sửa", "update1.png");
        JButton btndelete= createToolBarButton("Xóa", "trash.png");
        JButton btndetail= createToolBarButton("Chi tiết", "detail1.png");
        JButton btnexport= createToolBarButton("Xuất Excel", "export_excel.png");
        btnAdd.setFont(font);btnUpdate.setFont(font);btndelete.setFont(font);btndetail.setFont(font);btnexport.setFont(font);
        
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
        for(PhieuXuatDTO px: listPx){
            dataPhieuXuat.addRow(new Object[]{px.getMaphieu(),px.getManv(),px.getMakh(),px.getThoigiantao(),px.getTongTien(),
                                                    px.getTrangthai()});
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

        toolBar_Left.add(btnAdd);
        toolBar_Left.add(btnUpdate);
        toolBar_Left.add(btndelete);
        toolBar_Left.add(btndetail);
        toolBar_Left.add(btnexport);

        toolBar_Right.add(cbbox);
        toolBar_Right.add(txfFind);
        toolBar_Right.add(btnfind);

        toolBar.add(toolBar_Left);
        toolBar.add(toolBar_Right);

        setLayout(new BorderLayout());
        add(toolBar,BorderLayout.NORTH);
        add(SPPhieuXuat,BorderLayout.CENTER);
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
}
