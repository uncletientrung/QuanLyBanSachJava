/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.KhuyenMaiBUS;
import DTO.KhuyenMaiDTO;
import DTO.NhaCungCapDTO;
import GUI.Dialog.KhuyenMaiDialog.KhuyenMaiDialogAdd;
import GUI.Dialog.KhuyenMaiDialog.KhuyenMaiDialogAdd_Controller;
import GUI.Dialog.KhuyenMaiDialog.KhuyenMaiDialogDelete;
import GUI.Dialog.KhuyenMaiDialog.KhuyenMaiDialogUpdate;
import GUI.Dialog.KhuyenMaiDialog.KhuyenMaiDialogUpdate_Controller;
import GUI.Dialog.NhaCungCapDialog.NhaCungCapDialogDelete;
import GUI.Dialog.NhaCungCapDialog.NhaCungCapDialogUpdate;
import GUI.Dialog.NhaCungCapDialog.NhaCungCapDialogUpdate_Controller;
import GUI.View.KhuyenMaiPanel;
import GUI.WorkFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Hi
 */
public class KhuyenMaiController implements ActionListener,ListSelectionListener,DocumentListener{
    private KhuyenMaiPanel kmpn;
    private WorkFrame wk;
    private KhuyenMaiBUS khuyenmaiBUS;
    
    public KhuyenMaiController(KhuyenMaiPanel kmpn,WorkFrame wk){
        this.kmpn=kmpn;
        this.wk=wk;
        this.khuyenmaiBUS= new KhuyenMaiBUS();
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if ("Thêm".equals(action)) {  
        KhuyenMaiDialogAdd dialog = new KhuyenMaiDialogAdd(wk, kmpn); // Truyền panel vào
        KhuyenMaiDialogAdd_Controller controller = new KhuyenMaiDialogAdd_Controller(dialog, kmpn);
        dialog.setController(controller);
        dialog.setVisible(true);
        
        kmpn.refreshTable();
    }
        if("Sửa".equals(action)){
        
            KhuyenMaiDTO khuyenmaiDTO = kmpn.getSelectedKhuyenmai(); // Lấy đối tượng đã chọn
        if (khuyenmaiDTO == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chương trình khuyến mãi cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        KhuyenMaiDialogUpdate dialog = new KhuyenMaiDialogUpdate(wk, kmpn,khuyenmaiDTO);        
        KhuyenMaiDialogUpdate_Controller controller= new KhuyenMaiDialogUpdate_Controller(dialog, kmpn);
        dialog.setController(controller);
        dialog.setVisible(true);
        kmpn.refreshTable();
    }
        if("Xóa".equals(action)){
            KhuyenMaiDTO khuyenmaiDTOcanxoa = kmpn.getSelectedKhuyenmai();
            if (khuyenmaiDTOcanxoa == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Mở dialog xác nhận xóa
            KhuyenMaiDialogDelete dialog = new KhuyenMaiDialogDelete(wk, khuyenmaiDTOcanxoa);
            dialog.setVisible(true);

            // Nếu người dùng xác nhận xóa thì thực hiện xóa
            if (dialog.isXacNhan()) {
                boolean result = khuyenmaiBUS.deleteKhuyenMai(khuyenmaiDTOcanxoa.getMaKM());
                if (result) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    kmpn.capNhatBang(khuyenmaiBUS.getAllKhuyenMai());
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa thất bại! Nhóm quyền có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
            kmpn.refreshTable();
    
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
