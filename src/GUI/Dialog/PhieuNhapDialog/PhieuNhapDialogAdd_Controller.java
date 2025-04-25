/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhieuNhapDialog;

import BUS.PhieuNhapBUS;
import DAO.PhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.Timestamp;
import java.time.Instant;

/**
 *
 * @author Minnie
 */
public class PhieuNhapDialogAdd_Controller implements ActionListener{
    private final AddPanel PNDA;
    private PhieuNhapBUS pnBUS = new PhieuNhapBUS();

    public PhieuNhapDialogAdd_Controller(AddPanel PNDA){
        this.PNDA = PNDA;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        String evt = e.getActionCommand();
//        if(evt.equals("Thêm dữ liệu")){
//            if (PNDA.getTxfMaPhieu().getText().isEmpty() ||
//                PNDA.getTxfMaNv().getText().isEmpty() ||
//                PNDA.getTxfMaNcc().getText().isEmpty() ||
//                PNDA.getTxfTongTien().getText().isEmpty() ||
//                PNDA.getTxfTrangthai().getText().isEmpty()) {
//                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin", "Error", JOptionPane.ERROR_MESSAGE);
//            } else {
//                int ma = Integer.parseInt(PNDA.getTxfMaPhieu().getText());
//                int manv = Integer.parseInt(PNDA.getTxfMaNv().getText());
//                int mancc = Integer.parseInt(PNDA.getTxfMaNcc().getText());
//                Timestamp thoigiantao = Timestamp.from(Instant.now());
//                long tongtien = Long.parseLong(PNDA.getTxfTongTien().getText());
//                int trangthai = Integer.parseInt(PNDA.getTxfTrangthai().getText());
//                int maAuto = PhieuNhapDAO.getInstance().getAutoIncrement();
//                
//                PhieuNhapDTO pnnew = new PhieuNhapDTO(maAuto, manv, mancc, thoigiantao, tongtien, trangthai);
//                boolean result = pnBUS.addPN(pnnew);
//                if (result) {
//                   JOptionPane.showMessageDialog(null, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                   PNDA.dispose();
//                   } else {
//                    JOptionPane.showMessageDialog(null, "Thêm thất bại", "Error", JOptionPane.ERROR_MESSAGE);
//                   }
//            }
//        }
//        if(evt.equals("Xóa")){
//            PNDA.ClearTextField();
//        }
    }
}
