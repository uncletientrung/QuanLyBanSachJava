/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import GUI.Dialog.NhaCungCapDialog.NhaCungCapDialogAdd;
import GUI.Dialog.NhaCungCapDialog.NhaCungCapDialogAdd_Controller;
import GUI.Dialog.NhaCungCapDialog.NhaCungCapDialogDelete;
import GUI.Dialog.NhaCungCapDialog.NhaCungCapDialogUpdate;
import GUI.Dialog.NhaCungCapDialog.NhaCungCapDialogUpdate_Controller;
import GUI.Dialog.ThongTinChungDialog.NhaCungCapDialog;
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
public class NhaCungCapController implements ActionListener,ListSelectionListener,DocumentListener{
    private NhaCungCapDialog nccpn;
    private WorkFrame wk;
    private NhaCungCapBUS nhaCungCapBUS;
    
    public NhaCungCapController(NhaCungCapDialog nccpn,WorkFrame wk){
        this.nccpn=nccpn;
        this.wk=wk;
        this.nhaCungCapBUS= new NhaCungCapBUS();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if ("Thêm".equals(action)) {  
        NhaCungCapDialogAdd dialog = new NhaCungCapDialogAdd(wk, nccpn); // Truyền panel vào
        NhaCungCapDialogAdd_Controller controller = new NhaCungCapDialogAdd_Controller(dialog, nccpn);
        dialog.setController(controller);
        dialog.setVisible(true);
    }
        
        if("Sửa".equals(action)){
        
            NhaCungCapDTO nhacungcapDTO = nccpn.getSelectedNhaCungCap(); // Lấy đối tượng đã chọn
        if (nhacungcapDTO == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        NhaCungCapDialogUpdate dialog = new NhaCungCapDialogUpdate(wk, nccpn,nhacungcapDTO);        
        NhaCungCapDialogUpdate_Controller controller= new NhaCungCapDialogUpdate_Controller(dialog, nccpn);
        dialog.setController(controller);
        dialog.setVisible(true);
    }
        
        if("Xóa".equals(action)){
            NhaCungCapDTO nhaCungCapCanXoa = nccpn.getSelectedNhaCungCap();
        if (nhaCungCapCanXoa == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mở dialog xác nhận xóa
            NhaCungCapDialogDelete dialog = new NhaCungCapDialogDelete(wk, nhaCungCapCanXoa);
        dialog.setVisible(true);

        // Nếu người dùng xác nhận xóa thì thực hiện xóa
        if (dialog.isXacNhan()) {
            boolean result = nhaCungCapBUS.xoaNhaCungCap(nhaCungCapCanXoa.getMancc());
            if (result) {
                JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                nccpn.capNhatBang(nhaCungCapBUS.getNhaCungCapAll());
            } else {
                JOptionPane.showMessageDialog(null, "Xóa thất bại! Nhóm quyền có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
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
