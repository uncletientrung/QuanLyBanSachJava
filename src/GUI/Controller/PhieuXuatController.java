/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.PhieuXuatBUS;
import BUS.SachBUS;
import DTO.ChiTietPhieuXuatDTO;
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
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient;
/**
 *
 * @author DELL
 */
public class PhieuXuatController implements ActionListener, ChangeListener, DocumentListener,PropertyChangeListener,ListSelectionListener{
    private PhieuXuatPanel PxP;
    private WorkFrame WF;
    private PhieuXuatBUS pxBUS=new PhieuXuatBUS();
    private PhieuXuatDialogDelete PXDD;
    private PhieuXuatDialogDetail PXDDetail;
    private PhieuXuatDialogAdd currentPhieuXuatDialogAdd;
    private JScrollPane scrollPanePhieuXuatAdd;
    private int tabCount = 1;
    private boolean isRemoving = false;
    private ArrayList<PhieuXuatDTO> listPX=pxBUS.getAll();
    private SachBUS sBUS;

    public PhieuXuatController(PhieuXuatPanel PxP, WorkFrame Wf) {
        this.PxP = PxP;
        this.WF = Wf;
    }
// ActionListener
    public void actionPerformed(ActionEvent e) {
        String sukien = e.getActionCommand();
        if (sukien.equals("Trang chủ")) {
            // Hiện lại các nút và Panel Tìm kiếm khi về trang chủ
            PxP.getBtndelete().setVisible(true); 
            PxP.getBtndelete().setVisible(true);
            PxP.getBtndetail().setVisible(true);
            PxP.getBtnexport().setVisible(true);
            PxP.getToolBar_Right().setVisible(true);
            
            PxP.getPanelCenter().removeAll(); // Xóa ScrollPane hiện tại
            PxP.getScrollPanePhieuXuat().setViewportView(PxP.getTablePhieuXuat());
            
            PxP.getPanelCenter().setLayout(new BorderLayout());
            // Thêm lại cái label không biết sao khi thêm bằng cách gọi Panel không hiển thị cái này
            JLabel titleLabel = new JLabel("THÔNG TIN PHIẾU XUẤT", SwingConstants.CENTER);
            Font titleFont = new Font("Arial", Font.BOLD, 20);
            titleLabel.setFont(titleFont);
            titleLabel.setForeground(Color.WHITE);
            JPanel titlePanel = new JPanel(new BorderLayout());
            titlePanel.setBackground(new Color(72, 118, 255));
            titlePanel.add(titleLabel, BorderLayout.CENTER);
            titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            PxP.getBottomPanel().setLayout(new BorderLayout());
            PxP.getBottomPanel().add(titlePanel,BorderLayout.NORTH);
            PxP.getBottomPanel().add(PxP.getScrollPaneBookDetails(),BorderLayout.CENTER);
            
            PxP.getPanelCenter().add(PxP.getBottomPanel(),BorderLayout.CENTER);
            PxP.getPanelCenter().add(PxP.getTopPanel(),BorderLayout.NORTH);
            PxP.getPanelCenter().add(PxP.getTongTienPanel(),BorderLayout.SOUTH);
            PxP.refreshTablePx();
            PxP.resetHome();
        }
        if (sukien.equals("Thêm")) {
            PxP.getBtndelete().setVisible(false); 
            PxP.getBtndelete().setVisible(false);
            PxP.getBtndetail().setVisible(false);
            PxP.getBtnexport().setVisible(false);
            PxP.getToolBar_Right().setVisible(false);
            
            PxP.getPanelCenter().removeAll();// Xóa đi cái Table hiện tại
            if (PxP.getTabbedPane().getTabCount() == 0) { // Nếu chưa có tab nào thì mới tạo 
                ThemTabAdd();
                ThemTabPlus();
            }
                PxP.getPanelCenter().add(PxP.getTabbedPane());
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
                PxP.refreshTablePx();
            } else {
                JOptionPane.showMessageDialog(null, "Vui Lòng chọn hóa đơn muốn xem chi tiêt", "Error", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null, "Vui Lòng chọn hóa đơn muốn xóa", "Error", JOptionPane.ERROR_MESSAGE);
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
        
        // Thêm action cho tìm kiếm nâng cao CBB NhanVien và KhachHang
          
        
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
// ChangedListener
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

        if (userSelection != JFileChooser.APPROVE_OPTION) { // Kiểm tra có ấn save không nếu canel thì return
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
    // DocumentListener
    @Override
    public void insertUpdate(DocumentEvent e) {
        PxP.Filter();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        PxP.Filter();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        PxP.Filter();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Thêm action cho tìm kiếm nâng cao 2 cái datez
        if(PxP.getDateStart().getDate()!=null){
            PxP.Filter();
        }else{
            PxP.Filter();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            Object sukien=e.getSource(); // Lắng nghe sự kiện
            
            if(sukien == PxP.getTablePhieuXuat().getSelectionModel()){
                PxP.getDataBookDetails().setRowCount(0);
                pxBUS=new PhieuXuatBUS();
                sBUS=new SachBUS();
                int SelectRow=PxP.getTablePhieuXuat().getSelectedRow();
                if(SelectRow !=-1){
                    int mapx=Integer.parseInt(PxP.getTablePhieuXuat().getValueAt(SelectRow, 0).toString());
                    ArrayList<ChiTietPhieuXuatDTO> ListCTPX=pxBUS.getListCTPXById(mapx);
                    PxP.getTxfTongTien().setText(PxP.getTablePhieuXuat().getValueAt(SelectRow, 4).toString());
                    int STT=1;
                    for(ChiTietPhieuXuatDTO ctpx: ListCTPX){
                        String maSach=ctpx.getMasach().toString();
                        String tenSach= sBUS.getSachById(ctpx.getMasach()).getTensach();
                        String donGia=String.valueOf(ctpx.getDongia());
                        String soLuong=String.valueOf(ctpx.getSoluong());
                        String thanhTien=String.valueOf(ctpx.getDongia()*ctpx.getSoluong());
                        PxP.getDataBookDetails().addRow(new Object[]{STT,maSach,tenSach,donGia,soLuong,thanhTien});
                        STT+=1;
                    }
                }
            }
            
            else if(sukien == PxP.getJList_nv().getSelectionModel()){ // Nếu tích vào txf Jlistnhaan vieen thì gọi action này
                List<String> selected = PxP.getJList_nv().getSelectedValuesList();
                if (selected.contains("Tất cả") || selected.isEmpty()) {
                    PxP.getTxfEmployee().setText("Tất cả");
                    PxP.getJList_nv().setSelectedIndex(0); // Chọn mặc định trong Jlist là  tất cả
                } else {
                   PxP.getTxfEmployee().setText(selected.size() + " nhân viên được chọn");
                }
                PxP.Filter();
            }else if( sukien == PxP.getJList_kh().getSelectionModel()){
                 List<String> selected = PxP.getJList_kh().getSelectedValuesList();
                if (selected.contains("Tất cả") || selected.isEmpty()) {
                    PxP.getTxfCustomer().setText("Tất cả");
                    PxP.getJList_kh().setSelectedIndex(0); 
                } else {
                   PxP.getTxfCustomer().setText(selected.size() + " khách hàng được chọn");
                }
                PxP.Filter();
            }
            
        }
        
    }
}