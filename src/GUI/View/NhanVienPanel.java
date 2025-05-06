package GUI.View;

import GUI.WorkFrame;
import javax.swing.*;
import java.awt.*;
import BUS.*;
import DTO.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import GUI.Controller.*;

public class NhanVienPanel extends JPanel {
    private final JTable tableNV;
    private WorkFrame workFrame;
    private NhanVienBUS nvBUS;
    private ArrayList<NhanVienDTO> listNV;
    private final DefaultTableModel dataNV;
    private JTextField txtFind;
    private JComboBox<String> cbbox;

    public NhanVienPanel() {
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
        JButton btnexport = createToolBarButton("Xuất Excel", "export_excel.png");
        btnAdd.setFont(font);
        btnUpdate.setFont(font);
        btndelete.setFont(font);
        btndetail.setFont(font);
        btnexport.setFont(font);

        // Tạo phần tìm kiếm cho JPanel toolBar_Right
        String[] List_Combobox = {"Tất cả", "Họ tên", "Số điện thoại"};
        cbbox = new JComboBox<String>(List_Combobox);
        cbbox.setPreferredSize(new Dimension(150, 35));

        txtFind = new JTextField();
        txtFind.setPreferredSize(new Dimension(200, 35));
        txtFind.setForeground(Color.GRAY);

        JButton btnfind = createToolBarButton("", "find.png");
        btnfind.setPreferredSize(new Dimension(50, 50));

        // Tạo JTable NhanVienPanel
        listNV = new NhanVienBUS().getNVAll();
        String[] columnNV = {"Mã nhân viên", "Họ nhân viên", "Tên nhân viên", "Giới tính", "Số điện thoại", "Ngày sinh", "Trạng thái"};
        dataNV = new DefaultTableModel(columnNV, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Chặn chỉnh sửa tất cả các ô
            }
        };

        tableNV = new JTable(dataNV);
        tableNV.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Thêm dữ liệu từ bảng nhân viên vào Frame
        for (NhanVienDTO nv : listNV) {
            String gioitinh = (nv.getGioitinh() == 1) ? "Nam" : "Nữ";
            String trangthai = (nv.getTrangthai() == 1) ? "Hoạt động" : "Khóa";
            dataNV.addRow(new Object[]{nv.getManv(), nv.getHonv(), nv.getTennv(), gioitinh,
                    nv.getSdt(), nv.getNgaysinh(), trangthai});
        }

        // Căn giữa dữ liệu trong Table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        int[] columnsToCenter = {0, 1, 2, 3, 4, 5, 6};
        for (int col : columnsToCenter) {
            tableNV.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }

        // Điều chỉnh kích thước width và height của các cột tableNV
        tableNV.setRowHeight(30);
        tableNV.getTableHeader().setReorderingAllowed(false);
        tableNV.getColumnModel().getColumn(0).setPreferredWidth(30);
        tableNV.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableNV.getColumnModel().getColumn(2).setPreferredWidth(150);
        tableNV.getColumnModel().getColumn(3).setPreferredWidth(30);
        tableNV.getColumnModel().getColumn(4).setPreferredWidth(200);
        tableNV.getColumnModel().getColumn(5).setPreferredWidth(100);
        tableNV.getColumnModel().getColumn(6).setPreferredWidth(30);

        // Tạo ScrollPane cho Table để tên column hiện
        JScrollPane SPNV = new JScrollPane(tableNV);

        // Thêm các nút vào toolBar_Left
        toolBar_Left.add(btnAdd);
        toolBar_Left.add(btnUpdate);
        toolBar_Left.add(btndelete);
        toolBar_Left.add(btndetail);
        toolBar_Left.add(btnexport);

        // Thêm các thành phần vào toolBar_Right
        toolBar_Right.add(cbbox);
        toolBar_Right.add(txtFind);
        toolBar_Right.add(btnfind);

        // Thêm toolBar_Left và toolBar_Right vào toolBar
        toolBar.add(toolBar_Left);
        toolBar.add(Box.createHorizontalGlue()); // Thêm khoảng trống để đẩy toolBar_Right sang phải
        toolBar.add(toolBar_Right);

        // Thiết lập layout cho NhanVienPanel
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(SPNV, BorderLayout.CENTER);

        // Gắn event vào Table để hiện lên bản chỉnh sửa
        tableNV.getSelectionModel().addListSelectionListener(new NhanVienController(this, workFrame));
        // Thêm sự kiện cho nút
        ActionListener action = new NhanVienController(this, workFrame);
        btnAdd.addActionListener(action);
        btnUpdate.addActionListener(action);
        btndelete.addActionListener(action);
        btndetail.addActionListener(action);
        btnexport.addActionListener(action);
        cbbox.addActionListener(action);

        // Thêm sự kiện DocumentListener
        DocumentListener document = new NhanVienController(this, workFrame);
        txtFind.getDocument().addDocumentListener(document);
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
        return tableNV;
    }

    public JTextField getTxtFind() {
        return txtFind;
    }

    public JComboBox<String> getCbbox() {
        return cbbox;
    }

    public ArrayList<NhanVienDTO> getListNV() {
        return listNV;
    }

    public void refreshTableData() {
        listNV = new NhanVienBUS().getNVAll();
        dataNV.setRowCount(0);
        for (NhanVienDTO nv : listNV) {
            String gioitinh = (nv.getGioitinh() == 1) ? "Nam" : "Nữ";
            String trangthai = (nv.getTrangthai() == 1) ? "Hoạt động" : "Khóa";
            dataNV.addRow(new Object[]{
                    nv.getManv(),
                    nv.getHonv(),
                    nv.getTennv(),
                    gioitinh,
                    nv.getSdt(),
                    nv.getNgaysinh(),trangthai
            });
        }
    }

    public void FindNhanVien(String text,String choice_cbb) {
         if (choice_cbb.equals("Tất cả") || choice_cbb.equals("Tên khách hàng"))
            listNV=new NhanVienBUS().search(text);
        else if( choice_cbb.equals("Họ tên")){
            listNV = new NhanVienBUS().search(text);
        }
        else if(choice_cbb.equals("Số điện thoại")){
            listNV=new NhanVienBUS().searchSDTNv(text);
        }
        dataNV.setRowCount(0);
        for (NhanVienDTO nv : listNV) {
            String gioitinh = (nv.getGioitinh() == 1) ? "Nam" : "Nữ";
            String trangthai = (nv.getTrangthai() == 1) ? "Hoạt động" : "Khóa";
            dataNV.addRow(new Object[]{nv.getManv(), nv.getHonv(), nv.getTennv(), gioitinh,
                    nv.getSdt(), nv.getNgaysinh(), trangthai});
        }
        
    }

}