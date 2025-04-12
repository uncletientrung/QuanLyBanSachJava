/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhieuNhapDialog;
import java.awt.event.ActionListener;
/**
 *
 * @author Minnie
 */
public class PhieuNhapDialogDetail_Controller implements ActionListener{
    private PhieuNhapDialogDetail pnDialogDetail;
    public PhieuNhapDialogDetail_Controller(PhieuNhapDialogDetail pnDialogDetail) {
        this.pnDialogDetail = pnDialogDetail;
    }
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        // Xử lý sự kiện tại đây
    }
    public void showDialog() {
        pnDialogDetail.setVisible(true);
    }

    public void hideDialog() {
        pnDialogDetail.dispose();
    }
}
