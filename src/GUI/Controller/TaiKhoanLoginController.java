package GUI.Controller;

import GUI.WorkFrame;
import GUI.View.Taikhoan;
import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class TaiKhoanLoginController implements ActionListener {
    private Taikhoan tkview;
    private ArrayList<TaiKhoanDTO> listTaiKhoan;
    private TaiKhoanBUS tkBus = new TaiKhoanBUS();

    public TaiKhoanLoginController(Taikhoan tkview) {
        this.tkview = tkview;
        // Gắn keyListener cho username và password
        tkview.usernameField.addKeyListener(keyListener);
        tkview.passwordField.addKeyListener(keyListener);
    }

    public void actionPerformed(ActionEvent e) {
        listTaiKhoan = tkBus.getTaiKhoanAll();
        String sukien = e.getActionCommand();
        String username = tkview.usernameField.getText();
        String password = new String(tkview.passwordField.getPassword());
        boolean login = false;

        if (sukien.equals("Submit")) {
            // Kiểm tra tài khoản và mật khẩu
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(tkview, "Vui lòng nhập tên tài khoản!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(tkview, "Vui lòng nhập mật khẩu!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra tài khoản trong danh sách
            for (TaiKhoanDTO tk : listTaiKhoan) {
                if (username.equals(tk.getUsername()) && password.equals(tk.getMatkhau()) && tk.getTrangthai() == 1) {
                    login = true;
                    tkview.dispose(); // Đóng cửa sổ đăng nhập
                    new WorkFrame(tk); // Mở WorkFrame với thông tin tài khoản đã đăng nhập
                    break;
                }
            }

            // Nếu đăng nhập thất bại
            if (!login) {
                JOptionPane.showMessageDialog(tkview, "Tài khoản và mật khẩu không đúng hoặc tài khoản không hoạt động!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Xử lý reset
        if (sukien.equals("Reset")) {
            tkview.usernameField.setText("");
            tkview.passwordField.setText("");
        }
    }

    // Thêm hàm submit để làm KeyListener
    public void submit() {
        listTaiKhoan = tkBus.getTaiKhoanAll();
        String username = tkview.usernameField.getText();
        String password = new String(tkview.passwordField.getPassword());
        boolean login = false;

        // Kiểm tra tài khoản và mật khẩu
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(tkview, "Vui lòng nhập tên tài khoản!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(tkview, "Vui lòng nhập mật khẩu!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Kiểm tra tài khoản trong danh sách
        for (TaiKhoanDTO tk : listTaiKhoan) {
            if (username.equals(tk.getUsername()) && password.equals(tk.getMatkhau()) && tk.getTrangthai() == 1) {
                login = true;
                tkview.dispose(); // Đóng cửa sổ đăng nhập
                new WorkFrame(tk); // Mở WorkFrame với thông tin tài khoản đã đăng nhập
                break;
            }
        }

        // Nếu đăng nhập thất bại
        if (!login) {
            JOptionPane.showMessageDialog(tkview, "Tài khoản và mật khẩu không đúng hoặc tài khoản không hoạt động!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Thêm KeyListener
    KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                submit(); // Gọi hàm submit khi nhấn ENTER
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };
}
