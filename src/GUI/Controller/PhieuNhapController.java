/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.PhieuNhapBUS;
import DTO.PhieuNhapDTO;
import GUI.Dialog.PhieuNhapDialog.AddPanel;
import GUI.Dialog.PhieuNhapDialog.PhieuNhapDialogAdd;
import GUI.Dialog.PhieuNhapDialog.PhieuNhapDialogDelete;
import GUI.Dialog.PhieuNhapDialog.PhieuNhapDialogDetail;
import GUI.View.PhieuNhapPanel;
import GUI.WorkFrame;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
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
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
/**
 *
 * @author Minnie
 */
public class PhieuNhapController implements ActionListener, ChangeListener,DocumentListener{
    
    private PhieuNhapPanel pnp;
    private WorkFrame wf;
    private PhieuNhapBUS pnBUS;
    private PhieuNhapDialogAdd PNDA;
    private PhieuNhapDialogDelete PNDD;
    private PhieuNhapDialogDetail PNDX;
    
    public PhieuNhapController(){
    }
    
    public PhieuNhapController(PhieuNhapPanel pnp, WorkFrame wf){
        this.pnp = pnp;
        this.wf = wf;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String evt = e.getActionCommand();
        String evtCbb=pnp.getCbbox().getSelectedItem().toString();
        switch (evt) {
            case "Trang chủ":    
                pnp.getPanelCenter().removeAll(); // Xóa tất cả các thành phần hiện tại trong tab
                pnp.getBtndelete().setVisible(true);
                pnp.getBtndetail().setVisible(true);
                pnp.getBtnexport().setVisible(true);         
                pnp.getToolBar_Righ().setVisible(true);
                pnp.getScrollPanePhieuNhap().setViewportView(pnp.getTablePhieuNhap());
                
                pnp.getPanelCenter().setLayout(new GridLayout(1,1));
                pnp.getPanelCenter().add(pnp.getScrollPanePhieuNhap());
                pnp.refreshTablePn();
                
                pnp.getCbbox().setSelectedIndex(0);
                pnp.getTxfFind().setText("");
                break;
            case "Thêm":
                
                pnp.getPanelCenter().removeAll(); // Xóa tất cả các thành phần hiện tại trong tab
                pnp.getBtndelete().setVisible(false);
                pnp.getBtndetail().setVisible(false);
                pnp.getBtnexport().setVisible(false);
                pnp.getToolBar_Righ().setVisible(false);
                
                pnp.getPanelCenter().setLayout(new BorderLayout()); // Đặt layout cho tab
                
                // Thêm AddPanel vào PanelCenter
                AddPanel addPanel = new AddPanel();
                pnp.getPanelCenter().add(addPanel, BorderLayout.CENTER);
                
//                PhieuNhapDialogAdd pnda = new PhieuNhapDialogAdd();
//                pnp.getPanelCenter().add(pnda, BorderLayout.CENTER);
                
                pnp.getPanelCenter().revalidate(); // Làm mới giao diện
                pnp.getPanelCenter().repaint(); // Vẽ lại giao diện
                break;
            case "Chi tiết":
                JTable tablePN = pnp.getTablePhieuNhap();
                
                // Kiểm tra bảng có dữ liệu hay không
                if (tablePN.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để chọn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Kiểm tra hàng được chọn
                int selectedRow = tablePN.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng trong bảng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Xử lý dữ liệu hàng được chọn
                try {
                    int id = Integer.parseInt(tablePN.getValueAt(selectedRow, 0).toString());
                    pnBUS = new PhieuNhapBUS();
                    PhieuNhapDTO phieu = pnBUS.getPNById(id);
                    if (phieu != null) {
                        PNDX = new PhieuNhapDialogDetail(wf, phieu);
                        pnp.refreshTablePn();
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xử lý dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Hủy bỏ":
                JTable tablePNC = pnp.getTablePhieuNhap();
                
                // Kiểm tra bảng có dữ liệu hay không
                if (tablePNC.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để chọn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Kiểm tra hàng được chọn
                int selectedRow2 = tablePNC.getSelectedRow();
                if (selectedRow2 == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng trong bảng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    int id = Integer.parseInt(tablePNC.getValueAt(selectedRow2, 0).toString());
                    pnBUS = new PhieuNhapBUS();
                    PhieuNhapDTO phieu = pnBUS.getPNById(id);
                    if (phieu != null){
                        PNDD = new PhieuNhapDialogDelete(wf, phieu);
                        pnp.refreshTablePn();
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }   catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xử lý dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                    break;
            default:
                break;
        }
        if (evt.equals("Xuất Excel")) {
            try {
                WriteExcel();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("Lỗi đọc file Excel: " + ex.getMessage());
            }
        }
        if(evtCbb.equals("Hóa đơn thấp đến cao ⬆")){
            pnBUS=new PhieuNhapBUS();
            ArrayList<PhieuNhapDTO> list_Sort=pnBUS.LowToHighofBill(pnp.getListpn());
            pnp.FilterTableData(list_Sort);
        }else if(evtCbb.equals("Hóa đơn cao đến thấp ⬇")){
            pnBUS=new PhieuNhapBUS();
            ArrayList<PhieuNhapDTO> list_Sort=pnBUS.HighToLowofBill(pnp.getListpn());
            pnp.FilterTableData(list_Sort);
        }else if(evtCbb.equals("Ngày tạo thấp đến cao ⬆")){
            pnBUS=new PhieuNhapBUS();
            ArrayList<PhieuNhapDTO> list_Sort=pnBUS.LowToHighofDate(pnp.getListpn());
            pnp.FilterTableData(list_Sort);
        }else if(evtCbb.equals("Ngày tạo cao đến thấp ⬇")){
            pnBUS=new PhieuNhapBUS();
            ArrayList<PhieuNhapDTO> list_Sort=pnBUS.HighToLowofDate(pnp.getListpn());
            pnp.FilterTableData(list_Sort);
        }else if(evtCbb.equals("Tất cả")){
            pnp.getTxfFind().setText("");
            pnp.refreshTablePn();
        }
       
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        pnp.FindTableData(pnp.getTxfFind().getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
       pnp.FindTableData(pnp.getTxfFind().getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        pnp.FindTableData(pnp.getTxfFind().getText());   
    }
    
    public void WriteExcel() throws IOException {
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
        String defaultFileName = "DanhSachPhieuNhap.xlsx";
        fileChooser.setSelectedFile(new File(defaultFileName));

        // Hiển thị hộp thoại lưu file
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection != JFileChooser.APPROVE_OPTION) { // Kiểm tra nếu người dùng hủy lưu
            return;
        }

        // Lấy đường dẫn file đã chọn
        File fileToSave = fileChooser.getSelectedFile();
        String filePath = fileToSave.getAbsolutePath();

        if (!filePath.toLowerCase().endsWith(".xlsx")) {
            filePath += ".xlsx"; // Thêm đuôi .xlsx nếu chưa có
        }

        // Tạo workbook và sheet mới
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Danh sách phiếu nhập");

        // Tạo font cho tiêu đề
        XSSFFont titleFont = wb.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 14);

        // Tạo style cho tiêu đề
        XSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);

        // Tạo style cho header bảng
        XSSFCellStyle headerStyle = wb.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        // Tạo style cho dữ liệu
        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);

        // Tạo tiêu đề
        XSSFRow titleRow = sheet.createRow(0);
        XSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("DANH SÁCH TẤT CẢ PHIẾU NHẬP");
        titleCell.setCellStyle(titleStyle);

        // Gộp ô cho tiêu đề (5 cột)
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

        // Tạo header cho bảng
        XSSFRow headerRow = sheet.createRow(2);
        String[] headers = {"Mã phiếu nhập", "Nhân viên", "Nhà cung cấp", "Thời gian", "Tổng tiền"};

        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Lấy JTable từ PhieuNhapPanel
        JTable tablePN = pnp.getTablePhieuNhap();

        // Đổ dữ liệu vào bảng
        int rowNum = 3;

        for (int i = 0; i < tablePN.getRowCount(); i++) {
            XSSFRow row = sheet.createRow(rowNum++);

            // Mã phiếu nhập
            XSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(tablePN.getValueAt(i, 0).toString());
            cell0.setCellStyle(dataStyle);

            // Nhân viên
            XSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(tablePN.getValueAt(i, 1).toString());
            cell1.setCellStyle(dataStyle);

            // Nhà cung cấp
            XSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(tablePN.getValueAt(i, 2).toString());
            cell2.setCellStyle(dataStyle);

            // Thời gian
            XSSFCell cell3 = row.createCell(3);
            cell3.setCellValue(tablePN.getValueAt(i, 3).toString());
            cell3.setCellStyle(dataStyle);

            // Tổng tiền
            XSSFCell cell4 = row.createCell(4);
            cell4.setCellValue(tablePN.getValueAt(i, 4).toString());
            cell4.setCellStyle(dataStyle);
        }

        // Tự động điều chỉnh độ rộng cột
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Xuất file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            wb.write(fileOut);
            JOptionPane.showMessageDialog(null,
                    "Xuất file Excel thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(filePath)); // Mở file sau khi lưu
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Lỗi khi xuất file Excel: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            wb.close(); // Đóng workbook
        }
    }
}
