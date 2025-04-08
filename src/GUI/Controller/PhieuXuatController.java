/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.PhieuXuatBUS;
import DTO.PhieuXuatDTO;
import java.awt.event.ActionListener;
import GUI.WorkFrame;
import GUI.View.PhieuXuatPanel;
import java.awt.event.ActionEvent;
import GUI.Dialog.PhieuXuatDialog.PhieuXuatDialogAdd;
import GUI.Dialog.PhieuXuatDialog.PhieuXuatDialogDetail;
import GUI.Dialog.PhieuXuatDialog.PhieuXuatDialogDelete;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.BorderFactory;

/**
 *
 * @author DELL
 */
public class PhieuXuatController implements ActionListener, ChangeListener {
    private PhieuXuatPanel PxP;
    private WorkFrame WF;
    private PhieuXuatBUS pxBUS;
    private PhieuXuatDialogDelete PXDD;
    private PhieuXuatDialogDetail PXDDetail;
    private PhieuXuatDialogAdd currentPhieuXuatDialogAdd;
    private JScrollPane scrollPanePhieuXuatAdd;
    private int tabCount = 1;
    private boolean isRemoving = false;

    public PhieuXuatController() {
    }

    public PhieuXuatController(PhieuXuatPanel PxP, WorkFrame Wf) {
        this.PxP = PxP;
        this.WF = Wf;
    }

    public void actionPerformed(ActionEvent e) {
        String sukien = e.getActionCommand();
        if (sukien.equals("Trang chủ")) {
            PxP.getPanelCenter().remove(PxP.getScrollPanePhieuXuat()); // Xóa ScrollPane hiện tại
            PxP.getScrollPanePhieuXuat().setViewportView(PxP.getTablePhieuXuat());
            PxP.getPanelCenter().setLayout(new GridLayout(1, 1));
            PxP.getPanelCenter().add(PxP.getScrollPanePhieuXuat());
            PxP.refreshTablePx();
        }
        if (sukien.equals("Thêm")) {
            PxP.getPanelCenter().remove(PxP.getScrollPanePhieuXuat());// Xóa đi cái Table hiện tại
            if (PxP.getTabbedPane().getTabCount() == 0) { // Nếu chưa có tab nào thì mới tạo 
                ThemTabAdd();
                ThemTabPlus();
                PxP.getPanelCenter().add(PxP.getTabbedPane());
            }
            // Làm mới giao diện
            PxP.getPanelCenter().revalidate();
            PxP.getPanelCenter().repaint();
        }
        if (sukien.equals("Chi tiết")) {
            JTable tablePX = PxP.getTablePhieuXuat();
            if (tablePX.getSelectedRow() >= 0) {
                int selectRow = tablePX.getSelectedRow();
                pxBUS = new PhieuXuatBUS();
                PhieuXuatDTO phieu = pxBUS.getPXById(Integer.parseInt(tablePX.getValueAt(selectRow, 0).toString()));
                PXDDetail = new PhieuXuatDialogDetail(WF, phieu);
            } else {
                JOptionPane.showMessageDialog(null, "Vui Lòng chọn Hóa đơn muốn xóa", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (sukien.equals("Hủy bỏ")) {
            JTable tablePX = PxP.getTablePhieuXuat();
            if (tablePX.getSelectedRow() >= 0) {
                int selectRow = tablePX.getSelectedRow();
                pxBUS = new PhieuXuatBUS();
                PhieuXuatDTO phieu = pxBUS.getPXById(Integer.parseInt(tablePX.getValueAt(selectRow, 0).toString()));
                PXDD = new PhieuXuatDialogDelete(WF, phieu);
                PxP.refreshTablePx();
            } else {
                JOptionPane.showMessageDialog(null, "Vui Lòng chọn Hóa đơn muốn xóa", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (sukien.equals("Xuất Excel")) {
            try {
                WriteExcel();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("Lỗi đọc file Excel: " + ex.getMessage());
            }
        }
    }

    public void ThemTabAdd() { // Thêm tab hóa đơn
        PhieuXuatDialogAdd pxdaNew = new PhieuXuatDialogAdd();
        String title = "Hóa đơn " + tabCount;
        PxP.getTabbedPane().addTab(null, pxdaNew);
        int index = PxP.getTabbedPane().getTabCount() - 1;
        PxP.getTabbedPane().setTabComponentAt(index, createCloseableTab(title));
        tabCount++;
    }

    public void ThemTabPlus() { // Thêm tab +
        PxP.getTabbedPane().addTab("+", new JPanel());
    }

    private JPanel createCloseableTab(String title) { // Trong tab hóa đơn có nút X
        JPanel tabPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        tabPanel.setOpaque(false);

        JLabel label = new JLabel(title);

        JButton closeButton = new JButton("x") {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(Color.RED);
                g.fillOval(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(Color.RED);
                g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
            }
        };
        closeButton.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 10));
        closeButton.setMargin(new Insets(0, 0, 0, 0));
        closeButton.setPreferredSize(new Dimension(14, 14));
        closeButton.setForeground(Color.WHITE);
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);

        closeButton.addActionListener(e -> {
            isRemoving = true;
            int index = PxP.getTabbedPane().indexOfTabComponent(tabPanel);
            if (index != -1 && canRemoveTab()) {
                PxP.getTabbedPane().remove(index);
                if (index > 0) {
                    PxP.getTabbedPane().setSelectedIndex(index - 1);
                } else if (PxP.getTabbedPane().getTabCount() > 0) {
                    PxP.getTabbedPane().setSelectedIndex(0);
                }
            } else if (!canRemoveTab()) {
                JOptionPane.showMessageDialog(null, "Không thể xóa tab cuối !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
            isRemoving = false;
        });

        tabPanel.add(label);
        tabPanel.add(closeButton);
        return tabPanel;
    }

    private boolean canRemoveTab() {
        JTabbedPane tabbedPane = PxP.getTabbedPane();
        int totalTabs = tabbedPane.getTabCount();
        return totalTabs > 2;
    }

    @Override
    public void stateChanged(ChangeEvent e) { // Action chuyển động các tab
        if (isRemoving) return;
        JTabbedPane tabbedPane = PxP.getTabbedPane();
        int index = tabbedPane.getSelectedIndex();
        if (index == tabbedPane.getTabCount() - 1 && tabbedPane.getTitleAt(index).equals("+")) {
            tabbedPane.remove(index);
            ThemTabAdd();
            ThemTabPlus();
            tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 2);
        }
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
        String defaultFileName = "DanhSachHoaDon.xlsx";
        fileChooser.setSelectedFile(new File(defaultFileName));

        // Hiển thị hộp thoại lưu file
        int userSelection = fileChooser.showSaveDialog(PXDD);

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
        XSSFSheet sheet = wb.createSheet("Danh sách hóa đơn");

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
        titleCell.setCellValue("DANH SÁCH TẤT CẢ HÓA ĐƠN");
        titleCell.setCellStyle(titleStyle);

        // Merge cells cho tiêu đề (5 cột)
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

        // Tạo header cho bảng
        XSSFRow headerRow = sheet.createRow(2);
        String[] headers = {"Mã phiếu xuất", "Nhân viên", "Khách hàng", "Thời gian", "Tổng tiền"};

        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
        // Tạo JTable
        JTable tablePX=PxP.getTablePhieuXuat();

        // Đổ dữ liệu vào bảng
        int rowNum = 3;

        for (int i=0;i<tablePX.getRowCount();i++){
            XSSFRow row = sheet.createRow(rowNum++);

            // Mã phiếu xuất
            XSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(tablePX.getValueAt(i, 0).toString());
            cell0.setCellStyle(dataStyle);

            // Nhân viên
            XSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(tablePX.getValueAt(i, 1).toString());
            cell1.setCellStyle(dataStyle);

            // Khách hàng
            XSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(tablePX.getValueAt(i, 2).toString());
            cell2.setCellStyle(dataStyle);

            // Thời gian
            XSSFCell cell3 = row.createCell(3);
            cell3.setCellValue(tablePX.getValueAt(i, 3).toString());
            cell3.setCellStyle(dataStyle);

            // Tổng tiền
            XSSFCell cell4 = row.createCell(4);
            cell4.setCellValue(tablePX.getValueAt(i, 4).toString());
            cell4.setCellStyle(dataStyle);
        }

        // Tự động điều chỉnh độ rộng cột
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Xuất file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            wb.write(fileOut);
            JOptionPane.showMessageDialog(PXDD,
                    "Xuất file Excel thành công!\nĐường dẫn: " + filePath,
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(PXDD,
                    "Lỗi khi xuất file Excel: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            wb.close();
        }
    }
}