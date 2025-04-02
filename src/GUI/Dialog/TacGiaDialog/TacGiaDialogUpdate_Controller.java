/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.TacGiaDialog;

import BUS.TacGiaBUS;
import DTO.TacGiaDTO;
import GUI.Dialog.ThongTinChungDialog.TacGiaDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Hi
 */
public class TacGiaDialogUpdate_Controller implements  ActionListener{
    private TacGiaDialogUpdate TGDU;
    private TacGiaBUS TGBUS = new TacGiaBUS();
    private final TacGiaDialog tgPanel;
    

    public TacGiaDialogUpdate_Controller(TacGiaDialogUpdate TGDU,TacGiaDialog tgPanel) {
        this.tgPanel = tgPanel;//luu panel de cap nhat bang
        this.TGDU=TGDU;
    }

    
    
            
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Lưu thông tin")) {
        String tenTacGiaMoi = TGDU.getTenTacGia();
        
        if (tenTacGiaMoi.isEmpty()) {
            JOptionPane.showMessageDialog(TGDU, "Vui lòng nhập tên tác giả!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //lay doi tuong tac gia hien tai ra
        TacGiaDTO tacGiaCanSua =TGDU.getTacGia();
        
         // Kiểm tra nếu tên mới bị trùng với tác giả khác
        if (TGBUS.isTenTacGiaTrung(tenTacGiaMoi, tacGiaCanSua.getMatacgia())) {
            JOptionPane.showMessageDialog(TGDU, "Tên tác giả đã tồn tại! Vui lòng chọn tên khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tacGiaCanSua.setHotentacgia(tenTacGiaMoi);//thay the ten tac gia bang cái nguoi dung nhap

        try {
            Boolean result = TGBUS.updateTacGia(tacGiaCanSua);
            if (result) {
                JOptionPane.showMessageDialog(TGDU, "Thêm tác giả thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                TGDU.dispose();
                tgPanel.capNhatBang(TGBUS.getTacGiaAll()); // Cập nhật lại danh sách
            } else {
                JOptionPane.showMessageDialog(TGDU, "Tác giả có thể đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(TGDU, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    
}
}
