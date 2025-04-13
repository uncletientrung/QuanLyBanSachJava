/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.TaiKhoanDialog;
import BUS.PhanQuyenBUS;
import BUS.TaiKhoanBUS;
import GUI.View.TaiKhoanPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class TaiKhoanDialogAdd_Controller implements ActionListener{
    private TaiKhoanDialogAdd TKDA;
    private TaiKhoanBUS TKBUS = new TaiKhoanBUS();
    private final TaiKhoanPanel TKPanel;
    private PhanQuyenBUS pqBUS= new PhanQuyenBUS();
    
    public TaiKhoanDialogAdd_Controller(TaiKhoanDialogAdd TKDA,TaiKhoanPanel TKPanel){
        this.TKDA =TKDA;
        this.TKPanel=TKPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Thêm dữ liệu")) {
        String tendangnhap = TKDA.getTenDangNhap();
        String mk = TKDA.getMatKhau();
        String inputquyen = TKDA.getTenQuyen();
        
        
        
        if (tendangnhap.isEmpty()) {
            JOptionPane.showMessageDialog(TKDA, "Vui lòng nhập tên đăng nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (mk.isEmpty()) {
            JOptionPane.showMessageDialog(TKDA, "Vui lòng nhập mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (inputquyen.isEmpty()) {
            JOptionPane.showMessageDialog(TKDA, "Vui lòng nhập mã nhóm quyền!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
        int quyen = pqBUS.getIdquyenbyTen(inputquyen); // Chuyển đổi thành số nguyên
            // Tiếp tục xử lý với quyen...
             try {
                    Boolean result = TKBUS.themTaiKhoan(tendangnhap,mk,quyen);
                    if (result) {
                        JOptionPane.showMessageDialog(TKDA, "Thêm tài khoản thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        TKDA.dispose();
                        TKPanel.capNhatBang(TKBUS.getTaiKhoanAll()); // Cập nhật lại danh sách
                    } else {

                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(TKDA, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
        } catch (NumberFormatException q) {
            JOptionPane.showMessageDialog(TKDA, "Mã nhóm quyền phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        

       
    }
        
    }
    
}
