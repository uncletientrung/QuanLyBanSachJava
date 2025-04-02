/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;
import BUS.TacGiaBUS;
import DTO.TacGiaDTO;
import GUI.Dialog.TacGiaDialog.TacGiaDialogAdd;
import GUI.Dialog.TacGiaDialog.TacGiaDialogAdd_Controller;
import GUI.Dialog.TacGiaDialog.TacGiaDialogDelete;
import GUI.Dialog.TacGiaDialog.TacGiaDialogUpdate;
import GUI.Dialog.TacGiaDialog.TacGiaDialogUpdate_Controller;
import GUI.Dialog.ThongTinChungDialog.TacGiaDialog;
import GUI.WorkFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Hi
 */
public class TacGiaController implements ActionListener,ListSelectionListener,DocumentListener{
    private TacGiaDialog tgp;
    private WorkFrame wk;
    private TacGiaBUS tacGiaBus;
    
   
     public TacGiaController(TacGiaDialog tgp,WorkFrame wk){
        this.tgp=tgp;
        this.wk=wk;
        this.tacGiaBus = new TacGiaBUS();

        
    }

@Override
public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand(); 

    if ("Thêm".equals(action)) {  
        TacGiaDialogAdd dialog = new TacGiaDialogAdd(wk, tgp); // Truyền panel vào
        TacGiaDialogAdd_Controller controller = new TacGiaDialogAdd_Controller(dialog, tgp);
        dialog.setController(controller);
        dialog.setVisible(true);
    }
    
    if("Sửa".equals(action)){
        
        TacGiaDTO tacGiaDTO = tgp.getSelectedTacGia(); // Lấy đối tượng đã chọn
        if (tacGiaDTO == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn tác giả cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TacGiaDialogUpdate dialog = new TacGiaDialogUpdate(wk, tgp,tacGiaDTO);        
        TacGiaDialogUpdate_Controller controller = new TacGiaDialogUpdate_Controller(dialog, tgp);
        dialog.setController(controller);
        dialog.setVisible(true);
    }
    if ("Xóa".equals(e.getActionCommand())) {
        TacGiaDTO tacGiaCanXoa = tgp.getSelectedTacGia();
        if (tacGiaCanXoa == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn tác giả để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mở dialog xác nhận xóa
        TacGiaDialogDelete dialog = new TacGiaDialogDelete(wk, tacGiaCanXoa);
        dialog.setVisible(true);

        // Nếu người dùng xác nhận xóa thì thực hiện xóa
        if (dialog.isXacNhan()) {
            boolean result = tacGiaBus.xoaTacGia(tacGiaCanXoa.getMatacgia());
            if (result) {
                JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                tgp.capNhatBang(tacGiaBus.getTacGiaAll());
            } else {
                JOptionPane.showMessageDialog(null, "Xóa thất bại! Nhóm quyền có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
