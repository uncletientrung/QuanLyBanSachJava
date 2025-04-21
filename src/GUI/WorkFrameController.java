package GUI;

import GUI.View.BookPanel;
import GUI.View.PhieuXuatPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author DELL
 */
public class WorkFrameController implements ActionListener {
    private final WorkFrame wk;
    private DangNhapFrame DNF;

    public WorkFrameController(WorkFrame wk) {
        this.wk = wk;
        this.DNF = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String suKien = e.getActionCommand();
        JButton nutVuaNhan = (JButton) e.getSource();
        
        if (wk.getNutDangHoatDong() != null && wk.getNutDangHoatDong() != nutVuaNhan) {
            wk.getNutDangHoatDong().setBackground(new Color(240, 240, 240)); // Màu default
        }
        nutVuaNhan.setBackground(new Color(135,206,250)); // Màu xanh
        wk.setNutDangHoatDong(nutVuaNhan); // Cập nhập hoạt động
        if (suKien.equals("Sách")) {
            wk.cardLayout.show(wk.PanelCard, "Sách");
            ((BookPanel) wk.PanelCard.getComponent(1)).refreshTableData();
        } else if (suKien.equals("Trang chủ")) {
            wk.cardLayout.show(wk.PanelCard, "Trang chủ");
        } else if (suKien.equals("Nhân viên")) {
            wk.cardLayout.show(wk.PanelCard, "Nhân viên");
        } else if (suKien.equals("Phân quyền")) {
            wk.cardLayout.show(wk.PanelCard, "Phân quyền");
        } else if (suKien.equals("Thông tin chung")) {
            wk.cardLayout.show(wk.PanelCard, "Thông tin chung");
        } else if (suKien.equals("Phiếu xuất")) {
            wk.cardLayout.show(wk.PanelCard, "Phiếu xuất");
            ((PhieuXuatPanel) wk.PanelCard.getComponent(5)).refreshTablePx();
        } else if (suKien.equals("Tài khoản")) {
            wk.cardLayout.show(wk.PanelCard, "Tài khoản");
        } else if (suKien.equals("Khách hàng")) {
            wk.cardLayout.show(wk.PanelCard, "Khách hàng");
        } else if (suKien.equals("Phiếu nhập")) {
            wk.cardLayout.show(wk.PanelCard, "Phiếu nhập");
        } else if (suKien.equals("Khuyến mãi")) {
            wk.cardLayout.show(wk.PanelCard, "Khuyến mãi");
        } else if (suKien.equals("Đăng xuất")) {
            DangNhapFrame khungDangNhap = new DangNhapFrame();
            khungDangNhap.setVisible(true);
            wk.dispose();
            wk.setNutDangHoatDong(null);
        }else if (suKien.equals("Thống kê")) {
            wk.cardLayout.show(wk.PanelCard, "Thống kê");
        }

    }
}