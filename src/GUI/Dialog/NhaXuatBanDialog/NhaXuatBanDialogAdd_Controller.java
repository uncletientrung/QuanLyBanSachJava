/*
 * Click nbfs://SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.NhaXuatBanDialog;

import BUS.NhaXuatBanBUS;
import GUI.Dialog.ThongTinChungDialog.NhaXuatBanDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class NhaXuatBanDialogAdd_Controller implements ActionListener {
    private NhaXuatBanDialogAdd NXBDA;
    private NhaXuatBanBUS NXBBUS = new NhaXuatBanBUS();
    private final NhaXuatBanDialog NXBPanel;
    
    public NhaXuatBanDialogAdd_Controller(NhaXuatBanDialogAdd NXBDA, NhaXuatBanDialog NXBPanel) {
        this.NXBDA = NXBDA;
        this.NXBPanel = NXBPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Thêm dữ liệu")) {
            String tennxb = NXBDA.getTenNhaXuatBan();
            String diachinxb = NXBDA.getDiaChi();
            String sdtnxb = NXBDA.getSDT();
            String emailnxb = NXBDA.getEmail();
            
            if (tennxb.isEmpty()) {
                JOptionPane.showMessageDialog(NXBDA, "Vui lòng nhập tên nhà xuất bản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (diachinxb.isEmpty()) {
                JOptionPane.showMessageDialog(NXBDA, "Vui lòng nhập địa chỉ nhà xuất bản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (sdtnxb.isEmpty()) {
                JOptionPane.showMessageDialog(NXBDA, "Vui lòng nhập số điện thoại nhà xuất bản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (emailnxb.isEmpty()) {
                JOptionPane.showMessageDialog(NXBDA, "Vui lòng nhập email nhà xuất bản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Boolean result = NXBBUS.themNhaXuatBan(tennxb, diachinxb, sdtnxb, emailnxb);
                if (result) {
                    JOptionPane.showMessageDialog(NXBDA, "Thêm nhà xuất bản thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    NXBDA.dispose();
                    NXBPanel.capNhatBang(NXBBUS.getNhaXuatBanAll()); // Cập nhật lại danh sách
                } else {
                    
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(NXBDA, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
}