/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhieuXuatDialog;

import BUS.PhieuXuatBUS;
import DTO.ChiTietPhieuXuatDTO;
import DTO.PhieuXuatDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class PhieuXuatDialogDelete_Controller implements  ActionListener{
    private PhieuXuatDialogDelete PXDD;
    private PhieuXuatBUS pxBUS;
    public PhieuXuatDialogDelete_Controller(PhieuXuatDialogDelete PXDD){
        this.PXDD=PXDD;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       String sukien=e.getActionCommand();
       if (sukien.equals("Đóng")){
           PXDD.dispose();
       }
       if(sukien.equals("Xóa tất")){
           pxBUS=new PhieuXuatBUS();
           PhieuXuatDTO pxDTO=pxBUS.getPXById(PXDD.getMaPhieu());
           ArrayList<ChiTietPhieuXuatDTO> list_ctpx=pxBUS.getListCTPXById(PXDD.getMaPhieu());
           pxBUS.delete(pxDTO, list_ctpx);
           JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
           PXDD.dispose();
       }
    }
    
}
