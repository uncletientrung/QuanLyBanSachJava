/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.TheLoaiDialog;  // Changed package name

import BUS.TheLoaiBUS;  // Changed import
import DTO.TheLoaiDTO;  // Changed import
import GUI.Dialog.ThongTinChungDialog.TheLoaiDialog;  // Changed import
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class TheLoaiDialogUpdate_Controller implements ActionListener {  // Changed class name
    private TheLoaiDialogUpdate TLDU;  // Changed variable name and type
    private TheLoaiBUS TLBUS = new TheLoaiBUS();  // Changed variable name and type
    private final TheLoaiDialog tlPanel;  // Changed variable name and type
    
    public TheLoaiDialogUpdate_Controller(TheLoaiDialogUpdate TLDU, TheLoaiDialog tlPanel) {  // Changed parameter types and names
        this.tlPanel = tlPanel; // Lưu panel để cập nhật bảng  // Updated variable name
        this.TLDU = TLDU;  // Updated variable name
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Lưu thông tin")) {
            String tenTheLoaiMoi = TLDU.getTenTheLoai();  // Changed variable name and method call
            
            if (tenTheLoaiMoi.isEmpty()) {
                JOptionPane.showMessageDialog(TLDU, "Vui lòng nhập tên thể loại!", "Lỗi", JOptionPane.ERROR_MESSAGE);  // Changed message text
                return;
            }
            // Lấy đối tượng thể loại hiện tại ra
            TheLoaiDTO theLoaiCanSua = TLDU.getTheLoai();  // Changed variable name and method call
            
            // Kiểm tra nếu tên mới bị trùng với thể loại khác
            if (TLBUS.isTenTheLoaiTrung(tenTheLoaiMoi, theLoaiCanSua.getMatheloai())) {  // Changed method call
                JOptionPane.showMessageDialog(TLDU, "Tên thể loại đã tồn tại! Vui lòng chọn tên khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);  // Changed message text
                return;
            }
            theLoaiCanSua.setTentheloai(tenTheLoaiMoi); // Thay thế tên thể loại bằng cái người dùng nhập  // Changed method call

            try {
                Boolean result = TLBUS.updateTheLoai(theLoaiCanSua);  // Changed method call
                if (result) {
                    JOptionPane.showMessageDialog(TLDU, "Thêm thể loại thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);  // Changed message text
                    TLDU.dispose();
                    tlPanel.capNhatBang(TLBUS.getTheLoaiAll()); // Cập nhật lại danh sách  // Changed variable name and method call
                } else {
                    JOptionPane.showMessageDialog(TLDU, "Thể loại có thể đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);  // Changed message text
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(TLDU, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
}
