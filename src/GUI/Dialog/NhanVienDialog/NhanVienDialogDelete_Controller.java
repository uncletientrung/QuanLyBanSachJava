/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.NhanVienDialog;

import BUS.NhanVienBUS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Minnie
 */
public class NhanVienDialogDelete_Controller implements ActionListener {
    private NhanVienDialogDelete NVDD;
    private NhanVienBUS nvBUS;

    public NhanVienDialogDelete_Controller(NhanVienDialogDelete NVDD) {
        this.NVDD = NVDD;
    }
    
    public void actionPerformed(ActionEvent e){
        String evt = e.getActionCommand();
        if(evt.equals("Xóa")){
            boolean result = new NhanVienBUS().deleteById(Integer.parseInt(NVDD.getMaNV()));
            if(result){
                JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                NVDD.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên để xóa", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evt.equals("Đóng")) {
            NVDD.dispose();
        }
    }
}
