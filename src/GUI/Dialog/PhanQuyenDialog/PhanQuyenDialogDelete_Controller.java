package GUI.Dialog.PhanQuyenDialog;

import BUS.PhanQuyenBUS;
import DTO.NhomQuyenDTO;
import GUI.View.PhanQuyenPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class PhanQuyenDialogDelete_Controller implements ActionListener {
    private final PhanQuyenDialogDelete PQDD;  // Đúng tên class
    private final PhanQuyenBUS PQBUS = new PhanQuyenBUS();
    private final PhanQuyenPanel pqPanel;

    public PhanQuyenDialogDelete_Controller(PhanQuyenDialogDelete PQDD, PhanQuyenPanel pqPanel) {
        this.PQDD = PQDD;  // Sửa lỗi gán sai
        this.pqPanel = pqPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Xóa")) {
            NhomQuyenDTO nhomQuyenCanXoa = PQDD.getNhomQuyen(); // Lấy nhóm quyền cần xóa
            if (nhomQuyenCanXoa == null) {
                JOptionPane.showMessageDialog(PQDD, "Lỗi: Không tìm thấy nhóm quyền để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = nhomQuyenCanXoa.getManhomquyen();
            boolean result = PQBUS.xoaNhomQuyen(id);

            if (result) {
                
                JOptionPane.showMessageDialog(PQDD, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                pqPanel.loadData();  // Load lại danh sách nhóm quyền trên giao diện
                PQDD.dispose(); // Đóng dialog sau khi xóa
            } else {
                JOptionPane.showMessageDialog(PQDD, "Xóa thất bại! Nhóm quyền có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
