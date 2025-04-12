/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.PhieuNhapBUS;
import DTO.PhieuNhapDTO;
import GUI.Dialog.PhieuNhapDialog.AddPanel;
import GUI.Dialog.PhieuNhapDialog.PhieuNhapDialogAdd;
import GUI.Dialog.PhieuNhapDialog.PhieuNhapDialogDelete;
import GUI.Dialog.PhieuNhapDialog.PhieuNhapDialogDetail;
import GUI.View.PhieuNhapPanel;
import GUI.WorkFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Minnie
 */
public class PhieuNhapController implements ActionListener, ChangeListener{
    
    private PhieuNhapPanel pnp;
    private WorkFrame wf;
    private PhieuNhapBUS pnBUS;
    private PhieuNhapDialogAdd PNDA;
    private PhieuNhapDialogDelete PNDD;
    private PhieuNhapDialogDetail PNDX;
    private int tabCount = 1;
    private boolean isRemoving = false;
    
    public PhieuNhapController(){}
    
    public PhieuNhapController(PhieuNhapPanel pnp, WorkFrame wf){
        this.pnp = pnp;
        this.wf = wf;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        pnp.refreshTablePn();
        String evt = e.getActionCommand();
        switch (evt) {
            case "Trang chủ":       
                pnp.refreshTablePn();
                break;
            case "Thêm":
                AddPanel test = new AddPanel();
                test.setVisible(true);
                System.err.println("DANG NHAN THEM");
                break;
            case "Chi tiết":
                JTable tablePN = pnp.getTablePhieuNhap();
                if (tablePN.getSelectedRow() >= 0) {
                    int selectRow = tablePN.getSelectedRow();
                    pnBUS = new PhieuNhapBUS();
                    PhieuNhapDTO phieu = pnBUS.getPNById(Integer.parseInt(tablePN.getValueAt(selectRow, 0).toString()));
                    PNDX = new PhieuNhapDialogDetail(wf, phieu);
                } else {
                JOptionPane.showMessageDialog(null, "Vui Lòng chọn phiếu nhập muốn xem", "Error", JOptionPane.ERROR_MESSAGE);
                }
            case "Hủy bỏ":
                JTable tablePNC = pnp.getTablePhieuNhap();
                if (tablePNC.getSelectedRow() >= 0) {
                    int selectRow = tablePNC.getSelectedRow();
                    pnBUS = new PhieuNhapBUS();
                    PhieuNhapDTO phieu = pnBUS.getPNById(Integer.parseInt(tablePNC.getValueAt(selectRow, 0).toString()));
                    PNDD = new PhieuNhapDialogDelete(wf, phieu);
                    pnp.refreshTablePn();
                } else {
                JOptionPane.showMessageDialog(null, "Vui Lòng chọn phiếu nhập muốn xóa", "Error", JOptionPane.ERROR_MESSAGE);
                }
            default:
                pnp.refreshTablePn();
                break;
        }
       
    }
    
    


    @Override
    public void stateChanged(ChangeEvent e) {
        
    }
    
    
}
