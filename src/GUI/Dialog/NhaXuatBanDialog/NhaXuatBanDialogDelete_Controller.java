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
public class NhaXuatBanDialogDelete_Controller implements ActionListener {
    private NhaXuatBanDialogDelete NXBDD;
    private NhaXuatBanBUS NXBBUS = new NhaXuatBanBUS();
    private final NhaXuatBanDialog NXBPanel;

    public NhaXuatBanDialogDelete_Controller(NhaXuatBanDialogDelete NXBDD, NhaXuatBanDialog NXBPanel) {
        this.NXBPanel = NXBPanel; // Lưu panel để cập nhật bảng
        this.NXBDD = NXBDD;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Xóa")) {
            NhaXuatBanDTO nhaXuatBanCanXoa = NXBDD.getNhaXuatBan(); // Lấy nhà xuất bản cần xóa
            if (nhaXuatBanCanXoa == null) {
                JOptionPane.showMessageDialog(NXBDD, "Lỗi: Không tìm thấy nhà xuất bản để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = nhaXuatBanCanXoa.getManxb();
            boolean result = NXBBUS.xoaNhaXuatBan(id);

            if (result) {
                JOptionPane.showMessageDialog(NXBDD, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                NXBPanel.loadData();  // Load lại danh sách nhà xuất bản trên giao diện
                NXBDD.dispose(); // Đóng dialog sau khi xóa
            } else {
                JOptionPane.showMessageDialog(NXBDD, "Xóa thất bại! Nhà xuất bản có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}