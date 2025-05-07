/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.View;
import BUS.PhanQuyenBUS;
import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import GUI.Controller.TaiKhoanController;
import GUI.WorkFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
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
public class TaiKhoanPanel extends JPanel{
    private DefaultTableModel tableTaiKhoan;
    private JTable table;
    private JTextField txfind;
    private Boolean checkTimkiem=false;
    private WorkFrame workFrame;
    public PhanQuyenBUS pqBUS;
    
    
    public TaiKhoanPanel(){
        JPanel toolBar= new JPanel(new GridLayout(1,2));
        
        JPanel toolBar_Left=new JPanel(new FlowLayout(FlowLayout.LEFT,10,20));
        JPanel toolBar_Right=new JPanel(new FlowLayout(FlowLayout.RIGHT,10,30));
        Font font=new Font("Arial", Font.BOLD, 16);

        // Tạo các nút CRUD cho JPanel toolBar_Left
        JButton btnAdd= createToolBarButton("Thêm", "insert1.png");
        JButton btnUpdate= createToolBarButton("Sửa", "update1.png");
        JButton btndelete= createToolBarButton("Xóa", "trash.png");
//        JButton btndetail= createToolBarButton("Chi tiết", "detail1.png");
        btnAdd.setFont(font);
        //goi ham de thuc thi viec them
        TaiKhoanController controller = new TaiKhoanController(this, workFrame);
        btnAdd.addActionListener(controller);
        btnUpdate.addActionListener(controller);
        btndelete.addActionListener(controller);



        btnUpdate.setFont(font);
        btndelete.setFont(font);
//        btndetail.setFont(font);
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
        String[] columnTaiKhoan ={"Mã nhân viên","Tên đăng nhập","Mật khẩu","Mã nhóm quyền","Trạng thái"};
        tableTaiKhoan = new DefaultTableModel(columnTaiKhoan, 0){
        @Override
        public boolean isCellEditable(int row, int column){
             return false;// chặn chỉnh sửa các ô
        
        
        }}; 
        table = new JTable(tableTaiKhoan);
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setForeground(Color.BLACK); // Màu chữ đen
        //them du lieu vao bang GUI
        
        pqBUS= new PhanQuyenBUS();
        ArrayList<TaiKhoanDTO> listTaiKhoan= new TaiKhoanBUS().getTaiKhoanAll();
        for(TaiKhoanDTO tk: listTaiKhoan){
            String trangthai="";
            if(tk.getTrangthai()==0){
                trangthai="Khóa";
            }else{
                trangthai="Đang hoạt động";
            }   
            
            String tenquyen;
            tenquyen=pqBUS.getTenquyenbyid(tk.getManhomquyen());
            
            
            tableTaiKhoan.addRow(new Object[]{tk.getManv(),tk.getUsername(),tk.getMatkhau(),tenquyen,trangthai});
            
        }
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        int[] columnsToCenter = {0,1,2,3,4}; 
 
        for (int col : columnsToCenter) {
            table.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }
        // Điều chỉnh kích thước width và hieght của các cột tableBook 
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        
        
           
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

        ArrayList<TaiKhoanDTO> danhsachmoi;
        if (keyword.isEmpty() || keyword.equals("tìm kiếm.....")) {
                    danhsachmoi = new TaiKhoanBUS().getTaiKhoanAll();
        } else {
                    danhsachmoi = new TaiKhoanBUS().timkiem(keyword);
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
    
   public void capNhatBang(ArrayList<TaiKhoanDTO> danhSach) {
    tableTaiKhoan.setRowCount(0); // Xóa bảng cũ
    
    pqBUS= new PhanQuyenBUS();
    ArrayList<TaiKhoanDTO> listTaiKhoan= new TaiKhoanBUS().getTaiKhoanAll();
        for(TaiKhoanDTO tk: listTaiKhoan){
            String trangthai="";
            if(tk.getTrangthai()==0){
                trangthai="Khóa";
            }else{
                trangthai="Đang hoạt động";
            }   
            
            String tenquyen;
            tenquyen=pqBUS.getTenquyenbyid(tk.getManhomquyen());
            
            
            tableTaiKhoan.addRow(new Object[]{tk.getManv(),tk.getUsername(),tk.getMatkhau(),tenquyen,trangthai});
    }
}

//load lai du lieu khi moi them vo
    public void loadData() {
        ArrayList<TaiKhoanDTO> listTaiKhoan= new TaiKhoanBUS().getTaiKhoanAll();
        
        capNhatBang(listTaiKhoan); // Cập nhật lại bảng
}
    
    //hàm kiểm tra coi dòng trong bảng có được click chọn hay không để sửa
    public int getSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản để sửa!");
        }
        return selectedRow;
    }
    
    
    //lấy đối tượng nhóm quyền đang được click để hàm update biết
    public TaiKhoanDTO getSelectedTaiKhoan() {
        int selectedRow = table.getSelectedRow(); // Lấy chỉ số hàng đang chọn
        if (selectedRow == -1) return null; // Nếu không chọn gì, trả về null

        int maNV = (int) table.getValueAt(selectedRow, 0); // Lấy mã nhóm quyền
        String username = (String) table.getValueAt(selectedRow, 1); // Lấy tên nhóm quyền
        String mk = (String) table.getValueAt(selectedRow,2);
        String tenQuyen = (String) table.getValueAt(selectedRow, 3);
        int manhomquyen = pqBUS.getIdquyenbyTen(tenQuyen);
        String trangthaistring= (String) table.getValueAt(selectedRow, 4);
        if(trangthaistring.equals("Đang hoạt động")){
            int trangthai=1;
            return new TaiKhoanDTO(maNV, username,mk,manhomquyen,trangthai); // Tạo đối tượng NhomQuyenDTO
        }else{
            int trangthai=0;
            return new TaiKhoanDTO(maNV, username,mk,manhomquyen,trangthai); // Tạo đối tượng NhomQuyenDTO
        }
        

        
}


        
}
    

