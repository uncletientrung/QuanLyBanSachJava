/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhieuXuatDialog;

import BUS.PhieuXuatBUS;
import BUS.SachBUS;
import DTO.ChiTietPhieuXuatDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class PhieuXuatDialogDetail_Controller implements  ActionListener{
    private PhieuXuatBUS pxBUS;
    private ArrayList<ChiTietPhieuXuatDTO> list_ctpx;
    private SachBUS sBUS;
    private PhieuXuatDialogDetail PXDD;
    public PhieuXuatDialogDetail_Controller(PhieuXuatDialogDetail PXDD){
        this.PXDD=PXDD;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sukien=e.getActionCommand();
        if(sukien.equals("Đóng")){
            PXDD.dispose();
        }
    }
    
    
}
