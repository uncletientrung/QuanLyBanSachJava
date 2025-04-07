/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.PhieuXuatBUS;
import DTO.PhieuXuatDTO;
import java.awt.event.ActionListener;
import GUI.WorkFrame;
import  GUI.View.PhieuXuatPanel;
import java.awt.event.ActionEvent;
import GUI.Dialog.PhieuXuatDialog.PhieuXuatDialogAdd;
import GUI.Dialog.PhieuXuatDialog.PhieuXuatDialogDetail;
import GUI.Dialog.PhieuXuatDialog.PhieuXuatDialogDelete;
import javax.swing.JOptionPane;
import javax.swing.JTable;
/**
 *
 * @author DELL
 */
public class PhieuXuatController implements  ActionListener{
    private PhieuXuatPanel PxP;
    private WorkFrame WF;
    private PhieuXuatBUS pxBUS;
    private PhieuXuatDialogDelete PXDD;
    public PhieuXuatController(PhieuXuatPanel PxP, WorkFrame Wf ){
        this.PxP=PxP;
        this.WF=WF;
    }
    public void actionPerformed(ActionEvent e){
        String sukien=e.getActionCommand();
        if(sukien.equals("Thêm")){
            new PhieuXuatDialogAdd(WF, true);
            PxP.refreshTablePx();
        }
        if(sukien.equals("Chi Tiết")){
            new PhieuXuatDialogDetail(WF);
                    
        }
        if(sukien.equals("Hủy bỏ")){
            JTable tablePX=PxP.getTablePhieuXuat();
            if(tablePX.getSelectedRow() >=0){
                int selectRow=tablePX.getSelectedRow();
                pxBUS=new PhieuXuatBUS();
                PhieuXuatDTO phieu=pxBUS.getPXById(Integer.parseInt(tablePX.getValueAt(selectRow, 0).toString()));
                PXDD=new PhieuXuatDialogDelete(WF, phieu);
                PxP.refreshTablePx();                
            }else{
                JOptionPane.showMessageDialog(null, "Vui Lòng chọn Hóa đơn  muốn xóa", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
}
