package GUI.Dialog.PhieuXuatDialog;

import BUS.PhieuXuatBUS;
import BUS.SachBUS;
import DTO.ChiTietPhieuXuatDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.DocumentException;
import java.awt.Desktop;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class PhieuXuatDialogDetail_Controller implements ActionListener {
    private PhieuXuatBUS pxBUS;
    private ArrayList<ChiTietPhieuXuatDTO> list_ctpx;
    private SachBUS sBUS;
    private PhieuXuatDialogDetail PXDD;

    public PhieuXuatDialogDetail_Controller(PhieuXuatDialogDetail PXDD) {
        this.PXDD = PXDD;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String sukien = e.getActionCommand();
        if (sukien.equals("Đóng")) {
            PXDD.dispose();
        }
        if (sukien.equals("Xuất file Excel")) {
            try {
                WriteExcel();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("Lỗi đọc file Excel: " + ex.getMessage());
            }
        }if (sukien.equals("Xuất file PDF")) {
            try {
                WritePDF();
            } catch (IOException ex2) {
                ex2.printStackTrace();
                JOptionPane.showMessageDialog(PXDD,
                        "Lỗi khi xuất file PDF: " + ex2.getMessage(),
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void WriteExcel() throws IOException {
        // Lấy thông tin từ dialog
        String maPhieu = PXDD.getTxfMaPhieu().getText();
        String nhanVien = PXDD.getTxfNV().getText();
        String khachHang = PXDD.getTxfKhachHang().getText();
        String thoiGian = PXDD.getTxfTime().getText();
        String tongTien = PXDD.getTxfTongHD().getText();
        JTable tableCTPX = PXDD.getTableCTPX();
        String giamGia = PXDD.getTxfPhanTram().getText();

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
        int userSelection = fileChooser.showSaveDialog(PXDD);

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
        for (int i = 0; i < tableCTPX.getRowCount(); i++) {
            XSSFRow row = sheet.createRow(rowNum++);

            // STT
            XSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(tableCTPX.getValueAt(i, 0).toString());
            cell0.setCellStyle(dataStyle);

            // Mã sách
            XSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(tableCTPX.getValueAt(i, 1).toString());
            cell1.setCellStyle(dataStyle);

            // Tên sách
            XSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(tableCTPX.getValueAt(i, 2).toString());
            cell2.setCellStyle(dataStyle);

            // Đơn giá
            XSSFCell cell3 = row.createCell(3);
            cell3.setCellValue(tableCTPX.getValueAt(i, 3).toString());
            cell3.setCellStyle(dataStyle);

            // Số lượng
            XSSFCell cell4 = row.createCell(4);
            cell4.setCellValue(tableCTPX.getValueAt(i, 4).toString());
            cell4.setCellStyle(dataStyle);

            // Thành tiền
            XSSFCell cell5 = row.createCell(5);
            cell5.setCellValue(tableCTPX.getValueAt(i, 5).toString());
            cell5.setCellStyle(dataStyle);
        }

        // Tạo dòng phân cách
        // Dòng phân cách
        rowNum++;

        // Định dạng số tiền
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        DecimalFormat formatVND = new DecimalFormat("#,###", symbols);


        // Tính tiền được giảm
        giamGia = giamGia.replace("%", "").trim();
        double phantramGiamGia = Double.parseDouble(giamGia) / 100;
        double tienDuocGiam = PXDD.tinhtongtien() * phantramGiamGia;

        
        XSSFRow row1 = sheet.createRow(rowNum++);
        row1.createCell(0).setCellValue("Mã phiếu");
        row1.getCell(0).setCellStyle(headerStyle);
        row1.createCell(1).setCellValue(maPhieu);
        row1.getCell(1).setCellStyle(dataStyle);

        row1.createCell(4).setCellValue("Tổng tiền hàng");
        row1.getCell(4).setCellStyle(headerStyle);
        row1.createCell(5).setCellValue(formatVND.format(PXDD.tinhtongtien()));
        row1.getCell(5).setCellStyle(dataStyle);

        
        XSSFRow row2 = sheet.createRow(rowNum++);
        row2.createCell(0).setCellValue("Nhân viên thực hiện");
        row2.getCell(0).setCellStyle(headerStyle);
        row2.createCell(1).setCellValue(nhanVien);
        row2.getCell(1).setCellStyle(dataStyle);

        row2.createCell(4).setCellValue("Giảm giá");
        row2.getCell(4).setCellStyle(headerStyle);
        row2.createCell(5).setCellValue(giamGia + "%");
        row2.getCell(5).setCellStyle(dataStyle);

        
        XSSFRow row3 = sheet.createRow(rowNum++);
        row3.createCell(0).setCellValue("Khách hàng");
        row3.getCell(0).setCellStyle(headerStyle);
        row3.createCell(1).setCellValue(khachHang);
        row3.getCell(1).setCellStyle(dataStyle);

        row3.createCell(4).setCellValue("Tiền được giảm");
        row3.getCell(4).setCellStyle(headerStyle);
        row3.createCell(5).setCellValue(formatVND.format(tienDuocGiam));
        row3.getCell(5).setCellStyle(dataStyle);

        
        XSSFRow row4 = sheet.createRow(rowNum++);
        row4.createCell(0).setCellValue("Thời gian tạo");
        row4.getCell(0).setCellStyle(headerStyle);
        row4.createCell(1).setCellValue(thoiGian);
        row4.getCell(1).setCellStyle(dataStyle);

        row4.createCell(4).setCellValue("Tổng thanh toán");
        row4.getCell(4).setCellStyle(headerStyle);
        row4.createCell(5).setCellValue(tongTien);
        row4.getCell(5).setCellStyle(dataStyle);


        // Tự động điều chỉnh độ rộng cột
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Xuất file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            wb.write(fileOut);
            JOptionPane.showMessageDialog(PXDD,
                    "Xuất file Excel thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(new File(filePath));// Mở file sau khi thêm
                    }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(PXDD,
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
    public void WritePDF() throws IOException {
    // Lấy thông tin từ dialog
    String maPhieu = PXDD.getTxfMaPhieu().getText();
    String nhanVien = PXDD.getTxfNV().getText();
    String khachHang = PXDD.getTxfKhachHang().getText();
    String thoiGian = PXDD.getTxfTime().getText();
    String tongTien = PXDD.getTxfTongHD().getText();
    String phanTram = PXDD.getTxfPhanTram().getText();
    JTable tableCTPX = PXDD.getTableCTPX();

    // Tạo JFileChooser để chọn nơi lưu file
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Xuất hóa đơn PDF");
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Documents"));
    fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
        @Override
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().toLowerCase().endsWith(".pdf");
        }

        @Override
        public String getDescription() {
            return "PDF Files (*.pdf)";
        }
    });

    // Thiết lập tên file mặc định
    String defaultFileName = "PhieuXuat_" + maPhieu + ".pdf";
    fileChooser.setSelectedFile(new File(defaultFileName));

    // Hiển thị hộp thoại lưu file
    int userSelection = fileChooser.showSaveDialog(PXDD);

    // Nếu người dùng hủy thì thoát
    if (userSelection != JFileChooser.APPROVE_OPTION) {
        return;
    }

    // Lấy đường dẫn file
    File fileToSave = fileChooser.getSelectedFile();
    String filePath = fileToSave.getAbsolutePath();
    if (!filePath.toLowerCase().endsWith(".pdf")) {
        filePath += ".pdf";
    }

    // Tạo file PDF
    Document document = new Document();
    try {
        FileOutputStream fos = new FileOutputStream(filePath);
        PdfWriter.getInstance(document, fos);
        document.open();

        // Tạo font hỗ trợ tiếng Việt
        BaseFont bf = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font titleFont = new Font(bf, 16, Font.BOLD);
        Font billFont = new Font(bf, 12, Font.NORMAL);
        Font infoFont = new Font(bf, 12, Font.NORMAL);
        Font headerFont = new Font(bf, 12, Font.BOLD);
        Font cellFont = new Font(bf, 12, Font.NORMAL);
        // set  Tiêu đề
        Paragraph title = new Paragraph("HÓA ĐƠN BÁN HÀNG", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        // Enter xuống dòng
        document.add(new Paragraph("\n"));
        // Thông tin phiếu
        document.add(new Paragraph("Mã phiếu: " + maPhieu, infoFont));
        document.add(new Paragraph("Khách hàng: " + khachHang, infoFont));
        document.add(new Paragraph("Nhân viên thực hiện: " + nhanVien, infoFont));
        document.add(new Paragraph("Thời gian tạo: " + thoiGian, infoFont));

        // Enter xuống dòng
        document.add(new Paragraph("\n"));

        // Tạo bảng
        String[] headers = {"STT", "Mã sách", "Tên sách", "Đơn giá", "Số lượng", "Thành tiền"};
        PdfPTable table = new PdfPTable(headers.length);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{10, 15, 35, 15, 10, 15});

        // Thêm header cho bảng
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

        // Thêm dữ liệu vào bảng
        for (int i = 0; i < tableCTPX.getRowCount(); i++) {
            for (int j = 0; j < headers.length; j++) {
                PdfPCell cell = new PdfPCell(new Phrase(tableCTPX.getValueAt(i, j).toString(), cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
        }

        document.add(table);

        // Tổng tiền (góc phải)
        DecimalFormat formatter = new DecimalFormat("#,###");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        formatter.setDecimalFormatSymbols(symbols);

        Paragraph phanTramgiamgia = new Paragraph("Giảm giá: " + phanTram, billFont);
        phanTram = phanTram.replace("%", "").trim();
        double phantramgiamgia = Double.parseDouble(phanTram) / 100;
        double tienduocGiam=PXDD.tinhtongtien()*phantramgiamgia;
        Paragraph tienduocgiam = new Paragraph("Tiền được giảm: " + formatter.format(tienduocGiam), billFont);
        Paragraph total = new Paragraph("Tổng tiền hàng: " + formatter.format(PXDD.tinhtongtien()), billFont);       
        Paragraph tongHoaDon = new Paragraph("Tổng thanh toán: " + tongTien, headerFont);
        
        total.setAlignment(Element.ALIGN_RIGHT);
        phanTramgiamgia.setAlignment(Element.ALIGN_RIGHT);
        tongHoaDon.setAlignment(Element.ALIGN_RIGHT);
        tienduocgiam.setAlignment(Element.ALIGN_RIGHT);
        document.add(total);
        document.add(phanTramgiamgia);
        document.add(tienduocgiam);
        document.add(tongHoaDon);

        document.close();
        fos.close();

        JOptionPane.showMessageDialog(PXDD,
                "Xuất file PDF thành công!",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(new File(filePath));// Mở file sau khi thêm
        }
        
    } catch (DocumentException e) {
        throw new IOException("Lỗi khi tạo PDF: " + e.getMessage());
    }
}
    
}