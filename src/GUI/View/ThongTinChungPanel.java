
package GUI.View;
import GUI.Controller.ChucNangConst;
import GUI.Dialog.ThongTinChungDialog.NhaCungCapDialog;
import GUI.Dialog.ThongTinChungDialog.NhaXuatBanDialog;
import GUI.Dialog.ThongTinChungDialog.TacGiaDialog;
import GUI.Dialog.ThongTinChungDialog.TheLoaiDialog;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class ThongTinChungPanel extends JPanel {
    private JFrame parent;
    private List<Integer> quyenDuocCap;

    public ThongTinChungPanel(JFrame parent, List<Integer> quyenDuocCap) {
        this.parent = parent;
        this.quyenDuocCap = quyenDuocCap;

        // Tiêu đề
        JPanel pn_tieuDe = new JPanel(new BorderLayout());
        pn_tieuDe.setBackground(Color.decode("#B4CDCD"));
        pn_tieuDe.setPreferredSize(new Dimension(100, 135));

        JLabel lb_tieude = new JLabel("THÔNG TIN CHUNG", JLabel.CENTER);
        lb_tieude.setFont(new Font("Arial", Font.BOLD, 40));
        lb_tieude.setForeground(new Color(0x333333));
        pn_tieuDe.add(lb_tieude, BorderLayout.CENTER);

        // Panel chứa nút
        JPanel menu = new JPanel(new GridLayout(2, 2, 20, 20));
        menu.setBorder(BorderFactory.createEmptyBorder(40, 50, 50, 50));

        CustomButton btn_nhaCungCap = new CustomButton("Nhà cung cấp", "nhacungcap.png", new Color(173, 216, 230));
        CustomButton btn_nhaXuatBan = new CustomButton("Nhà xuất bản", "nxb.png", new Color(255, 228, 181));
        CustomButton btn_tacGia = new CustomButton("Tác giả", "tacgia.png", new Color(152, 251, 152));
        CustomButton btn_theLoai = new CustomButton("Thể loại", "theloai.png", new Color(255, 182, 193));

        menu.add(btn_nhaCungCap);
        menu.add(btn_nhaXuatBan);
        menu.add(btn_tacGia);
        menu.add(btn_theLoai);

        // Gán quyền và hành động
        ganQuyenChoNut(btn_nhaCungCap, ChucNangConst.NHA_CUNG_CAP, () -> {
            new NhaCungCapDialog(parent).setVisible(true);
        });

        ganQuyenChoNut(btn_nhaXuatBan, ChucNangConst.NHA_XUAT_BAN, () -> {
            new NhaXuatBanDialog(parent).setVisible(true);
        });

        ganQuyenChoNut(btn_tacGia, ChucNangConst.TAC_GIA, () -> {
            new TacGiaDialog(parent).setVisible(true);
        });

        ganQuyenChoNut(btn_theLoai, ChucNangConst.THE_LOAI, () -> {
            new TheLoaiDialog(parent).setVisible(true);
        });

        // Layout chính
        setLayout(new BorderLayout());
        add(pn_tieuDe, BorderLayout.NORTH);
        add(menu, BorderLayout.CENTER);
    }

    private boolean coQuyen(int machucnang) {
        return quyenDuocCap.contains(machucnang);
    }

    private void ganQuyenChoNut(CustomButton button, int chucNang, Runnable action) {
        button.addActionListener(e -> {
            if (coQuyen(chucNang)) {
                action.run();
            } else {
                JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập chức năng này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}
