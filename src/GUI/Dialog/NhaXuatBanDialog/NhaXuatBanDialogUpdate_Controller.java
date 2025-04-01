/*
 * Click nbfs://SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.NhaXuatBanDialog;

import BUS.NhaXuatBanBUS;
import DTO.NhaXuatBanDTO;
import GUI.Dialog.ThongTinChungDialog.NhaXuatBanDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class NhaXuatBanDialogUpdate_Controller implements ActionListener {
    private NhaXuatBanDialogUpdate NXBDU;
    private NhaXuatBanBUS NXBBUS = new NhaXuatBanBUS();
    private final NhaXuatBanDialog NXBPannel;
    
    public NhaXuatBanDialogUpdate_Controller(NhaXuatBanDialogUpdate NXBDU, NhaXuatBanDialog NXBPanel) {
        this.NXBDU = NXBDU;
        this.NXBPannel = NXBPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Lưu thông tin")) {
            String tenNhaXuatBanMoi = NXBDU.getTenNhaXuatBan();
            String diaChiNhaXuatBanMoi = NXBDU.getDiaChi();
            String sdtmoi = NXBDU.getSDT();
            String emailmoi = NXBDU.getEmail();

            if (tenNhaXuatBanMoi.isEmpty()) {
                JOptionPane.showMessageDialog(NXBDU, "Vui lòng nhập tên nhà xuất bản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (diaChiNhaXuatBanMoi.isEmpty()) {
                JOptionPane.showMessageDialog(NXBDU, "Vui lòng nhập địa chỉ nhà xuất bản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (sdtmoi.isEmpty()) {
                JOptionPane.showMessageDialog(NXBDU, "Vui lòng nhập số điện thoại nhà xuất bản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (emailmoi.isEmpty()) {
                JOptionPane.showMessageDialog(NXBDU, "Vui lòng nhập email nhà xuất bản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Lấy đối tượng nhà xuất bản hiện tại ra
            NhaXuatBanDTO nhaXuatBanCanSua = NXBDU.getNhaXuatBan();
            // Kiểm tra nếu thông tin mới bị trùng với nhà xuất bản khác
            if (NXBBUS.isThongTinNhaXuatBanTrung(diaChiNhaXuatBanMoi, sdtmoi, emailmoi, nhaXuatBanCanSua.getManxb())) {
                JOptionPane.showMessageDialog(NXBDU, "Nhà xuất bản đã tồn tại! Vui lòng chọn thông tin khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            nhaXuatBanCanSua.setTennxb(tenNhaXuatBanMoi); // Thay thế tên nhà xuất bản bằng cái người dùng nhập
            nhaXuatBanCanSua.setDiachinxb(diaChiNhaXuatBanMoi);
            nhaXuatBanCanSua.setSdt(sdtmoi);
            nhaXuatBanCanSua.setEmail(emailmoi);
            try {
                Boolean result = NXBBUS.updateNhaXuatBan(nhaXuatBanCanSua);
                if (result) {
                    JOptionPane.showMessageDialog(NXBDU, "Sửa nhà xuất bản thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    NXBDU.dispose();
                    NXBPannel.capNhatBang(NXBBUS.getNhaXuatBanAll()); // Cập nhật lại danh sách
                } else {
                    JOptionPane.showMessageDialog(NXBDU, "Nhà xuất bản có thể đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(NXBDU, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
}
