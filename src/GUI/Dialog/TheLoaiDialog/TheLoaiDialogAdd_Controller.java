/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.TheLoaiDialog;  // Changed package name

import BUS.TheLoaiBUS;  // Changed import
import GUI.Dialog.ThongTinChungDialog.TheLoaiDialog;  // Changed import
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class TheLoaiDialogAdd_Controller implements ActionListener {  // Changed class name
    private TheLoaiDialogAdd TLDA;  // Changed variable name and type
    private TheLoaiBUS TLBUS = new TheLoaiBUS();  // Changed variable name and type
    private final TheLoaiDialog theLoaiPanel;  // Changed variable name and type
    
    public TheLoaiDialogAdd_Controller(TheLoaiDialogAdd TLDA, TheLoaiDialog theLoaiPanel) {  // Changed parameter types and names
        this.TLDA = TLDA;
        this.theLoaiPanel = theLoaiPanel;  // Lưu lại panel để cập nhật bảng
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Thêm dữ liệu")) {
            String tenTheLoai = TLDA.getTenTheLoai();  // Changed variable name and method call
            
            if (tenTheLoai.isEmpty()) {
                JOptionPane.showMessageDialog(TLDA, "Vui lòng nhập tên Thể loại!", "Lỗi", JOptionPane.ERROR_MESSAGE);  // Changed message text
                return;
            }

            try {
                Boolean result = TLBUS.themTheLoai(tenTheLoai);  // Changed method call
                if (result) {
                    JOptionPane.showMessageDialog(TLDA, "Thêm thể loại thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);  // Changed message text
                    TLDA.dispose();
                    theLoaiPanel.capNhatBang(TLBUS.getTheLoaiAll()); // Cập nhật lại danh sách  // Changed method calls
                } else {
                    JOptionPane.showMessageDialog(TLDA, "Thể loại có thể đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);  // Changed message text
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(TLDA, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
}
