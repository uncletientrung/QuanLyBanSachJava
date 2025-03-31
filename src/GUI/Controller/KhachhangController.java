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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    
//    if(sukienCombobox.equals("Giá thấp đến cao ⬆")){
//        khBUS=new KhachHangBUS();
//        ArrayList<KhachHangDTO> List_Sort=khBUS.LowToHighofPrice(khf.getListKhachHang());
//        bf.FilterTableData(List_Sort);
//    }
//    if(sukienCombobox.equals("Giá cao đến thấp ⬇")){
//        sachBUS=new SachBUS();
//        ArrayList<SachDTO> List_Sort=sachBUS.HighToLowofPrice(bf.getListSach());
//        bf.FilterTableData(List_Sort);
//    }
//    if(sukienCombobox.equals("NXB thấp đến cao ⬆")){
//        sachBUS=new SachBUS();
//        ArrayList<SachDTO> List_Sort=sachBUS.LowToHighofNXB(bf.getListSach());
//        bf.FilterTableData(List_Sort);
//    }
//    if(sukienCombobox.equals("NXB cao đến thấp ⬇")){
//        sachBUS=new SachBUS();
//        ArrayList<SachDTO> List_Sort=sachBUS.HighToLowofNXB(bf.getListSach());
//        bf.FilterTableData(List_Sort);
//    }
//    if(sukienCombobox.equals("Tất cả")){
//        sachBUS=new SachBUS();
//        ArrayList<SachDTO> List_OLD=sachBUS.getSachAll();
//        bf.FilterTableData(List_OLD);
//    }
}

    @Override
    public void valueChanged(ListSelectionEvent e){}// Không tác động đến ListSelectionListener này

    @Override
    public void insertUpdate(DocumentEvent e) {
        khf.FindTableData(khf.getTxfFind().getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        khf.FindTableData(khf.getTxfFind().getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        

}
