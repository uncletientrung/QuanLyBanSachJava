/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import java.awt.event.ActionListener;
import GUI.WorkFrame;
import  GUI.View.PhieuXuatPanel;
import java.awt.event.ActionEvent;
import GUI.Dialog.PhieuXuatDialog.PhieuXuatDialogAdd;
/**
 *
 * @author DELL
 */
public class PhieuXuatController implements  ActionListener{
    private PhieuXuatPanel PxP;
    private WorkFrame WF;
    public PhieuXuatController(PhieuXuatPanel PxP, WorkFrame Wf){
        this.PxP=PxP;
        this.WF=WF;
    }
    public void actionPerformed(ActionEvent e){
        String sukien=e.getActionCommand();
        if(sukien.equals("Thêm")){
            new PhieuXuatDialogAdd(WF, true);
            PxP.refreshTablePx();
        }
        
    }
}
