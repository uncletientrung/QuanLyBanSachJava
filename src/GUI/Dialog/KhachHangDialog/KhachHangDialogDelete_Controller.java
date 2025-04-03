package GUI.Dialog.KhachHangDialog;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BUS.KhachHangBUS;
import javax.swing.JOptionPane;
/**
 *
 * @author DELL
 */
public class KhachHangDialogDelete_Controller implements ActionListener{
    private KhachHangDialogDelete BDD;
    private KhachHangBUS khBUS;
    
    public KhachHangDialogDelete_Controller(KhachHangDialogDelete BDD){
        this.BDD=BDD;
    }
    public void actionPerformed(ActionEvent e){
        String sukien=e.getActionCommand();
        if(sukien.equals("Xóa")){
            Boolean result=new KhachHangBUS().deleteById(Integer.parseInt(BDD.getMa()));
            if(result){
                JOptionPane.showMessageDialog(null, "Xóa khach hang thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                BDD.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Không tìm thấy khach hang để xóa", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(sukien.equals("Đóng")){
            BDD.dispose();
        }
    }
}
