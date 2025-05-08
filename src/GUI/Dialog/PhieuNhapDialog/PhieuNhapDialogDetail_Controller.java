package GUI.Dialog.PhieuNhapDialog;

import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

public class PhieuNhapDialogDetail_Controller implements ActionListener {
    private PhieuNhapDialogDetail pnDialogDetail;

    public PhieuNhapDialogDetail_Controller(PhieuNhapDialogDetail pnDialogDetail) {
        this.pnDialogDetail = pnDialogDetail;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String evt = e.getActionCommand();
        switch (evt) {
            case "Đóng":
                pnDialogDetail.dispose();
                break;
            case "Xuất file Excel":
                try {
                    WriteExcel();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(pnDialogDetail,
                            "Lỗi khi xuất file Excel: " + ex.getMessage(),
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    public void WriteExcel() throws IOException {
        // Lấy thông tin từ dialog
        String maPhieu = pnDialogDetail.getTxfMaPhieu().getText();
        String nhanVien = pnDialogDetail.getTxfNV().getText();
        String nhaCungCap = pnDialogDetail.getTxfNCC().getText();
        String thoiGian = pnDialogDetail.getTxfTime().getText();
        String tongTien = pnDialogDetail.getTxfTongHD().getText();
        JTable tableCTPN = pnDialogDetail.getTableCTPN();

        // Tạo JFileChooser để chọn nơi lưu file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Xuất phiếu nhập");
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
        String defaultFileName = "PhieuNhap_" + maPhieu + ".xlsx";
        fileChooser.setSelectedFile(new File(defaultFileName));

        // Hiển thị hộp thoại lưu file
        int userSelection = fileChooser.showSaveDialog(pnDialogDetail);

        // Nếu người dùng hủy thì thoát
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return;
        }

        // Lấy đường dẫn file đã chọn
        File fileToSave = fileChooser.getSelectedFile();
        String filePath = fileToSave.getAbsolutePath();

        // Đảm bảo file có đuôi .xlsx
        if (!filePath.toLowerCase().endsWith(".xlsx")) {
            filePath += ".xlsx";
        }

        // Tạo workbook và sheet mới
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Phiếu nhập " + maPhieu);

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

        // Tạo style cho dữ liệu
        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);

        // Tạo tiêu đề
        XSSFRow titleRow = sheet.createRow(0);
        XSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("THÔNG TIN PHIẾU NHẬP");
        titleCell.setCellStyle(titleStyle);

        // Gộp ô cho tiêu đề (5 cột)
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

        // Tạo header cho bảng
        XSSFRow headerRow = sheet.createRow(2);
        String[] headers = {"Mã sách", "Tên sách", "Số lượng", "Đơn giá", "Thành tiền"};

        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Đổ dữ liệu chi tiết phiếu nhập vào bảng
        int rowNum = 3;
        for (int i = 0; i < tableCTPN.getRowCount(); i++) {
            XSSFRow row = sheet.createRow(rowNum++);

            // Mã sách
            XSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(tableCTPN.getValueAt(i, 0).toString());
            cell0.setCellStyle(dataStyle);

            // Tên sách
            XSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(tableCTPN.getValueAt(i, 1).toString());
            cell1.setCellStyle(dataStyle);

            // Số lượng
            XSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(tableCTPN.getValueAt(i, 2).toString());
            cell2.setCellStyle(dataStyle);

            // Đơn giá
            XSSFCell cell3 = row.createCell(3);
            cell3.setCellValue(tableCTPN.getValueAt(i, 3).toString());
            cell3.setCellStyle(dataStyle);

            // Thành tiền
            XSSFCell cell4 = row.createCell(4);
            cell4.setCellValue(tableCTPN.getValueAt(i, 4).toString());
            cell4.setCellStyle(dataStyle);
        }

        // Tạo dòng phân cách
        rowNum++;

        // Tạo thông tin phiếu nhập
        XSSFRow row1 = sheet.createRow(rowNum++);
        row1.createCell(0).setCellValue("Mã phiếu");
        row1.getCell(0).setCellStyle(headerStyle);
        row1.createCell(1).setCellValue(maPhieu);
        row1.getCell(1).setCellStyle(dataStyle);

        row1.createCell(3).setCellValue("Tổng tiền");
        row1.getCell(3).setCellStyle(headerStyle);
        row1.createCell(4).setCellValue(tongTien);
        row1.getCell(4).setCellStyle(dataStyle);

        XSSFRow row2 = sheet.createRow(rowNum++);
        row2.createCell(0).setCellValue("Nhân viên thực hiện");
        row2.getCell(0).setCellStyle(headerStyle);
        row2.createCell(1).setCellValue(nhanVien);
        row2.getCell(1).setCellStyle(dataStyle);

        XSSFRow row3 = sheet.createRow(rowNum++);
        row3.createCell(0).setCellValue("Nhà cung cấp");
        row3.getCell(0).setCellStyle(headerStyle);
        row3.createCell(1).setCellValue(nhaCungCap);
        row3.getCell(1).setCellStyle(dataStyle);

        XSSFRow row4 = sheet.createRow(rowNum++);
        row4.createCell(0).setCellValue("Thời gian tạo");
        row4.getCell(0).setCellStyle(headerStyle);
        row4.createCell(1).setCellValue(thoiGian);
        row4.getCell(1).setCellStyle(dataStyle);

        // Tự động điều chỉnh độ rộng cột
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Xuất file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            wb.write(fileOut);
            JOptionPane.showMessageDialog(pnDialogDetail,
                    "Xuất file Excel thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(filePath)); // Mở file sau khi lưu
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pnDialogDetail,
                    "Lỗi khi xuất file Excel: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            wb.close();
        }
    }
}