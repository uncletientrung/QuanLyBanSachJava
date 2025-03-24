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
    private final WorkFrame wk;
    public WorkFrameController(WorkFrame wk){
        this.wk=wk;
    }
    @Override
   public void actionPerformed(ActionEvent e) {
    String sukien = e.getActionCommand();
    if (sukien.equals("Sách")) {
        wk.cardLayout.show(wk.PanelCard, "Sách");
    }  if (sukien.equals("Trang chủ")) {
        wk.cardLayout.show(wk.PanelCard, "Trang chủ");
    }  if (sukien.equals("Nhân viên")) {
        wk.cardLayout.show(wk.PanelCard, "Nhân viên");
    }  if (sukien.equals("Đóng")){
        System.exit(0);
    }  if (sukien.equals("Phân quyền")) {
        wk.cardLayout.show(wk.PanelCard, "Phân quyền");
    }
    }
}
