package GUI.View;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import GUI.Dialog.KhachHangDialog.GuiSupport;
import GUI.Dialog.KhachHangDialog.KhachHangDialogAdd;
import GUI.Dialog.KhachHangDialog.KhachHangDialogDelete;
import GUI.Dialog.KhachHangDialog.KhachHangDialogDetail;
import GUI.Dialog.KhachHangDialog.KhachHangDialogUpdate;
import GUI.WorkFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hslf.usermodel.HSLFFontInfo;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class KhachhangPanel extends JPanel  {
    private WorkFrame workFrame;
    private JTable tablekh;
    private DefaultTableModel datakh;
    private JTextField txfFind;
    private JComboBox<String> cbbox;
    private ArrayList<KhachHangDTO> listkh=new KhachHangBUS().getKhachHangAll();

    public KhachhangPanel() {
        // Tạo Panel toolBar cho thanh công cụ trên cùng
        JPanel toolBar = new JPanel();
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS)); // Sử dụng BoxLayout giống BookPanel
        JPanel toolBar_Left = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
        JPanel toolBar_Right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 30));
        Font font = new Font("Arial", Font.BOLD, 16);

        // Tạo các nút CRUD cho JPanel toolBar_Left
        JButton btnAdd = new GuiSupport().createToolBarButton("Thêm", "insert1.png");
        JButton btnUpdate = new GuiSupport().createToolBarButton("Sửa", "update1.png");
        JButton btndelete = new GuiSupport().createToolBarButton("Xóa", "trash.png");
        JButton btndetail = new GuiSupport().createToolBarButton("Chi tiết", "detail1.png");
        JButton btnExport = new GuiSupport().createToolBarButton("Xuất Excel", "export_excel.png");
        btnAdd.setFont(font);
        btnUpdate.setFont(font);
        btndelete.setFont(font);
        btndetail.setFont(font);
        btnExport.setFont(font);

        // Tạo phần tìm kiếm cho JPanel toolBar_Right
        String[] List_Combobox = {"Tất cả", "Mã khách hàng","Họ khách hàng","Tên khách hàng","Email","SĐT"};
        cbbox = new JComboBox<String>(List_Combobox);
        cbbox.setPreferredSize(new Dimension(150, 35));

        txfFind = new JTextField("");
        txfFind.setPreferredSize(new Dimension(200, 35));
        txfFind.setForeground(Color.GRAY);

        JButton btnfind = new GuiSupport().createToolBarButton("", "find.png");
        btnfind.setPreferredSize(new Dimension(50, 50));

        // Tạo JTable cho KhachhangPanel
        
        String[] columnkh = {"Mã", "Họ", "Tên", "Email", "Ngày sinh", "Số Điện Thoại"};
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

        // SỰ KIÊN >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
       
        // Thêm sự kiện cho nút
        btnAdd.addActionListener((ActionEvent e) -> {
            them();
        });
        
        btnUpdate.addActionListener((ActionEvent e) -> {
            sua();
        });
        
        btndelete.addActionListener((ActionEvent e) -> {
            xoa();
        });
        
        btndetail.addActionListener((ActionEvent e) -> {
            chitiet();
        });
        
        btnExport.addActionListener((ActionEvent e) -> {
            xcel();
        });
        
        // Thêm sự kiện DocumentListener
        
        txfFind.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                FindKhach(txfFind.getText(),cbbox.getSelectedItem().toString());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                FindKhach(txfFind.getText(),cbbox.getSelectedItem().toString());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                FindKhach(txfFind.getText(),cbbox.getSelectedItem().toString());
            }
        });
    }

    public void them(){
        new KhachHangDialogAdd(workFrame);
            refreshTableData();
    }
   
    public void sua(){
        if(tablekh.getSelectedRow()<0){
            JOptionPane.showMessageDialog(null, "Hãy chọn một khách hàng !", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for(KhachHangDTO i: listkh )
            if(i.getMakh()==(int) tablekh.getValueAt(tablekh.getSelectedRow(), 0)){
                new KhachHangDialogUpdate(workFrame,i);                 
                refreshTableData();
                break;
            }
    }
    
    public void xoa(){
        if (tablekh.getSelectedRow()<0){
                JOptionPane.showMessageDialog(null, "Hãy chọn một khách hàng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
        new KhachHangDialogDelete(workFrame,tablekh.getValueAt(tablekh.getSelectedRow(), 0).toString());
        refreshTableData();            
    }
    
    public void chitiet(){
        if (tablekh.getSelectedRow() < 0) { 
            JOptionPane.showMessageDialog(null, "Hãy chọn một Khách hàng ! ","Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for(KhachHangDTO i: listkh )
            if(i.getMakh()==(int) tablekh.getValueAt(tablekh.getSelectedRow(), 0)){
                new KhachHangDialogDetail(workFrame,i);
                break;
            }
    }

    public void refreshTableData() {
        listkh = new KhachHangBUS().getKhachHangAll();
        datakh.setRowCount(0);
        for (KhachHangDTO kh : listkh) {
            datakh.addRow(new Object[]{kh.getMakh(),kh.getHokh(),kh.getTenkh(),kh.getemail(),kh.getNgaysinh(),kh.getSdt()});
        }
    }
 
    public void FindKhach(String text,String choice_cbb) { 
        if (choice_cbb.equals("Tất cả")){
            // "Tất cả", "Mã khách hàng","Họ khách hàng","Tên khách hàng","Email","SĐT"
        }           
        else if(choice_cbb.equals("Họ khách hàng")){
            listkh=new KhachHangBUS().searchName(text);
        }
        else if(choice_cbb.equals("Tên khách hàng")){
            listkh=new KhachHangBUS().searchName(text);
        }
        else if( choice_cbb.equals("Mã khách hàng")){
            listkh = new KhachHangBUS().search(text);
        }
        else if(choice_cbb.equals("Email")){
            listkh=new KhachHangBUS().searchEmail(text);
        }
        else if(choice_cbb.equals("SĐT")){
            listkh=new KhachHangBUS().searchSDTKh(text);
        }
        datakh.setRowCount(0);
        for (KhachHangDTO kh : listkh) {
            datakh.addRow(new Object[]{kh.getMakh(),kh.getHokh(),kh.getTenkh(),kh.getemail(),kh.getNgaysinh(),kh.getSdt()});
        }
    }
    
    public void xcel(){
        try {
            ExportExcel();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Lỗi đọc file Excel: " + ex.getMessage());
        }
    }
    
    public void ExportExcel() throws Exception{
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setDialogTitle("Xuất Excel khách hàng");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")+"/Documents"));
        fileChooser.setFileFilter(new FileFilter(){
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".xlsx");
            }

            @Override
            public String getDescription() {
                return "Excel Files (*.xlsx)";
            }
        });
        String defaultFileName= "KhachHang.xlsx";
        fileChooser.setSelectedFile(new File(defaultFileName));
        
        int userchosser=fileChooser.showDialog(this,"Save");
        if(userchosser!=fileChooser.APPROVE_OPTION){
            return;
        }
        File fileToSave=fileChooser.getSelectedFile();
        String filePath=fileToSave.getAbsolutePath();
        if (!filePath.toLowerCase().endsWith(".xlsx")){
            filePath+=".xlsx";
        }       
        XSSFWorkbook wb=new XSSFWorkbook();
        XSSFSheet sheet=wb.createSheet("Khách hàng");
        JTable tableB = tablekh;
        XSSFRow headerRow=sheet.createRow(1);
        String[] header={"Mã","Họ","Tên","Email","Ngày sinh","Số điện thoại"};
        for (int i=0;i<header.length;i++){
            XSSFCell cell1=headerRow.createCell(i);
            cell1.setCellValue(header[i]);
        }
        int numrowdata=2;
        for (int i=0; i<tableB.getRowCount();i++){
            XSSFRow rowdata=sheet.createRow(numrowdata);
            XSSFCell cell1=rowdata.createCell(0);
            cell1.setCellValue(tableB.getValueAt(i, 0).toString());
            
            XSSFCell cell2=rowdata.createCell(1);
            cell2.setCellValue(tableB.getValueAt(i, 1).toString());
            
            XSSFCell cell3=rowdata.createCell(2);
            cell3.setCellValue(tableB.getValueAt(i, 2).toString());
            
            XSSFCell cell4=rowdata.createCell(3);
            cell4.setCellValue(tableB.getValueAt(i, 3).toString());
            
            XSSFCell cell5=rowdata.createCell(4);
            cell5.setCellValue(tableB.getValueAt(i, 4).toString());
            
            XSSFCell cell6=rowdata.createCell(5);
            cell6.setCellValue(tableB.getValueAt(i, 5).toString());
            numrowdata+=1;
        }
        for (int i=0;i<header.length;i++){
            sheet.autoSizeColumn(i);
        }
        
        
        try(FileOutputStream fileOut=new FileOutputStream(filePath)) {
                wb.write(fileOut);
                 JOptionPane.showMessageDialog(this,
                    "Xuất file Excel thành công!\nĐường dẫn: ",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(new File(filePath));// Mở file sau khi thêm
                    }
                if(Desktop.isDesktopSupported()){
                    Desktop.getDesktop().open(new File(filePath));// Mở file bằng đường dẫn sau khi thêm
            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Lỗi khi xuất file Excel: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            }finally {
            wb.close();
        }
    }
}