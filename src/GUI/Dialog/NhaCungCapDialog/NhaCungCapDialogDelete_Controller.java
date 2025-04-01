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
public class NhaCungCapDialogDelete_Controller implements  ActionListener{
    private NhaCungCapDialogDelete NCCDD;
    private NhaCungCapBUS NCCBUS = new NhaCungCapBUS();
    private final NhaCungCapDialog NCCPanel;
    


    public NhaCungCapDialogDelete_Controller( NhaCungCapDialogDelete NCCDD,NhaCungCapDialog NCCPanel) {
        this.NCCPanel = NCCPanel;//luu panel de cap nhat bang
        this.NCCDD=NCCDD;
    }


    
    
            
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Xóa")) {
            NhaCungCapDTO nhaCungCapCanXoa = NCCDD.getNhaCungCap(); // Lấy nhóm quyền cần xóa
            if (nhaCungCapCanXoa == null) {
                JOptionPane.showMessageDialog(NCCDD, "Lỗi: Không tìm thấy nhà cung cấp để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = nhaCungCapCanXoa.getMancc();
            boolean result = NCCBUS.xoaNhaCungCap(id);

            if (result) {
                
                JOptionPane.showMessageDialog(NCCDD, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                NCCPanel.loadData();  // Load lại danh sách nhóm quyền trên giao diện
                NCCDD.dispose(); // Đóng dialog sau khi xóa
            } else {
                JOptionPane.showMessageDialog(NCCDD, "Xóa thất bại! Nhóm quyền có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    
}
