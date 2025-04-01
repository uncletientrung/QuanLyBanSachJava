/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.NhaCungCapDialog;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import GUI.Dialog.ThongTinChungDialog.NhaCungCapDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class NhaCungCapDialogUpdate_Controller implements  ActionListener{
    private NhaCungCapDialogUpdate NCCDU;
    private NhaCungCapBUS NCCBUS = new NhaCungCapBUS();
    private final NhaCungCapDialog NCCPannel;
    
    public NhaCungCapDialogUpdate_Controller(NhaCungCapDialogUpdate NCCDU,NhaCungCapDialog NCCPanel){
        this.NCCDU=NCCDU;
        this.NCCPannel=NCCPanel;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Lưu thông tin")) {
            String tenNhaCungCapMoi = NCCDU.getTenNhaCungCap();
            String diaChiNhaCungCapMoi= NCCDU.getDiaChi();
            String sdtmoi=NCCDU.getSDT();
            String emailmoi= NCCDU.getEmail();

            if (tenNhaCungCapMoi.isEmpty()) {
                JOptionPane.showMessageDialog(NCCDU, "Vui lòng nhập tên nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (diaChiNhaCungCapMoi.isEmpty()) {
                JOptionPane.showMessageDialog(NCCDU, "Vui lòng nhập địa chỉ nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (sdtmoi.isEmpty()) {
                JOptionPane.showMessageDialog(NCCDU, "Vui lòng nhập số điện thoại nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (emailmoi.isEmpty()) {
                JOptionPane.showMessageDialog(NCCDU, "Vui lòng nhập email nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //lay doi tuong nhom quyen hien tai ra
            NhaCungCapDTO nhaCungCapCanSua =NCCDU.getNhaCungCap();
             // Kiểm tra nếu tên mới bị trùng với nhóm quyền khác
            if (NCCBUS.isThongTinNhaCungCapTrung(diaChiNhaCungCapMoi,sdtmoi,emailmoi, nhaCungCapCanSua.getMancc())) {
                JOptionPane.showMessageDialog(NCCDU, "Nhà cung cấp đã tồn tại! Vui lòng chọn tên khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            nhaCungCapCanSua.setTenncc(tenNhaCungCapMoi);//thay the ten nhom quyen bang cái nguoi dung nhap
            nhaCungCapCanSua.setDiachincc(diaChiNhaCungCapMoi);
            nhaCungCapCanSua.setSdt(sdtmoi);
            nhaCungCapCanSua.setEmail(emailmoi);
            try {
            Boolean result = NCCBUS.updateNhaCungCap(nhaCungCapCanSua);
            if (result) {
                JOptionPane.showMessageDialog(NCCDU, "Sửa nhà cung cấp thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                NCCDU.dispose();
                NCCPannel.capNhatBang(NCCBUS.getNhaCungCapAll()); // Cập nhật lại danh sách
            } else {
                JOptionPane.showMessageDialog(NCCDU, "Nhà cung cấp có thể đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(NCCDU, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
     }
    
    
}}
