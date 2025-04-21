/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.View;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import BUS.KhachHangBUS;
import DTO.SachDTO;
import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import GUI.WorkFrame;
import GUI.Controller.KhachhangController;
import GUI.Dialog.KhachHangDialog.KhachHangDialogUpdate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentListener;

public class KhachhangPanel extends JPanel {
    private JTable tablekh;
    private WorkFrame workFrame;
    private KhachHangBUS khBUS;
    private ArrayList<KhachHangDTO> listkh;
    private DefaultTableModel datakh;
    private JTextField txfFind;
    private JComboBox<String> cbbox;

    public KhachhangPanel() {
        // Tạo Panel toolBar cho thanh công cụ trên cùng
        JPanel toolBar = new JPanel();
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS)); // Sử dụng BoxLayout giống BookPanel
        JPanel toolBar_Left = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
        JPanel toolBar_Right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 30));
        Font font = new Font("Arial", Font.BOLD, 16);

        // Tạo các nút CRUD cho JPanel toolBar_Left
        JButton btnAdd = createToolBarButton("Thêm", "insert1.png");
        JButton btnUpdate = createToolBarButton("Sửa", "update1.png");
        JButton btndelete = createToolBarButton("Xóa", "trash.png");
        JButton btndetail = createToolBarButton("Chi tiết", "detail1.png");
        JButton btnExport = createToolBarButton("Xuất Excel", "export_excel.png");
        btnAdd.setFont(font);
        btnUpdate.setFont(font);
        btndelete.setFont(font);
        btndetail.setFont(font);
        btnExport.setFont(font);

        // Tạo phần tìm kiếm cho JPanel toolBar_Right
        String[] List_Combobox = {"Tất cả", "Mã khách hàng","Tên khách hàng", "Số điện thoại", "Email"};
        cbbox = new JComboBox<String>(List_Combobox);
        cbbox.setPreferredSize(new Dimension(150, 35));

        txfFind = new JTextField("");
        txfFind.setPreferredSize(new Dimension(200, 35));
        txfFind.setForeground(Color.GRAY);

        JButton btnfind = createToolBarButton("", "find.png");
        btnfind.setPreferredSize(new Dimension(50, 50));

        // Tạo JTable cho KhachhangPanel
        listkh = new KhachHangBUS().getKhachHangAll();
        String[] columnkh = {"Mã", "Họ", "Tên", "Email", "Ngày Sinh", "Số Điện Thoại"};
        datakh = new DefaultTableModel(columnkh, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Chặn chỉnh sửa tất cả các ô
            }
        };

        tablekh = new JTable(datakh);
        // Thêm dữ liệu từ khách hàng vào Frame
        for (KhachHangDTO s : listkh) {
            datakh.addRow(new Object[]{s.getMakh(), s.getHokh(), s.getTenkh(), s.getemail(), s.getNgaysinh(), s.getSdt()});
        }

        // Tạo renderer để căn giữa dữ liệu trong Table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        int[] columnsToCenter = {0, 3, 4, 5}; // Căn giữa các cột Mã, Email, Ngày Sinh, Số Điện Thoại
        for (int col : columnsToCenter) {
            tablekh.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }

        // Điều chỉnh kích thước width và height của các cột tablekh
        tablekh.setRowHeight(30);
        tablekh.getColumnModel().getColumn(0).setPreferredWidth(30);
        tablekh.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablekh.getColumnModel().getColumn(2).setPreferredWidth(200);
        tablekh.getColumnModel().getColumn(3).setPreferredWidth(65);
        tablekh.getColumnModel().getColumn(4).setPreferredWidth(65);
        tablekh.getColumnModel().getColumn(5).setPreferredWidth(30);

        // Tạo ScrollPane cho Table để tên cột column hiện
        JScrollPane SPBook = new JScrollPane(tablekh);

        // Thêm các nút vào toolBar_Left
        toolBar_Left.add(btnAdd);
        toolBar_Left.add(btnUpdate);
        toolBar_Left.add(btndelete);
        toolBar_Left.add(btndetail);
        toolBar_Left.add(btnExport);

        // Thêm các thành phần vào toolBar_Right
        toolBar_Right.add(cbbox);
        toolBar_Right.add(txfFind);
        toolBar_Right.add(btnfind);

        // Thêm toolBar_Left và toolBar_Right vào toolBar
        toolBar.add(toolBar_Left);
        toolBar.add(Box.createHorizontalGlue()); // Thêm khoảng trống để đẩy toolBar_Right sang phải
        toolBar.add(toolBar_Right);

        // Thiết lập layout cho KhachhangPanel
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(SPBook, BorderLayout.CENTER);

        // Gắn sự kiện vào Table để khi ấn vào hiện lên bản chỉnh sửa
        tablekh.getSelectionModel().addListSelectionListener(new KhachhangController(this, workFrame));
        // Thêm sự kiện cho nút
        ActionListener action = new KhachhangController(this, workFrame);
        btnAdd.addActionListener(action);
        btnUpdate.addActionListener(action);
        btndelete.addActionListener(action);
        btndetail.addActionListener(action);
        btnExport.addActionListener(action);
        cbbox.addActionListener(action);
        // Thêm sự kiện DocumentListener
        DocumentListener document = new KhachhangController(this, workFrame);
        txfFind.getDocument().addDocumentListener(document);
    }

    // Phương thức tạo nút menu với kích thước và căn chỉnh phù hợp
    private JButton createToolBarButton(String text, String imageLink) {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/GUI/Image/" + imageLink));
        JButton button = new JButton(text, imageIcon); // Đặt ảnh và chữ
        button.setHorizontalTextPosition(SwingConstants.CENTER); // Căn chữ vào giữa
        button.setVerticalTextPosition(SwingConstants.BOTTOM); // Đặt chữ dưới ảnh
        button.setFocusPainted(false); // Bỏ viền khi click
        button.setBorderPainted(false); // Ẩn viền nút
        button.setBackground(new Color(240, 240, 240)); // Màu nền nhẹ
        return button;
    }

    public JTable getTable() {
        return tablekh;
    }

    public JTextField getTxfFind() {
        return txfFind;
    }

    public JComboBox<String> getCbbox() {
        return cbbox;
    }

    public ArrayList<KhachHangDTO> getListKhachHang() {
        return listkh;
    }

    public void refreshTableData() {
        listkh = new KhachHangBUS().getKhachHangAll();
        datakh.setRowCount(0);
        for (KhachHangDTO kh : listkh) {
            datakh.addRow(new Object[]{
                    kh.getMakh(),
                    kh.getHokh(),
                    kh.getTenkh(),
                    kh.getemail(),
                    kh.getNgaysinh(),
                    kh.getSdt(),
            });
        }
    }
 
    public void FindKhach(String text,String choice_cbb) { // Tìm theo mã
        if (choice_cbb.equals("Tất cả") || choice_cbb.equals("Tên khách hàng"))
            listkh=new KhachHangBUS().searchName(text);
        else if( choice_cbb.equals("Mã khách hàng")){
            listkh = new KhachHangBUS().search(text);
        }
        else if(choice_cbb.equals("Email")){
            listkh=new KhachHangBUS().searchEmail(text);
        }
        else if(choice_cbb.equals("Số điện thoại")){
            listkh=new KhachHangBUS().searchSDTKh(text);
        }
        datakh.setRowCount(0);
        for (KhachHangDTO kh : listkh) {
            datakh.addRow(new Object[]{
                    kh.getMakh(),
                    kh.getHokh(),
                    kh.getTenkh(),
                    kh.getemail(),
                    kh.getNgaysinh(),
                    kh.getSdt(),
            });
        }
    }
    
}