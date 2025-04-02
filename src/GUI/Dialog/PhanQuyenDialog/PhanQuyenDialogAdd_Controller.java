/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhanQuyenDialog;

import BUS.PhanQuyenBUS;
import GUI.View.PhanQuyenPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class PhanQuyenDialogAdd_Controller implements  ActionListener{
    private PhanQuyenDialogAdd PQDA;
    private PhanQuyenBUS PQBUS = new PhanQuyenBUS();
    private final PhanQuyenPanel pqPanel;
    
    public PhanQuyenDialogAdd_Controller(PhanQuyenDialogAdd PQDA, PhanQuyenPanel pqPanel) {
    this.PQDA = PQDA;
    this.pqPanel = pqPanel;  // Lưu lại panel để cập nhật bảng
}



    
    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Thêm dữ liệu")) {
        String tenNhomQuyen = PQDA.getTenNhomQuyen();
        
        if (tenNhomQuyen.isEmpty()) {
            JOptionPane.showMessageDialog(PQDA, "Vui lòng nhập tên nhóm quyền!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Boolean result = PQBUS.themNhomQuyen(tenNhomQuyen);
            if (result) {
                JOptionPane.showMessageDialog(PQDA, "Thêm nhóm quyền thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                PQDA.dispose();
                pqPanel.capNhatBang(PQBUS.getNhomQuyenAll()); // Cập nhật lại danh sách
            } else {
                JOptionPane.showMessageDialog(PQDA, "Nhóm quyền có thể đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(PQDA, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}

}
