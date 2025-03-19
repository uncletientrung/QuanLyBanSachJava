/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Model;

import javax.swing.*;

/**
 *
 * @author DELL
 */
public class WorkFrame extends JFrame{
    public WorkFrame(){
        this.init();
        this.setVisible(true);
    }
    public void init(){
        setTitle("Quản lý bán sách");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JLabel label = new JLabel("Giao diện chính sẽ là ở đây", JLabel.CENTER);
        add(label);
        
    }
    
    public static void main(String[] args) {
        WorkFrame wk = new WorkFrame();
    }
}
