/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.PhieuNhapBUS;
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
        String evt = e.getActionCommand();
        switch (evt) {
            case "Trang chủ":
                pnp.getPanelCenter().removeAll();
                pnp.getScrollPanePhieuNhap().setViewportView(pnp.getTablePhieuNhap());
                pnp.getPanelCenter().setLayout(new GridLayout(1, 1));
                pnp.getPanelCenter().add(pnp.getScrollPanePhieuNhap());
                pnp.refreshTablePn();
                break;
            case "Thêm":
                System.out.println("GUI.Controller.PhieuNhapController.actionPerformed()");
                break;
            
            default:
                break;
        }
    }
    
    


    @Override
    public void stateChanged(ChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
