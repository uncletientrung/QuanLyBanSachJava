/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.NhaXuatBanBUS;
import DTO.NhaXuatBanDTO;
import GUI.Dialog.NhaXuatBanDialog.NhaXuatBanDialogAdd;
import GUI.Dialog.NhaXuatBanDialog.NhaXuatBanDialogAdd_Controller;
import GUI.Dialog.NhaXuatBanDialog.NhaXuatBanDialogDelete;
import GUI.Dialog.NhaXuatBanDialog.NhaXuatBanDialogUpdate;
import GUI.Dialog.NhaXuatBanDialog.NhaXuatBanDialogUpdate_Controller;
import GUI.Dialog.ThongTinChungDialog.NhaXuatBanDialog;
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
public class NhaXuatBanController implements ActionListener, ListSelectionListener, DocumentListener {
    private NhaXuatBanDialog nxbpn;
    private WorkFrame wk;
    private NhaXuatBanBUS nhaXuatBanBUS;
    
    public NhaXuatBanController(NhaXuatBanDialog nxbpn, WorkFrame wk) {
        this.nxbpn = nxbpn;
        this.wk = wk;
        this.nhaXuatBanBUS = new NhaXuatBanBUS();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if ("Thêm".equals(action)) {  
            NhaXuatBanDialogAdd dialog = new NhaXuatBanDialogAdd(wk, nxbpn); // Truyền panel vào
            NhaXuatBanDialogAdd_Controller controller = new NhaXuatBanDialogAdd_Controller(dialog, nxbpn);
            dialog.setController(controller);
            dialog.setVisible(true);
        }
        
        if ("Sửa".equals(action)) {
            NhaXuatBanDTO nhaxuatbanDTO = nxbpn.getSelectedNhaXuatBan(); // Lấy đối tượng đã chọn
            if (nhaxuatbanDTO == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà xuất bản cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            NhaXuatBanDialogUpdate dialog = new NhaXuatBanDialogUpdate(wk, nxbpn, nhaxuatbanDTO);        
            NhaXuatBanDialogUpdate_Controller controller = new NhaXuatBanDialogUpdate_Controller(dialog, nxbpn);
            dialog.setController(controller);
            dialog.setVisible(true);
        }
        
        if ("Xóa".equals(action)) {
            NhaXuatBanDTO nhaXuatBanCanXoa = nxbpn.getSelectedNhaXuatBan();
            if (nhaXuatBanCanXoa == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà xuất bản để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Mở dialog xác nhận xóa
            NhaXuatBanDialogDelete dialog = new NhaXuatBanDialogDelete(wk, nhaXuatBanCanXoa);
            dialog.setVisible(true);

            // Nếu người dùng xác nhận xóa thì thực hiện xóa
            if (dialog.isXacNhan()) {
                boolean result = nhaXuatBanBUS.xoaNhaXuatBan(nhaXuatBanCanXoa.getManxb());
                if (result) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    nxbpn.capNhatBang(nhaXuatBanBUS.getNhaXuatBanAll());
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa thất bại! Nhà xuất bản có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}