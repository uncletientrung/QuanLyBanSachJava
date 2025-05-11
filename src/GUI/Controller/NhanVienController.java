/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import GUI.Dialog.NhanVienDialog.NhanVienDialogAdd;
import GUI.Dialog.NhanVienDialog.NhanVienDialogDelete;
import GUI.Dialog.NhanVienDialog.NhanVienDialogDetail;
import GUI.Dialog.NhanVienDialog.NhanVienDialogUpdate;
import GUI.View.NhanVienPanel;
import GUI.WorkFrame;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Minnie
 */
public class NhanVienController implements ActionListener, ListSelectionListener, DocumentListener {
    private final NhanVienPanel np;
    private final WorkFrame wk;
    private NhanVienDialogUpdate nvdu;
    private NhanVienDialogDetail nvdd;
    private NhanVienDialogDelete nvddel;
    private NhanVienBUS nvbus;
    
    public NhanVienController(NhanVienPanel np, WorkFrame wk) {
        this.np = np;
        this.wk = wk;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String evt = e.getActionCommand(); //Nghe button
        String evtCbbox = (String) np.getCbbox().getSelectedItem(); //Nghe Combobox
        if (evt.equals("Thêm")) {
            new NhanVienDialogAdd(wk);
            np.refreshTableData();
        }
        if (evt.equals("Sửa")) {
            JTable tableS = np.getTable();
            int selectRow = tableS.getSelectedRow();
            
            if (selectRow >= 0) {
                String ma = tableS.getValueAt(selectRow, 0).toString();
                String ho = tableS.getValueAt(selectRow, 1).toString();
                String ten = tableS.getValueAt(selectRow, 2).toString();
                String gioitinh = tableS.getValueAt(selectRow, 3).toString();
                String sdt = tableS.getValueAt(selectRow, 4).toString();
                String trangthai = tableS.getValueAt(selectRow, 5).toString();
                String ngaysinh = tableS.getValueAt(selectRow, 6).toString();
            
                //Mở hộp thoại sửa và hiển thị thông tin
                nvdu = new NhanVienDialogUpdate(wk);
                nvdu.ShowInfo(ma, ho, ten, gioitinh, sdt, trangthai, ngaysinh);
                nvdu.setVisible(true);
                np.refreshTableData();
            } else {
                JOptionPane.showMessageDialog(null, "Hãy chọn một nhân viên", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if (evt.equals("Xóa")) {
            JTable tableS = np.getTable();
            int selectRow = tableS.getSelectedRow();
            
            if (selectRow < 0) {
                JOptionPane.showMessageDialog(null, "Hãy chọn một nhân viên", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                String ma = tableS.getValueAt(selectRow, 0).toString();
                nvddel = new NhanVienDialogDelete(wk, ma);
                np.refreshTableData();
            }
        }
        
        if (evt.equals("Chi tiết")) {
            JTable tableS = np.getTable();
            int selectRow = tableS.getSelectedRow();
            if (selectRow >= 0) {
                String ma = tableS.getValueAt(selectRow, 0).toString();
                String ho = tableS.getValueAt(selectRow, 1).toString();
                String ten = tableS.getValueAt(selectRow, 2).toString();
                String gioitinh = tableS.getValueAt(selectRow, 3).toString();
                String sdt = tableS.getValueAt(selectRow, 4).toString();
                String trangthai = tableS.getValueAt(selectRow, 5).toString();
                String ngaysinh = tableS.getValueAt(selectRow, 6).toString();
                
                //Mở hộp thoại chi tiết và hiển thị thông tin
                nvdd = new NhanVienDialogDetail(wk);
                nvdd.ShowInfo(ma, ho, ten, gioitinh, sdt, trangthai, ngaysinh);
                nvdd.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Hãy chọn một nhân viên", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if (evt.equals("Xuất Excel")) {
            try {
                ExportExcel();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("Lỗi xuất file Excel: " + ex.getMessage());
            }
        }
        
        if (evtCbbox.equals("Tất cả")) {  // Nếu chọn lại cbb thì refresh
            np.refreshTableData();
        } else if (evtCbbox.equals("Họ tên")) {
            np.refreshTableData();
        } else if (evtCbbox.equals("Số điện thoại")) {
            np.refreshTableData();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        np.FindNhanVien(np.getTxtFind().getText().toString(), np.getCbbox().getSelectedItem().toString());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        np.FindNhanVien(np.getTxtFind().getText().toString(), np.getCbbox().getSelectedItem().toString());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        np.FindNhanVien(np.getTxtFind().getText().toString(), np.getCbbox().getSelectedItem().toString());
    }
    
    public void ExportExcel() throws Exception {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Xuất Excel nhân viên");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Documents"));
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".xlsx");
            }

            @Override
            public String getDescription() {
                return "Excel Files (*.xlsx)";
            }
        });
        String defaultFileName = "NhanVien.xlsx";
        fileChooser.setSelectedFile(new File(defaultFileName));
        
        int userChoice = fileChooser.showDialog(np, "Save");
        if (userChoice != JFileChooser.APPROVE_OPTION) {
            return;
        }
        
        File fileToSave = fileChooser.getSelectedFile();
        String filePath = fileToSave.getAbsolutePath();
        if (!filePath.toLowerCase().endsWith(".xlsx")) {
            filePath += ".xlsx";
        }
        
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Nhân viên");
        JTable tableS = np.getTable();
        XSSFRow headerRow = sheet.createRow(1);
        String[] header = {"Mã", "Họ", "Tên", "Giới tính", "Số điện thoại", "Trạng thái", "Ngày sinh"};
        for (int i = 0; i < header.length; i++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(header[i]);
        }
        
        int numRowData = 2;
        for (int i = 0; i < tableS.getRowCount(); i++) {
            XSSFRow rowData = sheet.createRow(numRowData);
            XSSFCell cell1 = rowData.createCell(0);
            cell1.setCellValue(tableS.getValueAt(i, 0).toString());
            
            XSSFCell cell2 = rowData.createCell(1);
            cell2.setCellValue(tableS.getValueAt(i, 1).toString());
            
            XSSFCell cell3 = rowData.createCell(2);
            cell3.setCellValue(tableS.getValueAt(i, 2).toString());
            
            XSSFCell cell4 = rowData.createCell(3);
            cell4.setCellValue(tableS.getValueAt(i, 3).toString());
            
            XSSFCell cell5 = rowData.createCell(4);
            cell5.setCellValue(tableS.getValueAt(i, 4).toString());
            
            XSSFCell cell6 = rowData.createCell(5);
            cell6.setCellValue(tableS.getValueAt(i, 5).toString());
            
            XSSFCell cell7 = rowData.createCell(6);
            cell7.setCellValue(tableS.getValueAt(i, 6).toString());
            
            numRowData += 1;
        }
        
        for (int i = 0; i < header.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            wb.write(fileOut);
            JOptionPane.showMessageDialog(np,
                "Xuất file Excel thành công!",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(filePath));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(np,
                "Lỗi khi xuất file Excel: " + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
        } finally {
            wb.close();
        }
    }
}