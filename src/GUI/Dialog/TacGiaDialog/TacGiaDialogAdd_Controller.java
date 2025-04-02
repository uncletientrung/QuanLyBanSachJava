/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.TacGiaDialog;

import BUS.TacGiaBUS;
import GUI.Dialog.ThongTinChungDialog.TacGiaDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class TacGiaDialogAdd_Controller implements ActionListener{
    private TacGiaDialogAdd TGDA;
    private TacGiaBUS TGBUS = new TacGiaBUS();
    private final TacGiaDialog tgiaPanel;
    
    public TacGiaDialogAdd_Controller(TacGiaDialogAdd TGDA, TacGiaDialog tgiaPanel) {
    this.TGDA = TGDA;
    this.tgiaPanel = tgiaPanel;  // Lưu lại panel để cập nhật bảng
}



    
    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Thêm dữ liệu")) {
        String tenTacGia = TGDA.getTenTacGia();
        
        if (tenTacGia.isEmpty()) {
            JOptionPane.showMessageDialog(TGDA, "Vui lòng nhập tên Tác giả!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Boolean result = TGBUS.themTacGia(tenTacGia);
            if (result) {
                JOptionPane.showMessageDialog(TGDA, "Thêm tác giả thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                TGDA.dispose();
                tgiaPanel.capNhatBang(TGBUS.getTacGiaAll()); // Cập nhật lại danh sách
            } else {
                JOptionPane.showMessageDialog(TGDA, "Tác giả có thể đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(TGDA, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
    
}
