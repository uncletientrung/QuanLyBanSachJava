/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  GUI.WorkFrame;

/**
 *
 * @author DELL
 */
public class WorkFrameController implements  ActionListener{
    private WorkFrame wk;
    public WorkFrameController(WorkFrame wk){
        this.wk=wk;
    }
   public void actionPerformed(ActionEvent e) {
    String sukien = e.getActionCommand();
    if (sukien.equals("Sách")) {
        wk.cardLayout.show(wk.PanelCard, "Sách");
    }  if (sukien.equals("Trang chủ")) {
        wk.cardLayout.show(wk.PanelCard, "Trang chủ");
    }
}
}
