/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.SachBUS;
import DTO.SachDTO;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import GUI.View.BookPanel;
import GUI.WorkFrame;
import  GUI.Dialog.BookDialog.BookDialogAdd;
import GUI.Dialog.BookDialog.BookDialogUpdate;
import GUI.Dialog.BookDialog.BookDialogDetail;
import GUI.Dialog.BookDialog.BookDialogDelete;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import BUS.NhaXuatBanBUS;
import DTO.NhaXuatBanDTO;
import DTO.TacGiaDTO;
import BUS.TacGiaBUS;
import DTO.TheLoaiDTO;
import BUS.TheLoaiBUS;
import DAO.SachDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author DELL
 */
public class BookController implements ActionListener,ListSelectionListener,DocumentListener{
    private BookPanel bf;
    private WorkFrame wk;
    private BookDialogUpdate bdu;
    private BookDialogDetail bdd;
    private BookDialogDelete bddelete;
    private SachBUS sachBUS;
    private NhaXuatBanBUS NxbBUS=new NhaXuatBanBUS();
    private TacGiaBUS TgBUS=new TacGiaBUS();
    private TheLoaiBUS TlBUS=new TheLoaiBUS();
    private SachDAO sachDAO;
    public BookController(BookPanel bf,WorkFrame wk){
        this.bf=bf;
        this.wk=wk;
    }
 
    @Override
public void actionPerformed(ActionEvent e){
    String sukien = e.getActionCommand();  // Lắng nghe sự kiện của Button
    String sukienCombobox=(String) bf.getCbbox().getSelectedItem(); // Lắng nghe sự kiện khi chọn Combobox
    if (sukien.equals("Thêm")) {
        new BookDialogAdd(wk);
    } 
    if (sukien.equals("Sửa")) {
        JTable tableB = bf.getTable();
        int selectRow = tableB.getSelectedRow();

        if (selectRow >= 0) {  // Kiểm tra xem có hàng nào đang được chọn không
            String maSach = tableB.getValueAt(selectRow, 0).toString();
            String tenSach = tableB.getValueAt(selectRow, 1).toString();
            String maNxb = tableB.getValueAt(selectRow, 2).toString();
            String maTacgia = tableB.getValueAt(selectRow, 3).toString();
            String matheloai = tableB.getValueAt(selectRow, 4).toString();
            String soluongton = tableB.getValueAt(selectRow, 5).toString();
            String namxuatban = tableB.getValueAt(selectRow, 6).toString();
            String dongia = tableB.getValueAt(selectRow, 7).toString();

            // Mở hộp thoại sửa và hiển thị thông tin
            bdu = new BookDialogUpdate(wk);
            bdu.ShowInfo(maSach, tenSach, maNxb, maTacgia, matheloai, soluongton, namxuatban, dongia); // Cập nhật dữ liệu trước
            bdu.setVisible(true); // Hiển thị hộp thoại sau
            
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn một cuốn sách!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    if (sukien.equals("Xóa")){
        JTable tableB = bf.getTable();
        sachBUS=new SachBUS();
        int selectRow = tableB.getSelectedRow();
        
        if (selectRow==-1){
            JOptionPane.showMessageDialog(null, "Hãy chọn một cuốn sách!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }else{
            String maSach = tableB.getValueAt(selectRow, 0).toString();
            bddelete=new BookDialogDelete(wk,sachBUS.getSachById(maSach));
        }
    }
    if(sukien.equals("Chi tiết")){
        JTable tableB = bf.getTable();
        int selectRow = tableB.getSelectedRow();
        if (selectRow >= 0) {  // Kiểm tra xem có hàng nào đang được chọn không
            String maSach = tableB.getValueAt(selectRow, 0).toString();
            String tenSach = tableB.getValueAt(selectRow, 1).toString();
            String maNxb = tableB.getValueAt(selectRow, 2).toString();
            String maTacgia = tableB.getValueAt(selectRow, 3).toString();
            String matheloai = tableB.getValueAt(selectRow, 4).toString();
            String soluongton = tableB.getValueAt(selectRow, 5).toString();
            String namxuatban = tableB.getValueAt(selectRow, 6).toString();
            String dongia = tableB.getValueAt(selectRow, 7).toString();
            
            // Tìm mã nhà xuất bản từ tên nhà xuất bản để in lên chi tiết
            NhaXuatBanDTO NxbFind= NxbBUS.getNXBByNameNXB(maNxb);
            maNxb=String.valueOf(NxbFind.getManxb());
            
            // Tìm mã tác giả từ tên tác giả để in lên chi tiết
            TacGiaDTO TgFind=TgBUS.getTgByNameTG(maTacgia);
            maTacgia=String.valueOf(TgFind.getMatacgia());
            // Tìm mã thể loại từ tên thể loại để in lên chi tiết
            TheLoaiDTO TlFind=TlBUS.getTlByNameTL(matheloai);
            matheloai=String.valueOf(TlFind.getMatheloai());
            
            // Mở hộp thoại sửa và hiển thị thông tin
            bdd = new BookDialogDetail(wk);
            bdd.ShowInfo(maSach, tenSach, maNxb, maTacgia, matheloai, soluongton, namxuatban, dongia); // Cập nhật dữ liệu trước
            bdd.setVisible(true); // Hiển thị hộp thoại sau
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn một cuốn sách! ","Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    if(sukien.equals("Xuất Excel")){
            try {
                Export();
            }catch (IOException ex) {
                System.err.println("Lỗi đọc file Excel: " + ex.getMessage());
            }
            
    }
    if(sukien.equals("Nhập Excel")){
        try{
            Import();
        }catch(Exception ex){
            System.err.println("Lỗi đọc file Excel: " + ex.getMessage());
        }
        
    }

    if(sukienCombobox.equals("Giá thấp đến cao ⬆")){
        sachBUS=new SachBUS();
        ArrayList<SachDTO> List_Sort=sachBUS.LowToHighofPrice(bf.getListSach());
        bf.FilterTableData(List_Sort);
    }
    if(sukienCombobox.equals("Giá cao đến thấp ⬇")){
        sachBUS=new SachBUS();
        ArrayList<SachDTO> List_Sort=sachBUS.HighToLowofPrice(bf.getListSach());
        bf.FilterTableData(List_Sort);
    }
    if(sukienCombobox.equals("NXB thấp đến cao ⬆")){
        sachBUS=new SachBUS();
        ArrayList<SachDTO> List_Sort=sachBUS.LowToHighofNXB(bf.getListSach());
        bf.FilterTableData(List_Sort);
    }
    if(sukienCombobox.equals("NXB cao đến thấp ⬇")){
        sachBUS=new SachBUS();
        ArrayList<SachDTO> List_Sort=sachBUS.HighToLowofNXB(bf.getListSach());
        bf.FilterTableData(List_Sort);
    }
    if(sukienCombobox.equals("Tất cả")){
        bf.getTxfFind().setText(""); // Chỉnh sửa txf Find lại thành "" khi ấn vào Tất cả
        bf.refreshTableData();
    }

}

    @Override
    public void valueChanged(ListSelectionEvent e){}// Không tác động đến ListSelectionListener này

    @Override
    public void insertUpdate(DocumentEvent e) {
        bf.FindTableData(bf.getTxfFind().getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        bf.FindTableData(bf.getTxfFind().getText());
        
    }
    

    @Override
    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void Export() throws IOException{
    // Tạo JFileChooser để chọn nơi lưu file
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Documents"));
    fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
        @Override
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().toLowerCase().endsWith(".xlsx");
        }

        @Override
        public String getDescription() {
            return "Excel Files (*.xlsx)";
        }
    });

    // Thiết lập tên file mặc định
    String defaultFileName = "DanhSachSach.xlsx";
    fileChooser.setSelectedFile(new File(defaultFileName));

    // Hiển thị hộp thoại lưu file
    int userSelection = fileChooser.showSaveDialog(bf);

    if (userSelection != JFileChooser.APPROVE_OPTION) {
        return;
    }

    // Lấy đường dẫn file đã chọn
    File fileToSave = fileChooser.getSelectedFile();
    String filePath = fileToSave.getAbsolutePath();

    if (!filePath.toLowerCase().endsWith(".xlsx")) {
        filePath += ".xlsx";
    }

    // Tạo workbook và sheet mới
    XSSFWorkbook wb = new XSSFWorkbook();
    XSSFSheet sheet = wb.createSheet("Danh sách sách");

    // Tạo font cho tiêu đề
    XSSFFont titleFont = wb.createFont();
    titleFont.setBold(true);
    titleFont.setFontHeightInPoints((short) 14);

    // Tạo style cho tiêu đề (nền vàng, căn giữa)
    XSSFCellStyle titleStyle = wb.createCellStyle();
    titleStyle.setFont(titleFont);
    titleStyle.setAlignment(HorizontalAlignment.CENTER);
    titleStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
    titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    // Tạo style cho header bảng (nền vàng, chữ in đậm, căn giữa)
    XSSFFont headerFont = wb.createFont();
    headerFont.setBold(true);
    XSSFCellStyle headerStyle = wb.createCellStyle();
    headerStyle.setFont(headerFont);
    headerStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    headerStyle.setBorderBottom(BorderStyle.THIN);
    headerStyle.setBorderTop(BorderStyle.THIN);
    headerStyle.setBorderLeft(BorderStyle.THIN);
    headerStyle.setBorderRight(BorderStyle.THIN);
    headerStyle.setAlignment(HorizontalAlignment.CENTER);

    // Tạo style cho dữ liệu (căn giữa, có viền)
    XSSFCellStyle dataStyle = wb.createCellStyle();
    dataStyle.setBorderBottom(BorderStyle.THIN);
    dataStyle.setBorderTop(BorderStyle.THIN);
    dataStyle.setBorderLeft(BorderStyle.THIN);
    dataStyle.setBorderRight(BorderStyle.THIN);
    dataStyle.setAlignment(HorizontalAlignment.CENTER);

    // Tạo tiêu đề
    XSSFRow titleRow = sheet.createRow(0);
    XSSFCell titleCell = titleRow.createCell(0);
    titleCell.setCellValue("DANH SÁCH TẤT CẢ CÁC SÁCH");
    titleCell.setCellStyle(titleStyle);

    // Merge cells cho tiêu đề (8 cột: từ cột 0 đến cột 7)
    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

    // Tạo header cho bảng
    XSSFRow headerRow = sheet.createRow(2);
    String[] headers = {"Mã sách", "Tên sách", "Nhà xuất bản", "Tác giả", "Thể loại", "Số lượng", "Năm xuất bản", "Giá"};

    for (int i = 0; i < headers.length; i++) {
        XSSFCell cell = headerRow.createCell(i);
        cell.setCellValue(headers[i]);
        cell.setCellStyle(headerStyle);
    }

    // Đổ dữ liệu từ listSach vào bảng
    sachBUS=new SachBUS();
    ArrayList<SachDTO> listSach=sachBUS.getSachAll();
    int rowNum = 3;
    for (SachDTO sach : listSach) {
        XSSFRow row = sheet.createRow(rowNum++);

        // Tìm tên nhà xuất bản
        String tenNxb = "";
        NhaXuatBanDTO NxbFind = NxbBUS.getNXBById(sach.getManxb());
        tenNxb = NxbFind.getTennxb();

        // Tìm tên tác giả
        String tenTg = "";
        TacGiaDTO TgFind = TgBUS.getTGById(sach.getMatacgia());
        tenTg = TgFind.getHotentacgia();

        // Tìm tên thể loại
        String tenTl = "";
        TheLoaiDTO TLFind = TlBUS.getTlbyId(sach.getMatheloai());
        tenTl = TLFind.getTentheloai();

        // Mã sách
        XSSFCell cell0 = row.createCell(0);
        cell0.setCellValue(sach.getMasach());
        cell0.setCellStyle(dataStyle);

        // Tên sách
        XSSFCell cell1 = row.createCell(1);
        cell1.setCellValue(sach.getTensach());
        cell1.setCellStyle(dataStyle);

        // Nhà xuất bản
        XSSFCell cell2 = row.createCell(2);
        cell2.setCellValue(tenNxb);
        cell2.setCellStyle(dataStyle);

        // Tác giả
        XSSFCell cell3 = row.createCell(3);
        cell3.setCellValue(tenTg);
        cell3.setCellStyle(dataStyle);

        // Thể loại
        XSSFCell cell4 = row.createCell(4);
        cell4.setCellValue(tenTl);
        cell4.setCellStyle(dataStyle);

        // Số lượng
        XSSFCell cell5 = row.createCell(5);
        cell5.setCellValue(sach.getSoluongton());
        cell5.setCellStyle(dataStyle);

        // Năm xuất bản
        XSSFCell cell6 = row.createCell(6);
        cell6.setCellValue(sach.getNamxuatban());
        cell6.setCellStyle(dataStyle);

        // Giá
        XSSFCell cell7 = row.createCell(7);
        cell7.setCellValue(sach.getDongia());
        cell7.setCellStyle(dataStyle);
    }

    // Tự động điều chỉnh độ rộng cột
    for (int i = 0; i < headers.length; i++) {
        sheet.autoSizeColumn(i);
    }

    // Xuất file
    try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
        wb.write(fileOut);
        JOptionPane.showMessageDialog(bf,
                "Xuất file Excel thành công!\nĐường dẫn: " + filePath,
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(new File(filePath));// Mở file sau khi thêm
                }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(bf,
                "Lỗi khi xuất file Excel: " + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    
    
    
    public void Import() throws IOException{
    // Tạo JFileChooser để chọn file Excel
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn file Excel để nhập sách");
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Documents"));
    fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
        @Override
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().toLowerCase().endsWith(".xlsx");
        }

        @Override
        public String getDescription() {
            return "Excel Files (*.xlsx)";
        }
    });

    // Hiển thị hộp thoại chọn file
    int userSelection = fileChooser.showOpenDialog(bf);

    if (userSelection != JFileChooser.APPROVE_OPTION) {
        return;
    }

    // Lấy đường dẫn file đã chọn
    File fileToImport = fileChooser.getSelectedFile();
    String filePath = fileToImport.getAbsolutePath();

    // Khởi tạo SachBUS để thêm sách
    sachBUS = new SachBUS();
    sachDAO =new SachDAO();
    try (FileInputStream fis = new FileInputStream(filePath);
         XSSFWorkbook wb = new XSSFWorkbook(fis)) {

        // Lấy sheet đầu tiên
        XSSFSheet sheet = wb.getSheetAt(0);

        // Bắt đầu đọc từ hàng 1 (hàng 0 là tiêu đề)
        int rowNum = 1;
        boolean hasData = true;
        int successCount = 0;

        while (hasData) {
            Row row = sheet.getRow(rowNum);
            if (row == null || row.getCell(0) == null || row.getCell(0).getStringCellValue().trim().isEmpty()) {
                hasData = false; // Nếu hàng trống, dừng vòng lặp
                System.err.println("cccccc");
                break;
            }

            // Lấy dữ liệu từ các cột
            String masach=row.getCell(0).getStringCellValue();  // Mã sách
            String tenSach = row.getCell(1).getStringCellValue(); // Tên sách
            int maNXB = (int) row.getCell(2).getNumericCellValue(); // Mã nhà xuất bản
            int maTG = (int) row.getCell(3).getNumericCellValue(); // Mã tác giả
            int maTL = (int) row.getCell(4).getNumericCellValue(); // Mã thể loại
            int soluong = (int) row.getCell(5).getNumericCellValue(); // Số lượng tồn
            String namXuatBan = String.valueOf((int) row.getCell(6).getNumericCellValue()); // Năm xuất bản
            int donGia = (int) row.getCell(7).getNumericCellValue(); // Đơn giá
            // Tạo mã sách tự động
            int maSachAuto = SachDAO.getInstance().getAutoIncrement();
            // Tạo đối tượng SachDTO
            SachDTO sachNew = new SachDTO(masach, tenSach, maNXB, maTG, maTL, soluong, namXuatBan, donGia);

            // Thêm sách vào hệ thống
            boolean result = sachBUS.addSach(sachNew);
            if (result) {
                successCount++;
            }

            rowNum++;
        }

        // Cập nhật lại bảng sau khi nhập
        bf.refreshTableData();
        // Hiển thị thông báo
        if(successCount==0){
            JOptionPane.showMessageDialog(bf,
                "Danh sách rỗng hoặc Sách đã tồn tại!!!! ",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(bf,
                "Nhập sách thành công! Số sách đã nhập: " + successCount,
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
        }
        

    } catch (IOException e) {
        JOptionPane.showMessageDialog(bf,
                "Lỗi khi đọc file Excel: " + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(bf,
                "Lỗi khi nhập sách: " + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
    }
}
        

}
