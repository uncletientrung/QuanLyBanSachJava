/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
/**
 *
 * @author Minnie
 */
public class NhanVienPanel extends JPanel{
    private final JTable tableNV;
    private WorkFrame workFrame;
    private NhanVienBUS nvBUS;
    private ArrayList<NhanVienDTO> listNV;
    private final DefaultTableModel dataNV;
    private JTextField txtFind;
    private JComboBox<String> cbbox;
            
    public NhanVienPanel(){
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
        btnAdd.setFont(font);btnUpdate.setFont(font);btndelete.setFont(font);btndetail.setFont(font);

        // Tạo phần tìm kiếm cho JPanel toolBar_Right
        String[] List_Combobox={"Tất cả","Theo tên từ A -> Z","Theo tuổi cao đến thấp ⬇"};
        cbbox=new JComboBox<String>(List_Combobox);
        cbbox.setPreferredSize(new Dimension(150,35));

        txtFind=new JTextField("Tìm kiếm.....");
        txtFind.setPreferredSize(new Dimension(200,35));
        txtFind.setForeground(Color.GRAY);

        JButton btnfind=createToolBarButton("", "find.png");
        btnfind.setPreferredSize(new Dimension(50,50));
        
        // Tạo JTable NhanVienPanel
        listNV = new NhanVienBUS().getNVAll();
        String[] columnNV ={"Mã nhân viên","Họ nhân viên","Tên nhân viên","Giới tính","Số điện thoại","Ngày sinh","Trạng thái"};
        dataNV = new DefaultTableModel(columnNV,0){
             @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Chặn chỉnh sửa tất cả các ô
            }
        };
                
        tableNV = new JTable(dataNV);
        tableNV.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Thêm dữ liệu từ bảng nhân viên vào Frame
        for(NhanVienDTO nv: listNV){
            String gioitinh = (nv.getGioitinh() == 1) ? "Nam" : "Nữ";
            String trangthai = (nv.getTrangthai() == 1) ? "Hoạt động" : "Khóa";
            dataNV.addRow(new Object[]{nv.getManv(),nv.getHonv(),nv.getTennv(),gioitinh,
                nv.getSdt(),nv.getNgaysinh(),trangthai});
        }
        
        // Căn giữa dữ liệu trong Table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        int[] columnsToCenter = {0,1,2,3,4,5,6}; 
        for (int col : columnsToCenter){
            tableNV.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }
        
        // Điều chỉnh kích thước width và hieght của các cột tableBook 
        tableNV.setRowHeight(30);
        tableNV.getTableHeader().setReorderingAllowed(false);
        tableNV.getColumnModel().getColumn(0).setPreferredWidth(30);
        tableNV.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableNV.getColumnModel().getColumn(2).setPreferredWidth(150);
        tableNV.getColumnModel().getColumn(3).setPreferredWidth(30);
        tableNV.getColumnModel().getColumn(4).setPreferredWidth(200);
        tableNV.getColumnModel().getColumn(5).setPreferredWidth(100);
        tableNV.getColumnModel().getColumn(6).setPreferredWidth(30);
        
        //Tạo ScrollPane cho Table để tên column hiện
        JScrollPane SPNV= new JScrollPane(tableNV);

        
        toolBar_Left.add(btnAdd);
        toolBar_Left.add(btnUpdate);
        toolBar_Left.add(btndelete);
        toolBar_Left.add(btndetail);

        toolBar_Right.add(cbbox);
        toolBar_Right.add(txtFind);
        toolBar_Right.add(btnfind);

        toolBar.add(toolBar_Left);
        toolBar.add(toolBar_Right);

        setLayout(new BorderLayout());
        add(toolBar,BorderLayout.NORTH);
        add(SPNV,BorderLayout.CENTER);
        
        //Gắn event vào Table để hiện lên bản chỉnh sửa
        tableNV.getSelectionModel().addListSelectionListener(new NhanVienController(this,workFrame));
        //Thêm sự kiện cho nút
        ActionListener action= new NhanVienController(this,workFrame);
        btnAdd.addActionListener(action);
        btnUpdate.addActionListener(action);
        btndelete.addActionListener(action);
        btndetail.addActionListener(action);
        cbbox.addActionListener(action);
        
        // Thêm sự kiện DocumentListener
        DocumentListener document=new NhanVienController(this, workFrame);
        txtFind.getDocument().addDocumentListener(document);
        
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
        return tableNV;
    }
    
    public JTextField getTxtFind(){
        return txtFind;
    }
    
    public JComboBox<String> getCbbox(){
        return cbbox;
    }
    
    public ArrayList<NhanVienDTO> getListNV(){
        return listNV;
    }
    
    public void refreshTableData(){
        listNV = new NhanVienBUS().getNVAll();
        dataNV.setRowCount(0);
        for(NhanVienDTO nv : listNV){
            String gioitinh = (nv.getGioitinh() == 1) ? "Nam" : "Nữ";
            String trangthai = (nv.getTrangthai() == 1) ? "Hoạt động" : "Khóa";
            dataNV.addRow(new Object[]{
                nv.getManv(),
                nv.getHonv(),
                nv.getTennv(),
                gioitinh,
                nv.getSdt(),
                nv.getNgaysinh(),
                trangthai
            });
        }
    }
    
    public void FindTableData(String text){
        listNV = new NhanVienBUS().search(text);
        dataNV.setRowCount(0);
        for(NhanVienDTO nv : listNV){
            dataNV.addRow(new Object[]{
                nv.getManv(),
                nv.getHonv(),
                nv.getTennv(),
                nv.getGioitinh(),
                nv.getSdt(),
                nv.getNgaysinh(),
                nv.getTrangthai()
            });
        }
    }
    
    public void FilterTableData(ArrayList<NhanVienDTO> list_Sort){
        dataNV.setRowCount(0);
        for(NhanVienDTO nv : listNV){
            String gioitinh = (nv.getGioitinh() == 1) ? "Nam" : "Nữ";
            String trangthai = (nv.getTrangthai() == 1) ? "Hoạt động" : "Khóa";
            dataNV.addRow(new Object[]{
                nv.getManv(),
                nv.getHonv(),
                nv.getTennv(),
                gioitinh,
                nv.getSdt(),
                nv.getNgaysinh(),
                trangthai
            });
        }
    }
}

