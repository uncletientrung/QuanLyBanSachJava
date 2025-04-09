/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.PhanQuyenBUS;
import DAO.ChiTietQuyenDAO;
import DTO.NhomQuyenDTO;
import GUI.Dialog.PhanQuyenDialog.PhanQuyenDialogAdd;
import GUI.Dialog.PhanQuyenDialog.PhanQuyenDialogAdd_Controller;
import GUI.Dialog.PhanQuyenDialog.PhanQuyenDialogDelete;
import GUI.Dialog.PhanQuyenDialog.PhanQuyenDialogDetail;
import GUI.Dialog.PhanQuyenDialog.PhanQuyenDialogDetail_Controller;
import GUI.Dialog.PhanQuyenDialog.PhanQuyenDialogUpdate;
import GUI.Dialog.PhanQuyenDialog.PhanQuyenDialogUpdate_Controller;
import GUI.View.PhanQuyenPanel;
import GUI.WorkFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Hi
 */
public class PhanQuyenController implements ActionListener,ListSelectionListener,DocumentListener{
    
    private PhanQuyenPanel pqp;
    private WorkFrame wk;
    private PhanQuyenBUS phanQuyenBus;
    
    
    public PhanQuyenController(PhanQuyenPanel pqp,WorkFrame wk){
        this.pqp=pqp;
        this.wk=wk;
        this.phanQuyenBus = new PhanQuyenBUS();

        
    }

@Override
public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand(); 

    if ("Thêm".equals(action)) {  
        PhanQuyenDialogAdd dialog = new PhanQuyenDialogAdd(wk, pqp); // Truyền panel vào
        PhanQuyenDialogAdd_Controller controller = new PhanQuyenDialogAdd_Controller(dialog, pqp);
        dialog.setController(controller);
        dialog.setVisible(true);
    }
    
    if("Sửa".equals(action)){
        
        NhomQuyenDTO nhomQuyenDTO = pqp.getSelectedNhomQuyen(); // Lấy đối tượng đã chọn
        if (nhomQuyenDTO == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhóm quyền cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<Integer> danhSachChucNang = new ChiTietQuyenDAO().getDanhSachChucNang(nhomQuyenDTO.getManhomquyen());
        nhomQuyenDTO.setDsMaChucNang((ArrayList<Integer>) danhSachChucNang);
        


        PhanQuyenDialogUpdate dialog = new PhanQuyenDialogUpdate(wk, pqp,nhomQuyenDTO);        
        PhanQuyenDialogUpdate_Controller controller = new PhanQuyenDialogUpdate_Controller(dialog, pqp);
        dialog.setController(controller);
        dialog.setVisible(true);
    }
    if ("Xóa".equals(e.getActionCommand())) {
        NhomQuyenDTO nhomQuyenCanXoa = pqp.getSelectedNhomQuyen();
        if (nhomQuyenCanXoa == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhóm quyền để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mở dialog xác nhận xóa
        PhanQuyenDialogDelete dialog = new PhanQuyenDialogDelete(wk, nhomQuyenCanXoa);
        dialog.setVisible(true);

        // Nếu người dùng xác nhận xóa thì thực hiện xóa
        if (dialog.isXacNhan()) {
            boolean result = phanQuyenBus.xoaNhomQuyen(nhomQuyenCanXoa.getManhomquyen());
            if (result) {
                JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                pqp.capNhatBang(phanQuyenBus.getNhomQuyenAll());
            } else {
                JOptionPane.showMessageDialog(null, "Xóa thất bại! Nhóm quyền có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    if("Chi tiết".equals(e.getActionCommand())){
        NhomQuyenDTO nhomQuyen = pqp.getSelectedNhomQuyen(); 
        PhanQuyenDialogDetail dialog = new PhanQuyenDialogDetail(wk);
        PhanQuyenDialogDetail_Controller controller = new PhanQuyenDialogDetail_Controller(dialog, nhomQuyen);
        dialog.setVisible(true);

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
