/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.KhuyenMaiDialog;
import BUS.KhuyenMaiBUS;
import DTO.KhuyenMaiDTO;
import GUI.View.KhuyenMaiPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class KhuyenMaiDialogDelete_Controller implements ActionListener {
    private KhuyenMaiDialogDelete kmDialog;
    private KhuyenMaiBUS kmBUS = new KhuyenMaiBUS();
    private final KhuyenMaiPanel kmPanel;

    public KhuyenMaiDialogDelete_Controller(KhuyenMaiDialogDelete kmDialog, KhuyenMaiPanel kmPanel) {
        this.kmDialog = kmDialog;
        this.kmPanel = kmPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Xóa".equals(e.getActionCommand())) {
            KhuyenMaiDTO km = kmDialog.getKhuyenMai();
            if (km == null) {
                JOptionPane.showMessageDialog(kmDialog, "Lỗi: Không tìm thấy khuyến mãi để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = km.getMaKM();
            boolean result = kmBUS.deleteKhuyenMai(id);

            if (result) {
                JOptionPane.showMessageDialog(kmDialog, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                kmPanel.loadData();  // Reload lại bảng
                kmDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(kmDialog, "Xóa thất bại! Khuyến mãi có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

