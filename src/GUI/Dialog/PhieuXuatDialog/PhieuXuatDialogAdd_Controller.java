/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.PhieuXuatDialog;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author DELL
 */
public class PhieuXuatDialogAdd_Controller implements DocumentListener{
    public PhieuXuatDialogAdd PXDA;
    public PhieuXuatDialogAdd_Controller(PhieuXuatDialogAdd PXDA){
        this.PXDA=PXDA;
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        if (e.getDocument() == PXDA.getTxfTimKhach().getDocument()) { // Nếu nhập vào ô tìm khách
            PXDA.ShowSDT();
            PXDA.showNameKhandSDT();
    } else if (e.getDocument() == PXDA.getTxfTimSach().getDocument()) { // Nếu nhập vào ô tìm sách
            PXDA.FindBook(PXDA.getTxfTimSachText());
    }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if (e.getDocument() == PXDA.getTxfTimKhach().getDocument()) { // Nếu nhập vào ô tìm khách
            PXDA.ShowSDT();
            PXDA.showNameKhandSDT();
    } else if (e.getDocument() == PXDA.getTxfTimSach().getDocument()) { // Nếu nhập vào ô tìm sách
            PXDA.FindBook(PXDA.getTxfTimSachText());
    }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (e.getDocument() == PXDA.getTxfTimKhach().getDocument()) { // Nếu nhập vào ô tìm khách
            PXDA.ShowSDT();
            PXDA.showNameKhandSDT();
    } else if (e.getDocument() == PXDA.getTxfTimSach().getDocument()) { // Nếu nhập vào ô tìm sách
            PXDA.FindBook(PXDA.getTxfTimSachText());
    }
    }
    
}
