/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.NhaCungCapBUS;
import BUS.TaiKhoanBUS;
import DTO.NhaCungCapDTO;
import GUI.Dialog.NhaCungCapDialog.NhaCungCapDialogAdd;
import GUI.Dialog.NhaCungCapDialog.NhaCungCapDialogAdd_Controller;
import GUI.Dialog.NhaCungCapDialog.NhaCungCapDialogDelete;
import GUI.Dialog.NhaCungCapDialog.NhaCungCapDialogUpdate;
import GUI.Dialog.NhaCungCapDialog.NhaCungCapDialogUpdate_Controller;
import GUI.Dialog.TaiKhoanDialog.TaiKhoanDialogAdd;
import GUI.Dialog.TaiKhoanDialog.TaiKhoanDialogAdd_Controller;
import GUI.Dialog.ThongTinChungDialog.NhaCungCapDialog;
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
        
//        if("Sửa".equals(action)){
//        
//            NhaCungCapDTO nhacungcapDTO = nccpn.getSelectedNhaCungCap(); // Lấy đối tượng đã chọn
//        if (nhacungcapDTO == null) {
//            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        NhaCungCapDialogUpdate dialog = new NhaCungCapDialogUpdate(wk, nccpn,nhacungcapDTO);        
//        NhaCungCapDialogUpdate_Controller controller= new NhaCungCapDialogUpdate_Controller(dialog, nccpn);
//        dialog.setController(controller);
//        dialog.setVisible(true);
//    }
//        
//        if("Xóa".equals(action)){
//            NhaCungCapDTO nhaCungCapCanXoa = nccpn.getSelectedNhaCungCap();
//        if (nhaCungCapCanXoa == null) {
//            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        // Mở dialog xác nhận xóa
//            NhaCungCapDialogDelete dialog = new NhaCungCapDialogDelete(wk, nhaCungCapCanXoa);
//        dialog.setVisible(true);
//
//        // Nếu người dùng xác nhận xóa thì thực hiện xóa
//        if (dialog.isXacNhan()) {
//            boolean result = nhaCungCapBUS.xoaNhaCungCap(nhaCungCapCanXoa.getMancc());
//            if (result) {
//                JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                nccpn.capNhatBang(nhaCungCapBUS.getNhaCungCapAll());
//            } else {
//                JOptionPane.showMessageDialog(null, "Xóa thất bại! Nhóm quyền có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    
//        }
    
    
    }}
