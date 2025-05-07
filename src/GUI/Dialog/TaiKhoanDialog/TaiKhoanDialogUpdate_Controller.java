/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.TaiKhoanDialog;

import BUS.PhanQuyenBUS;
import BUS.TaiKhoanBUS;
import DTO.NhomQuyenDTO;
import DTO.TaiKhoanDTO;
import GUI.Dialog.PhanQuyenDialog.PhanQuyenDialogUpdate;
import GUI.View.PhanQuyenPanel;
import GUI.View.TaiKhoanPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class TaiKhoanDialogUpdate_Controller implements ActionListener{
    private TaiKhoanDialogUpdate TKDU;
    private TaiKhoanBUS TKBUS = new TaiKhoanBUS();
    private final TaiKhoanPanel tkPanel;
    


    public TaiKhoanDialogUpdate_Controller(TaiKhoanDialogUpdate TKDU,TaiKhoanPanel tkPanel) {
        this.tkPanel = tkPanel;//luu panel de cap nhat bang
        this.TKDU=TKDU;
    }


    
         
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Lưu thông tin")) {
        String tenTaiKhoanMoi = TKDU.getTenTaiKhoan();
        String matkhauMoi=TKDU.getMk();
        String nhomquyen=TKDU.getNhomquyen();
        
        if (tenTaiKhoanMoi.isEmpty()) {
            JOptionPane.showMessageDialog(TKDU, "Vui lòng nhập tên tài khoản mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (matkhauMoi.isEmpty()) {
            JOptionPane.showMessageDialog(TKDU, "Vui lòng nhập mật khẩu mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (nhomquyen.isEmpty()) {
            JOptionPane.showMessageDialog(TKDU, "Vui lòng nhập nhóm quyền!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int quyen= Integer.parseInt(TKDU.getNhomquyen());
        
        String trangthaimoiString=TKDU.getTrangThai();
        int trangthai;
        if(trangthaimoiString.equals("Đang hoạt động")){
            trangthai=1;
        }else{
            trangthai=0;
        }
        
        //lay doi tuong nhom quyen hien tai ra
            TaiKhoanDTO taiKhoanCanSua = TKDU.getTaiKhoanCanSua(); // ✅ Lấy đúng đối tượng

            
        
         // Kiểm tra nếu tên mới bị trùng với nhóm quyền khác
       if (!tenTaiKhoanMoi.equalsIgnoreCase(taiKhoanCanSua.getUsername())) {
            if (TKBUS.isTenTaiKhoanTrung(tenTaiKhoanMoi)) {
                JOptionPane.showMessageDialog(TKDU, "Tên tài khoản đã tồn tại! Vui lòng chọn tên khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
}
        taiKhoanCanSua.setUsername(tenTaiKhoanMoi);//thay the ten nhom quyen bang cái nguoi dung nhap
        taiKhoanCanSua.setMatkhau(matkhauMoi);
        taiKhoanCanSua.setManhomquyen(quyen);
        taiKhoanCanSua.setTrangthai(trangthai);

        try {
            Boolean result = TKBUS.updateTaiKhoan(taiKhoanCanSua);
            if (result) {
                JOptionPane.showMessageDialog(TKDU, "Sửa tài khoản thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                TKDU.dispose();
                tkPanel.capNhatBang(TKBUS.getTaiKhoanAll()); // Cập nhật lại danh sách
            } else {
                JOptionPane.showMessageDialog(TKDU, "Tài khoản có thể đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(TKDU, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
}}
