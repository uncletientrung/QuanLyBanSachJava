/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.NhaCungCapDialog;

import BUS.NhaCungCapBUS;
import GUI.Dialog.ThongTinChungDialog.NhaCungCapDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class NhaCungCapDialogAdd_Controller implements ActionListener{
    private NhaCungCapDialogAdd NCCDA;
    private NhaCungCapBUS NCCBUS = new NhaCungCapBUS();
    private final NhaCungCapDialog NCCPanel;
    
    public NhaCungCapDialogAdd_Controller(NhaCungCapDialogAdd NCCDA,NhaCungCapDialog NCCPanel){
        this.NCCDA =NCCDA;
        this.NCCPanel=NCCPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Thêm dữ liệu")) {
        String tenncc = NCCDA.getTenNhaCungCap();
        String diachincc = NCCDA.getDiaChi();
        String sdtncc = NCCDA.getSDT();
        String emailncc = NCCDA.getEmail();
        
        if (tenncc.isEmpty()) {
            JOptionPane.showMessageDialog(NCCDA, "Vui lòng nhập tên nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (diachincc.isEmpty()) {
            JOptionPane.showMessageDialog(NCCDA, "Vui lòng nhập địa chỉ nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (sdtncc.isEmpty()) {
            JOptionPane.showMessageDialog(NCCDA, "Vui lòng nhập số điện thoại nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (emailncc.isEmpty()) {
            JOptionPane.showMessageDialog(NCCDA, "Vui lòng nhập email nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Boolean result = NCCBUS.themNhaCungCap(tenncc,diachincc,sdtncc,emailncc);
            if (result) {
                JOptionPane.showMessageDialog(NCCDA, "Thêm nhà cung cấp thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                NCCDA.dispose();
                NCCPanel.capNhatBang(NCCBUS.getNhaCungCapAll()); // Cập nhật lại danh sách
            } else {
                
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(NCCDA, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
        
    }
    
}
