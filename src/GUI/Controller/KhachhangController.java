/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import DTO.SachDTO;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import GUI.View.BookPanel;
import GUI.WorkFrame;
import  GUI.Dialog.KhachHangDialog.KhachHangDialogAdd;
import GUI.Dialog.KhachHangDialog.KhachHangDialogUpdate;
import GUI.Dialog.KhachHangDialog.KhachHangDialogDetail;
import GUI.Dialog.KhachHangDialog.KhachHangDialogDelete;
import GUI.View.KhachhangPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * @author DELL
 */
public class KhachhangController implements ActionListener,ListSelectionListener,DocumentListener{
    private KhachhangPanel khf;
    private WorkFrame wk;
    private KhachHangDialogUpdate khdu;
    private KhachHangDialogDetail khdd;
    private KhachHangDialogDelete khddelete;
    private KhachHangBUS khBUS;
    public KhachhangController(KhachhangPanel khf,WorkFrame wk){
        this.khf=khf;
        this.wk=wk;
    }
 
    @Override
public void actionPerformed(ActionEvent e){
    String sukien = e.getActionCommand();  // Lắng nghe sự kiện của Button
    String sukienCombobox=(String) khf.getCbbox().getSelectedItem(); // Lắng nghe sự kiện khi chọn Combobox
    if (sukien.equals("Thêm")) {
        new KhachHangDialogAdd(wk);
        khf.refreshTableData();
    } 
    if (sukien.equals("Sửa")) {
        JTable tableB = khf.getTable();
        int selectRow = tableB.getSelectedRow();

        if (selectRow >= 0) {  // Kiểm tra xem có hàng nào đang được chọn không
            String ma = tableB.getValueAt(selectRow, 0).toString();
            String ho= tableB.getValueAt(selectRow, 1).toString();
            String ten = tableB.getValueAt(selectRow, 2).toString();
            String email = tableB.getValueAt(selectRow, 3).toString();
            String ngaysinh = tableB.getValueAt(selectRow, 4).toString();
            String sdt = tableB.getValueAt(selectRow, 5).toString();
           

            // Mở hộp thoại sửa và hiển thị thông tin
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
        
            khdu = new KhachHangDialogUpdate(wk);
            khdu.ShowInfo(ma, ho, ten, email,ngaysinh, sdt); // Cập nhật dữ liệu trước
            khdu.setVisible(true); // Hiển thị hộp thoại sau
            khf.refreshTableData();
            
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn một khach hang!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    if (sukien.equals("Xóa")){
        JTable tableB = khf.getTable();
        int selectRow = tableB.getSelectedRow();
        
        if (selectRow==-1){
            JOptionPane.showMessageDialog(null, "Hãy chọn mot khach hang!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }else{
             String ma = tableB.getValueAt(selectRow, 0).toString();
            String ho= tableB.getValueAt(selectRow, 1).toString();
            String ten = tableB.getValueAt(selectRow, 2).toString();
            String email = tableB.getValueAt(selectRow, 3).toString();
            String ngaysinh = tableB.getValueAt(selectRow, 4).toString();
            String sdt = tableB.getValueAt(selectRow, 5).toString();
            khddelete=new KhachHangDialogDelete(wk,ma);
            khf.refreshTableData();
        }
    }
    if(sukien.equals("Chi tiết")){
        JTable tableB = khf.getTable();
        int selectRow = tableB.getSelectedRow();
        System.out.println("Ok");
        if (selectRow >= 0) {  // Kiểm tra xem có hàng nào đang được chọn không
             String ma = tableB.getValueAt(selectRow, 0).toString();
            String ho= tableB.getValueAt(selectRow, 1).toString();
            String ten = tableB.getValueAt(selectRow, 2).toString();
            String email = tableB.getValueAt(selectRow, 3).toString();
            String ngaysinh = tableB.getValueAt(selectRow, 4).toString();
            String sdt = tableB.getValueAt(selectRow, 5).toString();

            // Mở hộp thoại sửa và hiển thị thông tin
            khdd = new KhachHangDialogDetail(wk);
            khdd.ShowInfo(ma, ho, ten, email, ngaysinh,sdt); // Cập nhật dữ liệu trước
            khdd.setVisible(true); // Hiển thị hộp thoại sau
            
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn một khach hang! ","Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    if(!sukienCombobox.equals("")){
        khf.refreshTableData();
    }
    if(sukien.equals("Xuất Excel")){
        try {
                ExportExcel();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("Lỗi đọc file Excel: " + ex.getMessage());
            }
    }
}

    @Override
    public void valueChanged(ListSelectionEvent e){}// Không tác động đến ListSelectionListener này

    @Override
    public void insertUpdate(DocumentEvent e) {
        khf.FindKhach(khf.getTxfFind().getText(), khf.getCbbox().getSelectedItem().toString());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
         khf.FindKhach(khf.getTxfFind().getText(), khf.getCbbox().getSelectedItem().toString());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
         khf.FindKhach(khf.getTxfFind().getText(), khf.getCbbox().getSelectedItem().toString());
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
        
        int userchosser=fileChooser.showDialog(khf,"Save");
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
        JTable tableB = khf.getTable();
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
                 JOptionPane.showMessageDialog(khf,
                    "Xuất file Excel thành công! ",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(new File(filePath));// Mở file sau khi thêm
                    }
                if(Desktop.isDesktopSupported()){
                    Desktop.getDesktop().open(new File(filePath));// Mở file bằng đường dẫn sau khi thêm
            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(khf,
                    "Lỗi khi xuất file Excel: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            }finally {
            wb.close();
        }
        
        
    
    }    

}
