/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.ThongKeDialog;

import BUS.NhanVienBUS;
import BUS.SachBUS;
import GUI.Dialog.BookDialog.BookDialogDetail;
import GUI.Dialog.NhanVienDialog.NhanVienDialogDetail;
import GUI.WorkFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 *
 * @author Hi
 */
public class SachController implements ActionListener,ListSelectionListener,DocumentListener{
    private SachDialog khf;
    private WorkFrame wk;
    private SachBUS khBUS;
    private BookDialogDetail khdd;
    
    public SachController(SachDialog nccpn,WorkFrame wk){
        this.khf=nccpn;
        this.wk=wk;
        this.khBUS= new SachBUS();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
 if(action.equals("Chi tiết")){
        JTable tableB = khf.getTable();
        JTable tableB1 = khf.getTable1();
        int selectRow = tableB.getSelectedRow();
        if (selectRow >= 0) {  // Kiểm tra xem có hàng nào đang được chọn không
             String ma = tableB.getValueAt(selectRow, 0).toString();
          
            String ten = tableB1.getValueAt(selectRow, 1).toString();
             String n = tableB1.getValueAt(selectRow, 2).toString();
            String gt=tableB1.getValueAt(selectRow, 3).toString();
            String sdt = tableB1.getValueAt(selectRow, 4).toString();
            String tt = tableB1.getValueAt(selectRow, 5).toString();
            String ns = tableB1.getValueAt(selectRow, 6).toString();
            String nq = tableB1.getValueAt(selectRow, 7).toString();

            // Mở hộp thoại sửa và hiển thị thông tin
            khdd = new BookDialogDetail(wk);
            khdd.ShowInfo(ma, ten,n, gt, sdt,tt,ns,nq); // Cập nhật dữ liệu trước
            khdd.setVisible(true); // Hiển thị hộp thoại sau
            
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn một Sach! ","Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
        
        

        
    
        
}

    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
