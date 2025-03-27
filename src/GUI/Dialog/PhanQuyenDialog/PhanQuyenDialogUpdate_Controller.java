/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhanQuyenDialog;

import BUS.PhanQuyenBUS;
import DTO.NhomQuyenDTO;
import GUI.View.PhanQuyenPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class PhanQuyenDialogUpdate_Controller implements  ActionListener{
    private PhanQuyenDialogUpdate PQDU;
    private PhanQuyenBUS PQBUS = new PhanQuyenBUS();
    private final PhanQuyenPanel pqPanel;
    


    public PhanQuyenDialogUpdate_Controller(PhanQuyenDialogUpdate PQDU,PhanQuyenPanel pqPanel) {
        this.pqPanel = pqPanel;//luu panel de cap nhat bang
        this.PQDU=PQDU;
    }


    
    
            
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Cập nhật dữ liệu")) {
        String tenNhomQuyenMoi = PQDU.getTenNhomQuyen();
        
        if (tenNhomQuyenMoi.isEmpty()) {
            JOptionPane.showMessageDialog(PQDU, "Vui lòng nhập tên nhóm quyền!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //lay doi tuong nhom quyen hien tai ra
        NhomQuyenDTO nhomQuyenCanSua =PQDU.getNhomQuyen();
        
         // Kiểm tra nếu tên mới bị trùng với nhóm quyền khác
        if (PQBUS.isTenNhomQuyenTrung(tenNhomQuyenMoi, nhomQuyenCanSua.getManhomquyen())) {
            JOptionPane.showMessageDialog(PQDU, "Tên nhóm quyền đã tồn tại! Vui lòng chọn tên khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        nhomQuyenCanSua.setTennhomquyen(tenNhomQuyenMoi);//thay the ten nhom quyen bang cái nguoi dung nhap

        try {
            Boolean result = PQBUS.updateNhomQuyen(nhomQuyenCanSua);
            if (result) {
                JOptionPane.showMessageDialog(PQDU, "Thêm nhóm quyền thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                PQDU.dispose();
                pqPanel.capNhatBang(PQBUS.getNhomQuyenAll()); // Cập nhật lại danh sách
            } else {
                JOptionPane.showMessageDialog(PQDU, "Nhóm quyền có thể đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(PQDU, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
        
    }
    
    
}
