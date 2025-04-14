package GUI.Dialog.BookDialog;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BUS.SachBUS;
import javax.swing.JOptionPane;
/**
 *
 * @author DELL
 */
public class BookDialogDelete_Controller implements ActionListener{
    private BookDialogDelete BDD;
    private SachBUS sachBUS;
    
    public BookDialogDelete_Controller(BookDialogDelete BDD){
        this.BDD=BDD;
    }
    public void actionPerformed(ActionEvent e){
        String sukien=e.getActionCommand();
        if(sukien.equals("Xóa")){
            Boolean result=new SachBUS().deleteById(BDD.getsDTO().getMasach());
            if(result){
                JOptionPane.showMessageDialog(null, "Xóa sách thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                BDD.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Không tìm thấy sách để xóa", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(sukien.equals("Đóng")){
            BDD.dispose();
        }
    }
}
