/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhieuNhapDialog;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;
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
/**
 *
 * @author Minnie
 */
public class PhieuNhapDialogDetail_Controller implements ActionListener{
    private PhieuNhapDialogDetail pnDialogDetail;
    public PhieuNhapDialogDetail_Controller(PhieuNhapDialogDetail pnDialogDetail) {
        this.pnDialogDetail = pnDialogDetail;
    }
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        // Xử lý sự kiện tại đây
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
                System.err.println("Lỗi đọc file Excel: " + ex.getMessage());
            }
            default:
                throw new AssertionError();
        }
    }
    public void showDialog() {
        pnDialogDetail.setVisible(true);
    }

    public void hideDialog() {
        pnDialogDetail.dispose();
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
        fileChooser.setDialogTitle("Xuất hóa đơn");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Documents")); // Mở ở thư mục Documents
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
        String defaultFileName = "PhieuXuat_" + maPhieu + ".xlsx";
        fileChooser.setSelectedFile(new File(defaultFileName));

        // Hiển thị hộp thoại lưu file
        int userSelection = fileChooser.showSaveDialog(pnDialogDetail);

        // Nếu người dùng hủy bỏ thì không làm gì
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
        XSSFSheet sheet = wb.createSheet("Phiếu xuất " + maPhieu);

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
        titleCell.setCellValue("THÔNG TIN PHIẾU XUẤT");
        titleCell.setCellStyle(titleStyle);

        // Merge cells cho tiêu đề
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

        // Tạo header cho bảng
        XSSFRow headerRow = sheet.createRow(2);
        String[] headers = {"STT", "Mã sách", "Tên sách", "Đơn giá", "Số lượng", "Thành tiền"};

        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Đổ dữ liệu chi tiết phiếu xuất vào bảng
        int rowNum = 3;
        for (int i = 0; i < tableCTPN.getRowCount(); i++) {
            XSSFRow row = sheet.createRow(rowNum++);

            // STT
            XSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(tableCTPN.getValueAt(i, 0).toString());
            cell0.setCellStyle(dataStyle);

            // Mã sách
            XSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(tableCTPN.getValueAt(i, 1).toString());
            cell1.setCellStyle(dataStyle);

            // Tên sách
            XSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(tableCTPN.getValueAt(i, 2).toString());
            cell2.setCellStyle(dataStyle);

            // Đơn giá
            XSSFCell cell3 = row.createCell(3);
            cell3.setCellValue(tableCTPN.getValueAt(i, 3).toString());
            cell3.setCellStyle(dataStyle);

            // Số lượng
            XSSFCell cell4 = row.createCell(4);
            cell4.setCellValue(tableCTPN.getValueAt(i, 4).toString());
            cell4.setCellStyle(dataStyle);

            // Thành tiền
            XSSFCell cell5 = row.createCell(5);
            cell5.setCellValue(tableCTPN.getValueAt(i, 5).toString());
            cell5.setCellStyle(dataStyle);
        }

        // Tạo dòng phân cách
        rowNum++;

        // Thêm thông tin footer
        XSSFRow footerRow1 = sheet.createRow(rowNum++);
        XSSFCell footerCell1 = footerRow1.createCell(0);
        footerCell1.setCellValue("Mã phiếu");
        footerCell1.setCellStyle(headerStyle);

        XSSFCell footerCell2 = footerRow1.createCell(1);
        footerCell2.setCellValue(maPhieu);
        footerCell2.setCellStyle(dataStyle);

        XSSFRow footerRow2 = sheet.createRow(rowNum++);
        XSSFCell footerCell3 = footerRow2.createCell(0);
        footerCell3.setCellValue("Nhân viên thực hiện");
        footerCell3.setCellStyle(headerStyle);

        XSSFCell footerCell4 = footerRow2.createCell(1);
        footerCell4.setCellValue(nhanVien);
        footerCell4.setCellStyle(dataStyle);

        XSSFRow footerRow3 = sheet.createRow(rowNum++);
        XSSFCell footerCell5 = footerRow3.createCell(0);
        footerCell5.setCellValue("Khách hàng");
        footerCell5.setCellStyle(headerStyle);

        XSSFCell footerCell6 = footerRow3.createCell(1);
        footerCell6.setCellValue(nhaCungCap);
        footerCell6.setCellStyle(dataStyle);

        XSSFRow footerRow4 = sheet.createRow(rowNum++);
        XSSFCell footerCell7 = footerRow4.createCell(0);
        footerCell7.setCellValue("Thời gian tạo");
        footerCell7.setCellStyle(headerStyle);

        XSSFCell footerCell8 = footerRow4.createCell(1);
        footerCell8.setCellValue(thoiGian);
        footerCell8.setCellStyle(dataStyle);

        XSSFRow footerRow5 = sheet.createRow(rowNum++);
        XSSFCell footerCell9 = footerRow5.createCell(0);
        footerCell9.setCellValue("Tổng hóa đơn");
        footerCell9.setCellStyle(headerStyle);

        XSSFCell footerCell10 = footerRow5.createCell(1);
        footerCell10.setCellValue(tongTien);
        footerCell10.setCellStyle(dataStyle);

        // Tự động điều chỉnh độ rộng cột
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Xuất file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            wb.write(fileOut);
            JOptionPane.showMessageDialog(pnDialogDetail,
                    "Xuất file Excel thành công!\nĐường dẫn: " + filePath,
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pnDialogDetail,
                    "Lỗi khi xuất file Excel: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            wb.close();
        }
    }
    public void WriteBasic() throws IOException {
        // Tạo một workbook mới
        XSSFWorkbook wb = new XSSFWorkbook();     
        XSSFSheet sheet = wb.createSheet("name");      
        // Tạo các row và cell
        XSSFRow row;
        XSSFCell cell1;
        XSSFCell cell2;       
        // Điền dữ liệu vào các cell
        for (int i = 0; i < 10; i++) {
            row = sheet.createRow(i); // Tạo row mới
            cell1 = row.createCell(0); // Tạo cell ở cột 0
            cell1.setCellValue(i + 1); // Gán giá trị cho cell 1       
            cell2 = row.createCell(1); // Tạo cell ở cột 1
            cell2.setCellValue(i + 2); // Gán giá trị cho cell 2
        }
        FileOutputStream fileOut = new FileOutputStream("D:\\BT_JAVA_VSC\\DoAnQuanLyBanSachJava1\\QuanLiSach2\\QuanLyBanSach\\src\\Excel\\Write.xlsx");
        wb.write(fileOut);
        
        fileOut.close();
        wb.close();
    }

    public void ReadBasic() throws  IOException {
        FileInputStream file = new FileInputStream("D:\\BT_JAVA_VSC\\DoAnQuanLyBanSachJava1\\QuanLiSach2\\QuanLyBanSach\\src\\Excel\\Read.xlsx");
        XSSFWorkbook wb=new XSSFWorkbook(file);
        XSSFSheet sheet=wb.getSheetAt(0);
        FormulaEvaluator formula=wb.getCreationHelper().createFormulaEvaluator();
        for (Row row: sheet){
            for (Cell cell: row){
                switch (formula.evaluate(cell).getCellType()) {
                    case STRING:
                        System.out.print(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                }
            }
        }
        wb.close();
        file.close();
    }
    
}

