/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.View;

import GUI.Dialog.ThongTinChungDialog.NhaCungCapDialog;
import GUI.Dialog.ThongTinChungDialog.NhaXuatBanDialog;
import GUI.Dialog.ThongTinChungDialog.TacGiaDialog;
import GUI.Dialog.ThongTinChungDialog.TheLoaiDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;



/**
 *
 * @author Hi
 */
public class ThongTinChungPanel extends JPanel{
    private JFrame parent;
    public ThongTinChungPanel(JFrame parent) {
//        this.parent=parent;
        // Tiêu đề
        JPanel pn_tieuDe = new JPanel(new BorderLayout());
        pn_tieuDe.setBackground(Color.decode("#B4CDCD"));
        pn_tieuDe.setPreferredSize(new Dimension(100, 137));

        JLabel lb_tieude = new JLabel("THÔNG TIN CHUNG", JLabel.CENTER);
        lb_tieude.setFont(new Font("Arial", Font.BOLD, 40));
        lb_tieude.setForeground(new Color(0x333333));
        pn_tieuDe.add(lb_tieude, BorderLayout.CENTER);

        //  Panel chứa nút
        JPanel menu = new JPanel(new GridLayout(2, 2, 20, 20)); // Khoảng cách giữa các button
        menu.setBorder(BorderFactory.createEmptyBorder(40, 50, 50, 50)); // Thêm padding, tăng khoảng cách với tiêu đề

        CustomButton btn_nhaCungCap = new CustomButton("Nhà cung cấp", "nhacungcap.png", new Color(173, 216, 230));
        CustomButton btn_nhaXuatBan = new CustomButton("Nhà xuất bản", "nxb.png", new Color(255, 228, 181));
        CustomButton btn_tacGia = new CustomButton("Tác giả", "tacgia.png", new Color(152, 251, 152));
        CustomButton btn_theLoai = new CustomButton("Thể loại", "theloai.png", new Color(255, 182, 193));

        menu.add(btn_nhaCungCap);
        menu.add(btn_nhaXuatBan);
        menu.add(btn_tacGia);
        menu.add(btn_theLoai);
        btn_nhaCungCap.addActionListener(e -> {
            NhaCungCapDialog dialog = new NhaCungCapDialog(parent);
            dialog.setVisible(true);
        });
        
        btn_nhaXuatBan.addActionListener(e -> {
            NhaXuatBanDialog dialog = new NhaXuatBanDialog(parent);
            dialog.setVisible(true);
        });
        
        btn_tacGia.addActionListener(e -> {
            TacGiaDialog dialog = new TacGiaDialog(parent);
            dialog.setVisible(true);
        });
        
        btn_theLoai.addActionListener(e ->{
            TheLoaiDialog dialog= new TheLoaiDialog(parent);
            dialog.setVisible(true);
        });

        //  Layout chính
        setLayout(new BorderLayout());
        add(pn_tieuDe, BorderLayout.NORTH);
        add(menu, BorderLayout.CENTER);
    }

    
}
