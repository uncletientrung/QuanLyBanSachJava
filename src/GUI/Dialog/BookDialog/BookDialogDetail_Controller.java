/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.BookDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class BookDialogDetail_Controller implements  ActionListener{
    private BookDialogDetail BDD;
    public  BookDialogDetail_Controller(BookDialogDetail BDD){
        this.BDD=BDD;
    }
    public void actionPerformed(ActionEvent e){
        String sukien=e.getActionCommand();
        if (sukien.equals("Đóng")){
            BDD.dispose();
        }
    }
}
