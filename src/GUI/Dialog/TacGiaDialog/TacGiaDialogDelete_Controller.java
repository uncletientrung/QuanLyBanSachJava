/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.TacGiaDialog;
import BUS.TacGiaBUS;
import DTO.TacGiaDTO;
import GUI.Dialog.ThongTinChungDialog.TacGiaDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class TacGiaDialogDelete_Controller implements ActionListener{
    private final TacGiaDialogDelete TGDD;  // Đúng tên class
    private final TacGiaBUS TGBUS = new TacGiaBUS();
    private final TacGiaDialog tgPanel;

    public TacGiaDialogDelete_Controller(TacGiaDialogDelete TGDD, TacGiaDialog tgPanel) {
        this.TGDD = TGDD;  // Sửa lỗi gán sai
        this.tgPanel = tgPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Xóa")) {
            TacGiaDTO tacGiaCanXoa = TGDD.getTacGia(); // Lấy nhóm quyền cần xóa
            if (tacGiaCanXoa == null) {
                JOptionPane.showMessageDialog(TGDD, "Lỗi: Không tìm thấy tác giả để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = tacGiaCanXoa.getMatacgia();
            boolean result = TGBUS.xoaTacGia(id);

            if (result) {
                
                JOptionPane.showMessageDialog(TGDD, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                tgPanel.loadData();  // Load lại danh sách tg trên giao diện
                TGDD.dispose(); // Đóng dialog sau khi xóa
            } else {
                JOptionPane.showMessageDialog(TGDD, "Xóa thất bại! Tác giả có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
