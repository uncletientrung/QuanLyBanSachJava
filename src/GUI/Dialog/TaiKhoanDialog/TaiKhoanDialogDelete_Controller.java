/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.TaiKhoanDialog;

import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import GUI.View.TaiKhoanPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class TaiKhoanDialogDelete_Controller implements ActionListener{
    private final TaiKhoanDialogDelete TKDD;  // Đúng tên class
    private final TaiKhoanBUS TKBUS = new TaiKhoanBUS();
    private final TaiKhoanPanel tkPanel;

    public TaiKhoanDialogDelete_Controller(TaiKhoanDialogDelete TKDD, TaiKhoanPanel tkPanel) {
        this.TKDD = TKDD;  
        this.tkPanel = tkPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Xóa")) {
            TaiKhoanDTO taiKhoanCanXoa = TKDD.getTaiKhoan(); // Lấy nhóm quyền cần xóa
            if (taiKhoanCanXoa == null) {
                JOptionPane.showMessageDialog(TKDD, "Lỗi: Không tìm thấy tài khoản để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = taiKhoanCanXoa.getManv();
            boolean result = TKBUS.xoaTaiKhoan(id);

            if (result) {
                
                JOptionPane.showMessageDialog(TKDD, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                tkPanel.loadData();  // Load lại danh sách nhóm quyền trên giao diện
                TKDD.dispose(); // Đóng dialog sau khi xóa
            } else {
                JOptionPane.showMessageDialog(TKDD, "Xóa thất bại! Tài khoản có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
