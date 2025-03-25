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
        bf.refreshTableData();
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
            bf.refreshTableData();
            
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn một cuốn sách!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    if (sukien.equals("Xóa")){
        JTable tableB = bf.getTable();
        int selectRow = tableB.getSelectedRow();
        
        if (selectRow==-1){
            JOptionPane.showMessageDialog(null, "Hãy chọn một cuốn sách!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }else{
            String maSach = tableB.getValueAt(selectRow, 0).toString();
            String tenSach = tableB.getValueAt(selectRow, 1).toString();
            String maNxb = tableB.getValueAt(selectRow, 2).toString();
            String maTacgia = tableB.getValueAt(selectRow, 3).toString();
            String matheloai = tableB.getValueAt(selectRow, 4).toString();
            String soluongton = tableB.getValueAt(selectRow, 5).toString();
            String namxuatban = tableB.getValueAt(selectRow, 6).toString();
            String dongia = tableB.getValueAt(selectRow, 7).toString();
            bddelete=new BookDialogDelete(wk,maSach);
            bf.refreshTableData();
        }
    }
    if(sukien.equals("Chi tiết")){
        JTable tableB = bf.getTable();
        int selectRow = tableB.getSelectedRow();
        System.out.println("Ok");
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
            bdd = new BookDialogDetail(wk);
            bdd.ShowInfo(maSach, tenSach, maNxb, maTacgia, matheloai, soluongton, namxuatban, dongia); // Cập nhật dữ liệu trước
            bdd.setVisible(true); // Hiển thị hộp thoại sau
            
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn một cuốn sách! ","Thông báo", JOptionPane.ERROR_MESSAGE);
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
        sachBUS=new SachBUS();
        ArrayList<SachDTO> List_OLD=sachBUS.getSachAll();
        bf.FilterTableData(List_OLD);
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
        

}
