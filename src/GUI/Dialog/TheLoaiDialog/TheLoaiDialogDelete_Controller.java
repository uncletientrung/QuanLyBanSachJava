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
public class TheLoaiDialogDelete_Controller implements ActionListener {  // Changed class name
    private final TheLoaiDialogDelete TLDD;  // Changed variable name and type
    private final TheLoaiBUS TLBUS = new TheLoaiBUS();  // Changed variable name and type
    private final TheLoaiDialog tlPanel;  // Changed variable name and type

    public TheLoaiDialogDelete_Controller(TheLoaiDialogDelete TLDD, TheLoaiDialog tlPanel) {  // Changed parameter types and names
        this.TLDD = TLDD;  // Updated variable name
        this.tlPanel = tlPanel;  // Updated variable name
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Xóa")) {
            TheLoaiDTO theLoaiCanXoa = TLDD.getTheLoai(); // Changed variable name and method call
            if (theLoaiCanXoa == null) {
                JOptionPane.showMessageDialog(TLDD, "Lỗi: Không tìm thấy thể loại để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);  // Changed message text
                return;
            }

            int id = theLoaiCanXoa.getMatheloai();  // Changed method call
            boolean result = TLBUS.xoaTheLoai(id);  // Changed method call

            if (result) {
                JOptionPane.showMessageDialog(TLDD, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                tlPanel.loadData();  // Load lại danh sách thể loại trên giao diện  // Updated variable name
                TLDD.dispose(); // Đóng dialog sau khi xóa
            } else {
                JOptionPane.showMessageDialog(TLDD, "Xóa thất bại! Thể loại có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);  // Changed message text
            }
        }
    }
}
