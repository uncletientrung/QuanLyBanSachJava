/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.NhanVienDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Minnie
 */
public class NhanVienDialogDetail_Controller implements ActionListener{
    private final NhanVienDialogDetail NVDD;
    public NhanVienDialogDetail_Controller(NhanVienDialogDetail NVDD){
        this.NVDD = NVDD;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String evt = e.getActionCommand();
        if(evt.equals("Đóng")){
            NVDD.dispose();
        }
    }
    
}
