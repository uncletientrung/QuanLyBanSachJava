/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.View;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import BUS.SachBUS;
import DTO.SachDTO;
import DAO.SachDAO;
import GUI.WorkFrame;
import GUI.Controller.BookController;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author DELL
 */
public class BookPanel extends JPanel{
    private WorkFrame workFrame;
    public ArrayList<SachDTO> listSach=new SachBUS().getSachAll();
    public BookPanel(){
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
        String[] List_Combobox={"Tất cả","Giá thấp đến cao ⬆","Giá cao đến thấp ⬇"};
        JComboBox<String> cbbox=new JComboBox<String>(List_Combobox);
        cbbox.setPreferredSize(new Dimension(150,35));

        JTextField txfind=new JTextField("Tìm kiếm.....");
        txfind.setPreferredSize(new Dimension(200,35));
        txfind.setForeground(Color.GRAY);

        JButton btnfind=createToolBarButton("", "find.png");
        btnfind.setPreferredSize(new Dimension(50,50));
        
        // Tạo JTable cho BookPanel
        String[] columnBook ={"Mã sách","Tên sách","Nhà xuất bản","Tác Giả","Thể loại","Số lượng","Năm xuất bản","Giá"};
        DefaultTableModel dataBook =new DefaultTableModel(columnBook,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Chặn chỉnh sửa tất cả các ô
            }
        };
        
        JTable tableBook= new JTable(dataBook);
        // Thêm dữ liệu từ sách vào Frame
        for(SachDTO s: listSach){
            dataBook.addRow(new Object[]{s.getMasach(),s.getTensach(),s.getManxb(),s.getMatacgia(),s.getMatheloai(),
                                                           s.getSoluongton(),s.getNamxuatban(),s.getDongia()});
        }
        
        // Tạo renderer để căn giữa dữ liệu trong TableBook
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        int[] columnsToCenter = {0 , 3,4,5,6,7}; // Căn giữa tất cả trừ tên sách và tên nbx
        for (int col : columnsToCenter) {
            tableBook.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }
        // Điều chỉnh kích thước width và hieght của các cột tableBook 
        tableBook.setRowHeight(30);
        tableBook.getColumnModel().getColumn(0).setPreferredWidth(30);
        tableBook.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableBook.getColumnModel().getColumn(2).setPreferredWidth(200);
        tableBook.getColumnModel().getColumn(3).setPreferredWidth(65);
        tableBook.getColumnModel().getColumn(4).setPreferredWidth(65);
        tableBook.getColumnModel().getColumn(5).setPreferredWidth(30);
        tableBook.getColumnModel().getColumn(6).setPreferredWidth(30);
        tableBook.getColumnModel().getColumn(7).setPreferredWidth(60);
        

        // Tạo ScrollPane cho Table để tên cột column hiện
        JScrollPane SPBook= new JScrollPane(tableBook);

        toolBar_Left.add(btnAdd);
        toolBar_Left.add(btnUpdate);
        toolBar_Left.add(btndelete);
        toolBar_Left.add(btndetail);
        toolBar_Left.add(btnexport);

        toolBar_Right.add(cbbox);
        toolBar_Right.add(txfind);
        toolBar_Right.add(btnfind);

        toolBar.add(toolBar_Left);
        toolBar.add(toolBar_Right);

        setLayout(new BorderLayout());
        add(toolBar,BorderLayout.NORTH);
        add(SPBook,BorderLayout.CENTER);
        
        // Thêm sự kiện cho nút
        ActionListener action= new BookController(this,workFrame);
        btnAdd.addActionListener(action);
        
    }

    // Phương thức tạo nút menu với kích thước và căn chỉnh phù hợp
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
