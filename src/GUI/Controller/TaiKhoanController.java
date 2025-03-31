/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Controller;
import GUI.WorkFrame;
import GUI.View.Taikhoan;
import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author DELL
 */
public class TaiKhoanController implements  ActionListener{
    private Taikhoan tkview;
    private ArrayList<TaiKhoanDTO> listTaiKhoan;
    private TaiKhoanBUS tkBus=new TaiKhoanBUS();
    public TaiKhoanController(Taikhoan tkview){
        this.tkview=tkview;
        //Gắn keyListener cho username và password
        tkview.usernameField.addKeyListener(keyListener);
        tkview.passwordField.addKeyListener(keyListener);
    }
    
    public void actionPerformed(ActionEvent e){
        listTaiKhoan= tkBus.getTaiKhoanAll();
        String sukien=e.getActionCommand();
        String username= tkview.usernameField.getText();
        String password=new String(tkview.passwordField.getPassword());
        boolean  login=false;

        if(sukien.equals("Submit")){
            if(tkview.usernameField.getText().isEmpty()){
                JOptionPane.showMessageDialog(tkview, "Vui lòng nhập tên tài khoản!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else if (new String(tkview.passwordField.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(tkview, "Vui lòng nhập mật khẩu!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else{
                for(TaiKhoanDTO tk: listTaiKhoan){
                    if (username.equals(tk.getUsername()) && password.equals(tk.getMatkhau()) && tk.getTrangthai()==1 ){
                        login=true;
                        tkview.dispose();
                        new WorkFrame();
                    }
                }
            }
            if(!login){
                JOptionPane.showMessageDialog(tkview, "Tài khoản và mật khẩu không tồn tài!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }if(sukien.equals("Reset")){
            tkview.usernameField.setText("");
            tkview.passwordField.setText("");
        }
    }
    
        //Thêm hàm submit để làm KeyListener
    public void submit(){
        listTaiKhoan= tkBus.getTaiKhoanAll();
        String username= tkview.usernameField.getText();
        String password=new String(tkview.passwordField.getPassword());
        boolean login = false;
        if(tkview.usernameField.getText().isEmpty()){
                JOptionPane.showMessageDialog(tkview, "Vui lòng nhập tên tài khoản!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else if (new String(tkview.passwordField.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(tkview, "Vui lòng nhập mật khẩu!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else{
                for(TaiKhoanDTO tk: listTaiKhoan){
                    if (username.equals(tk.getUsername()) && password.equals(tk.getMatkhau()) && tk.getTrangthai()==1 ){
                        login=true;
                        tkview.dispose();
                        new WorkFrame();
                    }
                }
            }
            if(!login){
                JOptionPane.showMessageDialog(tkview, "Tài khoản và mật khẩu không tồn tài!", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }
    
    //Thêm Keylistener
    KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                submit();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };
    
}
///*
// * Click nbfs://SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package GUI.Controller;
//
//import GUI.WorkFrame;
//import GUI.View.Taikhoan;
//import BUS.TaiKhoanBUS;
//import DTO.TaiKhoanDTO;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JOptionPane;
//
///**
// *
// * @author DELL
// */
//public class TaiKhoanController implements ActionListener {
//    private Taikhoan tkview;
//    private TaiKhoanBUS tkBus;
//
//    public TaiKhoanController(Taikhoan tkview) {
//        this.tkview = tkview;
//        this.tkBus = new TaiKhoanBUS();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String sukien = e.getActionCommand();
//        String username = tkview.usernameField.getText();
//        String password = new String(tkview.passwordField.getPassword());
//
//        if (sukien.equals("Submit")) {
//            if (username.isEmpty()) {
//                JOptionPane.showMessageDialog(tkview, "Vui lòng nhập tên tài khoản!", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            } else if (password.isEmpty()) {
//                JOptionPane.showMessageDialog(tkview, "Vui lòng nhập mật khẩu!", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            } else {
//                TaiKhoanDTO taiKhoan = tkBus.dangNhap(username, password);
//                if (taiKhoan != null) { // Đăng nhập thành công
//                    JOptionPane.showMessageDialog(tkview, "Đăng nhập thành công!");
//                    tkview.dispose();
//                    new WorkFrame(taiKhoan); // Truyền TaiKhoanDTO sang WorkFrame
//                } else {
//                    JOptionPane.showMessageDialog(tkview, "Tài khoản hoặc mật khẩu sai, hoặc tài khoản bị khóa!", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        } else if (sukien.equals("Reset")) {
//            tkview.usernameField.setText("");
//            tkview.passwordField.setText("");
//        }
//    }
//}