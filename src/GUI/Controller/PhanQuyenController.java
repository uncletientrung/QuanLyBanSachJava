/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;

import BUS.PhanQuyenBUS;
import GUI.Dialog.PhanQuyenDialog.PhanQuyenDialogAdd;
import GUI.Dialog.PhanQuyenDialog.PhanQuyenDialogAdd_Controller;
import GUI.View.PhanQuyenPanel;
import GUI.WorkFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Hi
 */
public class PhanQuyenController implements ActionListener,ListSelectionListener,DocumentListener{
    
    private PhanQuyenPanel pqp;
    private WorkFrame wk;
    private PhanQuyenBUS phanQuyenBus;
    
    
    public PhanQuyenController(PhanQuyenPanel pqp,WorkFrame wk){
        this.pqp=pqp;
        this.wk=wk;
        this.phanQuyenBus = new PhanQuyenBUS();

        
    }

@Override
public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand(); 

    if ("Thêm".equals(action)) {  
        PhanQuyenDialogAdd dialog = new PhanQuyenDialogAdd(wk, pqp); // Truyền panel vào
        PhanQuyenDialogAdd_Controller controller = new PhanQuyenDialogAdd_Controller(dialog, pqp);
        dialog.setController(controller);
        dialog.setVisible(true);
    }
}


    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
