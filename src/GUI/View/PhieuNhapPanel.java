/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.View;
import BUS.*;
import DTO.NhaXuatBanDTO;
import DTO.PhieuNhapDTO;
import DTO.SachDTO;
import DTO.TacGiaDTO;
import DTO.TheLoaiDTO;
import GUI.Controller.PhieuNhapController;
import GUI.Dialog.PhieuNhapDialog.AddPanel;
import GUI.Format.DateFormat;
import GUI.Format.NumberFormatter;
import GUI.WorkFrame;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Minnie
 */
public class PhieuNhapPanel extends JPanel{
    private ArrayList<PhieuNhapDTO> listpn;
    private PhieuNhapBUS pnBUS;
    private JTextField txfFind;
    private JComboBox<String> cbbox;
    private DefaultTableModel dataPhieuNhap;
    private JTable tablePhieuNhap;
    private WorkFrame wf;
    private NhanVienBUS nvBUS;
    private NhaCungCapBUS nccBUS;
    private JPanel PanelCenter;
    private JScrollPane SPPhieuNhap;
    private JTabbedPane tabbedPane;
    private JButton btnHome;
    private JButton btnAdd;
    private JButton btndelete;
    private JButton btndetail;
    private JButton btnexport;
    private JPanel toolBar_Right;
    private JButton btnfind;
            
            
    public PhieuNhapPanel(){
        // Tạo Panel toolBar cho thanh công cụ trên cùng
        JPanel toolBar = new JPanel(new GridLayout(1, 2));
        JPanel toolBar_Left = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
        toolBar_Right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 30));
        Font font = new Font("Arial", Font.BOLD, 16);

        // Tạo các nút CRUD cho JPanel toolBar_Left
        btnHome = createToolBarButton("Trang chủ", "home.png");
        btnAdd = createToolBarButton("Thêm", "insert1.png");
        btndelete = createToolBarButton("Hủy bỏ", "delete.png");
        btndetail = createToolBarButton("Chi tiết", "detail1.png");
        btnexport = createToolBarButton("Xuất Excel", "export_excel.png");
        btnHome.setFont(font);
        btnAdd.setFont(font);
        btndelete.setFont(font);
        btndetail.setFont(font);
        btnexport.setFont(font);
        
        // Tạo phần tìm kiếm cho JPanel toolBar_Right
        String[] List_Combobox = {"Tất cả", "Hóa đơn thấp đến cao ⬆", "Hóa đơn cao đến thấp ⬇", "Ngày tạo thấp đến cao ⬆", "Ngày tạo cao đến thấp ⬇"};
        cbbox = new JComboBox<String>(List_Combobox);
        cbbox.setPreferredSize(new Dimension(150, 35));
        
        txfFind = new JTextField("");
        txfFind.setPreferredSize(new Dimension(200, 35));
        txfFind.setForeground(Color.GRAY);
        
        btnfind = createToolBarButton("", "find.png");
        btnfind.setPreferredSize(new Dimension(50, 50));
        
        //Tạo JTable cho PhieuNhapPanel
        listpn = new PhieuNhapBUS().getAll();
        String[] columnPhieuNhap = {"Mã phiếu nhập","Nhân viên","Nhà cung cấp","Thời gian","Tổng tiền"};
        dataPhieuNhap = new DefaultTableModel(columnPhieuNhap,0){
            //Hàm không cho chỉnh sửa ô
            @Override
            public boolean isCellEditable(int row, int column){
                return false; 
            }
        };
        tablePhieuNhap = new JTable(dataPhieuNhap);
        tablePhieuNhap.getTableHeader().setBackground(Color.LIGHT_GRAY);
        tablePhieuNhap.getTableHeader().setForeground(Color.BLACK);
        tablePhieuNhap.getTableHeader().setReorderingAllowed(false); //Không cho kéo cột
        tablePhieuNhap.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Không cho chọn nhiều hàng cùng lúc
        
        nvBUS = new NhanVienBUS();
        nccBUS = new NhaCungCapBUS();
        for (PhieuNhapDTO pn: listpn){
            String nv = nvBUS.getHoTenNVById(pn.getManv());
            String ncc = nccBUS.getTenNCC(pn.getMancc());
            if (pn.getTrangthai() == 1) 
            dataPhieuNhap.addRow(new Object[]{pn.getMaphieu(),nv,ncc,DateFormat.fomat(pn.getThoigiantao().toString()), 
                    NumberFormatter.format(pn.getTongTien())});
        }
        //Tạo renderer để căn giữa dữ liệu
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        int[] columnsToCenter = {0,1,2,3,4};
        for (int col: columnsToCenter){
            tablePhieuNhap.getColumnModel().getColumn(col).setCellRenderer(center);
        }
        
        //Set size cho các cột
        tablePhieuNhap.setRowHeight(30);
        tablePhieuNhap.getColumnModel().getColumn(0).setPreferredWidth(30);
        tablePhieuNhap.getColumnModel().getColumn(1).setPreferredWidth(150);
        tablePhieuNhap.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablePhieuNhap.getColumnModel().getColumn(3).setPreferredWidth(165);
        tablePhieuNhap.getColumnModel().getColumn(4).setPreferredWidth(65);
         // Tạo ScrollPane cho Table để tên cột column hiện
        SPPhieuNhap= new JScrollPane(tablePhieuNhap);
        
        toolBar_Left.add(btnHome);
        toolBar_Left.add(btnAdd);
        toolBar_Left.add(btndelete);
        toolBar_Left.add(btndetail);
        toolBar_Left.add(btnexport);

        toolBar_Right.add(cbbox);
        toolBar_Right.add(txfFind);
        toolBar_Right.add(btnfind);

        toolBar.add(toolBar_Left);
        toolBar.add(toolBar_Right);
        PanelCenter = new JPanel();
        PanelCenter.setLayout(new GridLayout(1,1));
        PanelCenter.add(SPPhieuNhap);

        setLayout(new BorderLayout());
        add(toolBar,BorderLayout.NORTH);
        add(PanelCenter,BorderLayout.CENTER);
        
        ActionListener action=new PhieuNhapController(this, wf);
        btnAdd.addActionListener(action);
        btndetail.addActionListener(action);
        btndelete.addActionListener(action);
        btnexport.addActionListener(action);
        btnHome.addActionListener(action);
        cbbox.addActionListener(action);
        
        tabbedPane = new JTabbedPane();
        tabbedPane.addChangeListener((ChangeListener)action);
        
        // Thêm document Listener vào txf find
        txfFind.getDocument().addDocumentListener((DocumentListener) action);
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
   
   public void refreshTablePn(){
        listpn = new PhieuNhapBUS().getAll();
        dataPhieuNhap.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        nvBUS = new NhanVienBUS();
        nccBUS = new NhaCungCapBUS();
        for (PhieuNhapDTO pn : listpn) {
            String hoTenNV = nvBUS.getHoTenNVById(pn.getManv());
            String hoTenNCC = nccBUS.getTenNCC(pn.getMancc());
            if (pn.getTrangthai() == 1 )
            // Thay đổi dữ liệu trong bảng
              dataPhieuNhap.addRow(new Object[]{pn.getMaphieu(), hoTenNV, hoTenNCC, DateFormat.fomat(pn.getThoigiantao().toString()), 
                    NumberFormatter.format(pn.getTongTien())});
        }
   }
   
   public void FindTableData(String text) {
        if(txfFind.getText().equals("") && cbbox.getSelectedIndex()==0){
            refreshTablePn();
            return;
        }
        listpn = new PhieuNhapBUS().search(text);
        dataPhieuNhap.setRowCount(0);
        for (PhieuNhapDTO pn : listpn) {
            String hoTenNV = nvBUS.getHoTenNVById(pn.getManv());
            String hoTenNCC = nccBUS.getTenNCC(pn.getMancc());
            if (pn.getTrangthai() == 1 )
            // Thay đổi dữ liệu trong bảng
              dataPhieuNhap.addRow(new Object[]{pn.getMaphieu(), hoTenNV, hoTenNCC, DateFormat.fomat(pn.getThoigiantao().toString()), 
                    NumberFormatter.format(pn.getTongTien())});
        }
    }
   
   public void FilterTableData(ArrayList<PhieuNhapDTO> list_Sort) {
        dataPhieuNhap.setRowCount(0);
        for (PhieuNhapDTO pn : listpn) {
            String hoTenNV = nvBUS.getHoTenNVById(pn.getManv());
            String hoTenNCC = nccBUS.getTenNCC(pn.getMancc());
            if (pn.getTrangthai() == 1 )
            // Thay đổi dữ liệu trong bảng
              dataPhieuNhap.addRow(new Object[]{pn.getMaphieu(), hoTenNV, hoTenNCC, DateFormat.fomat(pn.getThoigiantao().toString()), 
                    NumberFormatter.format(pn.getTongTien())});
        }
    }

    public JPanel getToolBar_Righ() {
        return toolBar_Right;
    }

    public JButton getBtnfind() {
        return btnfind;
    }
    
    public JTable getTablePhieuNhap(){
         return tablePhieuNhap;
    }
    
    public JScrollPane getScrollPanePhieuNhap(){
        return SPPhieuNhap;
    }
    
    public JPanel getPanelCenter(){
        return PanelCenter;
    }

    public void setPanelCenter(JPanel panel){
        this.PanelCenter = panel;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    } 

    public void setSPPhieuNhap(JScrollPane sp){
        this.SPPhieuNhap = sp;
    }

    public JTextField getTxfFind() {
        return txfFind;
    }
    

    public JComboBox<String> getCbbox() {
        return cbbox;
    }

    public ArrayList<PhieuNhapDTO> getListpn() {
        return listpn;
    }
    

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnHome() {
        return btnHome;
    }

    public JButton getBtnexport() {
        return btnexport;
    }

    public JButton getBtndelete() {
        return btndelete;
    }

    public JButton getBtndetail() {
        return btndetail;
    }
    
}
