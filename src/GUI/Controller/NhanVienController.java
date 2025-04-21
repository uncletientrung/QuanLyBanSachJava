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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Minnie
 */
public class NhanVienController implements ActionListener, ListSelectionListener, DocumentListener{
    private final NhanVienPanel np;
    private final WorkFrame wk;
    private NhanVienDialogUpdate nvdu;
    private NhanVienDialogDetail nvdd;
    private NhanVienDialogDelete nvddel;
    private NhanVienBUS nvbus;
    
    
    
    public NhanVienController(NhanVienPanel np, WorkFrame wk){
        this.np = np;
        this.wk = wk;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String evt = e.getActionCommand(); //Nghe button
        String evtCbbox = (String) np.getCbbox().getSelectedItem(); //Nghe Combobox
        if (evt.equals("Thêm")){
            new NhanVienDialogAdd(wk);
            np.refreshTableData();
        }
        if(evt.equals("Sửa")){
            JTable tableS = np.getTable();
            int selectRow = tableS.getSelectedRow();
            
            if(selectRow >= 0){
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
                JOptionPane.showMessageDialog(null,"Hãy chọn một nhân viên","Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(evt.equals("Xóa")){
            JTable tableS = np.getTable();
            int selectRow = tableS.getSelectedRow();
            
            if(selectRow < 0){
                JOptionPane.showMessageDialog(null ,"Hãy chọn một nhân viên", "Thông báo",JOptionPane.ERROR_MESSAGE);
            } else {
                String ma = tableS.getValueAt(selectRow, 0).toString();
                nvddel = new NhanVienDialogDelete(wk, ma);
                np.refreshTableData();
            }
        }
        
        if(evt.equals("Chi tiết")){
            JTable tableS = np.getTable();
            int selectRow = tableS.getSelectedRow();
            if(selectRow >= 0){
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
                JOptionPane.showMessageDialog(null ,"Hãy chọn một nhân viên", "Thông báo",JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(evtCbbox.equals("Tất cả")){  // Nếu chọn lại cbb thì refresh
            np.refreshTableData();
        }else if(evtCbbox.equals("Họ tên")){
             np.refreshTableData();
        }else if(evtCbbox.equals("Số điện thoại")){
             np.refreshTableData();
        }
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

//    @Override
//    public void insertUpdate(DocumentEvent e) {
//        if(np.getCbbox().getSelectedItem().equals("Tất cả") || np.getCbbox().getSelectedItem().equals("Họ tên")) {
//            np.FindAll(np.getTxtFind().getText());
//        }else{      // Nếu chọn tìm SDT thì gọi hàm FindSDT
//            np.FindSDT(np.getTxtFind().getText());
//        }
//    }
//
//    @Override
//    public void removeUpdate(DocumentEvent e) {
//        if(np.getCbbox().getSelectedItem().equals("Tất cả") || np.getCbbox().getSelectedItem().equals("Họ tên")) {
//            np.FindAll(np.getTxtFind().getText());
//        }else{      // Nếu chọn tìm SDT thì gọi hàm FindSDT
//            np.FindSDT(np.getTxtFind().getText());
//        }
//    }
//
//    @Override
//    public void changedUpdate(DocumentEvent e) {
//        if(np.getCbbox().getSelectedItem().equals("Tất cả") || np.getCbbox().getSelectedItem().equals("Họ tên")) {
//            np.FindAll(np.getTxtFind().getText());
//        }else{      // Nếu chọn tìm SDT thì gọi hàm FindSDT
//            np.FindSDT(np.getTxtFind().getText());
//        }
//    }

    @Override
    public void insertUpdate(DocumentEvent e) {
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }
    
}
