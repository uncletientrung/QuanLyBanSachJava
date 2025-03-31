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
import GUI.Dialog.BookDialog.BookDialogUpdate;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentListener;
import BUS.NhaXuatBanBUS;
import DAO.NhaXuatBanDAO;
import DTO.NhaXuatBanDTO;
import DTO.TacGiaDTO;
import BUS.TacGiaBUS;
import DTO.TheLoaiDTO;
import BUS.TheLoaiBUS;
/**
 *
 * @author DELL
 */
public class BookPanel extends JPanel{
    private JTable tableBook;
    private WorkFrame workFrame;
    private SachBUS sachBUS;
    private ArrayList<SachDTO> listSach;
    private DefaultTableModel dataBook;
    private JTextField txfFind;
    private JComboBox<String> cbbox;
    private NhaXuatBanBUS NxbBUS=new NhaXuatBanBUS();
    private TacGiaBUS TgBUS=new TacGiaBUS();
    private TheLoaiBUS TlBUS=new TheLoaiBUS();
           
    
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
        String[] List_Combobox={"Tất cả","Giá thấp đến cao ⬆","Giá cao đến thấp ⬇","NXB thấp đến cao ⬆","NXB cao đến thấp ⬇"};
        cbbox=new JComboBox<String>(List_Combobox);
        cbbox.setPreferredSize(new Dimension(150,35));

        txfFind=new JTextField("");
        txfFind.setPreferredSize(new Dimension(200,35));
        txfFind.setForeground(Color.GRAY);

        JButton btnfind=createToolBarButton("", "find.png");
        btnfind.setPreferredSize(new Dimension(50,50));
        
        // Tạo JTable cho BookPanel
        listSach=new SachBUS().getSachAll();
        String[] columnBook ={"Mã sách","Tên sách","Nhà xuất bản","Tác Giả","Thể loại","Số lượng","Năm xuất bản","Giá"};
        dataBook =new DefaultTableModel(columnBook,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Chặn chỉnh sửa tất cả các ô
            }
        };
        
        tableBook= new JTable(dataBook);
        tableBook.getTableHeader().setBackground(Color.LIGHT_GRAY);
        tableBook.getTableHeader().setForeground(Color.BLACK); // Màu chữ đen
        
        // Thêm dữ liệu từ sách vào Frame
        for(SachDTO s: listSach){
            // Tìm tên nhà xuất 
            String tenNxb="";
             NhaXuatBanDTO NxbFind=NxbBUS.getNXBById(s.getManxb());
             tenNxb=NxbFind.getTennxb();
             // Tìm tên tác giả
             String tenTg="";
             TacGiaDTO TgFind=TgBUS.getTGById(s.getMatacgia());
             tenTg =TgFind.getHotentacgia();
             // Tìm tên thể loại
             String tenTl="";
             TheLoaiDTO TLFind=TlBUS.getTlbyId(s.getMatheloai());
             tenTl=TLFind.getTentheloai();
             
            
            
            dataBook.addRow(new Object[]{s.getMasach(),s.getTensach(),tenNxb,tenTg,tenTl,
                                                           s.getSoluongton(),s.getNamxuatban(),s.getDongia()});
        }
        
        // Tạo renderer để căn giữa dữ liệu trong TableBook
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        int[] columnsToCenter = {0 , 1,2,3,4,5,6,7}; // Căn giữa tất cả trừ tên sách và tên nbx
        for (int col : columnsToCenter) {
            tableBook.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }
        // Điều chỉnh kích thước width và hieght của các cột tableBook 
        tableBook.setRowHeight(30);
        tableBook.getColumnModel().getColumn(0).setPreferredWidth(30);
        tableBook.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableBook.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableBook.getColumnModel().getColumn(3).setPreferredWidth(115);
        tableBook.getColumnModel().getColumn(4).setPreferredWidth(115);
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
        toolBar_Right.add(txfFind);
        toolBar_Right.add(btnfind);

        toolBar.add(toolBar_Left);
        toolBar.add(toolBar_Right);

        setLayout(new BorderLayout());
        add(toolBar,BorderLayout.NORTH);
        add(SPBook,BorderLayout.CENTER);
        
        // Gắn sự kiện vào Table để khi ấn vào hiện lên bản chính sửa
        tableBook.getSelectionModel().addListSelectionListener(new BookController(this,workFrame));
        // Thêm sự kiện cho nút
        ActionListener action= new BookController(this,workFrame);
        btnAdd.addActionListener(action);
        btnUpdate.addActionListener(action);
        btndelete.addActionListener(action);
        btndetail.addActionListener(action);
        cbbox.addActionListener(action);
        // Thêm sự kiện DocumentListener
        DocumentListener document=new BookController(this, workFrame);
        txfFind.getDocument().addDocumentListener(document);
        
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
    public JTable getTable(){
        return tableBook;
    }
    public JTextField getTxfFind(){
        return txfFind;
    }
    public JComboBox<String> getCbbox(){
        return cbbox;
    }
    public ArrayList<SachDTO> getListSach(){
        return listSach;
    }
    public void refreshTableData(){
        listSach=new SachBUS().getSachAll();
        dataBook.setRowCount(0);
        for(SachDTO sach: listSach){
            // Tìm tên nhà xuất 
            String tenNxb="";
             NhaXuatBanDTO NxbFind=NxbBUS.getNXBById(sach.getManxb());
             tenNxb=NxbFind.getTennxb();
             // Tìm tên tác giả
             String tenTg="";
             TacGiaDTO TgFind=TgBUS.getTGById(sach.getMatacgia());
             tenTg =TgFind.getHotentacgia();
             // Tìm tên thể loại
             String tenTl="";
             TheLoaiDTO TLFind=TlBUS.getTlbyId(sach.getMatheloai());
             tenTl=TLFind.getTentheloai();
             dataBook.addRow(new Object[]{
                sach.getMasach(),
                sach.getTensach(),
                tenNxb,
                tenTg,
                tenTl,
                sach.getSoluongton(),
                sach.getNamxuatban(),
                sach.getDongia()
            });
        }
    }
    public void FindTableData(String text){
        listSach=new SachBUS().search(text);
        dataBook.setRowCount(0);
        for(SachDTO sach: listSach){
            // Tìm tên nhà xuất 
            String tenNxb="";
             NhaXuatBanDTO NxbFind=NxbBUS.getNXBById(sach.getManxb());
             tenNxb=NxbFind.getTennxb();
             // Tìm tên tác giả
             String tenTg="";
             TacGiaDTO TgFind=TgBUS.getTGById(sach.getMatacgia());
             tenTg =TgFind.getHotentacgia();
             // Tìm tên thể loại
             String tenTl="";
             TheLoaiDTO TLFind=TlBUS.getTlbyId(sach.getMatheloai());
             tenTl=TLFind.getTentheloai();
             dataBook.addRow(new Object[]{
                sach.getMasach(),
                sach.getTensach(),
                tenNxb,
                tenTg,
                tenTl,
                sach.getSoluongton(),
                sach.getNamxuatban(),
                sach.getDongia()
            });
        }
    }
    public void FilterTableData(ArrayList<SachDTO> list_Sort){
        dataBook.setRowCount(0);
        for(SachDTO sach: list_Sort){
            // Tìm tên nhà xuất 
            String tenNxb="";
             NhaXuatBanDTO NxbFind=NxbBUS.getNXBById(sach.getManxb());
             tenNxb=NxbFind.getTennxb();
             // Tìm tên tác giả
             String tenTg="";
             TacGiaDTO TgFind=TgBUS.getTGById(sach.getMatacgia());
             tenTg =TgFind.getHotentacgia();
             // Tìm tên thể loại
             String tenTl="";
             TheLoaiDTO TLFind=TlBUS.getTlbyId(sach.getMatheloai());
             tenTl=TLFind.getTentheloai();
             dataBook.addRow(new Object[]{
                sach.getMasach(),
                sach.getTensach(),
                tenNxb,
                tenTg,
                tenTl,
                sach.getSoluongton(),
                sach.getNamxuatban(),
                sach.getDongia()
            });
        }
    }
}
