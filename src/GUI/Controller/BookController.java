/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;


import javax.swing.JFrame;
import GUI.View.BookPanel;
import GUI.WorkFrame;
import  GUI.Dialog.BookDiaLog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author DELL
 */
public class BookController implements ActionListener{
    private BookPanel bf;
    private WorkFrame wk;
    public BookController(BookPanel bf,WorkFrame wk){
        this.bf=bf;
        this.wk=wk;
    }
    public void actionPerformed(ActionEvent e){
        String sukien=e.getActionCommand();
        if(sukien.equals("Thêm")){
            new BookDiaLog(wk);
        }
    }
}
