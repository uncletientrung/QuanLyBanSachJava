/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import GUI.Dialog.TaiKhoanDialog.TaiKhoanDialogAdd;
import GUI.Dialog.TaiKhoanDialog.TaiKhoanDialogAdd_Controller;
import GUI.Dialog.TaiKhoanDialog.TaiKhoanDialogDelete;
import GUI.Dialog.TaiKhoanDialog.TaiKhoanDialogUpdate;
import GUI.Dialog.TaiKhoanDialog.TaiKhoanDialogUpdate_Controller;
import GUI.View.TaiKhoanPanel;
import GUI.WorkFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/**
 *
 * @author Hi
 */
public class TaiKhoanController implements ActionListener{
    private TaiKhoanPanel tkpn;
    private WorkFrame wk;
    private TaiKhoanBUS taiKhoanBUS;
    
    public TaiKhoanController(TaiKhoanPanel tkpn,WorkFrame wk){
        this.tkpn=tkpn;
        this.wk=wk;
        this.taiKhoanBUS= new TaiKhoanBUS();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if ("Thêm".equals(action)) {  
        TaiKhoanDialogAdd dialog = new TaiKhoanDialogAdd(wk, tkpn); // Truyền panel vào
        TaiKhoanDialogAdd_Controller controller = new TaiKhoanDialogAdd_Controller(dialog, tkpn);
        dialog.setController(controller);
        dialog.setVisible(true);
    }
        
        if("Sửa".equals(action)){
        
            TaiKhoanDTO taikhoanDTO = tkpn.getSelectedTaiKhoan(); // Lấy đối tượng đã chọn
        if (taikhoanDTO == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TaiKhoanDialogUpdate dialog = new TaiKhoanDialogUpdate(wk, tkpn,taikhoanDTO);        
        TaiKhoanDialogUpdate_Controller controller= new TaiKhoanDialogUpdate_Controller(dialog, tkpn);
        dialog.setController(controller);
        dialog.setVisible(true);
    }
        
        if("Xóa".equals(action)){
            TaiKhoanDTO taiKhoanCanXoa = tkpn.getSelectedTaiKhoan();
        if (taiKhoanCanXoa == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mở dialog xác nhận xóa
            TaiKhoanDialogDelete dialog = new TaiKhoanDialogDelete(wk, taiKhoanCanXoa);
        dialog.setVisible(true);

        // Nếu người dùng xác nhận xóa thì thực hiện xóa
        if (dialog.isXacNhan()) {
            boolean result = taiKhoanBUS.xoaTaiKhoan(taiKhoanCanXoa.getManv());
            if (result) {
                JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                tkpn.capNhatBang(taiKhoanBUS.getTaiKhoanAll());
            } else {
                JOptionPane.showMessageDialog(null, "Xóa thất bại! Tài khoản có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
//    
//        }
        }}}
    
    
    
