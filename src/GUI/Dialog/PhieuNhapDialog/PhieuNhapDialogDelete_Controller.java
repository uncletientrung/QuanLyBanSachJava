/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhieuNhapDialog;
import BUS.PhieuNhapBUS;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Minnie
 */
public class PhieuNhapDialogDelete_Controller implements ActionListener{
        private PhieuNhapDialogDelete PNDD;
        private PhieuNhapBUS pnBUS;
        public PhieuNhapDialogDelete_Controller(PhieuNhapDialogDelete PNDD){
            this.PNDD=PNDD;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
           String sukien=e.getActionCommand();
           if (sukien.equals("Đóng")){
               PNDD.dispose();
           }
           if(sukien.equals("Xóa tất")){
               pnBUS=new PhieuNhapBUS();
               PhieuNhapDTO pnDTO=pnBUS.getPNById(PNDD.getMaPhieu());
               ArrayList<ChiTietPhieuNhapDTO> list_ctpn=pnBUS.getListCTPNById(PNDD.getMaPhieu());
               pnBUS.delete(pnDTO, list_ctpn);
               JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
               PNDD.dispose();
           }
        }
}
